package br.gov.pa.ideflorbio.ordenamentopeut.infraestructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import br.gov.pa.ideflorbio.ordenamentopeut.domain.repository.OrcamentoRepository;
import br.gov.pa.ideflorbio.ordenamentopeut.model.Orcamento;


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
	public void remover(Orcamento orcamento) {
	orcamento = buscar(orcamento.getId());
		manager.remove(orcamento);
		
	}

}
