package br.gov.pa.ideflorbio.ordenamentopeut.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.EntidadeEmUsoException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Processo;
import br.gov.pa.ideflorbio.ordenamentopeut.infraestructure.repository.ProcessoRepositoryImpl;

public class CadastroProcessoService {
	
	@Autowired
	private ProcessoRepositoryImpl processos;
	
	public Processo salvar(Processo pocesso) {
		return processos.salvar(pocesso);
	}
	
	public void remover(Long id) {
		
		try {
		processos.remover(id);
		}catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.
					format("Beneficiario de código % não pode ser removida, pois está em uso", id));
		}catch(EmptyResultDataAccessException e) {
			throw new EntidadeEmUsoException(String.
					format("Beneficiario de código % não pode ser removida, pois está em uso", id));
		}
	}
	

}
