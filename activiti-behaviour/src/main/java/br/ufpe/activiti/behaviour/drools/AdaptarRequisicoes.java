package br.ufpe.activiti.behaviour.drools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class AdaptarRequisicoes {
	
	public void requisitarDrools(Map<String, Double> mapaDeAnalisePlanejador){
		ArrayList<DroolsEncoding> codigosParaPlanejamento = new ArrayList<DroolsEncoding>();
		codigosParaPlanejamento = criarArrayDeCodigosParaPlanejamento(mapaDeAnalisePlanejador);
		executarDrools(codigosParaPlanejamento);
	}
	
	public ArrayList<DroolsEncoding> criarArrayDeCodigosParaPlanejamento(Map<String, Double> mapaDeAnalisePlanejador) {
		ArrayList<DroolsEncoding> codigosParaPlanejamento = new ArrayList<DroolsEncoding>();
		DroolsEncoding codigo = null;
		
		for (Iterator<Entry<String, Double>> iterator = mapaDeAnalisePlanejador.entrySet().iterator(); iterator.hasNext();) {
			Entry<String, Double> entry = iterator.next();
			codigo = new DroolsEncoding();
			codigo.setPromValue(entry.getValue());
			String propriedade[] = entry.getKey().split(":");
			codigo.setActivityName(propriedade[0]);
			codigo.setPropertyType(propriedade[3]);
			codigosParaPlanejamento.add(codigo);
		}
		
		return codigosParaPlanejamento;
	}
	
	public void executarDrools(ArrayList<DroolsEncoding> codigosParaPlanejamento){
		// Padronização Drools
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");
    	
    	for (DroolsEncoding codigosParaInserirNoDrools : codigosParaPlanejamento ) {
    		kSession.insert(codigosParaInserirNoDrools);
		}

    	kSession.fireAllRules();
	}
	
	public static void main(String args[]) {
		Map<String, Double> mapaDeAnalise = new HashMap<String, Double>();
		ArrayList<Double> inteiros = new ArrayList<Double>();
		mapaDeAnalise.put("1:2:1:Init",1.0);
		
		AdaptarRequisicoes planejar = new AdaptarRequisicoes();
		planejar.requisitarDrools(mapaDeAnalise);
	}
}