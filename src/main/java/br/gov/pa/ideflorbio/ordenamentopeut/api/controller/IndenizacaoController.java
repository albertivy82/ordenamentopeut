package br.gov.pa.ideflorbio.ordenamentopeut.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.EntidadeEmUsoException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.EntidadeNaoEncontradaException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Indenizacao;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Localizacao;
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
	@ResponseStatus(HttpStatus.OK)
	public Indenizacao buscar(@PathVariable Long id){
		return cadastroIndenizacao.localizarEntidade(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Indenizacao adicionar(@RequestBody Indenizacao indenizacao){
		return cadastroIndenizacao.salvar(indenizacao);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?>atualizar(@PathVariable Long id, @RequestBody Indenizacao indenizacao){
		Indenizacao indenizacaoEncontrada = indenizacoes.findById(id).orElse(null);
		try {	
				BeanUtils.copyProperties(indenizacao, indenizacaoEncontrada, "id");
				cadastroIndenizacao.salvar(indenizacaoEncontrada);
				return ResponseEntity.ok().body(indenizacaoEncontrada);
		}catch(EntidadeNaoEncontradaException e){	
				return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
		}
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Indenizacao>apagar(@PathVariable Long id ){
		try {
			cadastroIndenizacao.remover(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}catch(EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}catch(EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

}
