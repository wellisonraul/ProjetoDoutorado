package br.ufpe.activiti.behaviour.monitorDisponibilidade;


import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.eclipse.jetty.util.log.Log;

import br.ufpe.activiti.behaviour.model.Atributo;
import br.ufpe.activiti.behaviour.model.ProcessoNegocio;
import br.ufpe.activiti.behaviour.model.Servico;
import br.ufpe.activiti.behaviour.model.Tarefa;

public class MonitorDisponibilidade extends Thread{
	
	InicializaServicos inicializaServicos = new InicializaServicos();
	InvocarServicosDisponibilidade invocarServicosDisponibilidade = new InvocarServicosDisponibilidade();
	InsercaoJuddi insercaoJuddi = new InsercaoJuddi();
	
	public void monitorarDisponibilidade(){
		CopyOnWriteArrayList<Servico> servicos = new CopyOnWriteArrayList<Servico>();
		servicos = inicializaServicos.inicializarServicos();
		
		
		for(Servico servico: servicos) {
			double disponibilidadeAtual = invocarServicosDisponibilidade.invocarServico(servico, servico.getQuantidadeParametros());
			
			
			for(Atributo atributo: servico.getAtributos()) {
				if(atributo.getNome().equals("Disponibilidade")) {
					atributo.setValor(disponibilidadeAtual);
				}
			}
			
			insercaoJuddi.inserirNoJuddi(servico);
		}
		
		// Mecanismo para informar atualização no Juddi.
	}
	
	
	public void monitorarDisponibilidadeDois(){
		List<ProcessoNegocio> processosDeNegocio = new ArrayList<ProcessoNegocio>();
		processosDeNegocio = inicializaServicos.inicializarServicosBPMN();
		
		for(ProcessoNegocio processoNegocio: processosDeNegocio) {
			for(Tarefa tarefa: processoNegocio.getListaTarefas()) {
				for(Servico servico: tarefa.getListaServicos()) {
					double disponibilidadeAtual = invocarServicosDisponibilidade.invocarServicoDois(servico);
					
					
					System.out.println("\n\n\n\n\n-----------------------------------------------------------------------");
					FileWriter fstream;
					try {
						fstream = new FileWriter("Execucao2.txt", true);
						BufferedWriter out = new BufferedWriter(fstream);
						out.write("\n"+servico.getNome()+":"+disponibilidadeAtual+"\n");
						out.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					
					
					for(Atributo atributo: servico.getAtributos()) {
						if(atributo.getNome().equals("Disponibilidade")) {
							atributo.setValor(disponibilidadeAtual);
						}
					}
					
				}
			}
		}
	}
	
	public static void main(String []Args) {
		MonitorDisponibilidade md = new MonitorDisponibilidade();
		md.monitorarDisponibilidadeDois();
	}
}
