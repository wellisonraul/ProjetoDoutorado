package br.ufpe.activiti.behaviour.util;

import java.util.List;

import br.ufpe.activiti.behaviour.model.ProcessoNegocio;

public class Util {
	public static String arquivoXES = "/home/wellisonraul/eclipse-workspace/Doutorado/activiti-behaviour/traceExecucao.xes";
	public static String arquivoBPMN = "/home/wellisonraul/Desenvolvimento/Eclipse/activiti-behaviour/src/main/resources/processPizza.bpmn20.xml";
	public static String monitorDriverMySQL = "/home/wellisonraul/Desenvolvimento/Eclipse/activiti-behaviour/lib/mysql-connector-java-5.1.36.jar";
	public static int quantidadeTraces=20;
	public static boolean atualizacaoDisponibilidade = false;
	public static boolean atualizacaoLTLMiner = false;
	public static List<ProcessoNegocio> processosNegocio = null;
	public static List<String> processoAdaptacao = null;
	// 1 = Pesos ou 2 = Valores
	public static final int METODODEAGREGACAO=1;
}
