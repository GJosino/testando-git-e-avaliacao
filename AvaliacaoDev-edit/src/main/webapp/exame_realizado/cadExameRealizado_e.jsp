<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title><s:text name="label.titulo.pagina.cadastro"/></title>
		<link rel='stylesheet' href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
	</head>
	<body class="bg-secondary">
<a href="<s:url  action='inicio'/>" class="btn btn-success mb-3" >Inicio</a>
		<div class="container">
			<s:form action="/editarRealizados.action">

				<div class="card mt-5">
					<div class="card-header">
						<div class="row">
							<div class="col-sm-5">
								<s:url action="todosRealizados" var="todos"/>
								<a href="${todos}" class="btn btn-success" >Realizados</a>
							</div>
							
							<div class="col-sm">
								<h5 class="card-title">Editar Exame Realizado</h5>
							</div>
						</div>
					</div>
					
					<div class="card-body">
						<div class="row align-items-center">
							<label for="id" class="col-sm-1 col-form-label text-center">
								Código:
							</label>	

							<div class="col-sm-2">
								<s:textfield cssClass="form-control" id="id" name="exameRealizadoVo.rowid" readonly="true"/>							
							</div>	
						</div>
						
		<%--SELECT DE EXAMES --%>

		<s:select
			cssClass="form-select mb-1"
		 	label="Select Subject"
			listKey="%{rowid}"
			listValueKey="%{nome}"
			headerKey="0" headerValue="Nome do Exame"
			list="exames" name="exameRealizadoVo.exameVo.rowid"
			value="exameVo.rowid"
			 />
			
<%--SELECT DE FUNCIONARIOS --%>	

		<s:select 
			cssClass="form-select"
			label="Select Subject"
			listKey="%{rowid}"
			listValueKey="%{nome}"
			headerKey="0" headerValue="Nome do Funcionario"
			list="funcionarios" name="exameRealizadoVo.funcionarioVo.rowid"
			value="funcionarioVo.rowid" />

	
		<label>Data do Exame</label>
  		<s:textfield name="exameRealizadoVo.dataExame" label="Data do Exame" placeholder="dd/mm/aaaa" />
					</div>

					<div class="card-footer">
						<div class="form-row">
							<button class="btn btn-primary col-sm-4 offset-sm-1">Salvar</button>
							<button type="reset" class="btn btn-secondary col-sm-4 offset-sm-2">Limpar Formulario</button>
						</div>
					</div>
				</div>
			</s:form>			
		</div>
		
		<script src="webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
	</body>
</html>