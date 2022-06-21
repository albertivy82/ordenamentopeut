package br.gov.pa.ideflorbio.ordenamentopeut.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="indenizacao")
public class Indenizacao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String ob;
	
	@NotBlank
	@Column(name="data_ob")
	private Date dataOb;
	
	@NotBlank
	private String ne;
	
	@NotBlank
	@Column(name="data_ne")
	private Date dataNe;
	
	@NotBlank
	private String nl;
	
	@NotBlank
	private Date dataNl;
	
	@NotBlank
	@Column(name="data_acordo")
	private Date dataAcordo;
	
	@NotBlank
	private BigDecimal valor;
	
	
	@Column(name="status_pagamento")
	@Enumerated(EnumType.STRING)
	private Pagamento satusPagamento;
	
	//relacionamentos
	
	@ManyToOne
	@JoinColumn(name="beneficiario_id")
	private Beneficiario beneficiario;
	
	@ManyToOne
	@JoinColumn(name="indenizacoes")
	private Processo processo;
	
	@ManyToOne
	@JoinColumn(name="pagamento")
	private Orcamento Orcamento;

	
	//getters, setters, equal e hash
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOb() {
		return ob;
	}

	public void setOb(String ob) {
		this.ob = ob;
	}

	public Date getDataOb() {
		return dataOb;
	}

	public void setDataOb(Date dataOb) {
		this.dataOb = dataOb;
	}

	public String getNe() {
		return ne;
	}

	public void setNe(String ne) {
		this.ne = ne;
	}

	public Date getDataNe() {
		return dataNe;
	}

	public void setDataNe(Date dataNe) {
		this.dataNe = dataNe;
	}

	public Date getDataAcordo() {
		return dataAcordo;
	}

	public void setDataAcordo(Date dataAcordo) {
		this.dataAcordo = dataAcordo;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getNl() {
		return nl;
	}

	public void setNl(String nl) {
		this.nl = nl;
	}

	public Date getDataNl() {
		return dataNl;
	}

	public void setDataNl(Date dataNl) {
		this.dataNl = dataNl;
	}

	
	public Pagamento getSatusPagamento() {
		return satusPagamento;
	}

	public void setSatusPagamento(Pagamento satusPagamento) {
		this.satusPagamento = satusPagamento;
	}

	public Beneficiario getBeneficiario() {
		return beneficiario;
	}

	public void setBeneficiario(Beneficiario beneficiario) {
		this.beneficiario = beneficiario;
	}

	public Processo getProcesso() {
		return processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
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
		Indenizacao other = (Indenizacao) obj;
		return Objects.equals(id, other.id);
	}
	
	

}
