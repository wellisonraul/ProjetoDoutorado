package br.ufpe.activiti.behaviour.monitorDisponibilidade;

import org.uddi.api_v3.*;

import java.util.ArrayList;

import org.apache.juddi.api_v3.*;
import org.apache.juddi.v3.client.config.UDDIClient;
import org.apache.juddi.v3.client.transport.Transport;
import org.uddi.v3_service.UDDISecurityPortType;

import br.ufpe.activiti.behaviour.model.Atributo;
import br.ufpe.activiti.behaviour.model.Servico;

import org.uddi.v3_service.UDDIPublicationPortType;

public class InsercaoJuddi {
	
	private static UDDISecurityPortType security = null;
    private static UDDIPublicationPortType publish = null;

    public InsercaoJuddi() {
            try {
                    // create a client and read the config in the archive; 
                    // you can use your config file name
                    UDDIClient uddiClient = new UDDIClient("uddi.xml");
                    // a UddiClient can be a client to multiple UDDI nodes, so 
                    // supply the nodeName (defined in your uddi.xml.
                    // The transport can be WS, inVM, RMI etc which is defined in the uddi.xml
                    Transport transport = uddiClient.getTransport("default");
                    // Now you create a reference to the UDDI API
                    security = transport.getUDDISecurityService();
                    publish = transport.getUDDIPublishService();
            } catch (Exception e) {
                    e.printStackTrace();
            }
    }

    /**
     * This function shows you how to publish to UDDI using a fairly generic
     * mechanism that should be portable (meaning use any UDDI v3 library
     * with this code)
     */
    public void inserirNoJuddi(Servico servico) {
            try {
                    // Login aka retrieve its authentication token
                    GetAuthToken getAuthTokenMyPub = new GetAuthToken();
                    getAuthTokenMyPub.setUserID("wellison");                    //your username
                    getAuthTokenMyPub.setCred("wellison");                          //your password
                    AuthToken myPubAuthToken = security.getAuthToken(getAuthTokenMyPub);
                    
                    // Creating the parent business entity that will contain our service.
                    BusinessEntity myBusEntity = new BusinessEntity();
                    Name myBusName = new Name();
                    myBusName.setValue("uddi:localhost:service_"+servico.getNome()+"");
                    myBusEntity.getName().add(myBusName);

                    // Adding the business entity to the "save" structure, using our publisher's authentication info and saving away.
                    SaveBusiness sb = new SaveBusiness();
                    sb.getBusinessEntity().add(myBusEntity);
                    sb.setAuthInfo(myPubAuthToken.getAuthInfo());
                    BusinessDetail bd = publish.saveBusiness(sb);
                    bd.getBusinessEntity().get(0).getBusinessKey();
                    
                    // Creating a service to save.  Only adding the minimum data: the parent business key retrieved from saving the business 
                    // above and a single name.
                    BusinessService myService = new BusinessService();
                    myService.setBusinessKey("uddi:localhost:business_doutorado");
                    
                    Name myServName = new Name();
                    myServName.setValue(servico.getNome());
                    myService.getName().add(myServName);
                    
                    // Adcionado por Wellison
                    CategoryBag cb = new CategoryBag();
                    
                    KeyedReference kr = new KeyedReference();
                    
                    kr.setTModelKey("uddi:uddi.org:wsdl:address");
                    kr.setKeyName("uddi-org:types:wsdl");
                    kr.setKeyValue(servico.getWsdl());
                    
                    cb.getKeyedReference().add(kr);
                    
                    for(Atributo atributo: servico.getAtributos()) {
                    	KeyedReference kr1 = new KeyedReference();
                        
                    	if(atributo.getNome().equals("Disponibilidade")) {
                    		kr1.setTModelKey("urn:wsdm.org:qos:reliability");
                            kr1.setKeyName("uddi-org:types:wsdl");
                            kr1.setKeyValue(atributo.getValor()+"");
                    	}else if(atributo.getNome().equals("TempoDeResposta")) {
                    		kr1.setTModelKey("urn:wsdm.org:qos:responsetime_average");
                            kr1.setKeyName("uddi-org:types:wsdl");
                            kr1.setKeyValue(atributo.getValor()+"");
                    	}
                        
                    	
                        cb.getKeyedReference().add(kr1);
                    }
                    
                    
                    myService.setCategoryBag(cb);
                    myService.setServiceKey("uddi:localhost:service_"+servico.getNome()+"");

                    // Add binding templates, etc...
                    BindingTemplate myBindingTemplate = new BindingTemplate();
                    AccessPoint accessPoint = new AccessPoint();
                    accessPoint.setUseType(AccessPointType.WSDL_DEPLOYMENT.toString());
                    accessPoint.setValue(servico.getWsdl());
                    myBindingTemplate.setAccessPoint(accessPoint);
                    BindingTemplates myBindingTemplates = new BindingTemplates();
                    //optional but recommended step, this annotations our binding with all the standard SOAP tModel instance infos
                    myBindingTemplate = UDDIClient.addSOAPtModels(myBindingTemplate);
                    myBindingTemplates.getBindingTemplate().add(myBindingTemplate);

                    myService.setBindingTemplates(myBindingTemplates);

                    // Adding the service to the "save" structure, using our publisher's authentication info and saving away.
                    SaveService ss = new SaveService();
                    ss.getBusinessService().add(myService);
                    ss.setAuthInfo(myPubAuthToken.getAuthInfo());
                    ServiceDetail sd = publish.saveService(ss);
                    
                    sd.getBusinessService().get(0).getServiceKey();
                   
                    security.discardAuthToken(new DiscardAuthToken(myPubAuthToken.getAuthInfo()));
                    // Now you have published a business and service via 
                    // the jUDDI API!
                    
            } catch (Exception e) {
                    e.printStackTrace();
            }
    }

    public static void main(String args[]) {
    		InsercaoJuddi sp = new InsercaoJuddi();
            Servico s = new Servico();
            s.setWsdl("www.google.com.br");
            s.setNome("AliquotRateFour");
            Atributo atri = new Atributo();
            atri.setNome("TempoDeResposta");
            atri.setValor(0.2);
            Atributo atri2 = new Atributo();
            atri2.setNome("Disponibilidade");
            atri2.setValor(0.98);
            ArrayList<Atributo> a = new ArrayList<Atributo>();
            a.add(atri);
            a.add(atri2);
            s.setAtributos(a);
    		sp.inserirNoJuddi(s);
    }
    
    
}
