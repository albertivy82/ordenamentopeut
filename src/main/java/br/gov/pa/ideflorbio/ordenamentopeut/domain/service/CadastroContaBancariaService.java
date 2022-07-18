package br.gov.pa.ideflorbio.ordenamentopeut.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.ContaBancaria;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.repository.ContaBancariaRepository;

@Service
public class CadastroContaBancariaService {
	
	@Autowired
	private ContaBancariaRepository contasBancarias;
	
	public ContaBancaria salvar(ContaBancaria contaBnacaria) {
		return contasBancarias.salvar(contaBnacaria);
	}

}
