package br.gov.pa.ideflorbio.ordenamentopeut.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;



@Entity
@Table(name="endereco")
public class Endereco implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String logradouro;
	
	@NotBlank
	@Column(name="status_logradouro")
	private String statusLogradouro;
	
	@NotBlank
	private String bairro;
	
	@NotBlank
	private String Setor;
	
	@NotBlank
	private String municiopio;
	
	//relacionamentos
	
	@OneToOne
	@JoinColumn(name="id")
	private Processo processo;
	
	
	//getters,setters,equals e hash
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getStatusLogradouro() {
		return statusLogradouro;
	}
	public void setStatusLogradouro(String statusLogradouro) {
		this.statusLogradouro = statusLogradouro;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getSetor() {
		return Setor;
	}
	public void setSetor(String setor) {
		Setor = setor;
	}
	public String getMuniciopio() {
		return municiopio;
	}
	public void setMuniciopio(String municiopio) {
		this.municiopio = municiopio;
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
		Endereco other = (Endereco) obj;
		return Objects.equals(id, other.id);
	}

}
