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

import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Orcamento;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.repository.OrcamentoRepository;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.service.CadastroOrcamentoService;

@RestController
@RequestMapping("/orcamento")
public class OrcamentoController {
	
	@Autowired
	private OrcamentoRepository orcamento;
	
	@Autowired
	private CadastroOrcamentoService cadastroOrcamento;
	
	@GetMapping
	public List<Orcamento> listar(){
		return orcamento.findAll();
	}
	

	
	@DeleteMapping
	public ResponseEntity<Orcamento> apagar(@PathVariable Long id){
		try {
			cadastroOrcamento.remover(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}catch(DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}catch(EmptyResultDataAccessException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

}
