package br.ufpe.activiti.behaviour.ciclo.mapek;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.deckfour.xes.model.XLog;
import br.ufpe.activiti.behaviour.conversores.conversorBPMNObjetos;
import br.ufpe.activiti.behaviour.delegate.WsDelegate;
import br.ufpe.activiti.behaviour.model.ProcessoNegocio;
import br.ufpe.activiti.behaviour.util.Util;

public class MAPEK extends Thread{

	@Override
	public void run() {
		conversorBPMNObjetos conversorBO = new conversorBPMNObjetos();
		List<ProcessoNegocio> processosDeNegocios = new ArrayList<ProcessoNegocio>();
		processosDeNegocios = conversorBO.converterBPMNObjetos(Util.arquivoBPMN);
		
		Monitor monitor = new Monitor();
		Analisador analisador = new Analisador();
		Planejador planejador = new Planejador();
		PlanejadorDrools planejadordrools = new PlanejadorDrools();
		Executor executor = new Executor();
		
		try{
			Thread.sleep(40000); // DEFINE O TEMPO PARA A PRIMEIRA INICIALIZAÇÃO DO CICLO MAPE-K
			int instancia = WsDelegate.getProcessIDInicializacao()-1; // Tratamento de problema na inicialização do banco!
			monitor.setProcessInstanceIDCorrente(""+instancia);
		}catch(Exception e){
			System.out.println("Houve um problema para adormecer a Thread!");
		}
		
		
		while(true) {
			XLog log = monitor.Mapear();
			if(log!=null) {
				Map<String, Double> mapaDeAnalise = analisador.Analisar(log,processosDeNegocios);
				//Map<String, Integer> mapaDeAnalisePlanejador = planejador.Planejar(mapaDeAnalise);
				planejadordrools.Planejar(mapaDeAnalise);
				//executor.Executar(mapaDeAnalisePlanejador,processosDeNegocios);
			}
			
			try {
				Thread.sleep(40000);
			}catch(Exception e) {
				System.out.println("Houve um problema para adormecer a Thread!");
			}
		}
	}
	
}
