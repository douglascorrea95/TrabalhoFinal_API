package org.serratec.backend.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.serratec.backend.ecommerce.DTO.EnderecoDTO;
import org.serratec.backend.ecommerce.exception.EnderecoException;
import org.serratec.backend.ecommerce.model.Endereco;
import org.serratec.backend.ecommerce.repository.ClienteRepository;
import org.serratec.backend.ecommerce.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

	@Autowired
	EnderecoRepository enderecoRepository;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	
	public EnderecoDTO ModelEmDTO(EnderecoDTO enderecoDTO, Endereco endereco) {

		enderecoDTO.setIdEndereco(endereco.getIdEndereco());
		enderecoDTO.setEstado(endereco.getEstado());
		enderecoDTO.setCidade(endereco.getCidade());
		enderecoDTO.setRua(endereco.getRua());
		enderecoDTO.setCep(endereco.getCep());

		return enderecoDTO;
	}

	public Endereco DTOEmModel(EnderecoDTO enderecoDTO, Endereco endereco) {

		
		endereco.setEstado(enderecoDTO.getEstado());
		endereco.setCidade(enderecoDTO.getCidade());
		endereco.setRua(enderecoDTO.getRua());
		endereco.setCep(enderecoDTO.getCep());
		
		if(enderecoDTO.getIdCliente() != null) {
			endereco.setClienteEndereco(clienteRepository.findById(enderecoDTO.getIdCliente()).get());
		}

		return endereco;
	}

	public String salvar(EnderecoDTO enderecoDTO) {
		Endereco endereco = new Endereco();
		Endereco enderecoSalvar = new Endereco();
		enderecoSalvar = DTOEmModel(enderecoDTO, endereco);
		enderecoRepository.save(enderecoSalvar);
		return "O endereço foi criado com o id: " + endereco.getIdEndereco();

	}

	public EnderecoDTO buscarPorId(Integer idEndereco) throws EnderecoException {
		Optional<Endereco> endereco = enderecoRepository.findById(idEndereco);
		Endereco buscarEndereco = new Endereco();
		EnderecoDTO clienteDTO = new EnderecoDTO();
		if (endereco.isPresent()) {
			buscarEndereco = endereco.get();
			ModelEmDTO(clienteDTO, buscarEndereco);
			return clienteDTO;
		}
		throw new EnderecoException("Endereço com o id informado nao encontrado");
	}

	public List<EnderecoDTO> buscarTodos() {
		List<Endereco> listaEnderecoModel = enderecoRepository.findAll();
		List<EnderecoDTO> listaEnderecoDTO = new ArrayList<>();

		for (Endereco endereco : listaEnderecoModel) {
			EnderecoDTO enderecoDTO = new EnderecoDTO();
			ModelEmDTO(enderecoDTO, endereco);
			listaEnderecoDTO.add(enderecoDTO);

		}
		return listaEnderecoDTO;

	}

	public String atualizar(Integer idEndereco, EnderecoDTO enderecoDTO) throws EnderecoException {
		Optional<Endereco> endereco = enderecoRepository.findById(idEndereco);
		Endereco atualizarEndereco = new Endereco();
		if (endereco.isPresent()) {
			atualizarEndereco = endereco.get();
			if (enderecoDTO.getEstado() != null) {
				atualizarEndereco.setEstado(enderecoDTO.getEstado());
			}
			if (enderecoDTO.getCidade() != null) {
				atualizarEndereco.setCidade(enderecoDTO.getCidade());
			}
			if (enderecoDTO.getCep() != null) {
				atualizarEndereco.setCep(enderecoDTO.getCep());
			}
			if (enderecoDTO.getRua() != null) {
				atualizarEndereco.setRua(enderecoDTO.getRua());
			}
			enderecoRepository.save(atualizarEndereco);
			return "O endereço com o id " + atualizarEndereco.getIdEndereco() + " foi atualizado";
		}
		throw new EnderecoException("O endereco nao foi atualizado");
	}

	public void salvarListaEndereco(List<EnderecoDTO> listaEnderecoDTO) {
		List<Endereco> listaEndereco = new ArrayList<>();
		for (EnderecoDTO enderecoDTO : listaEnderecoDTO) {
			Endereco endereco = new Endereco();
			DTOEmModel(enderecoDTO, endereco);
			listaEndereco.add(endereco);
		}
		enderecoRepository.saveAll(listaEndereco);

	}
	
	public void deletar(Integer idEndereco) {
		enderecoRepository.deleteById(idEndereco);
	}

}