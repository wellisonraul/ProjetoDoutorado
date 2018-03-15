package br.ufpe.activiti.behaviour.gestaoLTL;

import java.util.HashMap;
import java.util.Properties;

import org.deckfour.xes.model.XLog;

import ee.tkasekamp.ltlminer.LTLMinerStarter;


public class GestaoLTL {
		
	public HashMap<String, Double> invocarLTLMinerBehaviour (XLog xlog, String formulaConsulta) throws Exception{
		HashMap<String, Double> mapaDeAnalise = new HashMap<String, Double>();
		
		LTLMinerStarter minerStater = new LTLMinerStarter(getProps(formulaConsulta));
		mapaDeAnalise = minerStater.mineBehaviour(xlog); // O método de análise
		
		return  (HashMap<String, Double>) mapaDeAnalise;
	}
	
	public Double invocarMinerarFormula (XLog xlog, String formulaConsulta) throws Exception{
		Double valor = null;
		
		LTLMinerStarter minerStater = new LTLMinerStarter(getProps(formulaConsulta));
		valor = minerStater.minerarFormula(xlog); // O método de análise
		
		return valor;
	}
	
	private Properties getProps(String formulaConsulta) {
		Properties props = new Properties();
		props.setProperty("considerEventTypes", "true");
		props.setProperty("minSupport", "0.0");
		props.setProperty("outputFormat", "console");
		props.setProperty("queries", formulaConsulta);
		
		return props;
	}
}
