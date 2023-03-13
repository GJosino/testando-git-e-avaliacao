<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title><s:text name="label.titulo.pagina.consulta"/></title>
		<link rel='stylesheet' href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
	</head>
	<body>	


	<p>NOVO FUNCIONARIO</p>
	
	<a href="<s:url  action='inicio'/>" class="btn btn-success mb-3" >Inicio</a>
	<a href="<s:url  action='todosFuncionarios'/>" class="btn btn-success mb-3" >Funcionarios</a>
	
	<s:form action="novoFuncionarios">
  		<s:textfield name="funcionarioVo.nome" label="Nome do Funcionario" />
  		<button type="submit">CRIAR</button>
	</s:form>

	<h2><s:property value="lista"/></h2>
	</body>
</html>