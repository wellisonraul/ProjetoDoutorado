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
	
	public void ordenacaoServicos(){
		
	}
	
	public Servico retorneMelhorServico() {
		return this.servicos.get(0);
	}

	public int retorneTamanhoServicos(){
		return this.servicos.size();
	}
	
	public void ordenarServicos() {
		// Lista de serviços maior que 1? 
		if(servicos.size()>1){
			// BUBBLE SORT
			for(int i = 0; i<servicos.size(); i++){
				for(int j = 0; j<servicos.size()-1; j++){
					if(servicos.get(j).getDisponibilidade() < servicos.get(j + 1).getDisponibilidade()){
						Servico aux = servicos.get(j);
						servicos.set(j, servicos.get(j+1));
						servicos.set(j+1, aux);
					}
				}
			}	
		}
	}
	
	public Servico proximoServico(int posicao){
		return this.servicos.get(posicao);
	}
}