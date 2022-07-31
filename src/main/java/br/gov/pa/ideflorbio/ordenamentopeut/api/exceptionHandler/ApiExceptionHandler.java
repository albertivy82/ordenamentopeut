package br.gov.pa.ideflorbio.ordenamentopeut.api.exceptionHandler;

import org.hibernate.validator.internal.util.privilegedactions.IsClassPresent;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.EntidadeEmUsoException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.EntidadeNaoEncontradaException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	//1____________________________________________________
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex, WebRequest request){
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		ProblemType problemType = ProblemType.ENTIDADE_NAO_ENCONTRADA;
		String detail = ex.getMessage();
		
				Problem problem = createProblemBuilder(status, problemType, detail).build();
		
		
					return handleExceptionInternal(ex, problem , new HttpHeaders(), status , request);
		
	}
	//2_____________________________________________________
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> handleNegocioException(NegocioException ex, WebRequest request){
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ProblemType problemType = ProblemType.ERRO_NEGOCIO;
		String detail = ex.getMessage();
		
				Problem problem = createProblemBuilder(status, problemType, detail).build();
		
		
					return handleExceptionInternal(ex, problem , new HttpHeaders(), status , request);
	}
	
	//3____________________________________________________
	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException ex, WebRequest request){
		
		HttpStatus status = HttpStatus.CONFLICT;
		ProblemType problemType = ProblemType.ENTIDADE_EM_USO;
		String detail = ex.getMessage();
		
				Problem problem = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(),status, request);
		
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		if(body == null) {
			body = Problem.builder()
				.type("nada")
				.title(status.getReasonPhrase())
				.detail("nada")
				.status(status.value())
				.build();
		}else if(body instanceof String) {
			body = Problem.builder()
					.type("nada")
					.title("nada")
					.detail("nada")
					.status(1)
					.build();
		}
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	
	private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType problemType, String detail){
		return Problem.builder()
				.status(status.value())
				.type(problemType.getUri())
				.title(problemType.getTitle())
				.detail(detail);
		
	}
	
}
