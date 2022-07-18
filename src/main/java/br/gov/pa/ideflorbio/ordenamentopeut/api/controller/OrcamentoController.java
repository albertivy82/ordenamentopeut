package br.gov.pa.ideflorbio.ordenamentopeut.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Orcamento;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.repository.OrcamentoRepository;

@RestController
@RequestMapping("/orcamento")
public class OrcamentoController {
	
	@Autowired
	private OrcamentoRepository orcamento;
	
	@GetMapping
	public List<Orcamento> listar(){
		return orcamento.listar();
	}

}
