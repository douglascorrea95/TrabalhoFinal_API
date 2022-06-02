package org.serratec.backend.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "endereco")
public class Endereco {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "endereco_cd_id")
	private Integer idEndereco;

	@Column(name = "endereco_tx_estado")
	@NotNull
	private String estado;

	@Column(name = "endereco_tx_cidade")
	@NotNull
	private String cidade;

	@Column(name = "endereco_num_cep")
	@NotNull
	private Integer cep;

	@Column(name = "endereco_tx_rua")
	@NotNull
	private String rua;

	@ManyToOne
	@JoinColumn(name = "cliente_id", referencedColumnName = "cliente_cd_id")
	@JsonIgnore
	private Cliente clienteEndereco;

	public Cliente getClienteEndereco() {
		return clienteEndereco;
	}

	public void setClienteEndereco(Cliente clienteEndereco) {
		this.clienteEndereco = clienteEndereco;
	}

	public Endereco() {
		super();
	}

	public Integer getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(Integer idEndereco) {
		this.idEndereco = idEndereco;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Integer getCep() {
		return cep;
	}

	public void setCep(Integer cep) {
		this.cep = cep;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

}
