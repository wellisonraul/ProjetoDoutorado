package br.ufpe.behaviour.mapeamento;

public class Mapeamento {
	
	public String eventualmenteTodasAtividadesAcontecem() {
		return "\" <>(?act1)\"";
	}
	
	public String eventualmenteAtividadeAcontece(String atividade) {
		return "\" <>(?act1("+atividade+")\"";
	}
}
