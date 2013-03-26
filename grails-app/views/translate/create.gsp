

<%@ page import="personagem.ded35.Translate" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'translate.label', default: 'Translate')}" />
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
            <g:hasErrors bean="${translateInstance}">
            <div class="errors">
                <g:renderErrors bean="${translateInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="idObj"><g:message code="translate.idObj.label" default="Id Obj" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: translateInstance, field: 'idObj', 'errors')}">
                                    <g:textField name="idObj" value="${fieldValue(bean: translateInstance, field: 'idObj')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="lang"><g:message code="translate.lang.label" default="Lang" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: translateInstance, field: 'lang', 'errors')}">
                                    <g:select name="lang.id" from="${manager.Language.list()}" optionKey="id" value="${translateInstance?.lang?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="nome"><g:message code="translate.nome.label" default="Nome" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: translateInstance, field: 'nome', 'errors')}">
                                    <g:textField name="nome" value="${translateInstance?.nome}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="desc"><g:message code="translate.desc.label" default="Desc" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: translateInstance, field: 'desc', 'errors')}">
                                    <g:textField name="desc" value="${translateInstance?.desc}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="tipo"><g:message code="translate.tipo.label" default="Tipo" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: translateInstance, field: 'tipo', 'errors')}">
                                    <g:select name="tipo" from="${personagem.ded35.TipoTranslate?.values()}" keys="${personagem.ded35.TipoTranslate?.values()*.name()}" value="${translateInstance?.tipo?.name()}"  />
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
