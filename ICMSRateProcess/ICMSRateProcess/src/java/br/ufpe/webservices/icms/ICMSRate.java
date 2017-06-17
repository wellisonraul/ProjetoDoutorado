/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpe.webservices.icms;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author glauciamelissa
 */
@WebService(serviceName = "ICMSRate")
public class ICMSRate {
    double valuefinal = 0;
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "toSayFinalCost")
    public double toSayFinalCost(@WebParam(name = "good") double good, @WebParam(name = "shipping") double shipping, @WebParam(name = "valiquot") double valiquot, @WebParam(name = "vimportation") double vimportation) {
        valuefinal = ((good + shipping + vimportation)/(1.00 - valiquot)) * valiquot; 
        //valuefinal = good + shipping + vimportation;
	return valuefinal;
		
    }
}
