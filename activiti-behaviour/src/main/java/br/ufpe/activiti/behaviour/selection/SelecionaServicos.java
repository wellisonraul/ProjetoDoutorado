package br.ufpe.activiti.behaviour.selection;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import br.ufpe.activiti.behaviour.model.Atributo;
import br.ufpe.activiti.behaviour.model.ProcessoNegocio;
import br.ufpe.activiti.behaviour.model.Servico;
import br.ufpe.activiti.behaviour.model.Tarefa;
import br.ufpe.activiti.behaviour.monitorDisponibilidade.MonitorDisponibilidade;
import br.ufpe.activiti.behaviour.util.Util;

public class SelecionaServicos{
	CopyOnWriteArrayList<Servico> servicos = new CopyOnWriteArrayList<Servico>();
	MonitorDisponibilidade monitor = new MonitorDisponibilidade();
	
	private Map<String, Double> valores;
	private Map<String, Double> pesos;
	
	public SelecionaServicos() {
		valores = new HashMap<String,Double>();
		valores.put("Disponibilidade", 0.93);
		valores.put("TempoDeResposta", 10.0);
		
		pesos = new HashMap<String,Double>();
		pesos.put("Disponibilidade", 2.0);
		pesos.put("TempoDeResposta", 1.5);	
	}
	
	public Map<String, Double> getValores() {
		return valores;
	}
	
	public void setValores(Map<String, Double> valores) {
		this.valores = valores;
	}

	public Map<String, Double> getPesos() {
		return pesos;
	}
	
	public void setPesos(Map<String, Double> pesos) {
		this.pesos = pesos;
	}

	public void addService(String wsdl, String operation) {
		Servico service = new Servico();
		service.setOperacao(operation);
		service.setWsdl(wsdl);
		this.addService(service);
	}

	public void addService(Servico service) {
		servicos.add(service);
	}
	
	public CopyOnWriteArrayList<Servico> getServicos() {
		return servicos;
	}
	
	public void setServicos(CopyOnWriteArrayList<Servico> servicos) {
		this.servicos = servicos;
	}

	public Servico retorneMelhorServico() {
		return this.servicos.get(0);
	}

	public int retorneTamanhoServicos(){
		return this.servicos.size();
	}
	
	public Servico proximoServico(int posicao){
		return this.servicos.get(posicao);
	}
	
	public void reordenarServico(){
		Servico servicoAuxiliar = servicos.get(0);
		for(int i=0; i<(servicos.size()-1); i++) {
			servicos.set(i, servicos.get(i+1));
		}
		servicos.set(servicos.size(), servicoAuxiliar);
	}
	
	public void atualizacaoServicos() {
		for(ProcessoNegocio processoNegocio: Util.processosNegocio) {
			for(Tarefa tarefa: processoNegocio.getListaTarefas()) {
				for(Servico servico: tarefa.getListaServicos()) {
					for(Servico esteServico: servicos) {
						if(servico.getNome().equals(esteServico.getNome())) {
							for(Atributo atributo: servico.getAtributos()) {
								if(atributo.getNome().equals("Disponibilidade")) {
									if(atributo.getValor()==-1) {
										Util.atualizacaoDisponibilidade = false;
									}else {
										for(Atributo at: esteServico.getAtributos()) {
											if(at.getNome().equals("Disponibilidade")) {
												System.out.println(esteServico.getNome()+":"+at.getValor());
												at.setValor(atributo.getValor());
												atributo.setValor(-1);
											}
										}
										
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	public SelecionaServicos adaptacaoServicos(SelecionaServicos selecionaServicos) {
		if(Util.processoAdaptacao.contains(selecionaServicos.getServicos().get(0).getTarefa())){
			Ordenacao ordenacao = new Ordenacao();
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n ENTRA \n\n\n\n\n\n\n");
			if(Util.METODODEAGREGACAO==1) ordenacao.ordenarAdicaoPeso(selecionaServicos);
			Util.processoAdaptacao.remove(selecionaServicos.getServicos().get(0).getTarefa());
		
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n SAI \n\n\n\n\n\n\n");
			
		}else if(Util.processoAdaptacao.isEmpty()) {
			Util.atualizacaoLTLMiner = false;
		}
		
		
		return selecionaServicos;
	}
}