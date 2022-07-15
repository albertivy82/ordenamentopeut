package br.gov.pa.ideflorbio.ordenamentopeut.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.pa.ideflorbio.ordenamentopeut.domain.repository.BeneficiarioRepository;
import br.gov.pa.ideflorbio.ordenamentopeut.model.Beneficiario;

@RestController
@RequestMapping("/beneficiarios")
public class BeneficiarioController {
	
	@Autowired
	private BeneficiarioRepository beneficiarios;
	
	@GetMapping
	public List<Beneficiario> listar(){
		return beneficiarios.listar();
	}

}
