package br.gov.pa.ideflorbio.ordenamentopeut.api.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.EntidadeEmUsoException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.EntidadeNaoEncontradaException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.ContaBancaria;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.repository.ContaBancariaRepository;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.service.CadastroContaBancariaService;

@RestController
@RequestMapping("/contasbancarias")
public class ContaBancoController {
	
	@Autowired
	private ContaBancariaRepository contasBancarias;
	
	@Autowired
	private CadastroContaBancariaService bancos;
	
	@GetMapping
	public List<ContaBancaria> listar(){
		return contasBancarias.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ContaBancaria> buscar(@PathVariable Long id) {
		ContaBancaria conta = contasBancarias.getReferenceById(id);
			if(conta!=null) {
				return ResponseEntity.status(HttpStatus.OK).body(conta);
			}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();	
	}
	
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody ContaBancaria contaBancaria){
		try {
		bancos.salvar(contaBancaria);
			return ResponseEntity.status(HttpStatus.CREATED).body(contaBancaria);
		}catch(EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	//@PutMapping("/{id}")
	//public ResponseEntity<?>atualizar(@PatVariable Long id, @RequestBody )
		
	@DeleteMapping("/{id}")
	public ResponseEntity<ContaBancaria> apagar(@PathVariable Long id){
		try {
			bancos.excluir(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}catch(EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}catch(EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

}
