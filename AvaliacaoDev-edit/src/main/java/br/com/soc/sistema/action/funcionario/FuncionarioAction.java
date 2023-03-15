package br.com.soc.sistema.action.funcionario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.soc.sistema.business.FuncionarioBusiness;
import br.com.soc.sistema.filter.FuncionarioFilter;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.infra.OpcoesComboBuscarFuncionarios;
import br.com.soc.sistema.vo.FuncionarioVo;

public class FuncionarioAction extends Action {
	private List<FuncionarioVo> funcionarios = new ArrayList<>();
	private FuncionarioBusiness business = new FuncionarioBusiness();
	private FuncionarioFilter filtrar = new FuncionarioFilter();
	private FuncionarioVo funcionarioVo = new FuncionarioVo();


	
	
	public String todos() {
		getFuncionarios().addAll(business.trazerTodosOsFuncionarios());	
		System.out.println(getFuncionarios());
		return SUCCESS;
	}
	
	public String novo() {
		if(funcionarioVo.getNome() == null)
			return INPUT;
		
		business.salvarFuncionario(funcionarioVo);
		
		return REDIRECT;	
	}
	

	public String filtrar() {
		if(getFiltrar().isNullOpcoesCombo())
			return REDIRECT;
		
		funcionarios = business.filtrarFuncionarios(getFiltrar());
		
		return SUCCESS;
	}
	
	public String editar() {
		if(funcionarioVo.getRowid() == null)
			return REDIRECT;
		
		String novoValor = funcionarioVo.getNome();
		
		if(funcionarioVo.getNome() == null) {
		return UPDATE;
		}
		business.editarFuncionario(funcionarioVo, novoValor);
		return REDIRECT;
	}

		public String deletar() {
			
			business.DeletarFuncionario(funcionarioVo);
			
			return REDIRECT;
		}
	
	
	public List<OpcoesComboBuscarFuncionarios> getListaOpcoesCombo(){
		return Arrays.asList(OpcoesComboBuscarFuncionarios.values());
	}


	public FuncionarioVo getFuncionarioVo() {
		return funcionarioVo;
	}

	public void setFuncionarioVo(FuncionarioVo funcionarioVo) {
		this.funcionarioVo = funcionarioVo;
	}

	public List<FuncionarioVo> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<FuncionarioVo> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public FuncionarioFilter getFiltrar() {
		return filtrar;
	}

	public void setFiltrar(FuncionarioFilter filtrar) {
		this.filtrar = filtrar;
	}

}
