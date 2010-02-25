
<%@ page import="it.prova.model.Autore" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'autore.label', default: 'Autore')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><st:link class="list"  action="list"><g:message code="default.list.label" args="[entityName]" /></st:link></span>
            <span class="menuButton"><st:link class="create"  action="create"><g:message code="default.new.label" args="[entityName]" /></st:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
           <g:stHasErrors bean="${autoreInstance}">
            <div class="errors">
                  <g:beanErrors bean="${autoreInstance}" />
            </div>
            </g:stHasErrors>
            <g:form controller="autore" action="update.dispatch" method="post" >
                <g:hiddenField name="id" value="${autoreInstance?.id}" />
                <g:hiddenField name="version" value="${autoreInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="cognome"><g:message code="autore.cognome.label" default="Cognome" /></label>
                                </td>
                                <td valign="top" class="value ${hasPropertyError(bean: autoreInstance, beanProperty:'cognome')}">
                                    <g:textField name="cognome" value="${autoreInstance?.cognome}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="date"><g:message code="autore.date.label" default="Date" /></label>
                                </td>
                                <td valign="top" class="value ${hasPropertyError(bean: autoreInstance, beanProperty:'date')}">
                                    <g:datePicker name="date" precision="day" value="${autoreInstance?.date}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="editore"><g:message code="autore.editore.label" default="Editore" /></label>
                                </td>
                                <td valign="top" class="value ${hasPropertyError(bean: autoreInstance, beanProperty:'editore')}">
                                    <g:select name="editore.id" from="${it.prova.model.Editore.list()}" optionKey="id" value="${autoreInstance?.editore?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="libros"><g:message code="autore.libros.label" default="Libros" /></label>
                                </td>
                                <td valign="top" class="value ${hasPropertyError(bean: autoreInstance, beanProperty:'libros')}">
                                    <g:select name="libros" from="${it.prova.model.Libro.list()}" multiple="yes" optionKey="id" size="5" value="${autoreInstance?.libros}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="nome"><g:message code="autore.nome.label" default="Nome" /></label>
                                </td>
                                <td valign="top" class="value ${hasPropertyError(bean: autoreInstance, beanProperty:'nome')}">
                                    <g:textField name="nome" value="${autoreInstance?.nome}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                  	<span class="button"><input type="submit" name=update value="${message(code: 'default.button.update.label', default: 'Update')}"/></span>
                    <span class="button"><input type="submit" name="undo" value="${message(code: 'default.button.undo.label', default: 'Undo')}"/></span>
                  	<span class="button"><input type="submit" name="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}"/></span>
                 
                
                </div>
            </g:form>
        </div>
    </body>
</html>
