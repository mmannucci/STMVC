<% import grails.persistence.Event %>
<%=packageName%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="\${message(code: '${domainClass.propertyName}.label', default: '${className}')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="\${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><st:link class="list" controller="${domainClass.shortName.toLowerCase()}" action="list"><g:message code="default.list.label" args="[entityName]" /></st:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
          
            <g:stHasErrors bean="\${${propertyName}}">
            <div class="errors">
                  <g:beanErrors bean="\${${propertyName}}" />
            </div>
            </g:stHasErrors>
            <g:form controller="${domainClass.shortName.toLowerCase()}" action="save.dispatch" method="post" <%= multiPart ? ' enctype="multipart/form-data"' : '' %>>
                <div class="dialog">
                    <table>
                        <tbody>
                        <%  excludedProps = Event.allEvents.toList() << 'version' << 'id'
                            props = domainClass.properties.findAll { !excludedProps.contains(it.name) }
                            Collections.sort(props, comparator.constructors[0].newInstance([domainClass] as Object[]))
                            props.each { p ->
                                if (!Collection.class.isAssignableFrom(p.type)) {
                                    cp = domainClass.constrainedProperties[p.name]
                                    display = (cp ? cp.display : true)        
                                    if (display) { %>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="${p.name}"><g:message code="${domainClass.propertyName}.${p.name}.label" default="${p.naturalName}" /></label>
                                </td>
                                 <td valign="top" class="value \${hasPropertyError(bean: ${propertyName}, beanProperty:'${p.name}')}">
                                    ${renderEditor(p)}
                                </td>
                            </tr>
                        <%  }   }   } %>
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                <span class="button"><input type="submit" name="save" value="<g:message code='default.button.save.label' default='Save'/>" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
