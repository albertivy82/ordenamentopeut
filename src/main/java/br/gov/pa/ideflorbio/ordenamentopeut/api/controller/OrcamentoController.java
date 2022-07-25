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
import org.springframework.web.bind.annotation.RestController;

import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.EntidadeEmUsoException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.EntidadeNaoEncontradaException;
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
	
	@GetMapping("/{id}")
	public ResponseEntity<?> listar(@PathVariable Long id){
		Optional<Orcamento> orcamentoBuscado = orcamento.findById(id);
			if(orcamentoBuscado.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(orcamentoBuscado.get());
			}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Orcamento orcamento){
		cadastroOrcamento.salvar(orcamento);
		return ResponseEntity.status(HttpStatus.CREATED).body(orcamento); 
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Orcamento orcamentoEnviado){
		Optional<Orcamento> orcamentoProcurado = orcamento.findById(id);
			if(orcamentoProcurado.isPresent()) {
				BeanUtils.copyProperties(orcamentoEnviado, orcamentoProcurado.get(), "id");
				Orcamento OrcamentoNovo = cadastroOrcamento.salvar(orcamentoProcurado.get());
				return ResponseEntity.status(HttpStatus.OK).body(OrcamentoNovo); 
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Orcamento> apagar(@PathVariable Long id){
		try {
			cadastroOrcamento.remover(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}catch(EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}catch(EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

}
