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

public class Engine {
	public static void main(String[] args) {
		
		
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		
		// INSERI O PROCESS
		RepositoryService repositoryService = processEngine.getRepositoryService();
		DeploymentBuilder db = repositoryService.createDeployment();
		db.addClasspathResource("processSequel.bpmn20.xml");
		db.deploy();
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("nameuf", "RN");
		variables.put("good", new Long(900));
		variables.put("shipping", new Long(100));
		RuntimeService runtimeService = processEngine.getRuntimeService();
	

		// GERADOR DE ERROS
		/*ThreadGeradorErros geradorErros = new ThreadGeradorErros();
		Thread threadGeradorErros = new Thread(geradorErros);
		threadGeradorErros.start();*/
		
		
		MAPEK mapek = new MAPEK();
		mapek.start();
		
		/*int numDays =10;
		for (int i = 0; i < numDays; i++) {

			for (int j = 0; j <3; j++) {
				try {
					System.out.println("####################### Invocacao "+j +" do dia "+ i+ "#####################################################");
					FileWriter fstream = new FileWriter("Execucao.txt", true); 
					BufferedWriter out = new BufferedWriter(fstream);
					out.write("##################### Invocacao "+j +" do dia "+ i+ " #####################"+ "\n");
					out.close();
					runtimeService.startProcessInstanceByKey("processICMS", variables);
					double a = 0;
					while(a<900000000){
						a=a+0.5;
					}
				} catch (Exception e) {
					System.out.println(e);
				}
			}

		}*/

	}

	//	}
}