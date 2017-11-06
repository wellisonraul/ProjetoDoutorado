package br.ufpe.activiti.behaviour.ciclo.mapek;

import java.util.HashMap;
import java.util.Map;

import org.deckfour.xes.model.XLog;

import ee.tkasekamp.ltlminer.StarterTest;

public class Analisador {
	
	public Map<String, Double> Analisar(XLog log) {
		
		StarterTest ltlMiner = new StarterTest();
		Map<String, Double> mapaDeAnalise = new HashMap<>();
		
		try {
			mapaDeAnalise = ltlMiner.testBehaviour(log); // Invoca LTLMiner
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return mapaDeAnalise;
	}
}
