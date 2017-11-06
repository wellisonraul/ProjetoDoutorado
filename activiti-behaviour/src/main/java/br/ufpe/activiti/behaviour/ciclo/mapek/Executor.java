package br.ufpe.activiti.behaviour.ciclo.mapek;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Executor {
	public static void Executar(Map<String, Integer> mapaDeAnalisePlanejador) {
		for (Iterator<Entry<String, Integer>> iterator = mapaDeAnalisePlanejador.entrySet().iterator(); iterator.hasNext();) {
			Entry<String, Integer> entry = iterator.next();
			int cod = (int) entry.getValue();
			if(cod==1) {
				System.out.println("Executor está trabalhando");
			}else{
				System.out.println("Executor não está trabalhando");
			}
		}
		
		System.out.println("Executor finalizou!");
	}
}
