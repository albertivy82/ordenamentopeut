package br.gov.pa.ideflorbio.ordenamentopeut.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.EntidadeEmUsoException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.EntidadeNaoEncontradaException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Beneficiario;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Indenizacao;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Orcamento;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Processo;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.repository.BeneficiarioRepository;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.repository.IndenizacaoRepository;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.repository.OrcamentoRepository;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.repository.ProcessoRepository;

@Service
public class CadastroIndenizacaoService {
	
	@Autowired
	public IndenizacaoRepository indenizacoes;
	
	@Autowired
	private BeneficiarioRepository pesquisarBenficiario;
	
	@Autowired
	private ProcessoRepository pesquisaProcesso;
	
	@Autowired
	private OrcamentoRepository pesquisaOrcamento;
	
	public Indenizacao salvar(Indenizacao indenizacao) {
		
		Beneficiario beneficiariPesquisado = pesquisarBenficiario.getReferenceById(indenizacao.getBeneficiario().getId());
		Processo processopesquisado = pesquisaProcesso.getReferenceById(indenizacao.getProcesso().getId());
		Orcamento orcamentoPesquisado = pesquisaOrcamento.getReferenceById(indenizacao.getOrcamento().getId());
		if(beneficiariPesquisado==null) {
			throw new EntidadeNaoEncontradaException("O beneficiario informado não existe");
		}
		if(processopesquisado==null) {
			throw new EntidadeNaoEncontradaException("O processo informado não existe");
		}
		if(orcamentoPesquisado==null) {
			throw new EntidadeNaoEncontradaException("O orçamento informado não existe");
		}
		
		indenizacao.setBeneficiario(beneficiariPesquisado);
		indenizacao.setProcesso(processopesquisado);
		indenizacao.setOrcamento(orcamentoPesquisado);
		
		return indenizacoes.save(indenizacao);
	}
	
	
	public void remover(Long id) {
		
		try {
		indenizacoes.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.
					format("Indenizacao de código %d não pode ser removido, pois está em uso", id));
		}catch(EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.
					format("Indenizacao de código %d não existe", id));
		}
	}

}
