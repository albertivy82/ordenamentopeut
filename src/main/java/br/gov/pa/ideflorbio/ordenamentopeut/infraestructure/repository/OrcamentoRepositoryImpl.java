package br.gov.pa.ideflorbio.ordenamentopeut.infraestructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Orcamento;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.repository.OrcamentoRepository;


@Component
public class OrcamentoRepositoryImpl implements OrcamentoRepository{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Orcamento> listar() {
		return manager.createQuery("from Orcamento", Orcamento.class).getResultList();
	}

	@Override
	public Orcamento buscar(Long id) {
		return manager.find(Orcamento.class, id);
	}

	@Transactional
	@Override
	public Orcamento salvar(Orcamento orcamento) {
		return manager.merge(orcamento);
	}

	@Transactional
	@Override
	public void remover(Long id) {
			
		Orcamento orcamento = buscar(id);
		
		if(orcamento==null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		manager.remove(orcamento);
		
	}

}
