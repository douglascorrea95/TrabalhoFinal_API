package org.serratec.backend.ecommerce.controller;

import java.util.List;

import javax.mail.MessagingException;

import org.serratec.backend.ecommerce.DTO.MovimentacaoDTO;
import org.serratec.backend.ecommerce.exception.EmailException;
import org.serratec.backend.ecommerce.exception.MovimentacaoException;
import org.serratec.backend.ecommerce.service.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movimentacao")
public class MovimentacaoController {
	
	@Autowired
	MovimentacaoService movimentacaoService;
	
	
	@GetMapping("/lista")
	public ResponseEntity<List<MovimentacaoDTO>> buscarTodos(){
		return ResponseEntity.ok(movimentacaoService.buscarTodas());
	}
	

	@PostMapping("/salvar")
	public ResponseEntity<String> salvarMovimentacao(@RequestBody MovimentacaoDTO movimentacaoDTO) throws MovimentacaoException, EmailException, MessagingException{
		return ResponseEntity.ok(movimentacaoService.salvarMovimentacao(movimentacaoDTO));
	}
}
