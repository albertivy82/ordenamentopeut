package br.gov.pa.ideflorbio.ordenamentopeut.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="processo")
public class Processo implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="processo_pae")
	@NotBlank
	private String processoPae;
	
	@NotBlank
	@Column(name="processo_original")
	private String processoOriginal;
	
	@NotBlank
	@Column(name="numero_lote")
	private String numeroLote;
	
	@NotBlank
	@Column(name="status_lote")
	private String statusLote;
	
	@NotBlank
	@Column(name="status_processo")
	private String satusProcesso;
	
	//relacionamentos
	
	@OneToOne
	@JoinColumn(name="Localizacao")
	private Localizacao Localizacao;
		
	@OneToOne
	@JoinColumn(name="beneficiarios")
	private Beneficiario beneficiario;
	
	
	@OneToMany(mappedBy="processo")
	private List<Indenizacao> indenizacoes;
	
	@OneToMany(mappedBy="processo")
	private List<Imagens> imagens;
	
	
	//getters, setters, equal e hash
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProcessoPae() {
		return processoPae;
	}
	public void setProcessoPae(String processoPae) {
		this.processoPae = processoPae;
	}
	public String getProcessoOriginal() {
		return processoOriginal;
	}
	public void setProcessoOriginal(String processoOriginal) {
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
