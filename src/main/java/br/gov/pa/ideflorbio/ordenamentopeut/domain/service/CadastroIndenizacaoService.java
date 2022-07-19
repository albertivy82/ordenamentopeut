package br.gov.pa.ideflorbio.ordenamentopeut.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.EntidadeEmUsoException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.EntidadeNaoEncontradaException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Indenizacao;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.repository.IndenizacaoRepository;

@Service
public class CadastroIndenizacaoService {
	
	@Autowired
	public IndenizacaoRepository indenizacoes;
	
	public Indenizacao salvar(Indenizacao indenizacao) {
		return indenizacoes.salvar(indenizacao);
	}
	
	
	public void remover(Long id) {
		
		try {
		indenizacoes.remover(id);
		}catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.
					format("Indenizacao de código % não pode ser removido, pois está em uso", id));
		}catch(EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.
					format("Indenizacao de código % não existe", id));
		}
	}

}
