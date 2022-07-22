package br.gov.pa.ideflorbio.ordenamentopeut.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.EntidadeNaoEncontradaException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Indenizacao;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.repository.IndenizacaoRepository;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.service.CadastroIndenizacaoService;

@RestController
@RequestMapping("/indenizacoes")
public class IndenizacaoController {
	
	@Autowired
	private IndenizacaoRepository indenizacoes;
	
	@Autowired
	private CadastroIndenizacaoService cadastroIndenizacao;
	
	@GetMapping
	public List<Indenizacao> listar(){
		return indenizacoes.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Indenizacao> buscar(@PathVariable Long id){
		
		Indenizacao indenizacao = indenizacoes.getReferenceById(id);
		if(indenizacao!=null) {
		return ResponseEntity.status(HttpStatus.OK).body(indenizacao);
		}
	
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Indenizacao indenizacao){
		try {
			Indenizacao indenizacaoCadastrada = cadastroIndenizacao.salvar(indenizacao);
			return ResponseEntity.status(HttpStatus.CREATED).body(indenizacaoCadastrada);
		}catch(EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Indenizacao>apagar(@PathVariable Long id ){
		try {
			cadastroIndenizacao.remover(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}catch(DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}catch(EmptyResultDataAccessException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

}
