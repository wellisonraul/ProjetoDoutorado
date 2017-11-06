package br.ufpe.activiti.behaviour.ciclo.mapek;

import java.util.Map;
import org.deckfour.xes.model.XLog;
import br.ufpe.activiti.behaviour.delegate.WsDelegate;

public class MAPEK extends Thread{

	@Override
	public void run() {
		Monitor monitor = new Monitor();
		
		Analisador analisador = new Analisador();
		Planejador planejador = new Planejador();
		PlanejadorDrools planejadordrools = new PlanejadorDrools();
		Executor executor = new Executor();
		
		try{
			//Thread.sleep(15000); // DEFINE O TEMPO PARA A PRIMEIRA INICIALIZAÇÃO DO CICLO MAPE-K
			//int instancia = WsDelegate.getProcessIDInicializacao()-1; // Tratamento de problema na inicialização do banco!
			//monitor.setProcessInstanceIDCorrente(""+instancia);
			monitor.setProcessInstanceIDCorrente("712505");
		}catch(Exception e){
			System.out.println("Houve um problema para adormecer a Thread!");
		}
		
		
		while(true) {
			XLog log = monitor.Mapear();
			Map<String, Double> mapaDeAnalise = analisador.Analisar(log);
			Map<String, Integer> mapaDeAnalisePlanejador = planejador.Planejar(mapaDeAnalise);
			Map<String, Integer> mapaDeAnalisePlanejadorDrools = planejadordrools.Planejar(mapaDeAnalise);
			executor.Executar(mapaDeAnalisePlanejador);
			
			try {
				Thread.sleep(10000);
			}catch(Exception e) {
				System.out.println("Houve um problema para adormecer a Thread!");
			}
		}
	}
	
}
