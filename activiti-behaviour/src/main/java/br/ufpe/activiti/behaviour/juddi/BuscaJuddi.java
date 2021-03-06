package br.ufpe.activiti.behaviour.juddi;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.juddi.v3.client.UDDIConstants;
import org.apache.juddi.v3.client.config.UDDIClient;
import org.apache.juddi.v3.client.transport.Transport;
import org.apache.juddi.v3.client.transport.TransportException;
import org.uddi.api_v3.BusinessService;
import org.uddi.api_v3.CategoryBag;
import org.uddi.api_v3.FindQualifiers;
import org.uddi.api_v3.FindService;
import org.uddi.api_v3.GetServiceDetail;
import org.uddi.api_v3.KeyedReference;
import org.uddi.api_v3.Name;
import org.uddi.api_v3.ServiceDetail;
import org.uddi.api_v3.ServiceList;
import org.uddi.v3_service.DispositionReportFaultMessage;
import org.uddi.v3_service.UDDIInquiryPortType;
import br.ufpe.activiti.behaviour.model.Atributo;
import br.ufpe.activiti.behaviour.model.Servico;


public class BuscaJuddi {
	
	public void setServiceWSDLKey(Servico service, String value) {
		UDDIInquiryPortType inquiry = null;
		
		try {
			
			UDDIClient uddiClient = new UDDIClient("uddi.xml");
			try {
				Transport transport = uddiClient.getTransport("default");

				inquiry = transport.getUDDIInquiryService();

				value = value.replaceAll("\\s+","");
				
				if(value.startsWith("www") || value.startsWith("WWW") || value.startsWith("HTTP") || value.startsWith("http") || value.startsWith("FILE") || value.startsWith("file")){
					service.setWsdl(value);
					FindService fs = new FindService();
					fs.setFindQualifiers(new FindQualifiers());
					fs.getFindQualifiers().getFindQualifier().add(UDDIConstants.APPROXIMATE_MATCH);

					
					fs.setCategoryBag(new CategoryBag());
					KeyedReference kr = new KeyedReference();
	                kr.setTModelKey("uddi:uddi.org:wsdl:address");
	                kr.setKeyName("uddi-org:types:wsdl");
	                kr.setKeyValue(value);
	                fs.getCategoryBag().getKeyedReference().add(kr);
					
	                ServiceList sl = inquiry.findService(fs);
	                if(sl.getServiceInfos() != null){
	                	
	                	org.uddi.api_v3.ServiceInfo serviceInfo = sl.getServiceInfos().getServiceInfo().get(0);
	                	service.setNome(serviceInfo.getName().get(0).getValue());
	                	
	                }
					
				} else if(value.startsWith("uddi") || value.startsWith(" uddi")){
					
					GetServiceDetail getSD = new GetServiceDetail();
					
					getSD.getServiceKey().add(value);
					
					ServiceDetail serviceDetails = inquiry.getServiceDetail(getSD);
					
					BusinessService bs = serviceDetails.getBusinessService().get(0);
					service.setNome(bs.getName().get(0).getValue());
					CategoryBag cb = bs.getCategoryBag();
					List<KeyedReference> keyedReferences = cb.getKeyedReference();
					for (KeyedReference keyedReference : keyedReferences) {
						if(keyedReference.getTModelKey().equals("uddi:uddi.org:wsdl:address")){
							service.setWsdl(keyedReference.getKeyValue());
							break;
						}
					}
					
				} else {
					FindService fs = new FindService();
					service.setNome(value);
					Name name = new Name();
					name.setValue(value);
					fs.getName().add(name);
					ServiceList sl = null;
					try{
						sl = inquiry.findService(fs);
						
						GetServiceDetail getSD = new GetServiceDetail();
						
						List<org.uddi.api_v3.ServiceInfo> serviceInfos = sl.getServiceInfos().getServiceInfo();
						
						org.uddi.api_v3.ServiceInfo serviceInfo = serviceInfos.get(0);
						getSD.getServiceKey().add(serviceInfo.getServiceKey());
						ServiceDetail serviceDetails = inquiry.getServiceDetail(getSD);
						BusinessService bs = serviceDetails.getBusinessService().get(0);
						CategoryBag cb = bs.getCategoryBag();
						List<KeyedReference> keyedReferences = cb.getKeyedReference();
						
						for (KeyedReference keyedReference : keyedReferences) {
							
							
							ArrayList<Atributo> listaAtributo = new ArrayList<Atributo>();
							
							if(keyedReference.getTModelKey().equals("uddi:uddi.org:wsdl:address")){
								service.setWsdl(keyedReference.getKeyValue());
							}
							
							if(keyedReference.getTModelKey().equals("urn:wsdm.org:qos:reliability")){
								Double reliability = Double.parseDouble(keyedReference.getKeyValue());
								
								Atributo atributo = new Atributo();
								
								atributo.setNome("Disponibilidade");
								atributo.setEMelhorOValorMaior(true);
								atributo.setValor(reliability);
								
								
								service.setAtributos(listaAtributo);
								listaAtributo.add(atributo);
							}
							
							/*if(keyedReference.getTModelKey().equals("urn:wsdm.org:qos:responsetime_average")){
								Double tempoRespostaMedio = Double.parseDouble(keyedReference.getKeyValue());
								
								Atributo atributo = new Atributo();
								
								atributo.setNome("TempoDeResposta");
								atributo.setEMelhorOValorMaior(false);
								atributo.setValor(tempoRespostaMedio);	
								
								if(!(service.getAtributos()==null)) 
									listaAtributo.addAll(service.getAtributos());
								
								service.setAtributos(listaAtributo);
								listaAtributo.add(atributo);
								
							}
							
							if(keyedReference.getTModelKey().equals("uddi:www.mycompany.com:default_parameters")){
								String divisao[] = keyedReference.getKeyValue().split("::");
								
								Double parametersDouble = null;
								Integer parametersInteger = null;
								String parametersString = null;
								Double differenceBetweenParameters = null;
								
								ArrayList<Object> parametros = new ArrayList<Object>();
								
								
								if(divisao.length==1) {
									try {
										parametersDouble = Double.parseDouble(divisao[0]);
										parametersInteger = Integer.parseInt(divisao[0]);
										differenceBetweenParameters = parametersDouble - parametersInteger;
									}catch (Exception e) {
										parametersString = divisao[0];
									}finally {
										if(parametersDouble==null && parametersInteger == ) {
											parametros.add(parametersString);
										}else if(differenceBetweenParameters!=0){
											parametros.add(parametersDouble);
										}else {
											parametros.add(parametersInteger);
										}
										
									}
								}else {
									for(int j=0; j<divisao.length; j++) {
										try {
											parametersDouble = Double.parseDouble(divisao[j]);
											parametersInteger = Integer.parseInt(divisao[j]);
											differenceBetweenParameters = parametersDouble - parametersInteger;
										}catch (Exception e) {
											parametersString = divisao[j];
										}finally {
											if(parametersDouble==null && parametersInteger == null) {
												parametros.add(parametersString);
											}else if(differenceBetweenParameters!=0){
												parametros.add(parametersDouble);
											}else {
												parametros.add(parametersInteger);
											}
										}
									}
								}
								
								service.setListaParametrosDisponibilidade(parametros);
							}*/
							
						}
					}catch (Exception e) {
						e.printStackTrace();
						System.out.println("Houve algum erro na aquisição do serviço "+service.getNome()+"!");
						System.out.println("Causas comuns: o serviço não está disponível"
								+ " no Juddi ou o Juddi está desligado!");
					}
					
					
				}
			} catch (TransportException e) {
				e.printStackTrace();
			} catch (DispositionReportFaultMessage e) {
				e.printStackTrace();
			} catch (RemoteException e) {
				e.printStackTrace();
			}

		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		
	}

}
