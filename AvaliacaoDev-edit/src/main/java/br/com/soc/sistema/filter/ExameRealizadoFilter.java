package br.com.soc.sistema.filter;

import br.com.soc.sistema.infra.OpcoesComboBuscarFuncionarios;

public class ExameRealizadoFilter {
	private OpcoesComboBuscarFuncionarios opcoesCombo;
	private String valorBusca;

	public String getValorBusca() {
		return valorBusca;
	}

	public ExameRealizadoFilter setValorBusca(String valorBusca) {
		this.valorBusca = valorBusca;
		return this;
	}

	public OpcoesComboBuscarFuncionarios getOpcoesCombo() {
		return opcoesCombo;
	}

	public ExameRealizadoFilter setOpcoesCombo(String codigo) {
		this.opcoesCombo = OpcoesComboBuscarFuncionarios.buscarPor(codigo);
		return this;
	}	
	
	public boolean isNullOpcoesCombo() {
		return this.getOpcoesCombo() == null;
	}
	
	public static ExameRealizadoFilter builder() {
		return new ExameRealizadoFilter();
	}
}
