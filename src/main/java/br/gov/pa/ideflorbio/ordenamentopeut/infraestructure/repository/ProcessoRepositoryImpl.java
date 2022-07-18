package br.gov.pa.ideflorbio.ordenamentopeut.infraestructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Processo;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.repository.ProcessoRepository;


@Component
public class ProcessoRepositoryImpl implements ProcessoRepository{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Processo> listar() {
		return manager.createQuery("from Processo", Processo.class).getResultList();
	}

	@Override
	public Processo buscar(Long id) {
		return manager.find(Processo.class, id);
	}

	@Transactional
	@Override
	public Processo salvar(Processo processo) {
		return manager.merge(processo);
	}

	@Transactional
	@Override
	public void remover(Long id) {
		Processo processo = buscar(id);
		if(processo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		manager.remove(processo);
		
	}

}
