package br.com.soc.sistema.action.relatorio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.soc.sistema.business.ExameBusiness;
import br.com.soc.sistema.business.ExameRealizadoBusiness;
import br.com.soc.sistema.business.FuncionarioBusiness;
import br.com.soc.sistema.filter.ExameRealizadoFilter;
import br.com.soc.sistema.filter.RelatorioFilter;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.infra.OpcoesComboBuscarRelatorios;
import br.com.soc.sistema.vo.ExameRealizadoVo;
import br.com.soc.sistema.vo.ExameVo;
import br.com.soc.sistema.vo.FuncionarioVo;

public class RelatorioAction extends Action {
	private List<ExameRealizadoVo> examesRealizados = new ArrayList<>();
	private ExameRealizadoBusiness business = new ExameRealizadoBusiness();
	private RelatorioFilter filtrar = new RelatorioFilter();
	private ExameRealizadoVo exameRealizadoVo = new ExameRealizadoVo();
	
	private List<FuncionarioVo> funcionarios = new ArrayList<>();
	private FuncionarioBusiness funcionariosBusiness = new FuncionarioBusiness();
	
	private List<ExameVo> exames = new ArrayList<>();
	private ExameBusiness examesBusiness = new ExameBusiness();
	
	
	public String todos() {
			
		return SUCCESS;
	}
	

	public String filtrar() {
		if(getFiltrar().isNullOpcoesCombo())
			return REDIRECT;
		
		examesRealizados = business.filtrarRelatorios(getFiltrar());
		
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
		

		
	
	
		


	
		
	public List<OpcoesComboBuscarRelatorios> getListaOpcoesCombo(){
		return Arrays.asList(OpcoesComboBuscarRelatorios.values());
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

	public RelatorioFilter getFiltrar() {
		return filtrar;
	}

	public void setFiltrar(RelatorioFilter filtrar) {
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
