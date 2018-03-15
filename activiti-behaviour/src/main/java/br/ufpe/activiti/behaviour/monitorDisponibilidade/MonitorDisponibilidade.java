package br.ufpe.activiti.behaviour.monitorDisponibilidade;

import java.util.ArrayList;
import java.util.List;
import br.ufpe.activiti.behaviour.model.Atributo;
import br.ufpe.activiti.behaviour.model.ProcessoNegocio;
import br.ufpe.activiti.behaviour.model.Servico;
import br.ufpe.activiti.behaviour.model.Tarefa;
import br.ufpe.activiti.behaviour.util.Util;

public class MonitorDisponibilidade extends Thread{
	
	InicializaServicos inicializaServicos = new InicializaServicos();
	InvocarServicosDisponibilidade invocarServicosDisponibilidade = new InvocarServicosDisponibilidade();
	InsercaoJuddi insercaoJuddi = new InsercaoJuddi();
	private static List<ProcessoNegocio> processosDeNegocio = new ArrayList<ProcessoNegocio>();
	
	@Override
	public void run(){
		
		processosDeNegocio = inicializaServicos.inicializarServicosBPMN();
		

		
		while(true) {
			

			try {
				Thread.sleep(10000);
			}catch (Exception e) {
				System.out.println(e);
			}
			
			for(ProcessoNegocio processoNegocio: processosDeNegocio) {
				for(Tarefa tarefa: processoNegocio.getListaTarefas()) {
					for(Servico servico: tarefa.getListaServicos()) {
						double disponibilidadeAtual = invocarServicosDisponibilidade.invocarServico(servico);
						
						try {
							for(Atributo atributo: servico.getAtributos()) {
								if(atributo.getNome().equals("Disponibilidade")) {
									System.out.println("O serviço "+servico.getNome()+" possui atualmente "
											+ "disponibilidade: " +disponibilidadeAtual);
									atributo.setValor(disponibilidadeAtual);
								}
							}
						}catch(Exception e) {
							System.out.println("Houve um problema ao atribuir o valor de disponibilidade ao serviço!");
						}
						
					}
				}
			}
			
			Util.atualizacaoDisponibilidade = true;
			Util.processosNegocio = processosDeNegocio;
		}
		
		
	}
	
	public List<ProcessoNegocio> getProcessosDeNegocio() {
		return processosDeNegocio;
	}

	public void setProcessosDeNegocio(List<ProcessoNegocio> processosDeNegocio) {
		MonitorDisponibilidade.processosDeNegocio = processosDeNegocio;
	}
}
