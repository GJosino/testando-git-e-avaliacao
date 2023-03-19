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
    <p><a href="<s:url action='todosRealizados'/>">Exames Realizados</a></p>
    <p><a href="<s:url action='todosRelatorios'/>">Relatorios</a></p>
  
    
   



    
  </body>
</html>