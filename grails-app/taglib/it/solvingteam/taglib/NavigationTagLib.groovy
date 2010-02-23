package it.solvingteam.taglib

import org.codehaus.groovy.grails.commons.GrailsControllerClass;
import org.codehaus.groovy.grails.plugins.web.taglib.ApplicationTagLib;
import org.codehaus.groovy.grails.web.mapping.UrlCreator;
import org.springframework.context.ApplicationContext;

import org.springframework.web.servlet.support.RequestContextUtils as RCU

import com.opensymphony.module.sitemesh.Factory
import com.opensymphony.module.sitemesh.RequestConstants
import grails.util.GrailsNameUtils
import groovy.text.Template
import java.util.concurrent.ConcurrentHashMap
import javax.servlet.ServletConfig
import org.codehaus.groovy.grails.plugins.GrailsPluginManager
import org.codehaus.groovy.grails.web.mapping.ForwardUrlMappingInfo
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.pages.GroovyPagesTemplateEngine
import org.codehaus.groovy.grails.web.sitemesh.FactoryHolder
import org.codehaus.groovy.grails.web.sitemesh.GSPSitemeshPage
import org.codehaus.groovy.grails.web.sitemesh.GrailsPageFilter
import org.codehaus.groovy.grails.web.util.StreamCharBuffer
import org.codehaus.groovy.grails.web.util.WebUtils


class NavigationTagLib extends ApplicationTagLib {
	static namespace = 'st'
	def link = { attrs, body ->
		def writer = getOut()
		def elementId = attrs.remove('elementId')
		writer <<  "<a href=\"${createLink(attrs).encodeAsHTML()}\""
		if(elementId) {
			writer << " id=\"${elementId}\""
		}
		
		writer << "${attrs.collect {k, v -> " $k=\"$v\"" }.join('')}>"
		writer << "${body()}</a>"
	}
	
	def createLink = { attrs ->
		def writer = getOut()
		// prefer URI attribute
		if(attrs['uri']) {
			writer << handleAbsolute(attrs)
			writer << attrs.uri.toString()
		}
		else {
			// prefer a URL attribute
			def urlAttrs = attrs
			if(attrs['url'] instanceof Map) {
				urlAttrs = attrs.remove('url').clone()
			}
			else if(attrs['url']) {
				urlAttrs = attrs.remove('url').toString()
			}
			
			if(urlAttrs instanceof String) {
				if(useJsessionId)
				writer << response.encodeURL(urlAttrs)
				else
				writer << urlAttrs
			}
			else {
				def controller = urlAttrs.containsKey("controller") ? urlAttrs.remove("controller")?.toString() : controllerName
				def action = urlAttrs.remove("action")?.toString()
				if(controller && !action) {
					GrailsControllerClass controllerClass = grailsApplication.getArtefactByLogicalPropertyName(ControllerArtefactHandler.TYPE, controller)
					String defaultAction = controllerClass?.getDefaultAction()
					if(controllerClass?.hasProperty(defaultAction))
					action = defaultAction
				}
				def id = urlAttrs.remove("id")
				def frag = urlAttrs.remove('fragment')?.toString()
				def params = urlAttrs.params && urlAttrs.params instanceof Map ? urlAttrs.remove('params') : [:]
				params.mappingName = urlAttrs.remove('mapping')
				if(request['flowExecutionKey']) {
					params."execution" = request['flowExecutionKey']
				}
				
				if(urlAttrs.event) {
					params."_eventId" = urlAttrs.remove('event')
				}
				def url
				if(id != null) {
					params.id = id + ".dispatch"
				} else {
					action = "${action}.dispatch"
				}
				if (controller.contains('.dispatch')) {
					def sb = new StringBuffer(controller)
					def deleteStart = sb.indexOf('.dispatch')
					sb.delete(deleteStart, controller.length())
					controller = sb.toString();
				}
				def urlMappings = applicationContext.getBean("grailsUrlMappingsHolder")
				UrlCreator mapping = urlMappings.getReverseMapping(controller,action,params)
				
				// cannot use jsessionid with absolute links
				if(useJsessionId && !attrs.absolute) {
					url = mapping.createURL(controller, action, params, request.characterEncoding, frag)
					def base = attrs.remove('base')
					if(base) writer << base
					writer << response.encodeURL(url)
				}
				else {
					url = mapping.createRelativeURL(controller, action, params, request.characterEncoding, frag)
					out << handleAbsolute(attrs)
					writer << url
				}
			}
		}
	}
	
	def sortableColumn = { attrs ->
		//System.out.println("..................................dentro stSortableColumn:" + attrs);
		def writer = out
		if(!attrs.property)
			throwTagError("Tag [sortableColumn] is missing required attribute [property]")
		
		if(!attrs.title && !attrs.titleKey)
			throwTagError("Tag [sortableColumn] is missing required attribute [title] or [titleKey]")
		
		def property = attrs.remove("property")
																					//added
		def action = attrs.action ? attrs.remove("action") : (actionName ?: "list.dispatch")
		
		def defaultOrder = attrs.remove("defaultOrder")
		if(defaultOrder != "desc") defaultOrder = "asc"
		
		// current sorting property and order
		def sort = params.sort
		def order = params.order
		
		// add sorting property and params to link params
		def linkParams = [:]
		if(params.id) linkParams.put("id",params.id)
		if(attrs.params) linkParams.putAll(attrs.remove("params"))
		linkParams.sort = property
		
		// determine and add sorting order for this column to link params
		attrs.class = (attrs.class ? "${attrs.class} sortable" : "sortable")
		if(property == sort) {
			attrs.class = attrs.class + " sorted " + order
			if(order == "asc") {
				linkParams.order = "desc"
			}
			else {
				linkParams.order = "asc"
			}
		}
		else {
			linkParams.order = defaultOrder
		}
		
		// determine column title
		def title = attrs.remove("title")
		def titleKey = attrs.remove("titleKey")
		if(titleKey) {
			if(!title) title = titleKey
			def messageSource = grailsAttributes.messageSource
			def locale = RCU.getLocale(request)
			title = messageSource.getMessage(titleKey, null, title, locale)
		}
		//added to sortableColumn base tag.
		def controller = attrs.containsKey("controller") ? attrs.remove("controller")?.toString() : controllerName
		
		
		writer << "<th "
		// process remaining attributes
		attrs.each { k, v -> 
			writer << "${k}=\"${v.encodeAsHTML()}\" "
		}
															//added
		writer << ">${link(action:action, params:linkParams, controller:controller) { title }}</th>"
	}
}
