package org.serratec.backend.ecommerce.DTO;

import java.util.Date;

public class ProdutoDTO {
	
	
	private Integer idProduto;
	private String nomeProduto;
	private String descricaoProduto;
	private Integer valorUnitario;
	private Date dataValidadeProduto;
	private Integer quantidadeEmEstoqueProduto;
	private String peridoDeValidade;
	private Integer idCategoria;
	private Integer idFuncionario;
	
	
	public ProdutoDTO() {}
	
	public Integer getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(Integer idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}


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


	public Integer getQuantidadeEmEstoqueProduto() {
		return quantidadeEmEstoqueProduto;
	}


	public void setQuantidadeEmEstoqueProduto(Integer quantidadeEmEstoqueProduto) {
		this.quantidadeEmEstoqueProduto = quantidadeEmEstoqueProduto;
	}


	public String getPeridoDeValidade() {
		return peridoDeValidade;
	}


	public void setPeridoDeValidade(String peridoDeValidade) {
		this.peridoDeValidade = peridoDeValidade;
	}


	
	
	

}
	