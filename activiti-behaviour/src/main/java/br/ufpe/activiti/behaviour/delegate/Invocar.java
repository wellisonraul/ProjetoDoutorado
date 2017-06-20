package br.ufpe.activiti.behaviour.delegate;

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
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.service.model.BindingInfo;
import org.apache.cxf.service.model.BindingMessageInfo;
import org.apache.cxf.service.model.BindingOperationInfo;
import org.apache.cxf.service.model.MessagePartInfo;
import org.apache.cxf.service.model.ServiceInfo;
import org.json.JSONObject;

import br.ufpe.activiti.behaviour.model.ProcessoNegocio;
import br.ufpe.activiti.behaviour.model.Servico;
import br.ufpe.activiti.behaviour.model.Tarefa;
import br.ufpe.activiti.behaviour.random.GeradorRandomico;
import br.ufpe.activiti.behaviour.selection.SelecionaServicos;
import br.ufpe.activiti.behaviour.selection.SelecionaTarefa;

public class Invocar implements Runnable{
	private Expression parameters;
	private Expression returnValue;
	private DelegateExecution execution;
	private Servico servico;
	private Object[] retorno = null;
	
	


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

	public DelegateExecution getExecution() {
		return execution;
	}

	public void setExecution(DelegateExecution execution) {
		this.execution = execution;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public Invocar(DelegateExecution execution, Servico servico, Expression parameters) {
		setExecution(execution);
		setServico(servico);
		setParameters(parameters);
	}

	
	public Object[] getRetorno() {
		return retorno;
	}

	public void setRetorno(Object[] retorno) {
		this.retorno = retorno;
	}

	public void run() {
		try {
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	
}
