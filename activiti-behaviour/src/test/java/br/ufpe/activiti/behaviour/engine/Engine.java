package br.ufpe.activiti.behaviour.engine;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.DeploymentBuilder;
import br.ufpe.activiti.behaviour.ciclo.mapek.MAPEK;
import br.ufpe.activiti.behaviour.monitorDisponibilidade.MonitorDisponibilidade;

public class Engine {
	public static void main(String[] args) {
		
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		
		// INSERI O PROCESS
		RepositoryService repositoryService = processEngine.getRepositoryService();
		DeploymentBuilder db = repositoryService.createDeployment();
		db.addClasspathResource("processPizzaCompany.bpmn20.xml");
		db.deploy();
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("cpf", "084.187.454-95");
		variables.put("orderClient", "Portuguesa");
		variables.put("creditcardNumber", "4539125464473757");
		
		
		RuntimeService runtimeService = processEngine.getRuntimeService();
		

		// GERADOR DE ERROS
		/*ThreadGeradorErros geradorErros = new ThreadGeradorErros();
		Thread threadGeradorErros = new Thread(geradorErros);
		threadGeradorErros.start();*/
		
		
		MAPEK mapek = new MAPEK();
		mapek.start();
		
		MonitorDisponibilidade md = new MonitorDisponibilidade();
		md.start();
		
		int numDays =15;
		int numRequisicao = 60;
		for (int i = 0; i < numDays; i++) {

			for (int j = 0; j <numRequisicao; j++) {
				try {
					/*//System.out.println("####################### Invocacao "+j +" do dia "+ i+ "#####################################################");
					FileWriter fstream = new FileWriter("Execucao.txt", true); 
					BufferedWriter out = new BufferedWriter(fstream);
					out.write("##################### Invocacao "+j+" do dia "+i+ " #####################"+ "\n");
					out.close();*/
					runtimeService.startProcessInstanceByKey("processPizzaCompany", variables);
				} catch (Exception e) {
					System.out.println(e);
				}
			}

		}

	}

	//	}
}