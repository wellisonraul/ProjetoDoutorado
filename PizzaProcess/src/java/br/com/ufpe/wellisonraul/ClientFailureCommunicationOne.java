/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufpe.wellisonraul;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author wellisonraul
 */
@WebService(serviceName = "ClientFailureCommunicationOne")
public class ClientFailureCommunicationOne {

    /**
     * This is a sample web service operation
     * @param returnValidation
     * @return 
     */
    @WebMethod(operationName = "failureCommunication")
    public String failureCommunication(@WebParam(name = "returnValidation") int returnValidation) {
        String returnClient = "";
        
        if(returnValidation == 1){
            returnClient = "Authentication failed! CPF is not registered!";
        }
        else if(returnValidation == 2){
            returnClient = "Failure during credit card validation! No limit available!";
        }
        
        return returnClient;
    }
}
