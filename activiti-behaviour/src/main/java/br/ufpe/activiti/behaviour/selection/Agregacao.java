package br.ufpe.activiti.behaviour.selection;

import br.ufpe.activiti.behaviour.model.Atributo;
import br.ufpe.activiti.behaviour.model.Servico;

public class Agregacao {

	public void simplesAdicaoDePeso(SelecionaServicos servicos) {
		Double fator = 0.0;
		Double auxiliar = 0.0;
		
		for (Servico servico : servicos.getServicos()) {
			fator = 0.0;
			auxiliar = 0.0;
			
			for(Atributo atributo: servico.getAtributos()) {
				auxiliar = atributo.getValor() * servicos.getPesos().get(atributo.getNome());
				fator += auxiliar;
			}
			
			servico.setFator(fator);
		}
	}
	
	public void distanciaEuclidiana(SelecionaServicos servicos) {
		Double fator = 0.0;
		Double auxiliar = 0.0;
		
		for (Servico servico : servicos.getServicos()) {
			fator = 0.0;
			auxiliar = 0.0;
			
			for(Atributo atributo: servico.getAtributos()) {
				auxiliar = atributo.getValor() - servicos.getValores().get(atributo.getNome());
				auxiliar = Math.pow(auxiliar, 2);
				fator += auxiliar;
			}
			
			fator = Math.sqrt(fator);
			
			servico.setFator(fator);
		}
	}
}
