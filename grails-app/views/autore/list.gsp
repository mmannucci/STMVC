
<%@ page import="it.prova.model.Autore" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'autore.label', default: 'Autore')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'autore.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="nome" title="${message(code: 'autore.nome.label', default: 'Nome')}" />
                        
                            <g:sortableColumn property="cognome" title="${message(code: 'autore.cognome.label', default: 'Cognome')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${autoreInstanceList}" status="i" var="autoreInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${autoreInstance.id}">${fieldValue(bean: autoreInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: autoreInstance, field: "nome")}</td>
                        
                            <td>${fieldValue(bean: autoreInstance, field: "cognome")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${autoreInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
