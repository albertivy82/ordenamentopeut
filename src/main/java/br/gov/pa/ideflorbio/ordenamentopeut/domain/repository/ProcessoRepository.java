package br.gov.pa.ideflorbio.ordenamentopeut.domain.repository;

import java.util.List;


import br.gov.pa.ideflorbio.ordenamentopeut.model.Processo;

public interface ProcessoRepository{
	
	List<Processo> listar();
	Processo buscar(Long id);
	Processo salvar(Processo processo);
	void remover(Processo processo);

}
