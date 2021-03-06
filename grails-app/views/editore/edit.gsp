
<%@ page import="it.prova.model.Editore" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'editore.label', default: 'Editore')}" />
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
           <g:stHasErrors bean="${editoreInstance}">
            <div class="errors">
                  <g:beanErrors bean="${editoreInstance}" />
            </div>
            </g:stHasErrors>
            <g:form controller="editore" action="update.dispatch" method="post" >
                <g:hiddenField name="id" value="${editoreInstance?.id}" />
                <g:hiddenField name="version" value="${editoreInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="autori"><g:message code="editore.autori.label" default="Autori" /></label>
                                </td>
                                <td valign="top" class="value ${hasPropertyError(bean: editoreInstance, beanProperty:'autori')}">
                                    <g:select name="autori" from="${it.prova.model.Autore.list()}" multiple="yes" optionKey="id" size="5" value="${editoreInstance?.autori}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="nome"><g:message code="editore.nome.label" default="Nome" /></label>
                                </td>
                                <td valign="top" class="value ${hasPropertyError(bean: editoreInstance, beanProperty:'nome')}">
                                    <g:textField name="nome" value="${editoreInstance?.nome}" />
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
