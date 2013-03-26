
<%@ page import="personagem.ded35.RacaTranslate" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'racaTranslate.label', default: 'RacaTranslate')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'racaTranslate.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="idObj" title="${message(code: 'racaTranslate.raca.label', default: 'Raca')}" />

                            <g:sortableColumn property="lang.id" title="${message(code: 'racaTranslate.lang.label', default: 'Lang')}" />
                        
                            <g:sortableColumn property="nome" title="${message(code: 'racaTranslate.nome.label', default: 'Nome')}" />
                        
                            <g:sortableColumn property="desc" title="${message(code: 'racaTranslate.desc.label', default: 'Desc')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${racaTranslateInstanceList}" status="i" var="racaTranslateInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${racaTranslateInstance.id}">${fieldValue(bean: racaTranslateInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: racaTranslateInstance, field: "raca")}</td>
                        
                            <td>${fieldValue(bean: racaTranslateInstance, field: "lang")}</td>
                        
                            <td>${fieldValue(bean: racaTranslateInstance, field: "nome")}</td>
                        
                            <td>${fieldValue(bean: racaTranslateInstance, field: "desc")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${racaTranslateInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
