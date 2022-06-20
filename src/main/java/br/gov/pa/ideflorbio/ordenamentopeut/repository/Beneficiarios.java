package br.gov.pa.ideflorbio.ordenamentopeut.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.pa.ideflorbio.ordenamentopeut.model.Beneficiario;

@Repository
public interface Beneficiarios extends JpaRepository<Beneficiario, Long> {

}
