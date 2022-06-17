package br.gov.pa.ideflorbio.ordenamentopeut.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="indenizacao")
public class Indenizacao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String ob;
	private Date dataOb;
	private String ne;
	private Date dataNe;
	private String nl;
	private Date dataNl;
	private Date dataAcordo;
	private Double valor;
	private String satusPagamento;
	
	//relacionamentos
	
	@ManyToOne
	@JoinColumn(name="indenizacoes")
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

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
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

	public String getSatusPagamento() {
		return satusPagamento;
	}

	public void setSatusPagamento(String satusPagamento) {
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
