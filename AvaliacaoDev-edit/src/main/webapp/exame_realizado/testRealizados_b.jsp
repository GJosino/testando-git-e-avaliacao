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


	<p>FUNCIONARIOS</p>
	
	<a href="<s:url  action='inicio'/>" class="btn btn-success mb-3" >Inicio</a>
	<a href="<s:url  action='todosFuncionarios'/>" class="btn btn-success mb-3" >Funcionarios</a>
	
	
	<s:form action="filtrarFuncionarios">
  		<s:textfield name="conteudoPayload" label="Nome do Funcionario" />
  		<button type="submit">FILTRAR/PESQUISAR</button>
	</s:form>
	
<div>
<table>
<s:iterator value="examesRealizados">
<tr>
								<td>${rowid}</td>
								<td>${exameVo.rowid}</td>
								<td>${exameVo.nome}</td>
								<td>${funcionarioVo.rowid}</td>
								<td>${funcionarioVo.nome}</td>
								<td>${dataExame}</td>
						
								<td class="text-end">
									<s:url action="editarExames" var="editar">
										<s:param name="exameVo.rowid" value="rowid"></s:param>
									</s:url>

									<a href="${editar}" class="btn btn-warning text-white">
										<s:text name="label.editar"/>
									</a>

									<a href="#" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#confirmarExclusao">
										<s:text name="label.excluir"/>
									</a>
								</td>
							</tr>
						
</s:iterator>
</table>
</div>

<div>
<s:form action="todosRealizados">
			<s:select label="Select Subject"
			listKey="%{exameVo.rowid}"
			listValueKey="%{exameVo.nome}"
			headerKey="-1" headerValue="Nome do Exame"
			list="examesRealizados" name="nome_da_variavel_parametro"
			value="exameVo.rowid" />
			<s:submit value="Submit"/>
		</s:form>
</div>
		



	
	
	<a href="<s:url  action='novoRealizados'/>" class="btn btn-success mb-3" >Novo</a>
	</body>
</html>