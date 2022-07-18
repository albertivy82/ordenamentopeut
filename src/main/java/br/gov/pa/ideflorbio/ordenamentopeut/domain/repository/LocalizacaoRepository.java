package br.gov.pa.ideflorbio.ordenamentopeut.domain.repository;



import java.util.List;

import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Localizacao;


public interface LocalizacaoRepository{
	
	List<Localizacao> listar();
	Localizacao buscar(Long id);
	Localizacao salvar(Localizacao Localizacao);
	void remover(Long id);
	

}
