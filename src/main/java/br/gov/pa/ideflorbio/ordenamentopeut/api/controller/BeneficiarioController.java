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
	
	
	//----MÉTODOS------//
	
	@GetMapping
	public List<Beneficiario> listar(){
		return beneficiarios.findAll();
		 
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Beneficiario> buscar(@PathVariable Long id){
		
		Optional<Beneficiario> beneficiario = beneficiarios.findById(id);
			if(beneficiario.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(beneficiario.get());
			}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@PostMapping
	public ResponseEntity<Beneficiario> adicionar(@RequestBody Beneficiario beneficiario) {
		beneficiarios.save(beneficiario);
		return ResponseEntity.status(HttpStatus.CREATED).body(beneficiario);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Beneficiario> atualizar(@PathVariable Long id, @RequestBody Beneficiario beneficiario) {
		
		Optional<Beneficiario> beneficiarioAtual = beneficiarios.findById(id);
		
		if(beneficiarioAtual.isPresent()) {
			
			BeanUtils.copyProperties(beneficiario, beneficiarioAtual.get(), "id");
			Beneficiario beneficiarioSalvo = cadastroBneficiario.salvar(beneficiarioAtual.get());
			return ResponseEntity.status(HttpStatus.OK).body(beneficiarioSalvo);
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
