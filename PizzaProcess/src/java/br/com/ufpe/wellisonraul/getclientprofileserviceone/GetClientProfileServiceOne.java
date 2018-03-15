package br.com.ufpe.wellisonraul.getclientprofileserviceone;

import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

@WebService(serviceName = "GetClientProfileServiceOne")
public class GetClientProfileServiceOne {
    
    public class Client{
        private String name;
        private String addres;
        private String cpf;
    }
    
    
    ArrayList<Client> clients = new ArrayList();
    public GetClientProfileServiceOne(){
        Client clientOne = new Client();
        clientOne.name = "Wellison Raul Mariz Santos";
        clientOne.cpf = "084.187.454-95";
        clientOne.addres = "Rua José Paulo da Costa, 18, Barra Nova, Caicó, RN";
        
        Client clientTwo = new Client();
        clientTwo.name = "Renan Correia Goncalves";
        clientTwo.cpf = "692.946.075-34";
        clientTwo.addres = "Rua Padre David Moreira, 639, Crato-CE";
        
        Client clientThree = new Client();
        clientThree.name = "Samuel Goncalves Barros";
        clientThree.cpf = "839.729.578-07";
        clientThree.addres = "Rua Urandi, 1757, Santa Luzia-MG";
        
        Client clientFour = new Client();
        clientFour.name = "Matilde Lima Pinto";
        clientFour.cpf = "689.664.677-25";
        clientFour.addres = "Rua Urupema, 1204, Itumbiara-GO";
        
        clients.add(clientOne);
        clients.add(clientTwo);
        clients.add(clientThree);
        clients.add(clientFour);
        
    }
    
    @WebMethod(operationName = "getData")
    public String getData(@WebParam(name = "cpf") String cpf) {
        String returnDataClient = "";
        for(Client cliente: clients){
            if(cliente.cpf.equals(cpf)){
                returnDataClient = "\n"
                        + "Name: "+cliente.name+""
                        + "\nAddres: "+cliente.addres+""
                        + "\nCPF: "+cliente.cpf+""; 
            }
        }
        
        return returnDataClient;
    }
}

