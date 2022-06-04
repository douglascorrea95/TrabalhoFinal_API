package org.serratec.backend.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.backend.ecommerce.DTO.ProdutoDTO;
import org.serratec.backend.ecommerce.DTO.ProdutoExibicaoDTO;
import org.serratec.backend.ecommerce.DTO.RelatorioDTO;
import org.serratec.backend.ecommerce.exception.ProdutoException;
import org.serratec.backend.ecommerce.model.Produto;
import org.serratec.backend.ecommerce.repository.CategoriaRepository;
import org.serratec.backend.ecommerce.repository.MovimentacaoRepository;
import org.serratec.backend.ecommerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Autowired
	MovimentacaoRepository movimentacaoRepository;

	
	//camada DTO
	public ProdutoDTO modelToDTO(Produto produto, ProdutoDTO produtoDTO) {
		
		produtoDTO.setIdProduto(produto.getIdProduto());
		produtoDTO.setNomeProduto(produto.getNomeProduto());
		produtoDTO.setDescricaoProduto(produto.getDescricaoProduto());
		produtoDTO.setValorUnitario(produto.getValorUnitario());
		produtoDTO.setDataValidade(produto.getDataValidadeProduto());;
		produtoDTO.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque());;
		produtoDTO.setIdCategoria(produto.getCategoria().getIdCategoria());
				
		return produtoDTO;
	}
	
	public Produto DTOToModel(ProdutoDTO produtoDTO, Produto produto) {
		
		produto.setNomeProduto(produtoDTO.getNomeProduto());
		produto.setDescricaoProduto(produtoDTO.getDescricaoProduto());
		produto.setValorUnitario(produtoDTO.getValorUnitario());
		produto.setDataValidadeProduto(produtoDTO.getDataValidade());
		produto.setQuantidadeEmEstoque(produtoDTO.getQuantidadeEmEstoque());
		
		if(produtoDTO.getIdCategoria() != null) {
			produto.setCategoria(categoriaRepository.findById(produtoDTO.getIdCategoria()).get());
		}
				
		return produto;
	}
	
	public ProdutoExibicaoDTO modelToDTOExibicao(Produto produto, ProdutoExibicaoDTO produtoExibicaoDTO) {
		
		produtoExibicaoDTO.setIdProduto(produto.getIdProduto());
		produtoExibicaoDTO.setNomeProduto(produto.getNomeProduto());
		produtoExibicaoDTO.setDescricaoProduto(produto.getDescricaoProduto());
		produtoExibicaoDTO.setValorUnitario(produto.getValorUnitario());
		produtoExibicaoDTO.setDataValidade(produto.getDataValidadeProduto());
		produtoExibicaoDTO.setQuantidadeEstoque(produto.getQuantidadeEmEstoque());
		produtoExibicaoDTO.setListaVendas(produto.getListaVendas());
		produtoExibicaoDTO.setIdCategoria(produto.getCategoria().getIdCategoria());
		
		return produtoExibicaoDTO;
	}
	
	
//	//buscar lista de produtos
		public List<ProdutoDTO> buscarTodos(){
			List<Produto> listaProdutos = produtoRepository.findAll();
			List<ProdutoDTO> listaProdutosDTO = new ArrayList<>();
			
			for (Produto produto : listaProdutos) {
				ProdutoDTO produtoDTO = new ProdutoDTO();
				modelToDTO(produto, produtoDTO);
				listaProdutosDTO.add(produtoDTO);			
			}
			
			return listaProdutosDTO;
			
		}
		
		//buscar produto por Id
		//vem junto a lista de itens
		public ProdutoExibicaoDTO buscarPorId(Integer idProduto) throws ProdutoException{
			Optional<Produto> produtoBuscado = produtoRepository.findById(idProduto);
			ProdutoExibicaoDTO produtoExibicaoDTO = new ProdutoExibicaoDTO();
			
			if(produtoBuscado.isPresent()) {
				Produto produto = produtoBuscado.get();
				modelToDTOExibicao(produto, produtoExibicaoDTO);
				return produtoExibicaoDTO;
			}
			
			throw new ProdutoException("O produto com id " + produtoExibicaoDTO.getIdProduto() + " não foi encontrado.");
			
		}
		
//		//salvar um produto
		public String salvarProduto(ProdutoDTO produtoDTO) {
			Produto produto = new Produto();
			DTOToModel(produtoDTO, produto);
			produtoRepository.save(produto);
			
			return "Produto salvo com sucesso com id " + produto.getIdProduto();
		}
//	
		
		//editar um produto
		public String editarProduto(Integer idProduto, ProdutoDTO produtoDTO) throws ProdutoException {
			Optional<Produto> produtoBuscado = produtoRepository.findById(idProduto);
			
			if(produtoBuscado.isPresent()) {
				Produto produto = produtoBuscado.get();
				
				if(produtoDTO.getNomeProduto() != null) {
					produto.setNomeProduto(produtoDTO.getNomeProduto());
				}
				
				if(produtoDTO.getDescricaoProduto() != null) {
					produto.setDescricaoProduto(produtoDTO.getDescricaoProduto());
				}
				
				if(produtoDTO.getValorUnitario() != null) {
					produto.setValorUnitario(produtoDTO.getValorUnitario());
				}
				
				if(produtoDTO.getDataValidade()!= null) {
					produto.setDataValidadeProduto(produtoDTO.getDataValidade());
				}
				
				if(produtoDTO.getQuantidadeEmEstoque() != 0) {
					produto.setQuantidadeEmEstoque(produtoDTO.getQuantidadeEmEstoque());
				}
				
				produtoRepository.save(produto);
				return "Produto atualizado com sucesso!";
			}
			
			throw new ProdutoException("O id " + produtoDTO.getIdProduto() + " não foi encontrado.");
		
		}
		
		//deletar um produto
		public String deletarProduto(Integer idProduto) {
			produtoRepository.deleteById(idProduto);
			return "Produto deletado com sucesso";
		}
		
		
		public List<RelatorioDTO> relatorio(){
			return movimentacaoRepository.buscarMaisVendidos();
		}
}
