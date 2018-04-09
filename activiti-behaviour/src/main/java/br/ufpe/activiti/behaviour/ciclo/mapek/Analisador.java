package br.ufpe.activiti.behaviour.ciclo.mapek;

import java.util.HashMap;
import java.util.LinkedHashMap;
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
		Map<String, Double> mapaDeAnalise = new LinkedHashMap<String, Double>();
		MapemanetoFormulasLTL mapemanetoFormulasLTL = new MapemanetoFormulasLTL();
		
		String formulaConsulta = mapemanetoFormulasLTL.eventualmenteAcontece
				("AuthenticationServiceOne", "authentication", "ClientAuthentication");
		String formulaConsulta2 = mapemanetoFormulasLTL.eventualmenteAcontece
				("AuthenticationServiceTwo", "authenticationTwo", "ClientAuthentication");
		
		try {
			Double valor = gestaoLTL.invocarMinerarFormula(xlog,formulaConsulta);
			Double valor2 = gestaoLTL.invocarMinerarFormula(xlog,formulaConsulta2);
			
			if(valor==null) mapaDeAnalise.put("ClientAuthentication:AuthenticationServiceOne:authentication:Existence", valor);	
			if(valor2==null) mapaDeAnalise.put("ClientAuthentication:AuthenticationServiceTwo:authenticationTwo:Existence", valor2);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*for(ProcessoNegocio processoNegocio: processosDeNegocios) {
			for(Tarefa tarefa: processoNegocio.getListaTarefas()) {
				for(Servico servico: tarefa.getListaServicos()) {	
					
					formulaConsulta = mapemanetoFormulasLTL.recursoParaUmaAtividade
							(servico.getNome(), servico.getOperacao(), tarefa.getNome());
					try {
						Double valor = gestaoLTL.invocarMinerarFormula(xlog,formulaConsulta);
						
						if(valor!=null) {
							mapaDeAnalise.put(tarefa.getNome()+":"+servico.getNome()+":"+servico.getOperacao(), valor);
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}*/
		
		return mapaDeAnalise;
	}

}