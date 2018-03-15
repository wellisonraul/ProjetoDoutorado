package br.com.ufpe.wellisonraul.extenalcommunicationclientserviceone;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

@WebService(serviceName = "ExternalCommunicationClientServiceOne")
public class ExternalCommunicationClientServiceOne {

    @WebMethod(operationName = "communicateClient")
    public String communicateClient(@WebParam(name = "cpf") String cpf) {
        System.out.println(cpf+", your order is already on the delivery route and will arrive within 60 minutes!");
        String returnFinalStatus = "Message sent to client successfully!";
        return returnFinalStatus;
    }
}
