/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufpe.wellisonraul.authenticationservicetwo;

import java.util.ArrayList;
import java.util.Random;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author wellisonraul
 */
@WebService(serviceName = "AuthenticationServiceTwo")
public class AuthenticationServiceTwo {

    ArrayList<String> clientes = new ArrayList();
        
    public AuthenticationServiceTwo(){
        clientes.add("084.187.454-95");
        clientes.add("692.946.075-34");
        clientes.add("839.729.578-07");
        clientes.add("689.664.677-25");
    }
    
    @WebMethod(operationName = "authenticationTwo")
    public int authenticationTwo(@WebParam(name = "cpf") String cpf) {
        
        Random gerador = new Random();  
        int numero = gerador.nextInt(10);
        
        if(numero<5){
            Integer.parseInt("084.187.454-95");
        }
        
        
        int returnValidation = 1;
        for(String cpfArray : clientes){
            if(cpfArray.equals(cpf)){
                returnValidation = 0;
            }
        }
        return returnValidation;
    }
}
