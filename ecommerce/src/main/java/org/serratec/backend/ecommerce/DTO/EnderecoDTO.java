package org.serratec.backend.ecommerce.DTO;

import java.io.Serializable;

public class EnderecoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idEndereco;
	private String estado;
	private String cidade;
	private Integer cep;
	private String rua;
	private Integer idCliente;

	public EnderecoDTO() {
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

}
