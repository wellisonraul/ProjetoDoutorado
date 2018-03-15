package br.com.ufpe.wellisonraul.getclientpreferredpizzaserviceone;

import java.util.HashMap;
import java.util.Map;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

@WebService(serviceName = "GetClientPreferredPizzaServiceOne")
public class GetClientPreferredPizzaServiceOne {
    
    HashMap<String,String> clientMenu = new HashMap<>();
    
    public GetClientPreferredPizzaServiceOne(){
        clientMenu.put("084.187.454-95", "Calabreza: 20R$, Brasileira: 20R$ e Portuguesa: 20R$");
        clientMenu.put("692.946.075-34", "Calabreza: 20R$, Margherita: 20R$ e Portuguesa: 20R$");
        clientMenu.put("839.729.578-07", "Calabreza: 20R$, Portuguesa: 20R$ e Margherita: 20R$");
        clientMenu.put("689.664.677-25", "Calabreza: 20R$, Margherita: 20R$ e Portuguesa: 20R$");
    }

    @WebMethod(operationName = "getCatalogue")
    public String getCatalogue(@WebParam(name = "cpf") String cpf) {
        String returnPreferredPizza = "";
        for(Map.Entry<String,String> menuClient : clientMenu.entrySet()) {
            if(menuClient.getKey().equals(cpf)){
                returnPreferredPizza = menuClient.getValue();
            }
        }
        return returnPreferredPizza;
    }
}
