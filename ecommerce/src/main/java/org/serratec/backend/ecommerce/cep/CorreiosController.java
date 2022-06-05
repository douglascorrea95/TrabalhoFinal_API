package org.serratec.backend.ecommerce.cep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.linecode.jcep.wsdl.EnderecoERP;

@RestController
@RequestMapping("/cep")
public class CorreiosController {

	@Autowired
	CorreiosService correiosService;

	
	@GetMapping("/buscar/{cep}")
	public ResponseEntity<EnderecoERP> consultarCep(@PathVariable String cep){
		return ResponseEntity.ok(correiosService.consultarCep(cep));
	}
	
}
