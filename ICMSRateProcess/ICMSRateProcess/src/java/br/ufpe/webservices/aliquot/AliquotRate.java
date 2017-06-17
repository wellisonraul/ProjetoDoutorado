/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpe.webservices.aliquot;


import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;


/**
 *
 * @author glauciamelissa
 */
@WebService(serviceName = "AliquotRate")
public class AliquotRate {
    
    private HashMap<String, Double> aliquot;
    
    
    @WebMethod(operationName = "toSayAliquot")
    public double toSayAliquot(@WebParam(name = "uf") String uf) {
		// TODO Auto-generated method stub"
	if ( aliquot.containsKey( uf ) ) { 
	//System.out.println("Valor da Chave "+UF+ " = "+aliquot.get(UF));
		return aliquot.get(uf);
	}else{ 
		System.out.println("Erro");
	return 0;	 
	}
		
    }
    
    public AliquotRate() { 
        //TODO write your implementation code here: 
                this.aliquot = new HashMap <String, Double>();
                aliquot.put("MG", 0.18);
		aliquot.put("RJ", 0.19);
		aliquot.put("BA", 0.17);
		aliquot.put("SP", 0.18);
		aliquot.put("AC", 0.17);
		aliquot.put("ES", 0.18);
		aliquot.put("PA", 0.17);
		aliquot.put("PE", 0.17);
		aliquot.put("AL", 0.17);
		aliquot.put("MA", 0.17);
		aliquot.put("RO", 0.18);
		aliquot.put("RN", 0.17);
		aliquot.put("RS", 0.17);
		aliquot.put("SC", 0.17);
		aliquot.put("SE", 0.17);
		aliquot.put("TO", 0.18);
		aliquot.put("AP", 0.17);
		aliquot.put("GO", 0.17);
		aliquot.put("RR", 0.17);
		aliquot.put("AM", 0.17);
		aliquot.put("MT", 0.18);
		aliquot.put("MS", 0.17);
		aliquot.put("CE", 0.17);
		aliquot.put("DF", 0.17);
		aliquot.put("PB", 0.17);
		aliquot.put("PR", 0.18);
		aliquot.put("PI", 0.17);
                
                
    } 
	
    
    
    
}
