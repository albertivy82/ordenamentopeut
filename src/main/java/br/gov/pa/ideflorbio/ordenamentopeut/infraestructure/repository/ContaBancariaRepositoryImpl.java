package br.gov.pa.ideflorbio.ordenamentopeut.infraestructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import br.gov.pa.ideflorbio.ordenamentopeut.domain.repository.ContaBancariaRepository;
import br.gov.pa.ideflorbio.ordenamentopeut.model.ContaBancaria;


@Component
public class ContaBancariaRepositoryImpl implements ContaBancariaRepository{

	@PersistenceContext
	private EntityManager manager;
	
	
	@Override
	public List<ContaBancaria> listar() {
		
		return manager.createQuery("from ContaBancaria", ContaBancaria.class).getResultList();
	}

	@Override
	public ContaBancaria buscar(Long id) {
		
		return manager.find(ContaBancaria.class, id);
	}

	@Transactional
	@Override
	public ContaBancaria salvar(ContaBancaria contaBancaria) {
		return manager.merge(contaBancaria);
	}

	@Transactional
	@Override
	public void remover(ContaBancaria contaBancaria) {
		contaBancaria = buscar(contaBancaria.getId());
		manager.remove(contaBancaria);
	}

}
