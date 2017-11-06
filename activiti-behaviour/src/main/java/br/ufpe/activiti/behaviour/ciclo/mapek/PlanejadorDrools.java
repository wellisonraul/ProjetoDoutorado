package br.ufpe.activiti.behaviour.ciclo.mapek;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;


public class PlanejadorDrools {
	public Map<String, Integer> Planejar(Map<String, Double> mapaDeAnalise) {
		Map<String, Integer> mapa = new HashMap<>(); // Objeto para conter resultados!
		ArrayList<Double> analises = new ArrayList<Double>();
		
		
		for (Iterator<Entry<String, Double>> iterator = mapaDeAnalise.entrySet().iterator(); iterator.hasNext();) {
			Entry<String, Double> entry = iterator.next();
			double x = (double) entry.getValue();
			analises.add(x);
		}
		
		//Planejamento planejar = new Planejamento();
		//planejar.RequisitarDrools(analises);
		
		System.out.println("Planejamento concluído!");
		return mapa;
	}
}
