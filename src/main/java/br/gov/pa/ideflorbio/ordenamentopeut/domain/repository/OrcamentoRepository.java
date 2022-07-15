package br.gov.pa.ideflorbio.ordenamentopeut.domain.repository;



import java.util.List;


import br.gov.pa.ideflorbio.ordenamentopeut.model.Orcamento;



public interface OrcamentoRepository{
	
	List<Orcamento> listar();
	Orcamento buscar(Long id);
	Orcamento salvar(Orcamento orcamento);
	void remover(Orcamento orcamento);

}
