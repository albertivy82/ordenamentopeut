package br.gov.pa.ideflorbio.ordenamentopeut.domain.service;

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
	
	public Beneficiario salvar(Beneficiario beneficiario) {
		return beneficiarios.salvar(beneficiario);
	}
	
	public void remover(Long id) {
		try {	
			beneficiarios.remover(id);
		}catch(EmptyResultDataAccessException e){
			throw new EntidadeNaoEncontradaException(String.
					format("Beneficiario de código % não existe", id));
		}catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.
					format("Beneficiario de código % não pode ser removido, pois está em uso", id));
		}
	}
}
