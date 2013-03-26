

<%@ page import="manager.PreferedLanguage" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'preferedLanguage.label', default: 'PreferedLanguage')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${preferedLanguageInstance}">
            <div class="errors">
                <g:renderErrors bean="${preferedLanguageInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${preferedLanguageInstance?.id}" />
                <g:hiddenField name="version" value="${preferedLanguageInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="usuario"><g:message code="preferedLanguage.usuario.label" default="Usuario" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: preferedLanguageInstance, field: 'usuario', 'errors')}">
                                    <g:select name="usuario.id" from="${manager.Usuario.list()}" optionKey="id" value="${preferedLanguageInstance?.usuario?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="idioma"><g:message code="preferedLanguage.idioma.label" default="Idioma" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: preferedLanguageInstance, field: 'idioma', 'errors')}">
                                    <g:select name="idioma.id" from="${manager.Language.list()}" optionKey="id" value="${preferedLanguageInstance?.idioma?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="orderInd"><g:message code="preferedLanguage.orderInd.label" default="Order Ind" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: preferedLanguageInstance, field: 'orderInd', 'errors')}">
                                    <g:textField name="orderInd" value="${fieldValue(bean: preferedLanguageInstance, field: 'orderInd')}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
