package br.gov.pa.ideflorbio.ordenamentopeut.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.ContaBancaria;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.repository.ContaBancariaRepository;

@RestController
@RequestMapping("/contasbancarias")
public class ContaBancoController {
	
	@Autowired
	private ContaBancariaRepository contasBancarias;
	
	@GetMapping
	public List<ContaBancaria> listar(){
		return contasBancarias.listar();
	}

}
