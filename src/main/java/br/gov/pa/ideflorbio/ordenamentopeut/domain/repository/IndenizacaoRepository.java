package br.gov.pa.ideflorbio.ordenamentopeut.domain.repository;



import java.util.List;

import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Indenizacao;



public interface IndenizacaoRepository{
	
	List<Indenizacao> listar();
	Indenizacao buscar(Long id);
	Indenizacao salvar(Indenizacao indenizacao);
	void remover(Long id);

}
