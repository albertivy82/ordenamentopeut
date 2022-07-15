package br.gov.pa.ideflorbio.ordenamentopeut.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.pa.ideflorbio.ordenamentopeut.domain.repository.IndenizacaoRepository;
import br.gov.pa.ideflorbio.ordenamentopeut.model.Indenizacao;

@RestController
@RequestMapping("/indenizacoes")
public class IndenizacaoController {
	
	@Autowired
	private IndenizacaoRepository indenizacoes;
	
	@GetMapping
	public List<Indenizacao> listar(){
		return indenizacoes.listar();
	}

}
