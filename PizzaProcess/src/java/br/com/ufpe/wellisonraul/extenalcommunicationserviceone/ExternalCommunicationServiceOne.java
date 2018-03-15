
package br.com.ufpe.wellisonraul.extenalcommunicationserviceone;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;


@WebService(serviceName = "ExternalCommunicationServiceOne")
public class ExternalCommunicationServiceOne {

    @WebMethod(operationName = "communicateDelivery")
    public String communicateDelivery(@WebParam(name = "cpf") String cpf, @WebParam(name = "orderClient") String orderClient) {
        System.out.println("O pedido da "+orderClient+" deve ser entregue ao cliente Paulo!");
        String returnFinalStatus = "Message sent to delivery successfully!";
        return returnFinalStatus;
    }
}
