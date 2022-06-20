package br.gov.pa.ideflorbio.ordenamentopeut.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.pa.ideflorbio.ordenamentopeut.model.Orcamento;


@Repository
public interface Orcamentos extends JpaRepository<Orcamento, Long> {

}
