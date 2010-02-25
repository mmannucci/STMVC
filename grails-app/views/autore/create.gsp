
<%@ page import="it.prova.model.Autore" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'autore.label', default: 'Autore')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><st:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></st:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
          
            <g:stHasErrors bean="${autoreInstance}">
            <div class="errors">
                  <g:beanErrors bean="${autoreInstance}" />
            </div>
            </g:stHasErrors>
            <g:form controller="autore" action="save.dispatch" method="post" >
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
                <span class="button"><input type="submit" name="save" value="<g:message code='default.button.save.label' default='Save'/>" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
