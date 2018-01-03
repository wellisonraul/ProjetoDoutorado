package br.ufpe.activiti.behaviour.conversores;

import java.util.List;

import br.ufpe.activiti.behaviour.model.ProcessoNegocio;
import br.ufpe.activiti.behaviour.model.Servico;
import br.ufpe.activiti.behaviour.model.Tarefa;

public class testeConversor {
	public static void main(String args[]) {
		conversorBPMNObjetos conversorBO = new conversorBPMNObjetos();
		List<ProcessoNegocio> processosDeNegocio = conversorBO.converterBPMNObjetos("/home/wellisonraul/eclipse-workspace/Doutorado/activiti-behaviour/src/main/resources/processICMS.bpmn20.xml");
		
		for(ProcessoNegocio processoNegocio: processosDeNegocio) {
			System.out.println("Processo contém ID "+processoNegocio.getId()+ " e nome "
					+processoNegocio.getNome()+ " este processo contém "
					+processoNegocio.getListaTarefas().size()+" tarefas");
			
			for(Tarefa tarefa: processoNegocio.getListaTarefas()) {
				System.out.println("Tarefa nome: "+tarefa.getNome());
				System.out.println("Tarefa ID: "+tarefa.getId());
				System.out.println("Serviços que implementam essa tarefa: "+tarefa.getListaServicos().size());
				System.out.println();
				
				for(Servico servico: tarefa.getListaServicos()) {
					System.out.println("Serviço nome: "+servico.getNome());
					System.out.println("Serviço operação: "+servico.getOperacao());
					System.out.println("Parametros nos serviços: "+servico.getListaParametros().size());
					System.out.println();
				}
				
				
			}
			
			
		}
		
	}
}
