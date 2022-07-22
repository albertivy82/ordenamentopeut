package br.gov.pa.ideflorbio.ordenamentopeut.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.EntidadeEmUsoException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.EntidadeNaoEncontradaException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Beneficiario;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.ContaBancaria;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.repository.BeneficiarioRepository;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.repository.ContaBancariaRepository;

@Service
public class CadastroContaBancariaService {
	
	@Autowired
	private ContaBancariaRepository contasBancarias;
	
	@Autowired
	private BeneficiarioRepository pesquisarBeneficiario;
	
	
	
	public ContaBancaria salvar(ContaBancaria contaBancaria) {
		
		Beneficiario beneficiarioPesquisado = pesquisarBeneficiario.
				findById(contaBancaria.getBeneficiario().getId()).
				orElseThrow(() -> new EntidadeNaoEncontradaException("O beneficiário informado não existe"));
				     
		contaBancaria.setBeneficiario(beneficiarioPesquisado);
				
		return contasBancarias.save(contaBancaria);
	}
	
	public void excluir(Long id) {
		try {
			contasBancarias.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.
					format("Conta Bancaria de código %d não existe", id));
		}catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.
					format("Conta Bancaia de código %d não pode ser removida, pois está em uso", id));
		}
	}

}
