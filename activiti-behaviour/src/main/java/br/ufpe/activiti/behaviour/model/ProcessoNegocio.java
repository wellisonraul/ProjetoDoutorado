package br.ufpe.activiti.behaviour.model;

import java.util.List;

public class ProcessoNegocio {
	private String id;
	private String nome;
	private List<Tarefa> listaTarefas;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Tarefa> getListaTarefas() {
		return listaTarefas;
	}
	public void setListaTarefas(List<Tarefa> listaTarefas) {
		this.listaTarefas = listaTarefas;
	}
}
