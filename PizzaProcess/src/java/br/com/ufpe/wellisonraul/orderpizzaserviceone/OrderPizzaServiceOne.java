
package br.com.ufpe.wellisonraul.orderpizzaserviceone;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

@WebService(serviceName = "OrderPizzaServiceOne")
public class OrderPizzaServiceOne {
    @WebMethod(operationName = "getOrderPizza")
    public String getOrderPizza(@WebParam(name = "orderClient") String orderClient) {
        return orderClient;
    }
}
