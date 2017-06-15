package br.ufpe.activiti.behaviour.model;

import java.util.List;

public class ProcessoNegocio {
	private int id;
	private String nome;
	private List<Tarefa> listaTarefas;
	
	// MÉTODOS GET E SET! 
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
