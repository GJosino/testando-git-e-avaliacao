package br.com.soc.sistema.filter;

import br.com.soc.sistema.infra.OpcoesComboBuscarRelatorios;

public class RelatorioFilter {
	private OpcoesComboBuscarRelatorios opcoesCombo;
	private String valorBusca;
	private String dataInicial;
	private String dataFinal;

	public String getValorBusca() {
		return valorBusca;
	}

	public RelatorioFilter setValorBusca(String valorBusca) {
		this.valorBusca = valorBusca;
		return this;
	}

	public OpcoesComboBuscarRelatorios getOpcoesCombo() {
		return opcoesCombo;
	}

	public RelatorioFilter setOpcoesCombo(String codigo) {
		this.opcoesCombo = OpcoesComboBuscarRelatorios.buscarPor(codigo);
		return this;
	}	
	
	public boolean isNullOpcoesCombo() {
		return this.getOpcoesCombo() == null;
	}
	
	public static RelatorioFilter builder() {
		return new RelatorioFilter();
	}

	public String getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}

	public String getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}
}
