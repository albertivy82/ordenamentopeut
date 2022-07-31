package br.gov.pa.ideflorbio.ordenamentopeut.domain.exception;

public class OrcamentoNaoEncontradoException extends EntidadeNaoEncontradaException {
	
	private static final long serialVersionUID = 1L;

	public OrcamentoNaoEncontradoException(String message) {
		super(message);
	}
	
	public OrcamentoNaoEncontradoException(Long id) {
		this(String.format("Beneficiario de código %d não existe", id));
	}

}
