package br.ufpe.activiti.behaviour.selection;

import java.util.concurrent.CopyOnWriteArrayList;

import br.ufpe.activiti.behaviour.model.Servico;

public class SelecionaServicos{
	CopyOnWriteArrayList<Servico> servicos = new CopyOnWriteArrayList<Servico>(); 
	
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
	
	
}