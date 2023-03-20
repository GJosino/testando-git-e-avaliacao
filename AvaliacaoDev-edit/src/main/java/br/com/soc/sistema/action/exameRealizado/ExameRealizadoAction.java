package br.com.soc.sistema.action.exameRealizado;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.soc.sistema.business.ExameBusiness;
import br.com.soc.sistema.business.ExameRealizadoBusiness;
import br.com.soc.sistema.business.FuncionarioBusiness;
import br.com.soc.sistema.filter.ExameRealizadoFilter;
import br.com.soc.sistema.filter.RelatorioFilter;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.infra.OpcoesComboBuscarExamesRealizados;
import br.com.soc.sistema.infra.OpcoesComboBuscarRelatorios;
import br.com.soc.sistema.vo.ExameRealizadoVo;
import br.com.soc.sistema.vo.ExameVo;
import br.com.soc.sistema.vo.FuncionarioVo;

public class ExameRealizadoAction extends Action {
	private List<ExameRealizadoVo> examesRealizados = new ArrayList<>();
	private ExameRealizadoBusiness business = new ExameRealizadoBusiness();
	private ExameRealizadoFilter filtrar = new ExameRealizadoFilter();
	private ExameRealizadoVo exameRealizadoVo = new ExameRealizadoVo();
	
	private List<FuncionarioVo> funcionarios = new ArrayList<>();
	private FuncionarioBusiness funcionariosBusiness = new FuncionarioBusiness();
	
	private List<ExameVo> exames = new ArrayList<>();
	private ExameBusiness examesBusiness = new ExameBusiness();
	
	
	public String todos() {
		getExamesRealizados().addAll(business.trazerTodosOsExamesRealizados());	
		return SUCCESS;
	}
	
	public String novo() {
		if(exameRealizadoVo.getDataExame() == null) {
		getExames().addAll(examesBusiness.trazerTodosOsExames());
		getFuncionarios().addAll(funcionariosBusiness.trazerTodosOsFuncionarios());
			return INPUT;
		}
		
		business.salvarExameRealizado(exameRealizadoVo);
		return REDIRECT;	
	}
	public String filtrar() {
		if(getFiltrar().isNullOpcoesCombo())
			return REDIRECT;
		
		examesRealizados = business.filtrarExamesRealizados(getFiltrar());
		
		return SUCCESS;
	}
	public String editar() {
		if(exameRealizadoVo.getRowid() == null)
			return REDIRECT;
		
		if(exameRealizadoVo.getDataExame() == null) {
			getExames().addAll(examesBusiness.trazerTodosOsExames());
			getFuncionarios().addAll(funcionariosBusiness.trazerTodosOsFuncionarios());
		return UPDATE;
		}
		business.editarExameRealizado(exameRealizadoVo);
		return REDIRECT;
	}
		public String deletar() {
			
			business.DeletarExameRealizado(exameRealizadoVo);
			
			return REDIRECT;
		}
		

		
	
	
		


	
		
	public List<OpcoesComboBuscarExamesRealizados> getListaOpcoesCombo(){
		return Arrays.asList(OpcoesComboBuscarExamesRealizados.values());
	}


	public ExameRealizadoVo getExameRealizadoVo() {
		return exameRealizadoVo;
	}

	public void setExameRealizadoVo(ExameRealizadoVo exameRealizadoVo) {
		this.exameRealizadoVo = exameRealizadoVo;
	}

	public List<ExameRealizadoVo> getExamesRealizados() {
		return examesRealizados;
	}

	public void setExamesRealizados(List<ExameRealizadoVo> examesRealizados) {
		this.examesRealizados = examesRealizados;
	}

	public ExameRealizadoFilter getFiltrar() {
		return filtrar;
	}

	public void setFiltrar(ExameRealizadoFilter filtrar) {
		this.filtrar = filtrar;
	}


	public List<FuncionarioVo> getFuncionarios() {
		return funcionarios;
	}


	public void setFuncionarios(List<FuncionarioVo> funcionarios) {
		this.funcionarios = funcionarios;
	}


	public List<ExameVo> getExames() {
		return exames;
	}


	public void setExames(List<ExameVo> exames) {
		this.exames = exames;
	}




















	
	
}
