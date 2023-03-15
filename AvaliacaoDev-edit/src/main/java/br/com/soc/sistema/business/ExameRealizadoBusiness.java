package br.com.soc.sistema.business;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.soc.sistema.dao.examesRealizados.ExameRealizadoDao;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.filter.ExameRealizadoFilter;
import br.com.soc.sistema.vo.ExameRealizadoVo;

public class ExameRealizadoBusiness {

	private static final String FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO = "Foi informado um caracter no lugar de um numero";
	private ExameRealizadoDao dao;
	
	public ExameRealizadoBusiness() {
		this.dao = new ExameRealizadoDao();
	}
	public List<ExameRealizadoVo> trazerTodosOsExamesRealizados(){
		return dao.findAllExamesRealizados();
	}
	
	public void salvarExameRealizado(ExameRealizadoVo exameRealizadoVo) {
		try {
			if(exameRealizadoVo.getDataExame().isEmpty())
				throw new IllegalArgumentException("Nome nao pode ser em branco");
			
			Integer exameId = Integer.parseInt(exameRealizadoVo.getExameVo().getRowid());
			Integer funcionarioId = Integer.parseInt(exameRealizadoVo.getFuncionarioVo().getRowid());
			
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		    LocalDate dataExame = LocalDate.parse(exameRealizadoVo.getDataExame(), formatter);	
		    
			dao.insertExameRealizado(exameRealizadoVo, exameId, funcionarioId, dataExame);
		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel realizar a inclusao do registro");
		}
		
	}
	
	public List<ExameRealizadoVo> filtrarExameRealizados(ExameRealizadoFilter filter){
		List<ExameRealizadoVo> examesRealizados = new ArrayList<>();
		
		switch (filter.getOpcoesCombo()) {
			case ID:
				try {
					Integer codigo = Integer.parseInt(filter.getValorBusca());
					examesRealizados.add(dao.findByCodigo(codigo));
				}catch (NumberFormatException e) {
					throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
				}
			break;

			case NOME:
				examesRealizados.addAll(dao.findAllByNome(filter.getValorBusca()));
			break;
		}
		
		return examesRealizados;
	}
	
	public ExameRealizadoVo buscarExameRealizadoPor(String codigo) {
		try {
			Integer cod = Integer.parseInt(codigo);
			return dao.findByCodigo(cod);
		}catch (NumberFormatException e) {
			throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
		}
	}
	public void editarExameRealizado(ExameRealizadoVo exameRealizadoVo, String novoValor) {
		try {
	//		if(exameRealizadoVo.getNome().isEmpty())
	//			throw new IllegalArgumentException("Nome nao pode ser em branco");
			Integer cod = Integer.parseInt(exameRealizadoVo.getRowid());
			dao.UpdateByCodigo(cod, novoValor);
		}catch (NumberFormatException e) {
			throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
		}
	}
	
	public void DeletarExameRealizado(ExameRealizadoVo exameRealizadoVo) {
		try {
			
			Integer cod = Integer.parseInt(exameRealizadoVo.getRowid());
			dao.DeleteByCodigo(cod);
		}catch (NumberFormatException e) {
			throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
		}
	}
}
