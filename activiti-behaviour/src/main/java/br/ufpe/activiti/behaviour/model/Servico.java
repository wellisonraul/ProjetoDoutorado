package br.ufpe.activiti.behaviour.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Servico {
	private String nome;
	private String wsdl;
	private String operacao;
	private Map<String, Object> listaParametros = new HashMap<String, Object>();
	private ArrayList<Object> listaParametrosDisponibilidade = new ArrayList<Object>();
	private String tarefa;
	private double disponibilidade;
	private double fator;
	private QualidadeDoServico qualidadeDoServico;
	private ArrayList<Atributo> atributos;
	private int quantidadeParametros;
	
	
	public ArrayList<Object> getListaParametrosDisponibilidade() {
		return listaParametrosDisponibilidade;
	}
	public void setListaParametrosDisponibilidade(ArrayList<Object> listaParametrosDisponibilidade) {
		this.listaParametrosDisponibilidade = listaParametrosDisponibilidade;
	}
	public int getQuantidadeParametros() {
		return quantidadeParametros;
	}
	public void setQuantidadeParametros(int quantidadeParametros) {
		this.quantidadeParametros = quantidadeParametros;
	}
	public ArrayList<Atributo> getAtributos() {
		return atributos;
	}
	public void setAtributos(ArrayList<Atributo> atributos) {
		this.atributos = atributos;
	}
	public QualidadeDoServico getQualidadeDoServico() {
		return qualidadeDoServico;
	}
	public void setQualidadeDoServico(QualidadeDoServico qualidadeDoServico) {
		this.qualidadeDoServico = qualidadeDoServico;
	}
	public String getWsdl() {
		return wsdl;
	}
	public void setWsdl(String wsdl) {
		this.wsdl = wsdl;
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
