
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
            <span class="menuButton"><st:link controller="editore" class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></st:link></span>
            <span class="menuButton"><st:link controller="editore" class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></st:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            
            <div class="dialog">
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
            </div>

            <div class="buttons">
                <g:form  controller="editore" action="edit.dispatch" method="POST">
                    <g:hiddenField name="id" value="${editoreInstance?.id}" />
                    <span class="button"><input type="submit" name="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}"/></span>
                    <span class="button"><input type="submit" name="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}"/></span>
                </g:form>
            </div>

        </div>
    </body>
</html>
