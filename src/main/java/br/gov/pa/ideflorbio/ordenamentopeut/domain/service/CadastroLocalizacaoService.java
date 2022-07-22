package br.gov.pa.ideflorbio.ordenamentopeut.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.EntidadeEmUsoException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.EntidadeNaoEncontradaException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Localizacao;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Processo;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.repository.LocalizacaoRepository;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.repository.ProcessoRepository;

@Service 
public class CadastroLocalizacaoService {
	
	@Autowired
	private LocalizacaoRepository localizacoes;
	
	@Autowired
	private ProcessoRepository processos;
	
	public Localizacao salvar(Localizacao localizacao) {
		Processo processo = processos.
				findById(localizacao.getProcesso().getId()).
				orElseThrow(()-> new EntidadeNaoEncontradaException("O processo informado não existe"));
		localizacao.setProcesso(processo);
		return localizacoes.save(localizacao);
	}
	
	public void remover(Long id) {
		try {
			localizacoes.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.
					format("Beneficiario de código %d não pode ser removido, pois está em uso", id));
		}catch(EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.
					format("Beneficiario de código %d não existe", id));
		}
	}

}
