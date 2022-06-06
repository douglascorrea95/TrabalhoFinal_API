package org.serratec.backend.ecommerce;

import java.util.concurrent.ExecutionException;

//import org.serratec.backend.ecommerce.cep.CorreiosService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcommerceApplication {

	public static void main(String[] args) 	 {
		SpringApplication.run(EcommerceApplication.class, args);
//		throws InterruptedException, ExecutionException
//		var applicationContext = SpringApplication.run(EcommerceApplication.class, args);
//		var correiosService    = applicationContext.getBean(CorreiosService.class);
//
//		correiosService.consultarCepAsync("49035620").whenComplete((endereco, exception) -> {
//			if (exception != null) System.err.println(exception.getMessage());
//			System.out.println(endereco.getBairro());
//		});
	}

}
	
	
