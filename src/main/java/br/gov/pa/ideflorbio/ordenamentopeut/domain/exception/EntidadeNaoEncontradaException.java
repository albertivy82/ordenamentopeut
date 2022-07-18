package br.gov.pa.ideflorbio.ordenamentopeut.domain.exception;

public class EntidadeNaoEncontradaException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public EntidadeNaoEncontradaException(String message) {
		super(message);
	}

}
