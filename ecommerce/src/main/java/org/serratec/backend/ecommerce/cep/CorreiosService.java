package org.serratec.backend.ecommerce.cep;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBElement;

import com.linecode.jcep.wsdl.ConsultaCEP;
import com.linecode.jcep.wsdl.ConsultaCEPResponse;
import com.linecode.jcep.wsdl.EnderecoERP;
import com.linecode.jcep.wsdl.ObjectFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.ws.client.core.WebServiceTemplate;


@Service
public class CorreiosService {
	
	public static final String EMPTY_CEP_VALUE_ERRO_MESSAGE = "O cep informado como null ou empty string.";
    public static final String INVALID_CEP_VALUE_ERRO_MESSAGE = "Informe um cep válido (8 dígitos numéricos).";

    private final Pattern validationCepPattern = Pattern.compile("^[0-9]{8}$"); 

    @Autowired
    private WebServiceTemplate correiosWebServiceTemplate;
    
    public CompletableFuture<EnderecoERP> consultarCepAsync(String cep) {
        
        var thread = Executors.newSingleThreadExecutor();

       
        return CompletableFuture
            .supplyAsync(() -> consultarCep(cep), thread)
            .whenComplete((response, exception) -> thread.shutdown());
    }

    public EnderecoERP consultarCep(String cep) {

        assertCep(cep);

        var consultaCEP = new ConsultaCEP();
        consultaCEP.setCep(cep);

        var request  = new ObjectFactory().createConsultaCEP(consultaCEP);
        var response = this.<ConsultaCEPResponse>callCorreiosWebServiceAction(request);
        
        return response
            .getValue()
            .getReturn();
    }

    private void assertCep(String cep) {

        if (!StringUtils.hasText(cep)) {
            throw new IllegalArgumentException(EMPTY_CEP_VALUE_ERRO_MESSAGE);
        }

        if (!validationCepPattern.matcher(cep).matches()) {
            throw new IllegalArgumentException(INVALID_CEP_VALUE_ERRO_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    private <T> JAXBElement<T> callCorreiosWebServiceAction(Object request) {
        return (JAXBElement<T>) correiosWebServiceTemplate.marshalSendAndReceive(request);
    }
	

}
