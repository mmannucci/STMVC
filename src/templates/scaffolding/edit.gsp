<% import grails.persistence.Event %>
<%=packageName%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="\${message(code: '${domainClass.propertyName}.label', default: '${className}')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="\${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:stLink class="create" controller="${domainClass.shortName.toLowerCase()}" action="list""><g:message code="default.list.label" args="[entityName]" /></g:stLink></span>
            <span class="menuButton"><g:stLink class="create" controller="${domainClass.shortName.toLowerCase()}" action="create""><g:message code="default.new.label" args="[entityName]" /></g:stLink></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
           <g:stHasErrors bean="\${${propertyName}}">
            <div class="errors">
                  <g:beanErrors bean="\${${propertyName}}" />
            </div>
            </g:stHasErrors>
            <g:form controller="${domainClass.shortName.toLowerCase()}" action="update.dispatch" method="post" <%= multiPart ? ' enctype="multipart/form-data"' : '' %>>
                <g:hiddenField name="id" value="\${${propertyName}?.id}" />
                <g:hiddenField name="version" value="\${${propertyName}?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        <%  excludedProps = Event.allEvents.toList() << 'version' << 'id'
                            props = domainClass.properties.findAll { !excludedProps.contains(it.name) }
                            Collections.sort(props, comparator.constructors[0].newInstance([domainClass] as Object[]))
                            props.each { p ->
                                cp = domainClass.constrainedProperties[p.name]
                                display = (cp ? cp.display : true)        
                                if (display) { %>
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="${p.name}"><g:message code="${domainClass.propertyName}.${p.name}.label" default="${p.naturalName}" /></label>
                                </td>
                                <td valign="top" class="value \${hasPropertyError(bean: ${propertyName}, beanProperty '${p.name}')}">
                                    ${renderEditor(p)}
                                </td>
                            </tr>
                        <%  }   } %>
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" controller="${domainClass.shortName.toLowerCase()}" action="update" value="\${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" controller="${domainClass.shortName.toLowerCase()}" action="delete" value="\${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('\${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
