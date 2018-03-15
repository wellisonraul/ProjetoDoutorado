package br.com.ufpe.wellisonraul.authenticationserviceone;

import java.util.ArrayList;
import java.util.Random;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

@WebService(serviceName = "AuthenticationServiceOne")
public class AuthenticationServiceOne {
    ArrayList<String> clientes = new ArrayList();
        
    public AuthenticationServiceOne(){
        clientes.add("084.187.454-95");
        clientes.add("692.946.075-34");
        clientes.add("839.729.578-07");
        clientes.add("689.664.677-25");
    }
    
    @WebMethod(operationName = "authentication")
    public int authentication(@WebParam(name = "cpf") String cpf) {
        
        int returnValidation = 1;
        for(String cpfArray : clientes){
            if(cpfArray.equals(cpf)){
                returnValidation = 0;
            }
        }
        return returnValidation;
    }
}

