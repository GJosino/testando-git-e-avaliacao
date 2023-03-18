package br.com.soc.sistema.dao.examesRealizados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.soc.sistema.dao.Dao;
import br.com.soc.sistema.vo.ExameRealizadoVo;

public class ExameRealizadoDao extends Dao {

	public void insertExameRealizado(ExameRealizadoVo exameRealizadoVo,Integer exameId, Integer funcionarioId, LocalDate dataExame)
			throws SQLException {
		StringBuilder query = new StringBuilder("INSERT INTO exame_realizado \n"
				+ "(rowid_exame, rowid_funcionario, data_exame) \n"
				+ "VALUES (?, ?, ?)");
		try(
			Connection con = getConexao();
			PreparedStatement  ps = con.prepareStatement(query.toString())){
			
			
			ps.setInt(1, exameId);
			ps.setInt(2, funcionarioId);
			ps.setDate(3, java.sql.Date.valueOf(dataExame));
			
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException();
		}
		
	}

	public List<ExameRealizadoVo> findAllExamesRealizados(){
		StringBuilder query = new StringBuilder("SELECT \n"
				+ "  exame_realizado.rowid id, \n"
				+ "  exame.rowid exame_id,\n"
				+ "  exame.nm_exame exame_nome,\n"
				+ "funcionario.rowid funcionario_id, \n"
				+ "  funcionario.nm_funcionario funcionario_nome, \n"
				+ "  exame_realizado.data_exame \n"
				+ "FROM \n"
				+ "  exame_realizado \n"
				+ "  JOIN exame ON exame_realizado.rowid_exame = exame.rowid \n"
				+ "  JOIN funcionario ON exame_realizado.rowid_funcionario = funcionario.rowid;");
		try(
			Connection con = getConexao();
			PreparedStatement  ps = con.prepareStatement(query.toString());
			ResultSet rs = ps.executeQuery()){
			
			ExameRealizadoVo vo =  null;
			List<ExameRealizadoVo> examesRealizados = new ArrayList<>();
			while (rs.next()) {
				vo = new ExameRealizadoVo();
				vo.setRowid(rs.getString("id"));
				vo.getExameVo().setRowid(rs.getString("exame_id"));
				vo.getExameVo().setNome(rs.getString("exame_nome"));
				vo.getFuncionarioVo().setRowid(rs.getString("funcionario_id"));
				vo.getFuncionarioVo().setNome(rs.getString("funcionario_nome"));
			
				
				DateTimeFormatter formataEntrada = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		        DateTimeFormatter formataSaida = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
		        LocalDate dataParse = LocalDate.parse(rs.getString("data_exame"), formataEntrada);
		        String dataExame = dataParse.format(formataSaida);
				vo.setDataExame(dataExame);
			
				examesRealizados.add(vo);
			}
			return examesRealizados;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Collections.emptyList();
	}
	
	
	public ExameRealizadoVo findByCodigo(Integer codigo){
		StringBuilder query = new StringBuilder("SELECT\n"
				+ "exame_realizado.rowid id,\n"
				+ "exame.rowid exame_id,\n"
				+ "exame.nm_exame exame_nome,\n"
				+ "funcionario.rowid funcionario_id, \n"
				+ "funcionario.nm_funcionario funcionario_nome,\n"
				+ "exame_realizado.data_exame\n"
				+ "FROM\n"
				+ "exame_realizado\n"
				+ "JOIN exame ON exame_realizado.rowid_exame = exame.rowid\n"
				+ "JOIN funcionario ON exame_realizado.rowid_funcionario = funcionario.rowid\n"
				+ "WHERE exame_realizado.rowid = ?;");
		
		try(Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())){
			int i = 1;
			
			ps.setInt(i, codigo);
			
			try(ResultSet rs = ps.executeQuery()){
				ExameRealizadoVo vo =  null;
				
				while (rs.next()) {
					vo = new ExameRealizadoVo();
					vo.setRowid(rs.getString("id"));
					vo.getExameVo().setRowid(rs.getString("exame_id"));
					vo.getExameVo().setNome(rs.getString("exame_nome"));
					vo.getFuncionarioVo().setRowid(rs.getString("funcionario_id"));
					vo.getFuncionarioVo().setNome(rs.getString("funcionario_nome"));
					DateTimeFormatter formataEntrada = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			        DateTimeFormatter formataSaida = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
			        LocalDate dataParse = LocalDate.parse(rs.getString("data_exame"), formataEntrada);
			        String dataExame = dataParse.format(formataSaida);
					vo.setDataExame(dataExame);
				}
				return vo;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		return null;
	}
	
	public void UpdateByCodigo(Integer codigo,Integer codExame, Integer codFuncionario, String dataExame) {
		StringBuilder query = new StringBuilder("UPDATE exame_realizado\n"
				+ "SET  \n"
				+ "    rowid_exame = COALESCE(?, rowid_exame), \n"
				+ "    rowid_funcionario = COALESCE(?, rowid_funcionario), \n"
				+ "    data_exame = COALESCE(?, data_exame)\n"
				+ "WHERE rowid = ?;");
		try(
			Connection con = getConexao();
			PreparedStatement  ps = con.prepareStatement(query.toString())){
			
			int i=1;
			
			if(codExame == 0) {
				ps.setNull(i++, java.sql.Types.INTEGER);
			} else {
				ps.setInt(i++, codExame);	
			}
			if(codFuncionario == 0) {
				ps.setNull(i++, java.sql.Types.INTEGER);
			} else {
				ps.setInt(i++, codFuncionario);	
			}
			if(dataExame.isEmpty()) {
				ps.setDate(i++, null);
				
			} else {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate data = LocalDate.parse(dataExame, formatter);
				ps.setDate(i++, java.sql.Date.valueOf(data));
			}
			ps.setInt(i++, codigo);
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
}
	
	public void DeleteByCodigo(Integer codigo) {
		StringBuilder query = new StringBuilder("DELETE exame_realizado WHERE rowid = ?");
		try(
			Connection con = getConexao();
			PreparedStatement  ps = con.prepareStatement(query.toString())){
			
			//int i=1;
			ps.setInt(1, codigo);
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	////////AQUI ESTOU FAZENDO OS MÉTODOS PRO FILTRO
	// ID_EXAME, NOME_EXAME, ID_FUNCIONARIO, NOME_FUNCIONARIO E DATA EXAME REALIZADO

	public List<ExameRealizadoVo> findByCodigoExame(Integer codigo){
		StringBuilder query = new StringBuilder("SELECT\n"
				+ "exame_realizado.rowid id,\n"
				+ "exame.rowid exame_id,\n"
				+ "exame.nm_exame exame_nome,\n"
				+ "funcionario.rowid funcionario_id, \n"
				+ "funcionario.nm_funcionario funcionario_nome,\n"
				+ "exame_realizado.data_exame\n"
				+ "FROM\n"
				+ "exame_realizado\n"
				+ "JOIN exame ON exame_realizado.rowid_exame = exame.rowid\n"
				+ "JOIN funcionario ON exame_realizado.rowid_funcionario = funcionario.rowid\n"
				+ "WHERE exame.rowid = ?;");
		
		try(Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())){
			int i = 1;
			
			ps.setInt(i, codigo);
			
			try(ResultSet rs = ps.executeQuery()){
				ExameRealizadoVo vo =  null;
				List<ExameRealizadoVo> examesRealizados = new ArrayList<>();
				while (rs.next()) {
					vo = new ExameRealizadoVo();
					vo.setRowid(rs.getString("id"));
					vo.getExameVo().setRowid(rs.getString("exame_id"));
					vo.getExameVo().setNome(rs.getString("exame_nome"));
					vo.getFuncionarioVo().setRowid(rs.getString("funcionario_id"));
					vo.getFuncionarioVo().setNome(rs.getString("funcionario_nome"));
					DateTimeFormatter formataEntrada = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			        DateTimeFormatter formataSaida = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
			        LocalDate dataParse = LocalDate.parse(rs.getString("data_exame"), formataEntrada);
			        String dataExame = dataParse.format(formataSaida);
					vo.setDataExame(dataExame);
					examesRealizados.add(vo);
				}
				return examesRealizados;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		return null;
	}
	
	public List<ExameRealizadoVo> findByCodigoFuncionario(Integer codigo){
		StringBuilder query = new StringBuilder("SELECT\n"
				+ "exame_realizado.rowid id,\n"
				+ "exame.rowid exame_id,\n"
				+ "exame.nm_exame exame_nome,\n"
				+ "funcionario.rowid funcionario_id, \n"
				+ "funcionario.nm_funcionario funcionario_nome,\n"
				+ "exame_realizado.data_exame\n"
				+ "FROM\n"
				+ "exame_realizado\n"
				+ "JOIN exame ON exame_realizado.rowid_exame = exame.rowid\n"
				+ "JOIN funcionario ON exame_realizado.rowid_funcionario = funcionario.rowid\n"
				+ "WHERE funcionario.rowid = ?;");
		
		try(Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())){
			int i = 1;
			
			ps.setInt(i, codigo);
			
			try(ResultSet rs = ps.executeQuery()){
				ExameRealizadoVo vo =  null;
				List<ExameRealizadoVo> examesRealizados = new ArrayList<>();
				while (rs.next()) {
					vo = new ExameRealizadoVo();
					vo.setRowid(rs.getString("id"));
					vo.getExameVo().setRowid(rs.getString("exame_id"));
					vo.getExameVo().setNome(rs.getString("exame_nome"));
					vo.getFuncionarioVo().setRowid(rs.getString("funcionario_id"));
					vo.getFuncionarioVo().setNome(rs.getString("funcionario_nome"));
					DateTimeFormatter formataEntrada = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			        DateTimeFormatter formataSaida = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
			        LocalDate dataParse = LocalDate.parse(rs.getString("data_exame"), formataEntrada);
			        String dataExame = dataParse.format(formataSaida);
					vo.setDataExame(dataExame);
					examesRealizados.add(vo);
				}
				return examesRealizados;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		return null;
	}
	
	public List<ExameRealizadoVo> findByNomeExame(String exame){
		StringBuilder query = new StringBuilder("SELECT\n"
				+ "exame_realizado.rowid id,\n"
				+ "exame.rowid exame_id,\n"
				+ "exame.nm_exame exame_nome,\n"
				+ "funcionario.rowid funcionario_id, \n"
				+ "funcionario.nm_funcionario funcionario_nome,\n"
				+ "exame_realizado.data_exame\n"
				+ "FROM\n"
				+ "exame_realizado\n"
				+ "JOIN exame ON exame_realizado.rowid_exame = exame.rowid\n"
				+ "JOIN funcionario ON exame_realizado.rowid_funcionario = funcionario.rowid\n"
				+ "WHERE lower(exame.nm_exame) like lower(?)");
		
		try(Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())){
			int i = 1;
			
			ps.setString(i, "%"+exame+"%");
			
			try(ResultSet rs = ps.executeQuery()){
				ExameRealizadoVo vo =  null;
				List<ExameRealizadoVo> examesRealizados = new ArrayList<>();
				while (rs.next()) {
					vo = new ExameRealizadoVo();
					vo.setRowid(rs.getString("id"));
					vo.getExameVo().setRowid(rs.getString("exame_id"));
					vo.getExameVo().setNome(rs.getString("exame_nome"));
					vo.getFuncionarioVo().setRowid(rs.getString("funcionario_id"));
					vo.getFuncionarioVo().setNome(rs.getString("funcionario_nome"));
					DateTimeFormatter formataEntrada = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			        DateTimeFormatter formataSaida = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
			        LocalDate dataParse = LocalDate.parse(rs.getString("data_exame"), formataEntrada);
			        String dataExame = dataParse.format(formataSaida);
					vo.setDataExame(dataExame);
					examesRealizados.add(vo);
				}
				return examesRealizados;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		return null;
	}
	
	public List<ExameRealizadoVo> findByNomeFuncionario(String funcionario){
		StringBuilder query = new StringBuilder("SELECT\n"
				+ "exame_realizado.rowid id,\n"
				+ "exame.rowid exame_id,\n"
				+ "exame.nm_exame exame_nome,\n"
				+ "funcionario.rowid funcionario_id, \n"
				+ "funcionario.nm_funcionario funcionario_nome,\n"
				+ "exame_realizado.data_exame\n"
				+ "FROM\n"
				+ "exame_realizado\n"
				+ "JOIN exame ON exame_realizado.rowid_exame = exame.rowid\n"
				+ "JOIN funcionario ON exame_realizado.rowid_funcionario = funcionario.rowid\n"
				+ "WHERE lower(funcionario.nm_funcionario) like lower(?)");
		
		try(Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())){
			int i = 1;
			
			ps.setString(i, "%"+funcionario+"%");
			
			try(ResultSet rs = ps.executeQuery()){
				ExameRealizadoVo vo =  null;
				List<ExameRealizadoVo> examesRealizados = new ArrayList<>();
				while (rs.next()) {
					vo = new ExameRealizadoVo();
					vo.setRowid(rs.getString("id"));
					vo.getExameVo().setRowid(rs.getString("exame_id"));
					vo.getExameVo().setNome(rs.getString("exame_nome"));
					vo.getFuncionarioVo().setRowid(rs.getString("funcionario_id"));
					vo.getFuncionarioVo().setNome(rs.getString("funcionario_nome"));
					DateTimeFormatter formataEntrada = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			        DateTimeFormatter formataSaida = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
			        LocalDate dataParse = LocalDate.parse(rs.getString("data_exame"), formataEntrada);
			        String dataExame = dataParse.format(formataSaida);
					vo.setDataExame(dataExame);
					examesRealizados.add(vo);
				}
				return examesRealizados;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		return null;
	}
	
	
	
	public List<ExameRealizadoVo> findByDataExame(LocalDate data){
		StringBuilder query = new StringBuilder("SELECT\n"
				+ "exame_realizado.rowid id,\n"
				+ "exame.rowid exame_id,\n"
				+ "exame.nm_exame exame_nome,\n"
				+ "funcionario.rowid funcionario_id, \n"
				+ "funcionario.nm_funcionario funcionario_nome,\n"
				+ "exame_realizado.data_exame\n"
				+ "FROM\n"
				+ "exame_realizado\n"
				+ "JOIN exame ON exame_realizado.rowid_exame = exame.rowid\n"
				+ "JOIN funcionario ON exame_realizado.rowid_funcionario = funcionario.rowid\n"
				+ "WHERE exame_realizado.data_exame = ?;");
		
		try(Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())){
			int i = 1;
			
			
			ps.setDate(i, java.sql.Date.valueOf(data));
	
			try(ResultSet rs = ps.executeQuery()){
				ExameRealizadoVo vo =  null;
				List<ExameRealizadoVo> examesRealizados = new ArrayList<>();
				while (rs.next()) {
					vo = new ExameRealizadoVo();
					vo.setRowid(rs.getString("id"));
					vo.getExameVo().setRowid(rs.getString("exame_id"));
					vo.getExameVo().setNome(rs.getString("exame_nome"));
					vo.getFuncionarioVo().setRowid(rs.getString("funcionario_id"));
					vo.getFuncionarioVo().setNome(rs.getString("funcionario_nome"));
					DateTimeFormatter formataEntrada = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			        DateTimeFormatter formataSaida = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
			        LocalDate dataParse = LocalDate.parse(rs.getString("data_exame"), formataEntrada);
			        String dataExame = dataParse.format(formataSaida);
					vo.setDataExame(dataExame);
					examesRealizados.add(vo);
				}
				return examesRealizados;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		return null;
	}
	
	
	
	
	/////////////////////////////////////////////RELATÓRIOS (PROCURAR POR DATA, POR DIA, MES, ANO)
		
	
	public List<ExameRealizadoVo> findRelatoriosData(String dataInicial, String dataFinal){
		LocalDate unicaDataInserida = null;
		LocalDate dataInicialFormatada = null;
		LocalDate dataFinalFormatada = null;
		if(!dataInicial.isEmpty()) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			dataInicialFormatada = LocalDate.parse(dataInicial, formatter);	
		} //else {
		//	dataInicialFormatada = null;
		//}
		if(!dataFinal.isEmpty()){
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			dataFinalFormatada = LocalDate.parse(dataFinal, formatter);
		} //else {
		//	dataFinalFormatada = null;
		//}
		
		StringBuilder query = new StringBuilder("SELECT\n"
				+ "exame_realizado.rowid id,\n"
				+ "exame.rowid exame_id,\n"
				+ "exame.nm_exame exame_nome,\n"
				+ "funcionario.rowid funcionario_id, \n"
				+ "funcionario.nm_funcionario funcionario_nome,\n"
				+ "exame_realizado.data_exame\n"
				+ "FROM\n"
				+ "exame_realizado\n"
				+ "JOIN exame ON exame_realizado.rowid_exame = exame.rowid\n"
				+ "JOIN funcionario ON exame_realizado.rowid_funcionario = funcionario.rowid\n");
				if(dataFinalFormatada == null) {
					unicaDataInserida = dataInicialFormatada;
					query.append("WHERE exame_realizado.data_exame >= ?;");
				}else if(dataInicialFormatada == null) {
					unicaDataInserida = dataFinalFormatada;
					query.append("WHERE exame_realizado.data_exame <= ?;");
				} else {
					query.append("WHERE exame_realizado.data_exame BETWEEN ? AND ?;");
				}
			
				//+ "WHERE exame_realizado.data_exame >= '2020-10-12';");
		try(Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())){
			int i = 1;
			
				if(unicaDataInserida == null) {
					ps.setDate(1, java.sql.Date.valueOf(dataInicialFormatada));
					ps.setDate(2, java.sql.Date.valueOf(dataFinalFormatada));
				} else {
					ps.setDate(1, java.sql.Date.valueOf(unicaDataInserida));
				}
	
			try(ResultSet rs = ps.executeQuery()){
				ExameRealizadoVo vo =  null;
				List<ExameRealizadoVo> examesRealizados = new ArrayList<>();
				while (rs.next()) {
					vo = new ExameRealizadoVo();
					vo.setRowid(rs.getString("id"));
					vo.getExameVo().setRowid(rs.getString("exame_id"));
					vo.getExameVo().setNome(rs.getString("exame_nome"));
					vo.getFuncionarioVo().setRowid(rs.getString("funcionario_id"));
					vo.getFuncionarioVo().setNome(rs.getString("funcionario_nome"));
					DateTimeFormatter formataEntrada = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			        DateTimeFormatter formataSaida = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
			        LocalDate dataParse = LocalDate.parse(rs.getString("data_exame"), formataEntrada);
			        String dataExame = dataParse.format(formataSaida);
					vo.setDataExame(dataExame);
					examesRealizados.add(vo);
				}
				return examesRealizados;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		
		}		
		return null;
	}
	
	
	
	public List<ExameRealizadoVo> findRelatoriosDia(String dataInicial, String dataFinal){
		Integer dataInicialFormatada = null;
		Integer dataFinalFormatada = null;
		if(!dataInicial.isEmpty()) {
			dataInicialFormatada = Integer.parseInt(dataInicial);			
		}
		if(!dataFinal.isEmpty()) {
			dataFinalFormatada = Integer.parseInt(dataFinal);			
		}
		Integer unicaDataInserida = null;
		StringBuilder query = new StringBuilder("SELECT\n"
				+ "exame_realizado.rowid id,\n"
				+ "exame.rowid exame_id,\n"
				+ "exame.nm_exame exame_nome,\n"
				+ "funcionario.rowid funcionario_id, \n"
				+ "funcionario.nm_funcionario funcionario_nome,\n"
				+ "exame_realizado.data_exame\n"
				+ "FROM\n"
				+ "exame_realizado\n"
				+ "JOIN exame ON exame_realizado.rowid_exame = exame.rowid\n"
				+ "JOIN funcionario ON exame_realizado.rowid_funcionario = funcionario.rowid\n");
				if(dataFinalFormatada == null) {
					unicaDataInserida = dataInicialFormatada;
					query.append("WHERE DAY(exame_realizado.data_exame) >= ?;");
				}else if(dataInicialFormatada == null) {
					unicaDataInserida = dataFinalFormatada;
					query.append("WHERE DAY(exame_realizado.data_exame) <= ?;");
				} else {
					query.append("WHERE DAY(exame_realizado.data_exame) BETWEEN ? AND ?;");
				}
			
				//+ "WHERE exame_realizado.data_exame >= '2020-10-12';");
		try(Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())){
			int i = 1;
			
				if(unicaDataInserida == null) {
					ps.setInt(1, dataInicialFormatada);
					ps.setInt(2, dataFinalFormatada);
				} else {
					ps.setInt(1, (unicaDataInserida));
				}
	
			try(ResultSet rs = ps.executeQuery()){
				ExameRealizadoVo vo =  null;
				List<ExameRealizadoVo> examesRealizados = new ArrayList<>();
				while (rs.next()) {
					vo = new ExameRealizadoVo();
					vo.setRowid(rs.getString("id"));
					vo.getExameVo().setRowid(rs.getString("exame_id"));
					vo.getExameVo().setNome(rs.getString("exame_nome"));
					vo.getFuncionarioVo().setRowid(rs.getString("funcionario_id"));
					vo.getFuncionarioVo().setNome(rs.getString("funcionario_nome"));
					DateTimeFormatter formataEntrada = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			        DateTimeFormatter formataSaida = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
			        LocalDate dataParse = LocalDate.parse(rs.getString("data_exame"), formataEntrada);
			        String dataExame = dataParse.format(formataSaida);
					vo.setDataExame(dataExame);
					examesRealizados.add(vo);
				}
				return examesRealizados;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		
		}		
		return null;
	}
	
	
	
	
	
	
	
	
}
