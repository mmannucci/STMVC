
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
            <span class="menuButton"><st:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></st:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
           
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn controller="autore" property="id" title="${message(code: 'autore.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn controller="autore" property="cognome" title="${message(code: 'autore.cognome.label', default: 'Cognome')}" />
                        
                            <g:sortableColumn controller="autore" property="date" title="${message(code: 'autore.date.label', default: 'Date')}" />
                        
                            <th><g:message code="autore.editore.label" default="Editore" /></th>
                   	    
                            <g:sortableColumn controller="autore" property="nome" title="${message(code: 'autore.nome.label', default: 'Nome')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${autoreInstanceList}" status="i" var="autoreInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><st:link controller="autore" action="show" id="${autoreInstance.id}">${fieldValue(bean: autoreInstance, field: "id")}</st:link></td>
                        
                            <td>${fieldValue(bean: autoreInstance, field: "cognome")}</td>
                        
                            <td><g:formatDate date="${autoreInstance.date}" /></td>
                        
                            <td>${fieldValue(bean: autoreInstance, field: "editore")}</td>
                        
                            <td>${fieldValue(bean: autoreInstance, field: "nome")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${autoreInstanceTotal}"  action="list.dispatch"/>
            </div>
        </div>
    </body>
</html>
