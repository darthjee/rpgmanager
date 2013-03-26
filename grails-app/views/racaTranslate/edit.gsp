

<%@ page import="personagem.ded35.RacaTranslate" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'racaTranslate.label', default: 'RacaTranslate')}" />
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
            <g:hasErrors bean="${racaTranslateInstance}">
            <div class="errors">
                <g:renderErrors bean="${racaTranslateInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${racaTranslateInstance?.id}" />
                <g:hiddenField name="version" value="${racaTranslateInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="idObj"><g:message code="racaTranslate.raca.label" default="Raca" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: racaTranslateInstance, field: 'idObj', 'errors')}">
                                    <g:select name="idObj" from="${personagem.ded35.Raca.list()}" optionKey="id" value="${racaTranslateInstance?.idObj}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="lang"><g:message code="racaTranslate.lang.label" default="Lang" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: racaTranslateInstance, field: 'lang', 'errors')}">
                                    <g:select name="lang.id" from="${manager.Language.list()}" optionKey="id" value="${racaTranslateInstance?.lang?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="nome"><g:message code="racaTranslate.nome.label" default="Nome" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: racaTranslateInstance, field: 'nome', 'errors')}">
                                    <g:textField name="nome" value="${racaTranslateInstance?.nome}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="desc"><g:message code="racaTranslate.desc.label" default="Desc" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: racaTranslateInstance, field: 'desc', 'errors')}">
                                    <g:textField name="desc" value="${racaTranslateInstance?.desc}" />
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
