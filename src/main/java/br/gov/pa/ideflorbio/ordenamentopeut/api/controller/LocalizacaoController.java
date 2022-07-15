package br.gov.pa.ideflorbio.ordenamentopeut.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.pa.ideflorbio.ordenamentopeut.domain.repository.LocalizacaoRepository;
import br.gov.pa.ideflorbio.ordenamentopeut.model.Localizacao;

@RestController
@RequestMapping("/localizacoes")
public class LocalizacaoController {
	
	@Autowired
	private LocalizacaoRepository localizacoes;
	
	@GetMapping
	private List<Localizacao> listar(){
		return localizacoes.listar();
	}
	

}
