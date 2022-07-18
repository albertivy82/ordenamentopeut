package br.gov.pa.ideflorbio.ordenamentopeut.infraestructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Localizacao;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.repository.LocalizacaoRepository;

@Component
public class LocalizacaoImpl implements LocalizacaoRepository{

	@PersistenceContext
	private EntityManager manager;
	
	
	@Override
	public List<Localizacao> listar() {
		
		return manager.createQuery("from Localizacao", Localizacao.class).getResultList();
	}

	@Override
	public Localizacao buscar(Long id) {
		return manager.find(Localizacao.class, id);
	}

	@Transactional
	@Override
	public Localizacao salvar(Localizacao Localizacao) {
		return manager.merge(Localizacao);
	}

	@Transactional
	@Override
	public void remover(Long id) {
		
		Localizacao localizacao = buscar(id);
			if(localizacao==null) {
				throw new EmptyResultDataAccessException(1);
			}
		manager.remove(localizacao);
	}

}
