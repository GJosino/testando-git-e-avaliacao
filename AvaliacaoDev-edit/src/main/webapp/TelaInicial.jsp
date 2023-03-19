<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title><s:text name="label.titulo.pagina.consulta"/></title>
		<link rel='stylesheet' href='webjars/bootstrap/5.1.3/css/bootstrap.min.css'>
	</head>
	<body class="bg-secondary">
	
	
	
		<div class="text-center" style="color:White;">
			<h1>Programa #souSOC</h1>
		</div>
		<div class="container">
			<div class="row mt-5 mb-2">
				<div class="col-sm p-0">
				</div>				
			</div>

			<div class="text_center" >
				<table class="table table-light table-sm table-striped align-middle text-center table mx-auto" style="width: 250px;height:250px;">
					<thead>
					</thead>
					
					<tbody>
							<tr>
								<td>
								<a href="<s:url action='todosExames'/>" class="btn btn-info text-white">
										<s:text name="label.ver.exames"/>
								</a>
								</td>		
							</tr>
								
							<tr>
								<td>		
								<a href="<s:url action='todosFuncionarios'/>" class="btn btn-info text-white">
										<s:text name="label.ver.funcionarios"/>
								</a>
								</td>
							</tr>
							
							<tr>
								<td>	
								<a href="<s:url action='todosRealizados'/>" class="btn btn-info text-white">
										<s:text name="label.ver.exames.realizados"/>
								</a>
								</td>
							</tr>
							
							<tr>
								<td>
								<a href="<s:url action='todosRelatorios'/>" class="btn btn-info text-white">
										<s:text name="label.ver.relatorios"/>
								</a>
								</td>
							</tr>
							
					</tbody>
					
					<tfoot class="table-secondary">
						
					</tfoot>				
				</table>
			</div>
		</div>
		
		<div  class="modal fade" id="confirmarExclusao" 
			data-bs-backdrop="static" data-bs-keyboard="false"
			tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title"><s:text name="label.modal.titulo"/></h5>
		        
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      
		      <div class="modal-body">
		      	<span><s:text name="label.modal.corpo"/></span>
		      </div>
		      
		      <div class="modal-footer">
	        	<a class="btn btn-secondary" data-bs-dismiss="modal" aria-label="Close">
					<s:text name="label.nao"/>
				</a>
	        	
				<s:a id="excluir" class="btn btn-primary" style="width: 75px;">
					<s:text name="label.sim"/>
				</s:a>						
		      </div>
		    </div>		    
		  </div>
		</div>
		
		<script src="webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
	</body>
</html>