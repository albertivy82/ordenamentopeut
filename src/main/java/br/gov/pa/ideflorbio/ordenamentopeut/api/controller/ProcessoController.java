package br.gov.pa.ideflorbio.ordenamentopeut.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.EntidadeEmUsoException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.EntidadeNaoEncontradaException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Processo;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.repository.ProcessoRepository;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.service.CadastroProcessoService;



@RestController
@RequestMapping("/processos")
public class ProcessoController {
	
	@Autowired
	private ProcessoRepository processos;
	
	@Autowired
	private CadastroProcessoService cadastroProcesso;
	
	@GetMapping
	public List<Processo> listar(){
		return processos.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Processo> Buscar(@PathVariable Long id){
		
		Processo processo = processos.getReferenceById(id);
		if(processo!=null) {
		return ResponseEntity.status(HttpStatus.OK).body(processo);
		}
	
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Processo adicionar(@RequestBody Processo processo) {
		return cadastroProcesso.salvar(processo);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Processo> excluir(@PathVariable Long id){
		try {
			cadastroProcesso.remover(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		}catch(EntidadeEmUsoException e){
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}catch(EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
			
	}

   
}
