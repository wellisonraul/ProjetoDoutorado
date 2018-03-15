package br.com.ufpe.wellisonraul.internalcommunicationserviceone;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

@WebService(serviceName = "InternalCommunicationServiceOne")
public class InternalCommunicationServiceOne {

    @WebMethod(operationName = "communicateBaker")
    public String communicateBaker(@WebParam(name = "orderClient") String orderClient) {
        String returnStatus = "Client order is ready! Order: "+orderClient+" ";
        return returnStatus;
    }
}
