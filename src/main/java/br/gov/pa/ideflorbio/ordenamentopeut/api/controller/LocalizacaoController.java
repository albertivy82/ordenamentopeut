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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
		return localizacoes.listar();
	}
	
	@DeleteMapping
	private ResponseEntity<Localizacao> apagar(@PathVariable Long id){
		try {
			cadastroLocalizacao.remover(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}catch(DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}catch(EmptyResultDataAccessException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	

}
