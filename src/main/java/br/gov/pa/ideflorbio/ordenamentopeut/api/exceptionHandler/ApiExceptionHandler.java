package br.gov.pa.ideflorbio.ordenamentopeut.api.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.EntidadeNaoEncontradaException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	public ResponseEntity<?> handleEntidadeNaoEncontradaExceptio(
			EntidadeNaoEncontradaException ex, WebRequest request){
		
		HttpStatus satus = HttpStatus.NOT_FOUND;
		ProblemType problemType = ProblemType.ENTIDADE_NAO_ENCONTRADA;
		String detail =
		Problem r = new Problem();
		r.
	}
	
}
