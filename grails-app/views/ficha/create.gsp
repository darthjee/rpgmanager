

<%@ page import="personagem.ded35.Ficha" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'ficha.label', default: 'Ficha')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${fichaInstance}">
            <div class="errors">
                <g:renderErrors bean="${fichaInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="personagem"><g:message code="ficha.personagem.label" default="Personagem" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: fichaInstance, field: 'personagem', 'errors')}">
                                    <g:select name="personagem.id" from="${personagem.Personagem.list()}" optionKey="id" value="${fichaInstance?.personagem?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="raca"><g:message code="ficha.raca.label" default="Raca" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: fichaInstance, field: 'raca', 'errors')}">
                                    <g:select name="raca.id" from="${personagem.ded35.Raca.list()}" optionKey="id" value="${fichaInstance?.raca?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="tipo"><g:message code="ficha.tipo.label" default="Tipo" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: fichaInstance, field: 'tipo', 'errors')}">
                                    <g:select name="tipo" from="${TipoPersonagem?.values()}" keys="${TipoPersonagem?.values()*.name()}" value="${fichaInstance?.tipo?.name()}"  />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
