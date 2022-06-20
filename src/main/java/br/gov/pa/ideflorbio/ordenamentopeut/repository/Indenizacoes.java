package br.gov.pa.ideflorbio.ordenamentopeut.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.pa.ideflorbio.ordenamentopeut.model.Indenizacao;


@Repository
public interface Indenizacoes extends JpaRepository<Indenizacao, Long>{

}
