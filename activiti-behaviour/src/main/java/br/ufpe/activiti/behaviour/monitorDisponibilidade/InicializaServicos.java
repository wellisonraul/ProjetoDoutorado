package br.ufpe.activiti.behaviour.monitorDisponibilidade;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import br.ufpe.activiti.behaviour.conversores.conversorBPMNObjetos;
import br.ufpe.activiti.behaviour.juddi.BuscaJuddi;
import br.ufpe.activiti.behaviour.model.ProcessoNegocio;
import br.ufpe.activiti.behaviour.model.Servico;
import br.ufpe.activiti.behaviour.model.Tarefa;

public class InicializaServicos {
	private ArrayList<String> servico;
	CopyOnWriteArrayList<Servico> servicos = new CopyOnWriteArrayList<Servico>();
	
	
	public CopyOnWriteArrayList<Servico> inicializarServicos() {
		servico = new ArrayList<String>();
		servico.add("AliquotRate::toSayAliquot::1");
		servico.add("AliquotRateSecond::toSayAliquotSecond::1");
		servico.add("ImportationRate::toSayImportationTax::2");
		servico.add("ICMSRate::toSayFinalCost::4");
		
		BuscaJuddi buscaJuddi = new BuscaJuddi();
		
		for(String nomeServico: servico) {
			Servico servico1 = new Servico();
			String array[] = new String[3];
			array = nomeServico.split("::");
			
			servico1.setNome(array[0]);
			servico1.setOperacao(array[1]);
			servico1.setQuantidadeParametros(Integer.parseInt(array[2]));
			buscaJuddi.setServiceWSDLKey(servico1, servico1.getNome());
			servicos.add(servico1);
		}
		
		return servicos;
	}
	
	public List<ProcessoNegocio> inicializarServicosBPMN() {
		conversorBPMNObjetos conversorBO = new conversorBPMNObjetos();
		List<ProcessoNegocio> processosDeNegocio = new ArrayList<ProcessoNegocio>();
		processosDeNegocio = conversorBO.converterBPMNObjetos("/home/wellisonraul/eclipse-workspace/Doutorado/activiti-behaviour/src/main/resources/processSequel.bpmn20.xml");
		
		BuscaJuddi buscaJuddi = new BuscaJuddi();
		
		for(ProcessoNegocio processoNegocio: processosDeNegocio) {
			for(Tarefa tarefa: processoNegocio.getListaTarefas()) {
				for(Servico servico: tarefa.getListaServicos()) {
					System.out.println(servico.getListaParametros().size());
					buscaJuddi.setServiceWSDLKey(servico, servico.getNome());
				}
			}
		}
		
		return processosDeNegocio;
	}
	
	
	
	public void inicializar() {
		/*ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		
		// INSERI O PROCESS
		RepositoryService repositoryService = processEngine.getRepositoryService();
		DeploymentBuilder db = repositoryService.createDeployment();
		db.addClasspathResource("VacationRequest.bpmn20.xml");
		db.deploy();
		
		System.out.println(repositoryService.getProcessModel("vacationRequest"));

		//runtimeService.startProcessInstanceByKey("processICMS", variables);	
		
		//System.out.println(repositoryService.getBpmnModel("vacationRequest").getFlowElement("flow1").getId());
		
		
		// Fetch all tasks for the management group
		TaskService taskService = processEngine.getTaskService();
		List<Task> tasks = taskService.createTaskQuery().list();
		for (Task task : tasks) {
		  System.out.println("Task available: " + task.getProcessDefinitionId());
		}*/
		
		
		

	}
	
	public static void main(String []args) {
		InicializaServicos i = new InicializaServicos();
		i.inicializar();
	}
}
