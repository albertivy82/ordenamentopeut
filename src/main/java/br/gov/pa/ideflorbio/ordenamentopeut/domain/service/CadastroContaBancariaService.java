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
	
	private static final String ENTIDADE_EM_USO = "Conta Bancaia de código %d não pode ser removida, pois está em uso";

	private static final String ENTIDADE_NAO_ENONTRDA = "Conta Bancaria de código %d não existe";
//-------------------------------------------------------------------------------------------------//
	@Autowired
	private ContaBancariaRepository contasBancarias;
	
	@Autowired
	private CadastroBeneficiarioService beneficiario;
	
//-------------------------------------------------------------------------------------------------//	
	
	public ContaBancaria salvar(ContaBancaria contaBancaria) {
		
		Beneficiario beneficiarioPesquisado = beneficiario.
				localizarEntidade(contaBancaria.getBeneficiario().getId());
				     
		contaBancaria.setBeneficiario(beneficiarioPesquisado);
				
		return contasBancarias.save(contaBancaria);
	}
//**********************************************************************************************//	
	public void excluir(Long id) {
		try {
			contasBancarias.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.
					format(ENTIDADE_NAO_ENONTRDA, id));
		}catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.
					format(ENTIDADE_EM_USO, id));
		}
	}
//************************************************************************************************//
	
	public ContaBancaria localizarEntidade(Long id) {
		ContaBancaria contaLocalizada = contasBancarias.findById(id).orElseThrow(()-> new EntidadeNaoEncontradaException(String.
				format(ENTIDADE_NAO_ENONTRDA, id)));
		return contaLocalizada;
	}
}
