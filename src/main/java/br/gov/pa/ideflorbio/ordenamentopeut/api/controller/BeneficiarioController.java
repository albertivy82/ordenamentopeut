package br.gov.pa.ideflorbio.ordenamentopeut.api.controller;

import java.util.List;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Beneficiario;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.repository.BeneficiarioRepository;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.service.CadastroBeneficiarioService;

@RestController
@RequestMapping("/beneficiarios")
public class BeneficiarioController {
	
	@Autowired
	private BeneficiarioRepository beneficiarios;
	
	@Autowired
	private CadastroBeneficiarioService cadastroBeneficiario;
	
	
	//----MÉTODOS------//
	
	
	@GetMapping
	public List<Beneficiario> listar(){
		return beneficiarios.findAll();
		 
	}
	
	
	@GetMapping("/{id}")
	public Beneficiario buscar(@PathVariable Long id){
		return cadastroBeneficiario.localizarEntidade(id);
		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Beneficiario adicionar(@RequestBody Beneficiario beneficiario) {
		return beneficiarios.save(beneficiario);
	}
	
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Beneficiario atualizar(@PathVariable Long id, @RequestBody Beneficiario beneficiario) {
		
		Beneficiario beneficiarioAtual = cadastroBeneficiario.localizarEntidade(id);
			BeanUtils.copyProperties(beneficiario, beneficiarioAtual, "id");
		return cadastroBeneficiario.salvar(beneficiarioAtual);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id){
		cadastroBeneficiario.remover(id);
	}

}
