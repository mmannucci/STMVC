
<%@ page import="it.prova.model.Autore" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />

<calendar:resources lang="en" theme="tiger"/>
        <g:set var="entityName" value="${message(code: 'autore.label', default: 'Autore')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
    
    	beanErrors:<g:beanErrors bean="${autoreInstance}" />
    	
    	beanPropertyError:<g:beanPropertyError bean="${autoreInstance}" property="nome" />
    	
    	<g:eachError><p>${it.defaultMessage}</p></g:eachError>
		
    
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${autoreInstance}">
            <div class="errors">
                <g:renderErrors bean="${autoreInstance}" as="list" />
            </div>
            </g:hasErrors>
            <form action="/ProvaSpringMVCGrails/updateUtente.dispatch" method="post" >
                <g:hiddenField name="id" value="${autoreInstance?.id}" />
                <g:hiddenField name="version" value="${autoreInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="nome"><g:message code="autore.nome.label" default="Nome" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: autoreInstance, field: 'nome', 'errors')}">
                                    <g:textField name="nome" value="${autoreInstance?.nome}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="cognome"><g:message code="autore.cognome.label" default="Cognome" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: autoreInstance, field: 'cognome', 'errors')}">
                                    <g:textField name="cognome" value="${autoreInstance?.cognome}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="libros"><g:message code="autore.libros.label" default="Libros" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: autoreInstance, field: 'libros', 'errors')}">
                                    <g:select name="libros" from="${it.prova.model.Libro.list()}" multiple="multiple" optionKey="id" size="5" value="${autoreInstance?.libros}" />
                                    
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>

<calendar:datePicker name="date" defaultValue="${new Date()}"/>
                <div class="buttons">
                    <span class="button"><input type="submit" value="Aggiorna"></input></span>
                    <g:link   controller ="autore" action="delete" id="${autoreInstance?.id}" params="[version:autoreInstance?.version]"  onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" >delete</g:link>
                </div>
            </form>
        </div>
    </body>
</html>
