
<%@ page import="personagem.Personagem" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'personagem.label', default: 'Personagem')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'personagem.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="nome" title="${message(code: 'personagem.nome.label', default: 'Nome')}" />
                        
                            <g:sortableColumn property="role" title="${message(code: 'personagem.role.label', default: 'Role')}" />
                        
                            <g:sortableColumn property="raca" title="${message(code: 'personagem.raca.label', default: 'Raca')}" />
                        
                            <g:sortableColumn property="desc" title="${message(code: 'personagem.desc.label', default: 'Desc')}" />
                        
                            <th><g:message code="personagem.owner.label" default="Owner" /></th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${personagemInstanceList}" status="i" var="personagemInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${personagemInstance.id}">${fieldValue(bean: personagemInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: personagemInstance, field: "nome")}</td>
                        
                            <td>${fieldValue(bean: personagemInstance, field: "role")}</td>
                        
                            <td>${fieldValue(bean: personagemInstance, field: "raca")}</td>
                        
                            <td>${fieldValue(bean: personagemInstance, field: "desc")}</td>
                        
                            <td>${fieldValue(bean: personagemInstance, field: "owner")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${personagemInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
