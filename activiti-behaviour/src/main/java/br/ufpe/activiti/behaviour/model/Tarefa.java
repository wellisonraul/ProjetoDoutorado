package br.ufpe.activiti.behaviour.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Tarefa {
	private String id;
	private String nome;
	private CopyOnWriteArrayList<Servico> listaServicos;
	
	// MÉTODOS GET E SET
	public String getNome() {
		return nome;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public CopyOnWriteArrayList<Servico> getListaServicos() {
		return listaServicos;
	}

	public void setListaServicos(CopyOnWriteArrayList<Servico> listaServicos) {
		this.listaServicos = listaServicos;
	}

	public String getId() {
		return id;
	}
}
