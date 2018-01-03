package br.ufpe.activiti.behaviour.gestaoLTL;

public class MapemanetoFormulasLTL {
	
	public String RecursoParaUmaAtividade(String servico, String operacao, String atividade) {
		return "\" (<>(((?res1{"+servico+":"+operacao+"})"+" /\\ (?act1{"+atividade+"}))))\"";
	}
	
}
