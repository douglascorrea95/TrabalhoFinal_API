package org.serratec.backend.ecommerce.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;



@Entity
@Table(name = "cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cliente_cd_id")
	private Integer idCliente;


	@Column(name = "cliente_tx_nome")
	@NotNull
	private String nomeCliente;

	@Column(name = "cliente_tx_Usuario")
	@NotNull
	private String clienteUsuario;

	@Column(name = "cliente_tx_email")
	@NotNull
	private String emailCliente;

	@Column(name = "cliente_tx_cpf")
	@NotNull
	private String cpfCliente;

	@Column(name = "cliente_dt_dataNascimento")
	@NotNull
	private LocalDate datanascimento;

	@Column(name = "cliente_tx_telefone")
	@NotNull
	private String telefoneCliente;
	
	@Column(name = "cliente_tx_telefoneSec")
	private String telefoneSec;

	@OneToMany(mappedBy="cliente")
	private List<Pedido> listaPedidos;
	
	// Constructor
	public Cliente() {
	}

	// Getters and Setters
	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getClienteUsuario() {
		return clienteUsuario;
	}

	public void setClienteUsuario(String clienteUsuario) {
		this.clienteUsuario = clienteUsuario;
	}

	public String getEmailCliente() {
		return emailCliente;
	}

	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public LocalDate getDatanascimento() {
		return datanascimento;
	}

	public void setDatanascimento(LocalDate datanascimento) {
		this.datanascimento = datanascimento;
	}

	public String getTelefoneCliente() {
		return telefoneCliente;
	}

	public void setTelefoneCliente(String telefoneCliente) {
		this.telefoneCliente = telefoneCliente;
	}

	public String getTelefoneSec() {
		return telefoneSec;
	}

	public void setTelefoneSec(String telefoneSec) {
		this.telefoneSec = telefoneSec;
	}
}
