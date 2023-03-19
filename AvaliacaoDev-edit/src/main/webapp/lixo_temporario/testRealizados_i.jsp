<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title><s:text name="label.titulo.pagina.consulta"/></title>
		<link rel='stylesheet' href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
	</head>
	<body>	


	<p>NOVO EXAME REALIZADO</p>
	
	<a href="<s:url  action='inicio'/>" class="btn btn-success mb-3" >Inicio</a>
	<a href="<s:url  action='todosFuncionarios'/>" class="btn btn-success mb-3" >Exames Realizados</a>
	

<div>
<s:form action="todosRealizados">
			<s:select label="Select Subject"
			listKey="%{exameVo.rowid}"
			listValueKey="%{exameVo.nome}"
			headerKey="-1" headerValue="Nome do Exame"
			list="examesRealizados" name="exameRealizadoVo.exameVo.rowid"
			value="exameVo.rowid" />
			<s:submit value="Submit"/>
		</s:form>
</div>
	
<s:form action="novoRealizados">

<%--SELECT DE EXAMES --%>	
		<s:select label="Select Subject"
			listKey="%{rowid}"
			listValueKey="%{nome}"
			headerKey="-1" headerValue="Nome do Exame"
			list="exames" name="exameRealizadoVo.exameVo.rowid"
			value="exameVo.rowid" />
			
<%--SELECT DE FUNCIONARIOS --%>	
		<s:select label="Select Subject"
			listKey="%{rowid}"
			listValueKey="%{nome}"
			headerKey="-1" headerValue="Nome do Funcionario"
			list="funcionarios" name="exameRealizadoVo.funcionarioVo.rowid"
			value="funcionarioVo.rowid" />
	
	
	
		<label>Data do Exame</label>
  		<s:textfield name="exameRealizadoVo.dataExame" label="Data do Exame" placeholder="dd/mm/aaaa" />
  		<button type="submit">CRIAR</button>
	</s:form>

	<h2><s:property value="lista"/></h2>
	</body>
</html>