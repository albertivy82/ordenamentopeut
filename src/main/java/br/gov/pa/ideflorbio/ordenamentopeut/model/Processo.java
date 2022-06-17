package br.gov.pa.ideflorbio.ordenamentopeut.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name="processo")
public class Processo implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long processoPae;
	private Long processoOriginal;
	private String numeroLote;
	private String statusLote;
	private String satusProcesso;
	
	//relacionamentos
	
	@OneToOne(mappedBy="processo")
	private Endereco endereco;
	
	
	@OneToOne
	@JoinColumn(name="processo")
	private Beneficiario beneficiario;
	
	
	@OneToMany(mappedBy="processo")
	private List<Indenizacao> indenizacoes;
	
	
	//getters, setters, equal e hash
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getProcessoPae() {
		return processoPae;
	}
	public void setProcessoPae(Long processoPae) {
		this.processoPae = processoPae;
	}
	public Long getProcessoOriginal() {
		return processoOriginal;
	}
	public void setProcessoOriginal(Long processoOriginal) {
		this.processoOriginal = processoOriginal;
	}
	public String getNumeroLote() {
		return numeroLote;
	}
	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}
	public String getStatusLote() {
		return statusLote;
	}
	public void setStatusLote(String statusLote) {
		this.statusLote = statusLote;
	}
	public String getSatusProcesso() {
		return satusProcesso;
	}
	public void setSatusProcesso(String satusProcesso) {
		this.satusProcesso = satusProcesso;
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
		Processo other = (Processo) obj;
		return Objects.equals(id, other.id);
	}
}
