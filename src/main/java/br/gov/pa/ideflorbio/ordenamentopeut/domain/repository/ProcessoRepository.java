package br.gov.pa.ideflorbio.ordenamentopeut.domain.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Processo;

@Repository
public interface ProcessoRepository extends JpaRepository<Processo, Long>{
	
	
}
