package br.ufpe.activiti.behaviour.selection;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import br.ufpe.activiti.behaviour.model.Servico;
import br.ufpe.activiti.behaviour.model.Tarefa;

public class SelecionaTarefa {
	CopyOnWriteArrayList<Tarefa> tarefas = new CopyOnWriteArrayList<Tarefa>(); 
	
	public void addTarefa(String nome, String id, CopyOnWriteArrayList<Servico> servicos) {
		Tarefa tarefa = new Tarefa();
		tarefa.setId(id);
		tarefa.setNome(nome);
		tarefa.setListaServicos(servicos);
		this.addTarefa(tarefa);

	}

	synchronized public void addTarefa(Tarefa tarefa) {
		tarefas.add(tarefa);
	}

	public CopyOnWriteArrayList<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(CopyOnWriteArrayList<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}
	
	
}
