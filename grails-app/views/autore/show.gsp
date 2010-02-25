
<%@ page import="it.prova.model.Autore" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'autore.label', default: 'Autore')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><st:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></st:link></span>
            <span class="menuButton"><st:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></st:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="autore.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: autoreInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="autore.cognome.label" default="Cognome" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: autoreInstance, field: "cognome")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="autore.date.label" default="Date" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${autoreInstance?.date}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="autore.editore.label" default="Editore" /></td>
                            
                            <td valign="top" class="value"><g:link controller="editore" action="show" id="${autoreInstance?.editore?.id}">${autoreInstance?.editore?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="autore.libros.label" default="Libros" /></td>
                            
                            <td valign="top" style="text-align: left;" class="value">
                                <ul>
                                <g:each in="${autoreInstance.libros}" var="l">
                                    <li><st:link controller="libro" action="show" id="${l.id}">${l?.encodeAsHTML()}</st:link></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="autore.nome.label" default="Nome" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: autoreInstance, field: "nome")}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form  controller="autore" method="POST" action="edit.dispatch">
                    <g:hiddenField name="id" value="${autoreInstance?.id}" />
                     <span class="button"><st:link action="edit" id="${autoreInstance?.id}">${message(code: 'default.button.edit.label', default: 'Edit')}</st:link></span>
                     <span class="button"><input type="submit" name="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}"/></span>
                   </g:form>
            </div>
        </div>
    </body>
</html>
