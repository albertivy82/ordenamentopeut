package br.gov.pa.ideflorbio.ordenamentopeut.infraestructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.ContaBancaria;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.repository.ContaBancariaRepository;


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
	public void remover(Long id) {
		
		ContaBancaria contaBancaria = buscar(id);
			if(contaBancaria==null) {
				throw new EmptyResultDataAccessException(1);
			}
		manager.remove(contaBancaria);
	}

}
