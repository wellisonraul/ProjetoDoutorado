package br.ufpe.activiti.behaviour.ciclo.mapek;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import br.ufpe.activiti.behaviour.model.ProcessoNegocio;
import br.ufpe.activiti.behaviour.selection.Reordenador;

public class Executor {
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
	
	public static void executarDrools(int cod) {
		if(cod==1) {
			System.out.println("Executor está trabalhando");
		}else{
			System.out.println("Executor não está trabalhando");
		}
	}
}
