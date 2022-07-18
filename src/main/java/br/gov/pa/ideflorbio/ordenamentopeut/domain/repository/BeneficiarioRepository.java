package br.gov.pa.ideflorbio.ordenamentopeut.domain.repository;




import java.util.List;

import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Beneficiario;


public interface BeneficiarioRepository{
	
	List<Beneficiario> listar();
	Beneficiario buscar(Long id);
	Beneficiario salvar(Beneficiario beneficiario);
	void remover(Long id);

}
