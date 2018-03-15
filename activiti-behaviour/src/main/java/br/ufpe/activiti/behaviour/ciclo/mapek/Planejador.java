package br.ufpe.activiti.behaviour.ciclo.mapek;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Planejador {
	Executor executor = new Executor();
	public Map<String, Integer>  Planejar(Map<String, Double> mapaDeAnalise){
		
		Map<String, Integer> mapa = new HashMap<String, Integer>(); // Objeto para conter resultados!
		
		for (Iterator<Entry<String, Double>> iterator = mapaDeAnalise.entrySet().iterator(); iterator.hasNext();) {
			Entry<String, Double> entry = iterator.next();
			double x = (double) entry.getValue();
			if(x>=0 && x<=0.9) {
				System.out.println("É necessário realizar adaptação no serviço");
				System.out.println(entry.getKey() + " -> " + entry.getValue());
				mapa.put((String) entry.getKey(), 1);
			}else {
				System.out.println("Não é necessário realizar adaptação no serviço");
				System.out.println(entry.getKey() + " -> " + entry.getValue());
				mapa.put((String) entry.getKey(), 0);
			}
		}
		
		System.out.println("Planejamento concluído!");
		
		return mapa;
	}
}
