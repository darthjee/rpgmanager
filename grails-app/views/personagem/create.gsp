

<%@ page import="personagem.Personagem" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'personagem.label', default: 'Personagem')}" />
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
            <g:hasErrors bean="${personagemInstance}">
            <div class="errors">
                <g:renderErrors bean="${personagemInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="nome"><g:message code="personagem.nome.label" default="Nome" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: personagemInstance, field: 'nome', 'errors')}">
                                    <g:textField name="nome" value="${personagemInstance?.nome}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="role"><g:message code="personagem.role.label" default="Role" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: personagemInstance, field: 'role', 'errors')}">
                                    <g:textField name="role" value="${personagemInstance?.role}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="raca"><g:message code="personagem.raca.label" default="Raca" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: personagemInstance, field: 'raca', 'errors')}">
                                    <g:textField name="raca" value="${personagemInstance?.raca}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="desc"><g:message code="personagem.desc.label" default="Desc" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: personagemInstance, field: 'desc', 'errors')}">
                                    <g:textField name="desc" value="${personagemInstance?.desc}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="owner"><g:message code="personagem.owner.label" default="Owner" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: personagemInstance, field: 'owner', 'errors')}">
                                    <g:select name="owner.id" from="${manager.Usuario.findAllByTipo(manager.TipoPessoa.U)}" optionKey="id" value="${personagemInstance?.owner?.id}"  />
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
