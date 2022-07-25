package br.gov.pa.ideflorbio.ordenamentopeut.domain.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Beneficiario;

@Repository
public interface BeneficiarioRepository extends JpaRepository<Beneficiario, Long>{
	
	List<Beneficiario> nome(String nome);
	
	Optional<Beneficiario> findByNome(String nome);
}
