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
import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Localizacao;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.repository.LocalizacaoRepository;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.service.CadastroLocalizacaoService;

@RestController
@RequestMapping("/localizacoes")
public class LocalizacaoController {
	
	@Autowired
	private LocalizacaoRepository localizacoes;
	
	@Autowired
	private CadastroLocalizacaoService cadastroLocalizacao;
	
	@GetMapping
	private List<Localizacao> listar(){
		return localizacoes.findAll();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	private Localizacao buscar(@PathVariable Long id){
		return cadastroLocalizacao.localizarEntidade(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Localizacao adicionar(@RequestBody Localizacao localizacao){
		return cadastroLocalizacao.salvar(localizacao);
		
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Localizacao atualizar(@PathVariable Long id, @RequestBody Localizacao localizacao){
		
		 cadastroLocalizacao.localizarEntidade(id);
		 BeanUtils.copyProperties(localizacao, localizacaoEnviada.get(), "id");
		 Localizacao localizacaoSalva = cadastroLocalizacao.salvar(localizacaoEnviada.get());
		 return ResponseEntity.status(HttpStatus.OK).body(localizacaoSalva);
		
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	
	
	@DeleteMapping("/{id}")
	private ResponseEntity<Localizacao> apagar(@PathVariable Long id){
		try {
			cadastroLocalizacao.remover(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}catch(EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}catch(EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	

}
