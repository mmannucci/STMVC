
<%@ page import="it.prova.model.Editore" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'editore.label', default: 'Editore')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:stLink class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:stLink></span>
            <span class="menuButton"><g:stLink class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:stLink></span>
        </div>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
           
            <div class="dialog">
            <g:if test="${!editoreInstance}">
            	<g:message code="default.not.found.message" args="[entityName, ]"/>
        	</g:if>
        	<g:if test="${editoreInstance}">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="editore.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: editoreInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="editore.nome.label" default="Nome" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: editoreInstance, field: "nome")}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </g:if>
            </div>
            <g:if test="${editoreInstance}">
            	<div class="buttons">
					<span class="menuButton"><g:stLink class="edit" action="edit" id="${editoreInstance?.id}"><g:message code="default.edit.label" args="[entityName]"/></g:stLink></span>
					<span class="menuButton"><g:stLink class="delete" action="delete" id="${editoreInstance?.id}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"><g:message code="default.delete.label" args="[entityName]"/></g:stLink></span>
            	</div>
            </g:if>
        </div>
    </body>
</html>
