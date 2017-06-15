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
import java.util.StringTokenizer;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.xml.namespace.QName;
// NÃO ENCONTRA ESSA CLASSE!
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

import br.ufpe.activiti.behaviour.juddi.BuscaJuddi;
import br.ufpe.activiti.behaviour.model.ProcessoNegocio;
import br.ufpe.activiti.behaviour.model.Servico;
import br.ufpe.activiti.behaviour.model.Tarefa;
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
	private SelecionaServicos selecionaServicos;
	private SelecionaTarefa selecionaTarefas;
	private ArrayList<Tarefa> tarefas;
	private ArrayList<Tarefa> auxiliarTarefas;
	private ProcessoNegocio processoNegocio;
	private Expression wsdl;
	private Expression operation;
	private Expression parameters;
	private Expression returnValue;
	private Expression eventname;
	
	
	// MÉTODOS GET E SET
	public Expression getWsdl() {
		return wsdl;
		}

		public void setWsdl(Expression wsdl) {
			this.wsdl = wsdl;
		}

		public Expression getOperation() {
			return operation;
		}

		public void setOperation(Expression operation) {
			this.operation = operation;
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
		
		

	public Expression getEventname() {
			return eventname;
		}

		public void setEventname(Expression eventname) {
			this.eventname = eventname;
		}

	private void criaListaServicos(DelegateExecution execution){
		// CLASSE PARA UTILIZAR O JUDDI
		BuscaJuddi buscaJuddi = new BuscaJuddi();
		
		// CRIANDO UM SERVIÇO
		Servico servico = new Servico ();
		servico.setId(Integer.parseInt(execution.getId()));
		servico.setNome((String) wsdl.getValue(execution));
		servico.setOperacao((String)operation.getValue(execution));
		servico.setListaParametros(execution.getVariables());
		servico.setFator(0);
		servico.setTarefa((String)eventname.getValue(execution));
		
		// UTILIZANDO O JUDDI
		buscaJuddi.setServiceWSDLKey(servico, servico.getNome());
		
		
		
		
		// CLASSES RESPONSÁVEIS POR TRATAR A ADIÇÃO DAS PROPRIEDADES (SERVICOS E TAREFAS)
		selecionaServicos = new SelecionaServicos();
		selecionaTarefas = new SelecionaTarefa();
		
		
		
		selecionaServicos.addService(servico);
		selecionaServicos.addService(servico);
		
		// CRIANDO UMA TAREFA
		Tarefa t = new Tarefa();
		t.setId(execution.getCurrentActivityId());
		t.setNome((String)eventname.getValue(execution));
		t.setListaServicos(selecionaServicos.getServicos());
		
		selecionaTarefas.addTarefa(t);
				
		//System.out.println("Lista:"+this.selecionaServicos.getServices());
		
		
		/* MUDANDO DE LÓGICA 
		// CRIA UM SERVIÇO
		s = new Servico();
		s.setId(Integer.parseInt(execution.getId()));
		s.setNome((String) wsdl.getValue(execution));
		s.setOperacao((String)operation.getValue(execution));
		s.setListaParametros(execution.getVariables());
		s.setFator(0);
		s.setTarefa((String)eventname.getValue(execution));
		
		// CONSULTA O JUDDI
		BuscaJuddi buscaJuddi = new BuscaJuddi();
		buscaJuddi.setServiceWSDLKey(s, s.getNome());
		servicos.add(s);
		
		// CRIA UMA TAREFA E INSERI OS SERVIÇOS
		t = new Tarefa();
		t.setId(execution.getCurrentActivityId());
		t.setNome((String) eventname.getValue(execution));
		t.setListaServicos(servicos);
		tarefas = new ArrayList<Tarefa>();
		tarefas.add(t);
		
		
		if(contAux==0){
			processoNegocio = new ProcessoNegocio();
			processoNegocio.setId(1);
			processoNegocio.setNome("ICMSRateTest");
			auxiliarTarefas.add(contAux++, t);
			processoNegocio.setListaTarefas(auxiliarTarefas);
		}else{
			auxiliarTarefas.add(contAux++, t);
			System.out.println("Neste instante:"+auxiliarTarefas.size());
			processoNegocio.setListaTarefas(tarefas);
		}*/
	}	
	
	public void execute(DelegateExecution execution) {		
		// LISTA DE SERVIÇOS NÃO EXISTE, DESSA FORMA CRIE!
		if(selecionaServicos==null){
			criaListaServicos(execution);
		}else{
			
			// DEMONSTRA O QUE SE ENCONTRA NA LISTA DE SERVIÇOS
			for (Tarefa tarefa : selecionaTarefas.getTarefas()) {
				System.out.println("Tarefa id: "+tarefa.getId());
				System.out.println("Tarefa nome: "+tarefa.getNome());
					for (Servico servico : tarefa.getListaServicos()) {
						System.out.println("Nome serviço:"+servico.getWsdl());
						System.out.println("Nome da operação:"+servico.getOperacao());
					}
			}
			
			
		}
		
	}
	
	
	public void CallService(DelegateExecution execution){

		String wsdlString = (String) wsdl.getValue(execution);
		
		
		
		// CRIA UMA NOVA INSTÂNCIA
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		// DEFINE UM NOVO CLIENTE PASSANDO O WSDL
		Client client = dcf.createClient(wsdlString);
		Endpoint endpoint = client.getEndpoint();

		ServiceInfo serviceInfo = endpoint.getService().getServiceInfos()
				.get(0);

		BindingInfo binding = (BindingInfo) serviceInfo.getBindings().toArray()[0];
		QName opName = new QName(serviceInfo.getTargetNamespace(),
				operation.getExpressionText());
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

		if (parameters != null && parameters.getExpressionText() != null) {
			parts = inputMessageInfo.getMessageParts();

			partInfo = parts.get(0);
			partClass = partInfo.getTypeClass();
			if (partClass == null) {
				partClass = String.class;
			}
			System.out.println("Aqui 1:"+ partClass.getCanonicalName());

			try {
				inputObject = partClass.newInstance();
			} catch (InstantiationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			fieldsService = partClass.getDeclaredFields();

			StringTokenizer st = null;
			try {
				st = new StringTokenizer(
						(String) parameters.getValue(execution), ";");
			} catch (ClassCastException e) {
				st = new StringTokenizer(parameters.getValue(execution)
						.toString());
			}
			int i = -1;
			while (st.hasMoreTokens()) {
				i++;
				String token = st.nextToken().trim();

				Field field = fieldsService[i];

				PropertyDescriptor partPropertyDescriptor = null;
				try {
					partPropertyDescriptor = new PropertyDescriptor(
							field.getName(), partClass);
				} catch (IntrospectionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				Class<?> partPropType = partPropertyDescriptor
						.getPropertyType();
				System.out.println("Aqui 2: "+ partPropType.getCanonicalName());
				
				if (partPropType.getCanonicalName().equals("byte[]")) {
					inputPartObject = token.getBytes(Charset.forName("UTF-8"));
				} else if (partPropType.getCanonicalName().equals(
						"java.lang.String")) {
					inputPartObject = token;
				} else if (partPropType.getCanonicalName().equals("short")) {
					inputPartObject = new Short(token);
				} else if (partPropType.getCanonicalName().equals("int")) {
					inputPartObject = new Integer(token);
				} else if (partPropType.getCanonicalName().equals("long")) {
					inputPartObject = new Long(token);
				} else if (partPropType.getCanonicalName().equals("float")) {
					inputPartObject = new Float(token);
				} else if (partPropType.getCanonicalName().equals("double")) {
					inputPartObject = new Double(token);
				} else if (partPropType.getCanonicalName().equals("char")) {
					inputPartObject = new Character(token.charAt(0));
				} else if (partPropType.getCanonicalName().equals("boolean")
						|| partPropType.equals(Boolean.class)) {
					inputPartObject = new Boolean(token);
				} else {
					try {
						inputPartObject = partPropType.newInstance();
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						partPropertyDescriptor.getWriteMethod().invoke(inputObject,
								inputPartObject);
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					JSONObject jsonObject = new JSONObject(token);
					Iterator<?> keys = jsonObject.keys();

					while (keys.hasNext()) {
						String key = (String) keys.next();
						PropertyDescriptor numberPropertyDescriptor = null;
						try {
							numberPropertyDescriptor = new PropertyDescriptor(
									key, partPropType);
						} catch (IntrospectionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							numberPropertyDescriptor.getWriteMethod().invoke(
									inputPartObject, jsonObject.get(key));
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}
				params.add(inputPartObject);

			}

		}

		// Object[] result = client.invoke(opName, inputObject);
		Object[] result = null;
		if (params.size() == 0) {
			try {
				result = client.invoke(opName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (params.size() == 1) {
			try {
				result = client.invoke(opName, params.get(0));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (params.size() == 2) {
			try {
				result = client.invoke(opName, params.get(0), params.get(1));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (params.size() == 3) {
			try {
				result = client.invoke(opName, params.get(0), params.get(1),
						params.get(2));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (params.size() == 4) {
			try {
				result = client.invoke(opName, params.get(0), params.get(1),
						params.get(2), params.get(3));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (params.size() == 5) {
			try {
				result = client.invoke(opName, params.get(0), params.get(1),
						params.get(2), params.get(3), params.get(4));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		Class<?> resultClass = result[0].getClass();

		if (returnValue != null) {
			String returnVariableName = (String) returnValue
					.getValue(execution);

			if (!(resultClass.equals(String.class)
					|| resultClass.equals(Integer.class)
					|| resultClass.equals(Byte.class)
					|| resultClass.equals(Short.class)
					|| resultClass.equals(Byte.class)
					|| resultClass.equals(Long.class)
					|| resultClass.equals(Float.class)
					|| resultClass.equals(Double.class)
					|| resultClass.equals(Character.class) || resultClass
						.equals(Boolean.class))) {
				Field[] fields = resultClass.getDeclaredFields();

				for (Field field : fields) {
					PropertyDescriptor resultDescriptor = null;
					try {
						resultDescriptor = new PropertyDescriptor(
								field.getName(), resultClass);
					} catch (IntrospectionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Object wsResponse = null;
					try {
						wsResponse = resultDescriptor.getReadMethod()
								.invoke(result[0]);
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					execution.setVariable(
							returnVariableName + "." + field.getName(),
							wsResponse);
				}
			} else {
				execution.setVariable(returnVariableName, result[0]);
			}
		}

		System.out.println("Aqui 3 - Retorno: " + result[0]);

		/*
		 * Class<?> resultClass = result[0].getClass();
		 * System.out.println(resultClass.getCanonicalName());
		 * PropertyDescriptor resultDescriptor = new
		 * PropertyDescriptor("agentWSResponse", resultClass); Object wsResponse
		 * = resultDescriptor.getReadMethod().invoke(result[0]); Class<?>
		 * wsResponseClass = wsResponse.getClass();
		 * System.out.println(wsResponseClass.getCanonicalName());
		 * PropertyDescriptor agentNameDescriptor = new
		 * PropertyDescriptor("agentName", wsResponseClass); String agentName =
		 * (String)agentNameDescriptor.getReadMethod().invoke(wsResponse);
		 * System.out.println("Agent name: " + agentName);
		 */

		/*
		 * ArrayList paramStrings = new ArrayList(); if (parameters!=null) {
		 * StringTokenizer st = new StringTokenizer(
		 * (String)parameters.getValue(execution), ","); while
		 * (st.hasMoreTokens()) { paramStrings.add(st.nextToken().trim()); } }
		 * Object response =
		 * client.invoke((String)operation.getValue(execution),
		 * paramStrings.toArray(new Object[0])); if (returnValue!=null) { String
		 * returnVariableName = (String) returnValue.getValue(execution);
		 * execution.setVariable(returnVariableName, response); }
		 */
	}
}