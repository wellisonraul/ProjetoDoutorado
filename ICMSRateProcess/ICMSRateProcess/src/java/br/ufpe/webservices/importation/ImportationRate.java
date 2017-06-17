/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpe.webservices.importation;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author glauciamelissa
 */
@WebService(serviceName = "ImportationRate")
public class ImportationRate {
    double result = 0;
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "toSayImportationTax")
    public double toSayImportationTax(@WebParam(name = "good") double good, @WebParam(name = "shipping") double shipping) {
        result = ((good+shipping)*0.6); 
        return result;
    }
    
    
    
}
