package br.ufpe.activiti.behaviour.selection;

import java.util.List;

import br.ufpe.activiti.behaviour.model.ProcessoNegocio;
import br.ufpe.activiti.behaviour.model.Servico;
import br.ufpe.activiti.behaviour.model.Tarefa;

public class Reordenador {
	
	public void reordenar(List<ProcessoNegocio> processosDeNegocios, String identificaoReordenar) {
		for(ProcessoNegocio processoNegocio: processosDeNegocios) {
			for(Tarefa tarefa: processoNegocio.getListaTarefas()) {
				if(identificaoReordenar.contains(tarefa.getNome())) {
					Servico servicoAuxiliar = tarefa.getListaServicos().get(0);
					
					for(int i=0; i<(tarefa.getListaServicos().size()-1); i++) {
						tarefa.getListaServicos().set(i, tarefa.getListaServicos().get(i+1));
					}
					
					tarefa.getListaServicos().set(tarefa.getListaServicos().size()-1, servicoAuxiliar);
				}
			}
		}
	}
	
}
