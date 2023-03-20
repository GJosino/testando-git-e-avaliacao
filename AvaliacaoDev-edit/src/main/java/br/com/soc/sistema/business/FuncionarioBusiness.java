package br.com.soc.sistema.business;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.soc.sistema.dao.funcionarios.FuncionarioDao;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.filter.FuncionarioFilter;
import br.com.soc.sistema.vo.FuncionarioVo;

public class FuncionarioBusiness {

	private static final String FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO = "Foi informado um caracter no lugar de um numero";
	private FuncionarioDao dao;
	
	public FuncionarioBusiness() {
		this.dao = new FuncionarioDao();
	}
	public List<FuncionarioVo> trazerTodosOsFuncionarios(){
		return dao.findAllFuncionarios();
	}
	
	public void salvarFuncionario(FuncionarioVo funcionarioVo) {
		try {
			if(funcionarioVo.getNome().isEmpty())
				throw new IllegalArgumentException("Nome nao pode ser em branco");
			
			Pattern pattern = Pattern.compile("\\d+");
		    Matcher matcher = pattern.matcher(funcionarioVo.getNome());
		    if (matcher.find()) {
		    	throw new IllegalArgumentException("Nome nao pode ter números");
		      }
			
			dao.insertFuncionario(funcionarioVo);
		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel realizar a inclusao do registro, o dado não pode ser em branco nem possuir números");
		}
		
	}
	
	public List<FuncionarioVo> filtrarFuncionarios(FuncionarioFilter filter){
		List<FuncionarioVo> funcionarios = new ArrayList<>();
		
		switch (filter.getOpcoesCombo()) {
			case ID:
				try {
					Integer codigo = Integer.parseInt(filter.getValorBusca());
					funcionarios.addAll(dao.findByCodigo(codigo));
				}catch (NumberFormatException e) {
					throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
				}
			break;

			case NOME:
				try {
				funcionarios.addAll(dao.findAllByNome(filter.getValorBusca()));
				}catch(Exception e) {
					throw new BusinessException("Ocorreu algum erro desconhecido");
				}
			break;
		}
		
		return funcionarios;
	}
	
	public List<FuncionarioVo> buscarFuncionarioPor(String codigo) {
		try {
			Integer cod = Integer.parseInt(codigo);
			return dao.findByCodigo(cod);
		}catch (NumberFormatException e) {
			throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
		}
	}
	public void editarFuncionario(FuncionarioVo funcionarioVo, String novoValor) {
		try {
			if(funcionarioVo.getNome().isEmpty())
				throw new IllegalArgumentException("Nome nao pode ser em branco");
			
			Pattern pattern = Pattern.compile("\\d+"); 
		    Matcher matcher = pattern.matcher(funcionarioVo.getNome());
		    if (matcher.find()) { 
		    	throw new IllegalArgumentException("Nome nao pode ter números");
		      }
			Integer cod = Integer.parseInt(funcionarioVo.getRowid());
			dao.UpdateByCodigo(cod, novoValor);
		}catch (NumberFormatException e) {
			throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
		}
	}
	
	public void DeletarFuncionario(FuncionarioVo funcionarioVo) {
		try {
			
			Integer cod = Integer.parseInt(funcionarioVo.getRowid());
			dao.DeleteByCodigo(cod);
		}catch (NumberFormatException e) {
			throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
		}
	}
}
