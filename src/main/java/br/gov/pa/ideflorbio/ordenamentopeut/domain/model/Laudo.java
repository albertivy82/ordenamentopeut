package br.gov.pa.ideflorbio.ordenamentopeut.domain.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

import javax.persistence.Entity;




public class Laudo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private VersaoLaudo versaolaudo;
	private ItemAvalido itemAvaliado;
	private String instituicaoAvaliadora;
	private Date dataAvaliacao;
	
	private Processo processo;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public VersaoLaudo getVersaolaudo() {
		return versaolaudo;
	}
	public void setVersaolaudo(VersaoLaudo versaolaudo) {
		this.versaolaudo = versaolaudo;
	}
	public ItemAvalido getItemAvaliado() {
		return itemAvaliado;
	}
	public void setItemAvaliado(ItemAvalido itemAvaliado) {
		this.itemAvaliado = itemAvaliado;
	}
	public String getInstituicaoAvaliadora() {
		return instituicaoAvaliadora;
	}
	public void setInstituicaoAvaliadora(String instituicaoAvaliadora) {
		this.instituicaoAvaliadora = instituicaoAvaliadora;
	}
	public Date getDataAvaliacao() {
		return dataAvaliacao;
	}
	public void setDataAvaliacao(Date dataAvaliacao) {
		this.dataAvaliacao = dataAvaliacao;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Laudo other = (Laudo) obj;
		return Objects.equals(id, other.id);
	}
	public Processo getProcesso() {
		return processo;
	}
	public void setProcesso(Processo processo) {
		this.processo = processo;
	}
	

}
