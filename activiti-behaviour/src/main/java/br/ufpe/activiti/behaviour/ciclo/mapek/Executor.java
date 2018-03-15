package br.ufpe.activiti.behaviour.ciclo.mapek;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import br.ufpe.activiti.behaviour.model.ProcessoNegocio;
import br.ufpe.activiti.behaviour.selection.Reordenador;
import br.ufpe.activiti.behaviour.util.Util;

public class Executor {
	static Map<String, Integer> adaptacoes;
	static Map<String, Integer> quarentena;
	
	public Executor() {
		adaptacoes = new LinkedHashMap<String, Integer>();
		quarentena = new LinkedHashMap<String, Integer>();
	}
	
	
	
	public void Executar(Map<String, Integer> mapaDeAnalisePlanejador, List<ProcessoNegocio> processosDeNegocios) {
		Reordenador reordenar = new Reordenador();
		for (Iterator<Entry<String, Integer>> iterator = mapaDeAnalisePlanejador.entrySet().iterator(); iterator.hasNext();) {
			Entry<String, Integer> entry = iterator.next();
			int cod = (int) entry.getValue();
			if(cod==1) {
				System.out.println("Executor está trabalhando");
				reordenar.reordenar(processosDeNegocios, entry.getKey());
			}else{
				System.out.println("Executor não está trabalhando");
			}
		}
		
		System.out.println("Executor finalizou!");
	}
	
	public static void executorDrools(String activitiName, int codigo){
		if(codigo==1) {
			System.out.println("Executor está trabalhando para correção");
			adaptacoes.put(activitiName, codigo);
			System.out.println(adaptacoes.toString());
		}else if(codigo==2) {
			if(quarentena.containsKey(activitiName)) {
				quarentena.remove(activitiName);
				adaptacoes.put(activitiName,1);
				System.out.println(adaptacoes.toString());
			}else {
				quarentena.put(activitiName, codigo);
				System.out.println(quarentena.toString());
			}
			System.out.println("Executor está trabalhando para marcação de problemas");
		}else{
			System.out.println("Executor não está trabalhando");
		}
	}
	
	public static void ativarAdaptacao() {
		if(adaptacoes.isEmpty()) {
			System.out.println("Nenhuma adaptação necessária!");
		}else {
			ArrayList<String> listaParaAdaptacao = new ArrayList<String>();
			for (Iterator<Entry<String, Integer>> iterator = adaptacoes.entrySet().iterator(); iterator.hasNext();) {
				Entry<String, Integer> entry = iterator.next();
				listaParaAdaptacao.add(entry.getKey());
			}
			
			adaptacoes = new LinkedHashMap<String, Integer>();
			Util.atualizacaoLTLMiner = true;
			Util.processoAdaptacao = listaParaAdaptacao;
			
			System.out.println(listaParaAdaptacao.toString());
		}
	}
}
