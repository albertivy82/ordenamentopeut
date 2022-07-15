package br.gov.pa.ideflorbio.ordenamentopeut.infraestructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import br.gov.pa.ideflorbio.ordenamentopeut.domain.repository.BeneficiarioRepository;
import br.gov.pa.ideflorbio.ordenamentopeut.model.Beneficiario;


@Component
public class BeneficiarioRepositoryImpl implements BeneficiarioRepository{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Beneficiario> listar() {
		return manager.createQuery("from Beneficiario", Beneficiario.class).getResultList();
	}

	@Override
	public Beneficiario buscar(Long id) {
		return manager.find(Beneficiario.class, id);
	}

	@Transactional
	@Override
	public Beneficiario salvar(Beneficiario beneficiario) {
		return manager.merge(beneficiario);
	}

	@Transactional
	@Override
	public void remover(Beneficiario beneficiario) {
		beneficiario = buscar(beneficiario.getId());
		manager.remove(beneficiario);
		
	}

}
