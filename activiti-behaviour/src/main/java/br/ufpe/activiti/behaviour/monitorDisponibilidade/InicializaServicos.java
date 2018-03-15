package br.ufpe.activiti.behaviour.monitorDisponibilidade;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import br.ufpe.activiti.behaviour.conversores.conversorBPMNObjetos;
import br.ufpe.activiti.behaviour.juddi.BuscaJuddi;
import br.ufpe.activiti.behaviour.model.ProcessoNegocio;
import br.ufpe.activiti.behaviour.model.Servico;
import br.ufpe.activiti.behaviour.model.Tarefa;
import br.ufpe.activiti.behaviour.util.Util;

public class InicializaServicos {
	CopyOnWriteArrayList<Servico> servicos = new CopyOnWriteArrayList<Servico>();
	
	public List<ProcessoNegocio> inicializarServicosBPMN() {
		conversorBPMNObjetos conversorBO = new conversorBPMNObjetos();
		List<ProcessoNegocio> processosDeNegocio = new ArrayList<ProcessoNegocio>();
		processosDeNegocio = conversorBO.converterBPMNObjetos(Util.arquivoBPMN);
		
		BuscaJuddi buscaJuddi = new BuscaJuddi();
		
		for(ProcessoNegocio processoNegocio: processosDeNegocio) {
			for(Tarefa tarefa: processoNegocio.getListaTarefas()) {
				for(Servico servico: tarefa.getListaServicos()) {
					buscaJuddi.setServiceWSDLKey(servico, servico.getNome());
				}
			}
		}
		
		return processosDeNegocio;
	}
}
