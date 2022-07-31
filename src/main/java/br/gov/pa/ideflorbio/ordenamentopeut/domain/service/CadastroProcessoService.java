package br.gov.pa.ideflorbio.ordenamentopeut.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.EntidadeNaoEncontradaException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.LocalizacaoNaoEncontradaException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.ProcessoNaoEncontradoException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Beneficiario;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Localizacao;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Processo;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.repository.ProcessoRepository;

@Service
public class CadastroProcessoService {
	
	@Autowired
	private ProcessoRepository processos;
	
	@Autowired
	private CadastroBeneficiarioService beneficiarios;
	
	@Autowired
	private CadastroLocalizacaoService localizacoes;
	
	
	//-----MÉTODOS------//
	//1_____________________________________________________
	@Transactional
	public Processo salvar(Processo processo) {
		
		
		Beneficiario beneficiarioProcurado = beneficiarios.
				localizarEntidade(processo.getBeneficiario().getId());
		
		Localizacao localizacaoProcurada = localizacoes.
				localizarEntidade(processo.getLocalizacao().getId());
		
		
		processo.setBeneficiario(beneficiarioProcurado);
		processo.setLocalizacao(localizacaoProcurada);
		return processos.save(processo);
	}
	//2___________________________________________________
	public Processo localizarEntidade(Long id) {
		return processos.findById(id).
				orElseThrow(()-> new ProcessoNaoEncontradoException(id));
	}
	//3___________________________________________________
	@Transactional
	public void remover(Long id) {
		
		try {
		processos.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new LocalizacaoNaoEncontradaException(id);
		}catch(EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format("Processo de código %d não existe", id));
		}
	}
	

}
