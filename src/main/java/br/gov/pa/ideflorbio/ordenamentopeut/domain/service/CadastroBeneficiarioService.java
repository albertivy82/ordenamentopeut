package br.gov.pa.ideflorbio.ordenamentopeut.domain.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.EntidadeEmUsoException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.EntidadeNaoEncontradaException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Beneficiario;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.repository.BeneficiarioRepository;

@Service
public class CadastroBeneficiarioService {

	@Autowired
	private BeneficiarioRepository beneficiarios;
	
	//------MÉTODOS------//
	
	public Beneficiario salvar(Beneficiario beneficiario) {
		return beneficiarios.save(beneficiario);
	}
	
	public void remover(Long id) {
		try {	
			beneficiarios.deleteById(id);
		}catch(EmptyResultDataAccessException e){
			throw new EntidadeNaoEncontradaException(String.
					format("Beneficiario de código %d não existe", id));
		}catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.
					format("Beneficiario de código %d não pode ser removido, pois está em uso", id));
		}
	}
}
