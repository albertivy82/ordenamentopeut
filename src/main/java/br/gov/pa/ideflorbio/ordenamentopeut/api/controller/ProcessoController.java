package br.gov.pa.ideflorbio.ordenamentopeut.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.pa.ideflorbio.ordenamentopeut.domain.repository.ProcessoRepository;
import br.gov.pa.ideflorbio.ordenamentopeut.model.Processo;



@RestController
@RequestMapping("/processos")
public class ProcessoController {
	
	@Autowired
	private ProcessoRepository processos;
	
	@GetMapping
	public List<Processo> listar(){
		
		return processos.listar();
		
	}

}
