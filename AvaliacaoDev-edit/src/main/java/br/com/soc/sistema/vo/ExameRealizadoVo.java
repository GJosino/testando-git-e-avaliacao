package br.com.soc.sistema.vo;

import java.time.LocalDate;

public class ExameRealizadoVo {
	private String rowid;
	private ExameVo exameVo = new ExameVo();
	private FuncionarioVo funcionarioVo = new FuncionarioVo();
	private String dataExame;

	
	public ExameRealizadoVo() {}
	
	public ExameRealizadoVo(String rowid, ExameVo exameVo, FuncionarioVo funcionarioVo, String dataExame) {
		this.rowid = rowid;
		this.exameVo = exameVo;
		this.funcionarioVo = funcionarioVo;
		this.dataExame = dataExame;
	}

	public String getRowid() {
		return rowid;
	}

	public void setRowid(String rowid) {
		this.rowid = rowid;
	}

	public ExameVo getExameVo() {
		return exameVo;
	}

	public void setExameVo(ExameVo exameVo) {
		this.exameVo = exameVo;
	}

	public FuncionarioVo getFuncionarioVo() {
		return funcionarioVo;
	}

	public void setFuncionarioVo(FuncionarioVo funcionarioVo) {
		this.funcionarioVo = funcionarioVo;
	}

	public String getDataExame() {
		return dataExame;
	}

	public void setDataExame(String dataExame) {
		this.dataExame = dataExame;
	}

	@Override
	public String toString() {
		return "ExameRealizadoVo [rowid=" + rowid + ", exameVo=" + exameVo + ", funcionarioVo=" + funcionarioVo
				+ ", dataExame=" + dataExame + "]";
	}
	
	
	
}
