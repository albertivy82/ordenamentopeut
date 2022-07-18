package br.gov.pa.ideflorbio.ordenamentopeut.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Beneficiario;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.repository.BeneficiarioRepository;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.service.CadastroBeneficiarioService;

@RestController
@RequestMapping("/beneficiarios")
public class BeneficiarioController {
	
	@Autowired
	private BeneficiarioRepository beneficiarios;
	
	@Autowired
	private CadastroBeneficiarioService cadastroBneficiario;
	
	@GetMapping
	public List<Beneficiario> listar(){
		return beneficiarios.listar();
		 
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Beneficiario> buscar(@PathVariable("id") Long id){
		Beneficiario beneficiario = beneficiarios.buscar(id);
		
		if(beneficiario!=null) {
			return ResponseEntity.status(HttpStatus.OK).body(beneficiario);	
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Beneficiario adicionar(@RequestBody Beneficiario beneficiario) {
		return cadastroBneficiario.salvar(beneficiario);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Beneficiario> atualizar(@PathVariable Long id, @RequestBody Beneficiario beneficiario) {
		Beneficiario beneficiarioAtual = beneficiarios.buscar(id);
		
		if(beneficiarioAtual!=null) {
			
			BeanUtils.copyProperties(beneficiario, beneficiarioAtual, "id");
			beneficiarioAtual = cadastroBneficiario.salvar(beneficiarioAtual);
			return ResponseEntity.status(HttpStatus.OK).body(beneficiarioAtual);
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Beneficiario> apagar(@PathVariable Long id){
		try {
		cadastroBneficiario.remover(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}catch(EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}catch(EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

}
