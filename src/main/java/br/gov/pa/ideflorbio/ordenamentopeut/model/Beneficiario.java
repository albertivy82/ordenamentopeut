package br.gov.pa.ideflorbio.ordenamentopeut.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name="beneficiario")
public class Beneficiario implements Serializable {
	
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;
	
	private String rg;
	
	private String cpf;
	
	@Enumerated(EnumType.STRING)
	private Perfil perfil;
	
	//relacionamentos
	
	@OneToOne(mappedBy="beneficiario")
	private Processo processo;

	@OneToMany(mappedBy="beneficiario")
	private List<Indenizacao> indenizacoes;
	
	@OneToOne(mappedBy="beneficiario")
	private ContaBancaria banco;
	
	
	
	//getters, setters, equal e hash
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Processo getProcesso() {
		return processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

	public List<Indenizacao> getIndenizacoes() {
		return indenizacoes;
	}

	public void setIndenizacoes(List<Indenizacao> indenizacoes) {
		this.indenizacoes = indenizacoes;
	}

	public ContaBancaria getBanco() {
		return banco;
	}

	public void setBanco(ContaBancaria banco) {
		this.banco = banco;
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
		Beneficiario other = (Beneficiario) obj;
		return Objects.equals(id, other.id);
	}


}
