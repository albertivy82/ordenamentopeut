package br.gov.pa.ideflorbio.ordenamentopeut.domain.repository;

import java.util.List;


import br.gov.pa.ideflorbio.ordenamentopeut.model.ContaBancaria;

public interface ContaBancariaRepository {
	
	List<ContaBancaria> listar();
	ContaBancaria buscar(Long id);
	ContaBancaria salvar(ContaBancaria contaBancaria);
	void remover(ContaBancaria contaBancaria);

}
