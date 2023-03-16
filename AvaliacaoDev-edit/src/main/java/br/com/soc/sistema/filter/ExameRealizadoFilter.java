package br.com.soc.sistema.filter;

import br.com.soc.sistema.infra.OpcoesComboBuscarExamesRealizados;

public class ExameRealizadoFilter {
	private OpcoesComboBuscarExamesRealizados opcoesCombo;
	private String valorBusca;

	public String getValorBusca() {
		return valorBusca;
	}

	public ExameRealizadoFilter setValorBusca(String valorBusca) {
		this.valorBusca = valorBusca;
		return this;
	}

	public OpcoesComboBuscarExamesRealizados getOpcoesCombo() {
		return opcoesCombo;
	}

	public ExameRealizadoFilter setOpcoesCombo(String codigo) {
		this.opcoesCombo = OpcoesComboBuscarExamesRealizados.buscarPor(codigo);
		return this;
	}	
	
	public boolean isNullOpcoesCombo() {
		return this.getOpcoesCombo() == null;
	}
	
	public static ExameRealizadoFilter builder() {
		return new ExameRealizadoFilter();
	}
}
