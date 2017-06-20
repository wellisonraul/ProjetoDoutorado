package br.ufpe.activiti.behaviour.random;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

import javax.xml.namespace.QName;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.service.model.BindingInfo;
import org.apache.cxf.service.model.BindingMessageInfo;
import org.apache.cxf.service.model.BindingOperationInfo;
import org.apache.cxf.service.model.MessagePartInfo;
import org.apache.cxf.service.model.ServiceInfo;
import org.json.JSONObject;

import br.ufpe.activiti.behaviour.model.Servico;

public class ThreadTemporizador extends Thread {
	private DelegateExecution execution;
	private Expression parameters;
	private Expression returnValue;
	private StringTokenizer stringTokenizer;
	private Servico servico;
	private Object[] retorno;
	
	public Object[] getRetorno() {
		return retorno;
	}
	public void setRetorno(Object[] retorno) {
		this.retorno = retorno;
	}
	public StringTokenizer getStringTokenizer() {
		return stringTokenizer;
	}
	public void setStringTokenizer(StringTokenizer stringTokenizer) {
		this.stringTokenizer = stringTokenizer;
	}
	public Servico getServico() {
		return servico;
	}
	public void setServico(Servico servico) {
		this.servico = servico;
	}
	public DelegateExecution getExecution() {
		return execution;
	}
	public void setExecution(DelegateExecution execution) {
		this.execution = execution;
	}
	public Expression getParameters() {
		return parameters;
	}
	public void setParameters(Expression parameters) {
		this.parameters = parameters;
	}
	public Expression getReturnValue() {
		return returnValue;
	}
	public void setReturnValue(Expression returnValue) {
		this.returnValue = returnValue;
	}
	
	public ThreadTemporizador(DelegateExecution execution, Expression parameters, Expression returnValue, Servico servico, StringTokenizer stringTokenizer){
		setExecution(execution);
		setParameters(parameters);
		setReturnValue(returnValue);
		setServico(servico);
		setStringTokenizer(stringTokenizer);
		
	}
	
	public void run(){	
		try {
			/*Random r = new Random();
			int x = r.nextInt(11)*1000;
			System.out.println(x);
			Thread.sleep(x);*/
			Object[] aux = invoca(getExecution(), getParameters(), getReturnValue(), getServico(),getStringTokenizer());
			this.setRetorno(aux);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}
	
	public Object[] invoca(DelegateExecution execution, Expression parameters, Expression returnValue, Servico servico, StringTokenizer stringTokenizer) throws Exception{
		// TEMPORIZADOR 2 (0 a 50)
					// THREAD.SLEEP(40)
					String wsdlString = servico.getWsdl(); //.getValue(execution);
					System.out.println(wsdlString);
				    
					
				    JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
				    Client client = dcf.createClient(wsdlString);
				    Endpoint endpoint = client.getEndpoint();
				    
				    ServiceInfo serviceInfo = endpoint.getService().getServiceInfos().get(0);
				    
				    BindingInfo binding = (BindingInfo) serviceInfo.getBindings().toArray()[0];
				    QName opName = new QName(serviceInfo.getTargetNamespace(), servico.getOperacao());
				    BindingOperationInfo boi = binding.getOperation(opName);
				    BindingMessageInfo inputMessageInfo = boi.getInput();
				    List<MessagePartInfo> parts = null;
				    MessagePartInfo partInfo = null;
				    Class<?> partClass = null;
				    Object inputObject = null;
				    Field[] fieldsService = null;
				    Object inputPartObject = null;
				    ArrayList params = new ArrayList();
				    
				    try {
				    	 parameters.getExpressionText();
					} catch (Exception e) {
						parameters = null;
					}
				   
				    
				    if (parameters!=null && parameters.getExpressionText() != null) {
				    	parts = inputMessageInfo.getMessageParts();
					    
					    partInfo = parts.get(0);
					    partClass = partInfo.getTypeClass();
					    if(partClass == null){
					    	partClass = String.class;
					    }
					    System.out.println(partClass.getCanonicalName());
					    
					    inputObject = partClass.newInstance();
					    
					    fieldsService = partClass.getDeclaredFields();
					       
				    	StringTokenizer st = this.stringTokenizer;
				    	
				      int i = -1;
				      while (st.hasMoreTokens()) {
				    	i++;
				        String token = st.nextToken().trim();
				        
				        Field field = fieldsService[i];
				        
					    PropertyDescriptor partPropertyDescriptor = new PropertyDescriptor(field.getName(), partClass);
						    
						Class<?> partPropType = partPropertyDescriptor.getPropertyType();
						System.out.println(partPropType.getCanonicalName());
						if(partPropType.getCanonicalName().equals("byte[]")){
							 inputPartObject = token.getBytes(Charset.forName("UTF-8"));
						}else if(partPropType.getCanonicalName().equals("java.lang.String")){
							 inputPartObject = token;
						 }else if(partPropType.getCanonicalName().equals("short")){
							 inputPartObject = new Short(token);
						 }else if(partPropType.getCanonicalName().equals("int")){
							 inputPartObject = new Integer(token);
						 }else if(partPropType.getCanonicalName().equals("long")){
							 inputPartObject = new Long(token);
						 }else if(partPropType.getCanonicalName().equals("float")){
							 inputPartObject = new Float(token);
						 }else if(partPropType.getCanonicalName().equals("double")){
							 inputPartObject = new Double(token);
						 }else if(partPropType.getCanonicalName().equals("char")){
							 inputPartObject = new Character(token.charAt(0));
						 }else if(partPropType.getCanonicalName().equals("boolean") || partPropType.equals(Boolean.class)){
							 inputPartObject = new Boolean(token);
						 }else {
							inputPartObject = partPropType.newInstance(); 
						    partPropertyDescriptor.getWriteMethod().invoke(inputObject, inputPartObject);
						    JSONObject jsonObject = new JSONObject(token);
							Iterator<?> keys = jsonObject.keys();
							    
							while(keys.hasNext()){
							    String key =  (String) keys.next();
							    PropertyDescriptor numberPropertyDescriptor = new PropertyDescriptor(key, partPropType);
								numberPropertyDescriptor.getWriteMethod().invoke(inputPartObject, jsonObject.get(key));
								    
							}
						 }
						params.add(inputPartObject);
				      
				    }     
				}

				    Object[] result = null;
				    if(params.size() == 0){
				    	result = client.invoke(opName);
				    }else if(params.size() == 1){
				    	 result = client.invoke(opName, params.get(0));
				    }else if(params.size() == 2){
				    	result = client.invoke(opName, params.get(0), params.get(1));
				    }else if(params.size() == 3){
				    	result = client.invoke(opName, params.get(0), params.get(1), params.get(2));
				    }else if(params.size() == 4){
				    	result = client.invoke(opName, params.get(0), params.get(1), params.get(2), params.get(3));
				    }else if(params.size() == 5){
				    	result = client.invoke(opName, params.get(0), params.get(1), params.get(2), params.get(3), params.get(4));
				    }
				    
				    return result;
	}
	
	public void setRetornaResult(DelegateExecution execution, Servico servico, Object[] result) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		 Class<?> resultClass = result[0].getClass();
		    
		    if (returnValue!=null) {
			      String returnVariableName = (String) returnValue.getValue(execution);
			     		      
			      if(!(resultClass.equals(String.class) || resultClass.equals(Integer.class) || resultClass.equals(Byte.class) || resultClass.equals(Short.class) || resultClass.equals(Byte.class) || resultClass.equals(Long.class)|| resultClass.equals(Float.class) || resultClass.equals(Double.class) || resultClass.equals(Character.class) || resultClass.equals(Boolean.class) ) ){
				      Field[] fields = resultClass.getDeclaredFields();
					    
					  for (Field field : fields) {
						  PropertyDescriptor resultDescriptor = new PropertyDescriptor(field.getName(), resultClass);
						  Object wsResponse = resultDescriptor.getReadMethod().invoke(result[0]);
						  execution.setVariable(returnVariableName+"."+field.getName(), wsResponse);
					  }
			      } else {
			    	  execution.setVariable(returnVariableName, result[0]);
			      }
			 }
		    
		    //setRetorno(result);
		    System.out.println("Retorno: "+ result[0]);
	}

}
