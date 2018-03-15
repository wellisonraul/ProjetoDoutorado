package br.ufpe.activiti.behaviour.selection;

import br.ufpe.activiti.behaviour.model.Atributo;
import br.ufpe.activiti.behaviour.model.Servico;


public class Normalizacao {
	
	public double normalizarAtributoParaDistanciaEuclidiana(double valorMaximo, double valorMinimo, boolean tipo, double valor) {
		if (!tipo) {
			Double qNormalized = 0.0;
			Double qValue = valor;
			
			if (valorMaximo - valorMinimo != 0) {
				qNormalized = (valorMaximo - qValue) / (valorMaximo - valorMinimo);
			} else {
				qNormalized = 1.0;
			}
			
			return qNormalized;
		
		}else {
			Double qNormalized = 0.0;
			Double qValue = valor;
			
			if (valorMaximo - valorMinimo != 0) {
				qNormalized = (qValue - valorMinimo) / (valorMaximo - valorMinimo);
			} else {
				qNormalized = 1.0;
			}
			
			return qNormalized;
		}
	}
	
	public void normalizarListaDeAtributos(Servico servico, double valorMaximo, double valorMinimo, int interacao) {
		
		Atributo atributo = servico.getAtributos().get(interacao);
		
			if (!atributo.getEMelhorOValorMaior()) {
				Double qNormalized = 0.0;
				Double qValue = atributo.getValor();
				
				if (valorMaximo - valorMinimo != 0) {
					qNormalized = (valorMaximo - qValue) / (valorMaximo - valorMinimo);
				} else {
					qNormalized = 1.0;
				}
				
				atributo.setValor(qNormalized);
			
			}else {
				Double qNormalized = 0.0;
				Double qValue = atributo.getValor();
				
				if (valorMaximo - valorMinimo != 0) {
					qNormalized = (qValue - valorMinimo) / (valorMaximo - valorMinimo);
				} else {
					qNormalized = 1.0;
				}
				
				servico.getAtributos().get(interacao).setValor(qNormalized);
			}
	}

}
