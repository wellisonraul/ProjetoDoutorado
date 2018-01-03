package br.ufpe.activiti.behaviour.ciclo.mapek;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.deckfour.xes.model.XLog;
import br.ufpe.activiti.behaviour.gestaoLTL.GestaoLTL;
import br.ufpe.activiti.behaviour.gestaoLTL.MapemanetoFormulasLTL;
import br.ufpe.activiti.behaviour.model.ProcessoNegocio;
import br.ufpe.activiti.behaviour.model.Servico;
import br.ufpe.activiti.behaviour.model.Tarefa;

public class Analisador {
	
	public Map<String, Double> Analisar(XLog xlog, List<ProcessoNegocio> processosDeNegocios) {
		
		GestaoLTL gestaoLTL = new GestaoLTL();
		Map<String, Double> mapaDeAnalise = new HashMap<>();
		MapemanetoFormulasLTL mapemanetoFormulasLTL = new MapemanetoFormulasLTL();
		
		for(ProcessoNegocio processoNegocio: processosDeNegocios) {
			for(Tarefa tarefa: processoNegocio.getListaTarefas()) {
				for(Servico servico: tarefa.getListaServicos()) {	
					
					System.out.println(servico.getNome()+": "+servico.getListaParametros().size());
					
					String formulaConsulta = mapemanetoFormulasLTL.RecursoParaUmaAtividade
							(servico.getNome(), servico.getOperacao(), tarefa.getNome());
					try {
						Double valor = gestaoLTL.invocarMinerarFormula(xlog,formulaConsulta);
						
						if(valor!=null) {
							mapaDeAnalise.put(tarefa.getNome()+":"+servico.getNome()+":"+servico.getOperacao(), valor);
							//mapaDeAnalise.put(tarefa.getNome(), valor);
						}
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		
		return mapaDeAnalise;
	}
	
	public Map<String, Double> Analisar(XLog xlog) {
		
		GestaoLTL gestaoLTL = new GestaoLTL();
		Map<String, Double> mapaDeAnalise = new HashMap<>();
		
		try {
			//mapaDeAnalise = gestaoLTL.invocarLTLMiner(xlog); // Invoca LTLMiner
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return mapaDeAnalise;
	}
}
