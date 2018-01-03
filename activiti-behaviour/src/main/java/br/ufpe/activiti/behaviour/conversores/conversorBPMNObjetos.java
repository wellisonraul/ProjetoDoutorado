package br.ufpe.activiti.behaviour.conversores;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import org.activiti.bpmn.model.FieldExtension;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.ServiceTask;
import br.ufpe.activiti.behaviour.model.ProcessoNegocio;
import br.ufpe.activiti.behaviour.model.Servico;
import br.ufpe.activiti.behaviour.model.Tarefa;

public class conversorBPMNObjetos {
	
	public List<ProcessoNegocio> converterBPMNObjetos(String caminhoArquivo) {
		List<Process> processos = recuperarProcessosBPMN(caminhoArquivo);
		return criarListadeProcessos(processos);
	}
	
	public List<Process> recuperarProcessosBPMN(String caminhoArquivo){
		ConversorXMLBPMN conversorXB = new ConversorXMLBPMN();
		return conversorXB.converterXMLBPMN(caminhoArquivo);
	}
	
	public List<ProcessoNegocio> criarListadeProcessos(List<Process> processos){
		List<ProcessoNegocio> processosNegocio = new ArrayList<ProcessoNegocio>();
		
		for(Process processo: processos) {
			ProcessoNegocio processoNegocio = new ProcessoNegocio();
			processoNegocio.setId(processo.getId());
			processoNegocio.setNome(processo.getName());
			processoNegocio.setListaTarefas(listarTarefasdoProcesso(processo));
			processosNegocio.add(processoNegocio);
		}
		
		return processosNegocio;
	}
	
	public List<Tarefa> listarTarefasdoProcesso(Process processo){
		List<Tarefa> listaTarefas = new ArrayList<Tarefa>();
		
		Collection<FlowElement> colecaoFlow = processo.getFlowElements();
		
		for(FlowElement flow: colecaoFlow) {
			if(flow.getClass().equals(ServiceTask.class)) {
				ServiceTask tarefaBPMN = (ServiceTask) flow;
				Tarefa tarefa = new Tarefa();
				tarefa.setId(tarefaBPMN.getId());
				tarefa.setNome(tarefaBPMN.getName());
				tarefa.setListaServicos(listarServicosParaTarefa(tarefaBPMN));
				
				listaTarefas.add(tarefa);
			}			
		}
		
		return listaTarefas;
	}
	
	public CopyOnWriteArrayList<Servico> listarServicosParaTarefa(ServiceTask tarefa) {
		String wsdlOperacao = "", parametros = "";
		
		for(FieldExtension extensao: tarefa.getFieldExtensions()) {
			if(extensao.getFieldName().equals("wsdloperation")) {
				wsdlOperacao = extensao.getExpression();
			}else if(extensao.getFieldName().equals("parameters")) {
				parametros = extensao.getExpression();
			}
		}
		
		return criarServico(wsdlOperacao,parametros,tarefa.getName());
	}
	
	public CopyOnWriteArrayList<Servico> criarServico(String wsdlOperacao, String parametros, String tarefa){
		CopyOnWriteArrayList<Servico> servicos = new CopyOnWriteArrayList<Servico>();
		
		if(wsdlOperacao.contains(";")){
			String servicoOperacao[] = wsdlOperacao.split(";");
			for(int i=0; i<servicoOperacao.length;i++){
				String divisaoServicoOperacao[] = servicoOperacao[i].split(":");
				Servico servico = new Servico ();	
				servico.setNome(divisaoServicoOperacao[0]);
				servico.setOperacao(divisaoServicoOperacao[1]);
				
				int qtdParametros = 0;
				
				for(int j=0; j<parametros.length();j++) {
					if(parametros.charAt(j)=='$') {
						qtdParametros++;
					}
				}
				
				qtdParametros = qtdParametros / servicoOperacao.length;
				
				Map<String,Object> parametro = new HashMap<String, Object>();
				for(int k=0; k<qtdParametros; k++) {
					Object objeto = new Object();
					
					parametro.put(k+"", objeto);
				}
				
				
				servico.setListaParametros(parametro);
				
				servicos.add(servico);
			}
		}else{
			String divisaoServicoOperacao[] = wsdlOperacao.split(":");
			Servico servico = new Servico ();
			servico.setNome(divisaoServicoOperacao[0]);
			servico.setOperacao(divisaoServicoOperacao[1]);
			
			
			int qtdParametros = 0;
			
			for(int j=0; j<parametros.length();j++) {
				if(parametros.charAt(j)=='$') {
					qtdParametros++;
				}
			}
			
			Map<String,Object> parametro = new HashMap<String, Object>();
			for(int k=0; k<qtdParametros; k++) {
				Object objeto = new Object();
				
				parametro.put(k+"", objeto);
			}
			
			
			servico.setListaParametros(parametro);
			
			
			servicos.add(servico);
		}
		
		return servicos;
	}
}
