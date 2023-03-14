package br.com.soc.sistema.dao.examesRealizados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.soc.sistema.dao.Dao;
import br.com.soc.sistema.vo.ExameRealizadoVo;

public class ExameRealizadoDao extends Dao {

	public void insertExameRealizado(ExameRealizadoVo exameRealizadoVo) {
		StringBuilder query = new StringBuilder("INSERT INTO funcionario (nm_funcionario) values (?)");
		try(
			Connection con = getConexao();
			PreparedStatement  ps = con.prepareStatement(query.toString())){
			
			int i=1;
	//		ps.setString(i++, exameRealizadoVo.getNome());
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
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
				vo.setDataExame(rs.getString("data_exame"));
			
				examesRealizados.add(vo);
			}
			return examesRealizados;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Collections.emptyList();
	}
	
	public List<ExameRealizadoVo> findAllByNome(String nome){
		StringBuilder query = new StringBuilder("SELECT rowid id, nm_funcionario nome FROM funcionario ")
								.append("WHERE lower(nm_funcionario) like lower(?)");
		
		try(Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())){
			int i = 1;
			
			ps.setString(i, "%"+nome+"%");
			
			try(ResultSet rs = ps.executeQuery()){
				ExameRealizadoVo vo =  null;
				List<ExameRealizadoVo> examesRealizados = new ArrayList<>();
				
				while (rs.next()) {
					vo = new ExameRealizadoVo();
					vo.setRowid(rs.getString("id"));
			//		vo.setNome(rs.getString("nome"));	
					
					examesRealizados.add(vo);
				}
				return examesRealizados;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		return Collections.emptyList();
	}
	public ExameRealizadoVo findByCodigo(Integer codigo){
		StringBuilder query = new StringBuilder("SELECT rowid id, nm_funcionario nome FROM funcionario ")
								.append("WHERE rowid = ?");
		
		try(Connection con = getConexao();
			PreparedStatement ps = con.prepareStatement(query.toString())){
			int i = 1;
			
			ps.setInt(i, codigo);
			
			try(ResultSet rs = ps.executeQuery()){
				ExameRealizadoVo vo =  null;
				
				while (rs.next()) {
					vo = new ExameRealizadoVo();
					vo.setRowid(rs.getString("id"));
		//			vo.setNome(rs.getString("nome"));	
				}
				return vo;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		return null;
	}
	
	public void UpdateByCodigo(Integer codigo, String novoValor) {
		StringBuilder query = new StringBuilder("UPDATE funcionario SET nm_funcionario = ? WHERE rowid = ?");
		try(
			Connection con = getConexao();
			PreparedStatement  ps = con.prepareStatement(query.toString())){
			
			int i=1;
			ps.setString(i++, novoValor);
			ps.setInt(i++, codigo);
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
}
	
	public void DeleteByCodigo(Integer codigo) {
		StringBuilder query = new StringBuilder("DELETE funcionario WHERE rowid = ?");
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
	
}
