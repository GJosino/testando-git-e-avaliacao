package br.com.soc.sistema.business;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
				throw new IllegalArgumentException("Nome não pode ser em branco");
			
			Integer exameId = Integer.parseInt(exameRealizadoVo.getExameVo().getRowid());
			Integer funcionarioId = Integer.parseInt(exameRealizadoVo.getFuncionarioVo().getRowid());
			
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		    LocalDate dataExame = LocalDate.parse(exameRealizadoVo.getDataExame(), formatter);	
		    
			dao.insertExameRealizado(exameRealizadoVo, exameId, funcionarioId, dataExame);
		} catch (Exception e) {
			throw new BusinessException("Não foi possível realizar a inclusão do registro, todos os campos devem ser preenchidos, a data deve ser no formato DD/MM/YYYY"
					+ " e os dados não podem ser duplicados.");
		}
		
	}
	
	public List<ExameRealizadoVo> filtrarExamesRealizados(ExameRealizadoFilter filter){
		List<ExameRealizadoVo> examesRealizados = new ArrayList<>();
		
		switch (filter.getOpcoesCombo()) {
			case ID:
				try {
					Integer codigo = Integer.parseInt(filter.getValorBusca());
					examesRealizados.addAll(dao.findByCodigo(codigo));
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
				try {
				examesRealizados.addAll(dao.findByNomeExame(filter.getValorBusca()));
				} catch(Exception e) {
					throw new BusinessException("Ocorreu um erro desconhecido");
				}
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
				try {
				examesRealizados.addAll(dao.findByNomeFuncionario(filter.getValorBusca()));
				} catch(Exception e) {
					throw new BusinessException("Ocorreu um erro desconhecido");
				}
			break;
			
			case DATA_EXAME: try {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			    LocalDate dataExame = LocalDate.parse(filter.getValorBusca(), formatter);
				examesRealizados.addAll(dao.findByDataExame(dataExame));
				}catch(DateTimeParseException e) {
					throw new BusinessException("Data inserida inválida, por favor insira uma data no formato dd/mm/yyyy");
				}
			break;
		}
		
		return examesRealizados;
	}
	
	public List<ExameRealizadoVo> buscarExameRealizadoPor(String codigo) {
		try {
			Integer cod = Integer.parseInt(codigo);
			return dao.findByCodigo(cod);
		}catch (NumberFormatException e) {
			throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
		}
	}
	public void editarExameRealizado(ExameRealizadoVo exameRealizadoVo) {
		try {
			Integer cod = Integer.parseInt(exameRealizadoVo.getRowid());
			Integer codExame = Integer.parseInt(exameRealizadoVo.getExameVo().getRowid());
			Integer codFuncionario = Integer.parseInt(exameRealizadoVo.getFuncionarioVo().getRowid());
			String dataExame = exameRealizadoVo.getDataExame();
			
			
			dao.UpdateByCodigo(cod, codExame, codFuncionario, dataExame);
		}catch (DateTimeParseException e) {
			throw new BusinessException("Data inserida inválida, por favor insira uma data no formato dd/mm/yyyy");
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
	
	
	
	
	
	
	
	
	
/////////RELATORIOS POR DATA, DIA, DIA, MES, ANO ///////////////////////
	
	
	public List<ExameRealizadoVo> filtrarRelatorios(RelatorioFilter filter){
		List<ExameRealizadoVo> examesRealizados = new ArrayList<>();
		
		switch (filter.getOpcoesCombo()) {
			case DATA:
				try {
			
					examesRealizados.addAll(dao.findRelatoriosData(filter.getDataInicial(), filter.getDataFinal() ));
				}catch(DateTimeParseException e) {
					throw new BusinessException("Data inserida inválida, por favor insira uma data no formato dd/mm/yyyy");
				}
			break;
			
			case DIA:
				try {
	
					examesRealizados.addAll(dao.findRelatoriosDia(filter.getDataInicial(), filter.getDataFinal() ));
				}catch (NumberFormatException e) {
					throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
				}
			break;
			
			case MES:
				try {
		
					examesRealizados.addAll(dao.findRelatoriosMes(filter.getDataInicial(), filter.getDataFinal() ));
				}catch (NumberFormatException e) {
					throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
				}
			break;
			
			case ANO:
				try {
					
					examesRealizados.addAll(dao.findRelatoriosAno(filter.getDataInicial(), filter.getDataFinal() ));
				}catch (NumberFormatException e) {
					throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
				}
			break;
		}
		
		return examesRealizados;
	}

	
	
	
	
	
	
	
}
