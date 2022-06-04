package org.serratec.backend.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.backend.ecommerce.DTO.MovimentacaoDTO;
import org.serratec.backend.ecommerce.DTO.MovimentacaoProdutoDTO;
import org.serratec.backend.ecommerce.exception.MovimentacaoException;
import org.serratec.backend.ecommerce.model.Movimentacao;
import org.serratec.backend.ecommerce.repository.ClienteRepository;
import org.serratec.backend.ecommerce.repository.MovimentacaoRepository;
import org.serratec.backend.ecommerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovimentacaoService {
	
	@Autowired
	MovimentacaoRepository movimentacaoRepository;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	ProdutoRepository produtoRepository;

	//camada DTO
		public MovimentacaoDTO modelToDTO(Movimentacao movimentacao, MovimentacaoDTO movimentacaoDTO) {
				
			movimentacaoDTO.setIdMovimentacao(movimentacao.getIdMovimentacao());
			movimentacaoDTO.setIdCliente(movimentacao.getCliente().getIdCliente());
			movimentacaoDTO.setNotaFiscal(movimentacao.getNotaFiscal());
			movimentacaoDTO.setTipoMovimentacao(movimentacao.getTipoMovimentacao());
			
			List<Movimentacao> listaMovimentacoes = movimentacaoRepository.findAll();			
			List<Movimentacao> listaPorNotaFiscal = movimentacaoRepository.findByNotaFiscal(movimentacaoDTO.getNotaFiscal(), listaMovimentacoes);
			List<MovimentacaoProdutoDTO> listaProdutos = new ArrayList<>();
			
			for (Movimentacao movimentacao2 : listaPorNotaFiscal) {
				MovimentacaoProdutoDTO compraDTO = new MovimentacaoProdutoDTO();
				compraDTO.setQuantidadeCompra(movimentacao2.getQuantidadeCompra());
				compraDTO.setValorUnitario(movimentacao2.getValorUnitario());
				compraDTO.setIdProduto(movimentacao2.getProduto().getIdProduto());
				listaProdutos.add(compraDTO);
				
			}
						
			movimentacaoDTO.setListaProduto(listaProdutos);
			return movimentacaoDTO;
			
			}
			
		public Movimentacao DTOToModel(MovimentacaoDTO movimentacaoDTO, Movimentacao movimentacao) throws MovimentacaoException {
			
			movimentacao.setNotaFiscal(movimentacaoDTO.getNotaFiscal());		
			movimentacao.setTipoMovimentacao(movimentacaoDTO.getTipoMovimentacao());								
			
			if(movimentacaoDTO.getIdCliente() != null) {
				
				movimentacao.setCliente(clienteRepository.findById(movimentacaoDTO.getIdCliente()).get());
			}
			
			
				return movimentacao;
		}

		//buscar lista de movimentações
		public List<MovimentacaoDTO> buscarTodas(){
			List<Movimentacao> listaMovimentacao = movimentacaoRepository.findAll();
			List<MovimentacaoDTO> listaMovimentacaoDTO = new ArrayList<>();
					
			for (Movimentacao movimentacao : listaMovimentacao) {
				MovimentacaoDTO movimentacaoDTO = new MovimentacaoDTO();
				modelToDTO(movimentacao, movimentacaoDTO);
				listaMovimentacaoDTO.add(movimentacaoDTO);			
			}
								
			return listaMovimentacaoDTO;
					
		}
		
		//buscar por nota fiscal
		public List<MovimentacaoDTO> buscarPorNotaFiscal(String notaFiscal){
			List<Movimentacao> listaMovimentacao = movimentacaoRepository.findAll();
			List<Movimentacao> listaPorNotaFiscal = movimentacaoRepository.findByNotaFiscal(notaFiscal, listaMovimentacao);
			List<MovimentacaoDTO> listaMovimentacaoDTO = new ArrayList<>();
					
			for (Movimentacao movimentacao : listaPorNotaFiscal) {
				MovimentacaoDTO movimentacaoDTO = new MovimentacaoDTO();
				modelToDTO(movimentacao, movimentacaoDTO);
				listaMovimentacaoDTO.add(movimentacaoDTO);			
			}
					
			return listaMovimentacaoDTO;
					
		}
		
		//salvar uma movimentacao		
		public String salvarMovimentacao(MovimentacaoDTO movimentacaoDTO) throws MovimentacaoException {
					
			for (MovimentacaoProdutoDTO movimentacaoProdutoDTO : movimentacaoDTO.getListaProduto()) {
				Movimentacao movimentacao = new Movimentacao();			
				movimentacao.setProduto(produtoRepository.findById(movimentacaoProdutoDTO.getIdProduto()).get());
				movimentacao.setValorUnitario(movimentacaoProdutoDTO.getValorUnitario());
				movimentacao.setQuantidadeCompra(movimentacaoProdutoDTO.getQuantidadeCompra());
						
				if(movimentacaoProdutoDTO.getQuantidadeCompra() > movimentacao.getProduto().getQuantidadeEmEstoque()) {
					throw new MovimentacaoException("Falta de estoque!");
				}
						
				DTOToModel(movimentacaoDTO, movimentacao);		
				movimentacaoRepository.save(movimentacao);
						
			}	
					
			return "Movimentacao salva com sucesso";
					
		}
		
		//editar uma movimentação
		public String editarMovimentacao(Integer idMovimentacao, MovimentacaoDTO movimentacaoDTO) throws MovimentacaoException {
			Optional<Movimentacao> movimentacaoBuscada = movimentacaoRepository.findById(idMovimentacao);
					
			if(movimentacaoBuscada.isPresent()) {
				Movimentacao movimentacao = movimentacaoBuscada.get();
						
				if(movimentacaoDTO.getIdCliente() != null) {
					movimentacao.setCliente(clienteRepository.findById(movimentacaoDTO.getIdCliente()).get());
				}
						
				if(movimentacaoDTO.getNotaFiscal() != null) {
					movimentacao.setNotaFiscal(movimentacaoDTO.getNotaFiscal());
				}
						
						
				if(movimentacaoDTO.getTipoMovimentacao() != null) {
					movimentacao.setTipoMovimentacao(movimentacaoDTO.getTipoMovimentacao() );
				}
						
				if(movimentacaoDTO.getListaProduto() != null) {
					for (MovimentacaoProdutoDTO movimentacaoProdutoDTO : movimentacaoDTO.getListaProduto()) {
						movimentacao.setProduto(produtoRepository.findById(movimentacaoProdutoDTO.getIdProduto()).get());
						movimentacao.setValorUnitario(movimentacaoProdutoDTO.getValorUnitario());
						movimentacao.setQuantidadeCompra(movimentacaoProdutoDTO.getQuantidadeCompra());
					}		
				}				
						
				movimentacaoRepository.save(movimentacao);
				return "Categoria atualizada com sucesso!";
			}
					
			throw new MovimentacaoException("O id " + movimentacaoDTO.getIdMovimentacao() + " não foi encontrado.");
				
		}
		
		//deletar uma movimentacao
		public String deletarMovimentacao(Integer idMovimentacao) {
			movimentacaoRepository.deleteById(idMovimentacao);
			return "Funcionário deletado com sucesso";
		}

}