package br.ufpe.activiti.behaviour.gestaoLTL;

public class MapemanetoFormulasLTL {
	
	public String recursoParaUmaAtividade (String servico, String operacao, String atividade) {
		return "\" (<>(((?res1{"+servico+":"+operacao+"})"+" /\\ (?act1{"+atividade+"}))))\"";
	}
	
	public String eventualmenteAcontece (String servico, String operacao, String atividade){
		return "\" (<>(((?res1{"+servico+":"+operacao+"})"+" /\\ (?act1{"+atividade+"}))))\"";
	}
	
	public String eventualmenteNaoAcontece (String servico, String operacao, String atividade) {
		return "\" !(<>(((?res1{"+servico+":"+operacao+"})"+" /\\ (?act1{"+atividade+"}))))\"";
	}
	
	public String naoInicializa (String servico, String operacao, String atividade) {
		return "\" ((((?res1{"+servico+":"+operacao+"})"+" /\\ (?act1{"+atividade+"}))))\"";
	}

	
	
}
