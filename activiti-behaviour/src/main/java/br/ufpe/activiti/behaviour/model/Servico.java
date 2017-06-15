package br.ufpe.activiti.behaviour.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Servico {
	private int id;
	private String nome;
	private String wsdl;
	private String operacao;
	Map<String, Object> listaParametros = new HashMap<String, Object>();
	private String tarefa;
	private double disponibilidade;
	private double fator;
	
	
	// MÉTODOS GET E SET
	
	
	public int getId() {
		return id;
	}
	public String getWsdl() {
		return wsdl;
	}
	public void setWsdl(String wsdl) {
		this.wsdl = wsdl;
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
	public String getOperacao() {
		return operacao;
	}
	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}
	
	public Map<String, Object> getListaParametros() {
		return listaParametros;
	}
	public void setListaParametros(Map<String, Object> listaParametros) {
		this.listaParametros = listaParametros;
	}
	public String getTarefa() {
		return tarefa;
	}
	public void setTarefa(String tarefa) {
		this.tarefa = tarefa;
	}
	public double getDisponibilidade() {
		return disponibilidade;
	}
	public void setDisponibilidade(double disponibilidade) {
		this.disponibilidade = disponibilidade;
	}
	public double getFator() {
		return fator;
	}
	public void setFator(double fator) {
		this.fator = fator;
	}
	
	
}
