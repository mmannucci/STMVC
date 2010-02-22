package it.solvingteam.taglib

import org.codehaus.groovy.grails.commons.GrailsControllerClass;
import org.codehaus.groovy.grails.plugins.web.taglib.ApplicationTagLib;
import org.codehaus.groovy.grails.web.mapping.UrlCreator;
import org.springframework.context.ApplicationContext;


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
}
