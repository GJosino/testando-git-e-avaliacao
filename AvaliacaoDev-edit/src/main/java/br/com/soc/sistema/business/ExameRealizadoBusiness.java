package br.com.soc.sistema.business;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.dao.examesRealizados.ExameRealizadoDao;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.filter.ExameRealizadoFilter;
import br.com.soc.sistema.filter.RelatorioFilter;
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
			throw new BusinessException("Nao foi possivel realizar a inclusao do registro, todos os campos devem ser preenchidos e a data deve ser no formato DD/MM/YYYY");
		}
		
	}
	
	public List<ExameRealizadoVo> filtrarExamesRealizados(ExameRealizadoFilter filter){
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
			
			case ID_EXAME:
				try {
					Integer codigo = Integer.parseInt(filter.getValorBusca());
					examesRealizados.addAll(dao.findByCodigoExame(codigo));
				}catch (NumberFormatException e) {
					throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
				}
			break;

			case NOME_EXAME:
				examesRealizados.addAll(dao.findByNomeExame(filter.getValorBusca()));
			break;
			
			case ID_FUNCIONARIO:
				try {
					Integer codigo = Integer.parseInt(filter.getValorBusca());
					examesRealizados.addAll(dao.findByCodigoFuncionario(codigo));
				}catch (NumberFormatException e) {
					throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
				}
			break;
			
			case NOME_FUNCIONARIO:
				examesRealizados.addAll(dao.findByNomeFuncionario(filter.getValorBusca()));
			break;
			
			case DATA_EXAME:
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			    LocalDate dataExame = LocalDate.parse(filter.getValorBusca(), formatter);
				examesRealizados.addAll(dao.findByDataExame(dataExame));
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
	public void editarExameRealizado(ExameRealizadoVo exameRealizadoVo) {
		try {
	//		if(exameRealizadoVo.getNome().isEmpty())
	//			throw new IllegalArgumentException("Nome nao pode ser em branco");
			Integer cod = Integer.parseInt(exameRealizadoVo.getRowid());
			Integer codExame = Integer.parseInt(exameRealizadoVo.getExameVo().getRowid());
			Integer codFuncionario = Integer.parseInt(exameRealizadoVo.getFuncionarioVo().getRowid());
			String dataExame = exameRealizadoVo.getDataExame();
			
			
			dao.UpdateByCodigo(cod, codExame, codFuncionario, dataExame);
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
	
	
	
	
	
	
	
	
	
///////////////////////////////////////LOGICA DO RELATORIOS
	
	
	
	
	
	
	
	public List<ExameRealizadoVo> filtrarRelatorios(RelatorioFilter filter){
		List<ExameRealizadoVo> examesRealizados = new ArrayList<>();
		
		switch (filter.getOpcoesCombo()) {
			case DATA:
				try {
					//Integer codigo = Integer.parseInt(filter.getDataInicial());
					//examesRealizados.add(dao.findByCodigo(codigo));
					
					examesRealizados.addAll(dao.findRelatorios(filter.getDataInicial(), filter.getDataFinal() ));
				}catch (NumberFormatException e) {
					throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
				}
			break;
			
			case DIA:
				try {
					Integer codigo = Integer.parseInt(filter.getValorBusca());
					examesRealizados.addAll(dao.findByCodigoExame(codigo));
				}catch (NumberFormatException e) {
					throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
				}
			break;
		}
		
		return examesRealizados;
	}

	
	
	
	
	
	
	
}
