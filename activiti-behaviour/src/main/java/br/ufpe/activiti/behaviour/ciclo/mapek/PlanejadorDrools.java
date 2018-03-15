package br.ufpe.activiti.behaviour.ciclo.mapek;

import java.util.Map;

import br.ufpe.activiti.behaviour.drools.AdaptarRequisicoes;

public class PlanejadorDrools {
	public void Planejar(Map<String, Double> mapaDeAnalise) {
		AdaptarRequisicoes drools = new AdaptarRequisicoes();
		drools.requisitarDrools(mapaDeAnalise);
	}
}
