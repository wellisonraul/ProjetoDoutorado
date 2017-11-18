package br.ufpe.activiti.behaviour.drools;

import java.util.ArrayList;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class AdaptarRequisicoes {
	
	public void requisitarDrools(ArrayList<Double> codigosParaAnalise){
		ArrayList<Codigo> codigosParaPlanejamento = new ArrayList<Codigo>();
		codigosParaPlanejamento = criarArrayDeCodigosParaPlanejamento(codigosParaAnalise);
		executarDrools(codigosParaPlanejamento);
	}
	
	public ArrayList<Codigo> criarArrayDeCodigosParaPlanejamento(ArrayList<Double> codigosParaAnalise) {
		ArrayList<Codigo> codigosParaPlanejamento = new ArrayList<Codigo>();
		Codigo codigo = null;
		
		for (Double codigoParaAnalise : codigosParaAnalise) {
			codigo = new Codigo();
			codigo.setCodigoParaAnalise(codigoParaAnalise);
			codigosParaPlanejamento.add(codigo);
		}
		
		return codigosParaPlanejamento;
	}
	
	public void executarDrools(ArrayList<Codigo> codigosParaPlanejamento){
		// Padronização Drools
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");
    	
    	for (Codigo codigosParaInserirNoDrools : codigosParaPlanejamento ) {
    		kSession.insert(codigosParaInserirNoDrools);
		}

    	kSession.fireAllRules();
	}
	
	public static void main(String args[]) {
		ArrayList<Double> inteiros = new ArrayList<Double>();
		inteiros.add(0.1);
		inteiros.add(0.5);
		inteiros.add(0.85);
		inteiros.add(0.95);
		inteiros.add(1.0);
		
		AdaptarRequisicoes planejar = new AdaptarRequisicoes();
		planejar.requisitarDrools(inteiros);
	}
}