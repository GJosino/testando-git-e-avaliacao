package br.com.soc.sistema.infra;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import br.com.soc.sistema.exception.BusinessException;

public enum OpcoesComboBuscarRelatorios {
	DATA("1", "DATA"),
	DIA("2", "DIA"),
	MES("3", "MES"),
	ANO("4", "ANO");

	
	
	private String codigo;
	private String descricao;
	private final static Map<String, OpcoesComboBuscarRelatorios> opcoes = new HashMap<>();
	
	static {
		Arrays.asList(OpcoesComboBuscarRelatorios.values())
		.forEach(
			opcao -> opcoes.put(opcao.getCodigo(), opcao)
		);
	}
	
	private OpcoesComboBuscarRelatorios(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public static OpcoesComboBuscarRelatorios buscarPor(String codigo) throws IllegalArgumentException {
		if(codigo == null)
			throw new IllegalArgumentException("informe um codigo valido");
		
		OpcoesComboBuscarRelatorios opcao = getOpcao(codigo)
				.orElseThrow(() -> new BusinessException("Codigo informado nao existe"));
		
		return opcao;
	}
	
	private static Optional<OpcoesComboBuscarRelatorios> getOpcao(String codigo){
		return Optional.ofNullable(opcoes.get(codigo));
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
}