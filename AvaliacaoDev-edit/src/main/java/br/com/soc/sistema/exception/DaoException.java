package br.com.soc.sistema.exception;

public class DaoException extends RuntimeException{
	public DaoException(String mensagem) {
		super(mensagem);
	}
}
