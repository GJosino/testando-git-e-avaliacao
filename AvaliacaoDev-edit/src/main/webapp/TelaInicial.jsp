<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Basic Struts 2 Application - Welcome</title>
  </head>
  <body>
  
    <h1>Josino SAÚDE</h1>
    <p><a href="<s:url action='todosExames'/>">Ver Exames</a></p>
    <p><a href="<s:url action='todosFuncionarios'/>">Ver Funcionarios</a></p>
    <p><a href="<s:url action='todosExames'/>">Ver Funcionarios que agendaram exames</a></p>
    
    
   


<%-- <p>Abaixo Exemplos para eu nao esquecer</p>
<s:url action="inicio" var="helloLink">

  <s:param name="userName">Bruce Phillips</s:param>
</s:url>

<p><a href="${helloLink}">Hello Bruce Phillips</a></p>

<s:form action="inicio">
  <s:textfield name="funcionarioVo.nome" label="Your name" />
  <s:submit value="Submit" />
</s:form>

<p><a href="inicio.action">Please register</a> for our prize drawing.</p> --%>
    
    
    
  </body>
</html>