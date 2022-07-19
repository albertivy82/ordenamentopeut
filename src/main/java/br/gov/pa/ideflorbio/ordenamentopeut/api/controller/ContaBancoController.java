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
		return contasBancarias.listar();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ContaBancaria> apagar(@PathVariable Long id){
		try {
			bancos.excluir(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}catch(DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}catch(EmptyResultDataAccessException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

}
