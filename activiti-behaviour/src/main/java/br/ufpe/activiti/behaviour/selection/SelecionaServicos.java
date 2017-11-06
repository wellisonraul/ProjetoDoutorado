package br.ufpe.activiti.behaviour.selection;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import br.ufpe.activiti.behaviour.model.Servico;

public class SelecionaServicos{
	CopyOnWriteArrayList<Servico> servicos = new CopyOnWriteArrayList<Servico>();
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
	
}