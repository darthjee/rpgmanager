
<%@ page import="personagem.ded35.Translate" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'translate.label', default: 'Translate')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'translate.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="idObj" title="${message(code: 'translate.idObj.label', default: 'Id Obj')}" />
                        
                            <th><g:message code="translate.lang.label" default="Lang" /></th>
                        
                            <g:sortableColumn property="nome" title="${message(code: 'translate.nome.label', default: 'Nome')}" />
                        
                            <g:sortableColumn property="desc" title="${message(code: 'translate.desc.label', default: 'Desc')}" />
                        
                            <g:sortableColumn property="tipo" title="${message(code: 'translate.tipo.label', default: 'Tipo')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${translateInstanceList}" status="i" var="translateInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${translateInstance.id}">${fieldValue(bean: translateInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: translateInstance, field: "idObj")}</td>
                        
                            <td>${fieldValue(bean: translateInstance, field: "lang")}</td>
                        
                            <td>${fieldValue(bean: translateInstance, field: "nome")}</td>
                        
                            <td>${fieldValue(bean: translateInstance, field: "desc")}</td>
                        
                            <td>${fieldValue(bean: translateInstance, field: "tipo")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${translateInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
