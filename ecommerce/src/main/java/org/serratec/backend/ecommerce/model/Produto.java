package org.serratec.backend.ecommerce.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="produto")
public class Produto implements Serializable{

	private static final long  serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="produto_cd_id")
	private Integer idProduto;
	
	@Column(name="produto_tx_nome")
	@NotNull
	private String nomeProduto;
	
	@Column(name="produto_tx_descricao")
	@NotNull
	private String descricaoProduto;
	
	@Column(name="produto_tx_valor_unitario")
	@NotNull
	private Integer valorUnitario;
	
	@Column(name="produto_dt_data_validade")
	@NotNull
	@DateTimeFormat(pattern="yyyy-MM-DD")
	private Date dataValidadeProduto;
	
	@Column(name="produto_tx_quantidade_em_estoque")
	@NotNull
	private Integer quantidadeEmEstoque;
	
	@Column(name="produto_tx_periodo_de_validade")
	private String peridoDeValidade;
	
				
	@ManyToOne
	@JoinColumn(name = "categoria_id", referencedColumnName = "categoria_cd_id")
	private Categoria categoria;
	
	@ManyToOne
	@JoinColumn(name = "funcionario_id", referencedColumnName = "funcionario_cd_id")
	private Funcionario funcionario;                                
	
	
	public Categoria getCategoria() {
	return categoria;
	}


	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	public Produto() {}

	
	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public String getDescricaoProduto() {
		return descricaoProduto;
	}

	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}

	public Integer getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Integer valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	public Date getDataValidadeProduto() {
		return dataValidadeProduto;
	}

	public void setDataValidadeProduto(Date dataValidadeProduto) {
		this.dataValidadeProduto = dataValidadeProduto;
	}

	public Integer getQuantidadeEmEstoque() {
		return quantidadeEmEstoque;
	}

	public void setQuantidadeEmEstoque(Integer quantidadeEmEstoque) {
		this.quantidadeEmEstoque = quantidadeEmEstoque;
	}

	public String getPeridoDeValidade() {
		return peridoDeValidade;
	}

	public void setPeridoDeValidade(String peridoDeValidade) {
		this.peridoDeValidade = peridoDeValidade;
	}
		
}
