package br.ufpe.activiti.behaviour.model;

import java.util.concurrent.CopyOnWriteArrayList;

import br.ufpe.activiti.behaviour.model.Servico;

public interface InterfaceSelecionaServico{
	
	public void addService(String wsdl, String operation);
	public Servico getTheBestService();
	public Servico getServiceByIndex(int i);
	public CopyOnWriteArrayList<Servico> getServices();
	public String getProcessId();
	public String getActivityId();

	public void serviceOrdering();
	CopyOnWriteArrayList<Servico> getServiceList();
}