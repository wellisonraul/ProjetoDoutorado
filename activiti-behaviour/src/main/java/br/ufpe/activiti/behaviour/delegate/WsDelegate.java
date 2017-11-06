package br.ufpe.activiti.behaviour.delegate;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;
import javax.xml.namespace.QName;

//  ENCONTRA ESSA CLASSE!
//import org.activiti.bpmn.model.ServiceRequirement;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.service.model.BindingInfo;
import org.apache.cxf.service.model.BindingMessageInfo;
import org.apache.cxf.service.model.BindingOperationInfo;
import org.apache.cxf.service.model.MessagePartInfo;
import org.apache.cxf.service.model.ServiceInfo;
import org.json.JSONException;
import org.json.JSONObject;

import br.ufpe.activiti.bahaviour.util.Util;
import br.ufpe.activiti.behaviour.dao.DefineProcessLog;
import br.ufpe.activiti.behaviour.juddi.BuscaJuddi;
import br.ufpe.activiti.behaviour.model.Atributo;
import br.ufpe.activiti.behaviour.model.ProcessoNegocio;
import br.ufpe.activiti.behaviour.model.Servico;
import br.ufpe.activiti.behaviour.model.Tarefa;
import br.ufpe.activiti.behaviour.random.ThreadGeradorErros;
import br.ufpe.activiti.behaviour.random.ThreadTemporizador;
import br.ufpe.activiti.behaviour.random.GeradorRandomico;
import br.ufpe.activiti.behaviour.selection.Ordenacao;
import br.ufpe.activiti.behaviour.selection.SelecionaServicos;
import br.ufpe.activiti.behaviour.selection.SelecionaTarefa;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import javax.xml.namespace.QName;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.service.model.BindingInfo;
import org.apache.cxf.service.model.BindingMessageInfo;
import org.apache.cxf.service.model.BindingOperationInfo;
import org.apache.cxf.service.model.MessagePartInfo;
import org.apache.cxf.service.model.ServiceInfo;
import org.json.JSONObject;

public class WsDelegate extends SelecionaServicos implements JavaDelegate {

	private ArrayList<Servico> servicos;
	private GeradorRandomico geradorRandomico = new GeradorRandomico();
	private SelecionaServicos selecionaServicos;
	private SelecionaTarefa selecionaTarefas;
	private ArrayList<Tarefa> tarefas;
	private ArrayList<Tarefa> auxiliarTarefas;
	private ProcessoNegocio processoNegocio;
	private Expression wsdloperation;
	private Expression parameters;
	private Expression returnValue;
	private Expression eventname;
	private Invocar invocar;
	public static int processIDInicializacao;
	
	public Expression getParameters() {
		return parameters;
	}
	public static int getProcessIDInicializacao() {
		return processIDInicializacao;
	}
	public static void setProcessIDInicializacao(int processIDInicializacao) {
		WsDelegate.processIDInicializacao = processIDInicializacao;
	}
	public Expression getWsdloperation() {
		return wsdloperation;
	}
	public void setWsdloperation(Expression wsdloperation) {
		this.wsdloperation = wsdloperation;
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
	public Expression getEventname() {
		return eventname;
	}
	public void setEventname(Expression eventname) {
		this.eventname = eventname;
	}

	private void criaListaServicos(DelegateExecution execution){
		BuscaJuddi buscaJuddi = new BuscaJuddi(); // Objeto para criar uma instância para consultar o repositório Juddi
		
		selecionaServicos = new SelecionaServicos(); // CLASSES RESPONSÁVEIS POR TRATAR A ADIÇÃO DAS PROPRIEDADES (SERVICOS E TAREFAS)
		selecionaTarefas = new SelecionaTarefa();
		
		// Criar um objeto tarefa.
		Tarefa t = new Tarefa();
		t.setId(execution.getCurrentActivityId()); // Cria id da tarefa com base no BPMN!
		t.setNome((String)eventname.getValue(execution)); // Recebe o nome da tarefa com base no BPMN
		
		// RECEBE OS VALORES VINDOS DA STRING WSDL, ELA CONTÉM QUANTOS SERVIÇOS ESSA TAREFA TEM.
		String wsdlServicos = (String) wsdloperation.getValue(execution);
			
		// CASO SEJA MAIS DE UM SERVIÇO PARA ESTA TAREFA
		if(wsdlServicos.contains(";")){
			// DIVIDE OS SERVIÇOS
			String servicoOperacao[] = wsdlServicos.split(";");
			// RODA QUANTOS SERVIÇOS TEM
			for(int i=0; i<servicoOperacao.length;i++){
				// DIVIDE WSDL DA OPERAÇÃO
				String divisaoServicoOperacao[] = servicoOperacao[i].split(":");
				// CRIA O SERVIÇO
				Servico servico = new Servico ();
				
				servico.setNome(divisaoServicoOperacao[0]);
				servico.setOperacao(divisaoServicoOperacao[1]);
				servico.setListaParametros(execution.getVariables());
				servico.setFator(0);
				servico.setTarefa((String)eventname.getValue(execution));
				
				// UTILIZANDO O JUDDI PARA RECEBER O WSDL E A DISPONIBILIDADE
				buscaJuddi.setServiceWSDLKey(servico, servico.getNome());
				
				selecionaServicos.addService(servico);
			}
			
		// CASO SEJA APENAS UM SERVIÇO PARA ESTA TAREFA
		}else{
			String divisaoServicoOperacao[] = wsdlServicos.split(":");
			
			// CRIA O SERVIÇO
			Servico servico = new Servico ();
			
			servico.setNome(divisaoServicoOperacao[0]);
			servico.setOperacao(divisaoServicoOperacao[1]);
			servico.setListaParametros(execution.getVariables());
			servico.setFator(0);
			servico.setTarefa((String)eventname.getValue(execution));
			
			// UTILIZANDO O JUDDI PARA RECEBER O WSDL E A DISPONIBILIDADE
			buscaJuddi.setServiceWSDLKey(servico, servico.getNome());
			selecionaServicos.addService(servico);
		}
		
		// ADICIONO A LISTA DE SERVIÇOS DESSA TAREFA		
		t.setListaServicos(selecionaServicos.getServicos());
		selecionaTarefas.addTarefa(t);
		
		for(Servico servicos: selecionaServicos.getServicos()) {
			System.out.println(servicos.getNome());
			for(Atributo atributo: servicos.getAtributos()) {
				System.out.println(atributo.getNome()+": "+atributo.getValor());
			}
		}
		
		Ordenacao ordenacao = new Ordenacao();
		if(Util.METODODEAGREGACAO==1) ordenacao.ordenarAdicaoPeso(selecionaServicos);
		else if(Util.METODODEAGREGACAO==2) ordenacao.ordenarDistanciaEuclidiana(selecionaServicos);
		
		for(Servico servicos: selecionaServicos.getServicos()) {
			System.out.println(servicos.getNome());
			for(Atributo atributo: servicos.getAtributos()) {
				System.out.println(atributo.getNome()+": "+atributo.getValor());
			}
			System.out.println("Fator: "+servicos.getFator());
		}
	}	
	
	// MÉTODO PRINCIPAL QUE CHAMA OS RECURSOS
	/* RECURSOS *
	 * CRIA LISTA DE SERVIÇO
	 * INICIALIZA SERVIÇO
	 * INVOCA
	 * RECEBE RESPOSTA
	 * */
	
	public void execute(DelegateExecution execution) {	
		// A LISTA DE SERVIÇOS EXISTE?
		if(selecionaServicos==null){ // Não, então chame o método de criação!
			criaListaServicos(execution); // Método para cria lista de serviços
			setProcessIDInicializacao(Integer.parseInt(execution.getProcessInstanceId())); // Recebe o processo que está sendo iniciado
		} 
		
		DefineProcessLog dao = new DefineProcessLog(); // Inserção de traces
		try {
			dao.inserirLog(execution,selecionaServicos.retorneMelhorServico(),null); // Insira o trace de inicialização
			invocarServico(execution, selecionaServicos.retorneMelhorServico()); // Invoca o serviço da lista de serviços
		}catch (Exception e) {
			System.out.println("O serviço "+selecionaServicos.retorneMelhorServico().getNome()+" falhou!");
		}finally {
			// Inseri traces de execução, caso seja concluído, inseri complete!
			if(execution.getVariable((String)returnValue.getValue(execution))!=null) 
				dao.inserirLog(execution,selecionaServicos.retorneMelhorServico(),execution.getVariable((String)returnValue.getValue(execution)));
		}
	}
	
	// INVOCA O SERVIÇO E RECEBE A REPOSTA
	public void invocarServico( DelegateExecution execution, Servico servico) throws Exception{
		
		String wsdlString = servico.getWsdl(); //.getValue(execution);
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
				    
			inputObject = partClass.newInstance();
			fieldsService = partClass.getDeclaredFields();
				    
			StringTokenizer st = null;
			try{
				st = new StringTokenizer( (String)parameters.getValue(execution), "::");
			}catch(ClassCastException e){
				st = new StringTokenizer(parameters.getValue(execution).toString());
			}
			
			int i = -1;
			while (st.hasMoreTokens()) {
				i++;
			    String token = st.nextToken().trim();  
			    Field field = fieldsService[i];
			    PropertyDescriptor partPropertyDescriptor = new PropertyDescriptor(field.getName(), partClass);
					    
				Class<?> partPropType = partPropertyDescriptor.getPropertyType();
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
				}else{
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

		//Object[] result = client.invoke(opName, inputObject);
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
			}else{
				execution.setVariable(returnVariableName, result[0]);
			}
		}else{
			
		}
		
		
		System.out.println("Retorno: "+ result[0]);
	}
}