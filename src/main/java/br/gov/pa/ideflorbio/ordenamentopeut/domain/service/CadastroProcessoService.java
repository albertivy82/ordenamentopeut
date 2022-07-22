package br.gov.pa.ideflorbio.ordenamentopeut.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.EntidadeEmUsoException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.EntidadeNaoEncontradaException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Beneficiario;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Processo;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.repository.BeneficiarioRepository;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.repository.ProcessoRepository;

@Service
public class CadastroProcessoService {
	
	@Autowired
	private ProcessoRepository processos;
	
	@Autowired
	private BeneficiarioRepository beneficiarios;
	
	
	//-----MÉTODOS------//
	
	public Processo salvar(Processo processo) {
		Beneficiario beneficiarioProcurado = beneficiarios.findById(processo.getBeneficiario().getId()).
				orElseThrow(()-> new EntidadeNaoEncontradaException("O beneficiario informado não existe"));
		
		processo.setBeneficiario(beneficiarioProcurado);
		return processos.save(processo);
	}
	
	public void remover(Long id) {
		
		try {
		processos.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new EntidadeNaoEncontradaException(String.
					format("Processo de código %d de código % não existe", id));
		}catch(EmptyResultDataAccessException e) {
			throw new EntidadeEmUsoException(String.
					format("Processo de código %d não pode ser removido, pois está em uso", id));
		}
	}
	

}
