package br.gov.pa.ideflorbio.ordenamentopeut.infraestructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Indenizacao;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.repository.IndenizacaoRepository;


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
	public void remover(Long id) {
		
	
		Indenizacao indenizacao = buscar(id);
		    if(indenizacao == null)	{
		    	throw new EmptyResultDataAccessException(1);
		    }
		manager.remove(indenizacao);
		
	}

}
