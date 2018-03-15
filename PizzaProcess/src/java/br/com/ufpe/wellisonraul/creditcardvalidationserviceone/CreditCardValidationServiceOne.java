package br.com.ufpe.wellisonraul.creditcardvalidationserviceone;

import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

@WebService(serviceName = "CreditCardValidationServiceOne")
public class CreditCardValidationServiceOne {

    ArrayList<String> creditCards = new ArrayList<>();
    
    public CreditCardValidationServiceOne(){
        creditCards.add("4539125464473757");
        creditCards.add("4929202175875907");
        creditCards.add("4485463790365676");
    }
    
    @WebMethod(operationName = "validation")
    public int validation(@WebParam(name = "creditCardNumber") String creditCardNumber) {
        int returnValidation = 2;
        for(String cards: creditCards){
            if(cards.equals(creditCardNumber)){
                returnValidation = 0;
            }
        }
        
        return returnValidation;
    }
}
