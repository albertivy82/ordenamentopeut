package br.gov.pa.ideflorbio.ordenamentopeut.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
		
		Optional<Processo> processo = processos.findById(id);
		
		if(processo.isPresent()) {
		return ResponseEntity.status(HttpStatus.OK).body(processo.get());
		}
	
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Processo processo) {
		
		try{
			cadastroProcesso.salvar(processo);
			return ResponseEntity.status(HttpStatus.CREATED).body(processo);
		}catch (EntidadeNaoEncontradaException e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Processo processo){
		 Optional<Processo> processoProcurado = processos.findById(id);
		 
		 if(processoProcurado.isPresent()) {
			 BeanUtils.copyProperties(processo, processoProcurado.get(), "id");
			 Processo processoNovo = cadastroProcesso.salvar(processoProcurado.get());
			 return ResponseEntity.status(HttpStatus.OK).body(processoNovo);
		 }
		 return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
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
