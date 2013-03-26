
<%@ page import="manager.PreferedLanguage" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'preferedLanguage.label', default: 'PreferedLanguage')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'preferedLanguage.id.label', default: 'Id')}" />
                        
                            <th><g:message code="preferedLanguage.usuario.label" default="Usuario" /></th>
                        
                            <th><g:message code="preferedLanguage.idioma.label" default="Idioma" /></th>
                        
                            <g:sortableColumn property="orderInd" title="${message(code: 'preferedLanguage.orderInd.label', default: 'Order Ind')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${preferedLanguageInstanceList}" status="i" var="preferedLanguageInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${preferedLanguageInstance.id}">${fieldValue(bean: preferedLanguageInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: preferedLanguageInstance, field: "usuario")}</td>
                        
                            <td>${fieldValue(bean: preferedLanguageInstance, field: "idioma")}</td>
                        
                            <td>${fieldValue(bean: preferedLanguageInstance, field: "orderInd")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${preferedLanguageInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
