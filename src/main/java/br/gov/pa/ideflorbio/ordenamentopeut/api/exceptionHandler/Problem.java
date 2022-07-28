package br.gov.pa.ideflorbio.ordenamentopeut.api.exceptionHandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;



@JsonInclude(Include.NON_NULL)
@Builder
@Getter
public class Problem {
	
	private String type;
	private String title;
	private String detail;
	private Integer status;
	
	

}
