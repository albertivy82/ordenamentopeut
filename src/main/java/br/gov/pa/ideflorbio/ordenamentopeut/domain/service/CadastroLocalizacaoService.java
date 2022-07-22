package br.gov.pa.ideflorbio.ordenamentopeut.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.EntidadeEmUsoException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.EntidadeNaoEncontradaException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Localizacao;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.repository.LocalizacaoRepository;

@Service 
public class CadastroLocalizacaoService {
	
	@Autowired
	private LocalizacaoRepository localizacoes;
	
	public Localizacao salvar(Localizacao localizacao) {
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
