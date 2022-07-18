package br.gov.pa.ideflorbio.ordenamentopeut.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Indenizacao;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.repository.IndenizacaoRepository;

@RestController
@RequestMapping("/indenizacoes")
public class IndenizacaoController {
	
	@Autowired
	private IndenizacaoRepository indenizacoes;
	
	@GetMapping
	public List<Indenizacao> listar(){
		return indenizacoes.listar();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Indenizacao> buscar(@PathVariable Long id){
		
		Indenizacao indenizacao = indenizacoes.buscar(id);
		if(indenizacao!=null) {
		return ResponseEntity.status(HttpStatus.OK).body(indenizacao);
		}
	
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

}
