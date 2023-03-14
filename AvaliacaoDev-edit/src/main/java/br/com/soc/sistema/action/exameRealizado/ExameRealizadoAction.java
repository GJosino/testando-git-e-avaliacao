package br.com.soc.sistema.action.exameRealizado;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.soc.sistema.business.ExameRealizadoBusiness;
import br.com.soc.sistema.filter.ExameRealizadoFilter;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.infra.OpcoesComboBuscarExamesRealizados;
import br.com.soc.sistema.vo.ExameRealizadoVo;

public class ExameRealizadoAction extends Action {
	private List<ExameRealizadoVo> examesRealizados = new ArrayList<>();
	private ExameRealizadoBusiness business = new ExameRealizadoBusiness();
	private ExameRealizadoFilter filtrar = new ExameRealizadoFilter();
	private ExameRealizadoVo exameRealizadoVo = new ExameRealizadoVo();

	
	public String todos() {
		getExamesRealizados().addAll(business.trazerTodosOsExamesRealizados());	
		System.out.println(getExamesRealizados());
		return SUCCESS;
	}
	
	
	public String novo() {
		//if(exameRealizadoVo.getNome() == null)
		//	return INPUT;
		
		business.salvarExameRealizado(exameRealizadoVo);
		
		return REDIRECT;	
	}
	

	public String filtrar() {
		if(getFiltrar().isNullOpcoesCombo())
			return REDIRECT;
		
	//	examesRealizados = business.filtrarExamesRealizados(getFiltrar());
		
		return SUCCESS;
	}
	
	public String editar() {
		if(exameRealizadoVo.getRowid() == null)
			return REDIRECT;
		
	//	String novoValor = exameRealizadoVo.getNome();
		
		//if(exameRealizadoVo.getNome() == null) {
	//	return UPDATE;
	//	}
	//	business.editarExameRealizado(exameRealizadoVo, novoValor);
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

	
	
}
