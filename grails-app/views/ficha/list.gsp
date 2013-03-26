
<%@ page import="personagem.ded35.Ficha" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'ficha.label', default: 'Ficha')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'ficha.id.label', default: 'Id')}" />
                        
                            <th><g:message code="ficha.personagem.label" default="Personagem" /></th>
                        
                            <th><g:message code="ficha.raca.label" default="Raca" /></th>
                        
                            <g:sortableColumn property="tipo" title="${message(code: 'ficha.tipo.label', default: 'Tipo')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${fichaInstanceList}" status="i" var="fichaInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${fichaInstance.id}">${fieldValue(bean: fichaInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: fichaInstance, field: "personagem")}</td>
                        
                            <td>${fieldValue(bean: fichaInstance, field: "raca")}</td>
                        
                            <td>${fieldValue(bean: fichaInstance, field: "tipo")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${fichaInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
