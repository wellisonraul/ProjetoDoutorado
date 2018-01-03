package br.ufpe.activiti.behaviour.dao;

import java.util.Date;

import org.activiti.engine.delegate.DelegateExecution;

import br.ufpe.activiti.behaviour.model.ProcessoLog;
import br.ufpe.activiti.behaviour.model.Servico;

public class DefineProcessLog {
	
	public void inserirLog(DelegateExecution execution, Servico servico, Object object) {
		// DEFINE O QUE VAI SER INSERIDO!
		
		ProcessoLog processLog = new ProcessoLog();
		
		processLog.setProcessId(execution.getProcessDefinitionId());
		processLog.setProcessInstanceId(execution.getProcessInstanceId());
		processLog.setActivityId(execution.getCurrentActivityId());
		processLog.setActivityName(servico.getTarefa());
		Date date = new Date();
		processLog.setResource(servico.getNome()+":"+servico.getOperacao());
		processLog.setTimestamp(date);
		ActivityDAO activityDAO = new ActivityDAO();
		
		if(object==null) processLog.setEventType("start");
		else processLog.setEventType("complete");
		
		activityDAO.insertActivityLog(processLog);
	}
	
	
	
	
}
