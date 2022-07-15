package br.gov.pa.ideflorbio.ordenamentopeut.infraestructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Component;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.repository.LocalizacaoRepository;
import br.gov.pa.ideflorbio.ordenamentopeut.model.Localizacao;

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
	public void remover(Localizacao Localizacao) {
		Localizacao = buscar(Localizacao.getId());
		manager.remove(Localizacao);
	}

}
