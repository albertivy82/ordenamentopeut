package br.gov.pa.ideflorbio.ordenamentopeut.infraestructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import br.gov.pa.ideflorbio.ordenamentopeut.domain.repository.IndenizacaoRepository;
import br.gov.pa.ideflorbio.ordenamentopeut.model.Indenizacao;


@Component
public class IndenizacaoRepositoryImpl implements IndenizacaoRepository{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Indenizacao> listar() {
		return manager.createQuery("from Indenizacao", Indenizacao.class).getResultList();
	}

	@Override
	public Indenizacao buscar(Long id) {
		return manager.find(Indenizacao.class, id);
	}

	@Transactional
	@Override
	public Indenizacao salvar(Indenizacao indenizacao) {
		return manager.merge(indenizacao);
	}

	@Override
	public void remover(Indenizacao indenizacao) {
		indenizacao = buscar(indenizacao.getId());
		manager.remove(indenizacao);
		
	}

}
