package br.ufpe.activiti.behaviour.selection;

import java.util.Map;
import java.util.Map.Entry;
import br.ufpe.activiti.behaviour.model.Servico;

public class Ordenacao {
	
	private Normalizacao normalizacao;
	private Agregacao agregacao;
	
	public Ordenacao() {
		super();
		this.normalizacao = new Normalizacao();
		this.agregacao = new Agregacao();
	}
	
	// Ordenação com base na agregação de adição.
	public void ordenarAdicaoPeso(SelecionaServicos servicos) {
		int tamListaServicos = servicos.getServicos().size();
		int tamListaAtributos = servicos.getServicos().get(0).getAtributos().size();
		
		for(int interarAtributo=0;interarAtributo<tamListaAtributos;interarAtributo++) {
			
			boolean primeiraInteracao = true;
			Double valorMaximo = 0.0;
			Double valorMinimo = 0.0;
			
			for(int interarServicos=0;interarServicos<tamListaServicos;interarServicos++) {
				Double valor = servicos.getServicos().get(interarServicos).getAtributos().get(interarAtributo).getValor();
				
				if (primeiraInteracao) {
					valorMaximo = valor;
					valorMinimo = valor;
					primeiraInteracao = false;
				} else {
					if (valor < valorMinimo) {
						valorMinimo = valor;
					} else if (valor > valorMaximo) {
						valorMaximo = valor;
					}
				}
			}
			normalizar(servicos,valorMaximo,valorMinimo,interarAtributo);
			
		}
		
		agregarAdiçãoPeso(servicos);
		ordenarServicosAdicaoPeso(servicos);
	}
	public void normalizar(SelecionaServicos servicos, Double valorMaximo, Double valorMinimo, int interarAtributo) {
		for (Servico servico : servicos.getServicos()) {
			this.normalizacao.normalizarListaDeAtributos(servico,valorMaximo,valorMinimo,interarAtributo);
		}
	}
	public void agregarAdiçãoPeso(SelecionaServicos servicos) {
		this.agregacao.simplesAdicaoDePeso(servicos);
	}
	public void ordenarServicosAdicaoPeso(SelecionaServicos servicos) {
		// Lista de serviços maior que 1? 
		if(servicos.getServicos().size()>1){
			// BUBBLE SORT
			for(int i = 0; i<servicos.getServicos().size(); i++){
				for(int j = 0; j<servicos.getServicos().size()-1; j++){
					if(servicos.getServicos().get(j).getFator() < servicos.getServicos().get(j+1).getFator()){
						Servico aux = servicos.getServicos().get(j);
						servicos.getServicos().set(j, servicos.getServicos().get(j+1));
						servicos.getServicos().set(j+1, aux);
					}
				}
			}	
		}
	}
	
	// Ordenação com base na distancia euclidiana.
	public void ordenarDistanciaEuclidiana(SelecionaServicos servicos){

		int tamListaServicos = servicos.getServicos().size();
		int tamListaAtributos = servicos.getServicos().get(0).getAtributos().size();
		
		for(int interarAtributo=0;interarAtributo<tamListaAtributos;interarAtributo++) {
			boolean primeiraInteracao = true;
			Double valorMaximo = 0.0;
			Double valorMinimo = 0.0;
			Double guardarValorUsuario = 0.0;
			
			for(int interarServicos=0;interarServicos<tamListaServicos;interarServicos++) {
				Double valor = servicos.getServicos().get(interarServicos).getAtributos().get(interarAtributo).getValor();
				
				if (primeiraInteracao) {
					for (Entry<String, Double> pair : servicos.getValores().entrySet()) {
					    if(pair.getKey().equals(servicos.getServicos().get(interarServicos).getAtributos().get(interarAtributo).getNome())) {
					    	valorMaximo = pair.getValue();
							valorMinimo = pair.getValue();
							guardarValorUsuario = pair.getValue();
							primeiraInteracao = false;
					    }
					}
				} 
				
					
				if (valor < valorMinimo) {
					valorMinimo = valor;
				} else if (valor > valorMaximo) {
					valorMaximo = valor;	
				}
			}
			
			normalizarDistanciaEuclidiana(servicos, valorMaximo, valorMinimo, interarAtributo,guardarValorUsuario);
		}
		
		agregarDistanciaEuclidiana(servicos);
		ordenarServicosDistanciaEuclidiana(servicos);
	}
	public void normalizarDistanciaEuclidiana(SelecionaServicos servicos, Double valorMaximo, Double valorMinimo, int interarAtributo, Double guardarValorUsuario) {
		for (Servico servico : servicos.getServicos()) {
			this.normalizacao.normalizarListaDeAtributos(servico,valorMaximo,valorMinimo,interarAtributo);
		}
		
		Double novoValorUsuario = this.normalizacao.normalizarAtributoParaDistanciaEuclidiana(valorMaximo, valorMinimo, servicos.
					getServicos().get(0).getAtributos().get(interarAtributo).getEMelhorOValorMaior(), guardarValorUsuario);
		
		Map<String,Double> atualizarValorAtributo = servicos.getValores();
		atualizarValorAtributo.put(servicos.getServicos().get(0).getAtributos().get(interarAtributo).getNome(), novoValorUsuario);
		servicos.setValores(atualizarValorAtributo);
	}
	public void agregarDistanciaEuclidiana(SelecionaServicos servicos) {

		this.agregacao.distanciaEuclidiana(servicos);
	}
	public void ordenarServicosDistanciaEuclidiana(SelecionaServicos servicos) {
		// Lista de serviços maior que 1? 
		if(servicos.getServicos().size()>1){
			// BUBBLE SORT
			for(int i = 0; i<servicos.getServicos().size(); i++){
				for(int j = 0; j<servicos.getServicos().size()-1; j++){
					if(servicos.getServicos().get(j).getFator() > servicos.getServicos().get(j+1).getFator()){
						Servico aux = servicos.getServicos().get(j);
						servicos.getServicos().set(j, servicos.getServicos().get(j+1));
						servicos.getServicos().set(j+1, aux);
					}
				}
			}	
		}
	}

	
	
public void ordenarModificado(SelecionaServicos servicos) {
		
		
		for(int j=0;j<servicos.getServicos().get(0).getAtributos().size();j++) {
			Double valorMaximo = 0.0;
			Double valorMinimo = 0.0;
			
			boolean primeiraInteracao = true;
			double valores = 0.0;
			
			for(int k=0;k<servicos.getServicos().size();k++) {
				
				Double valor = servicos.getServicos().get(k).getAtributos().get(j).getValor();
				if (primeiraInteracao) {
					for (Entry<String, Double> pair : servicos.getValores().entrySet()) {
					    if(pair.getKey().equals(servicos.getServicos().get(k).getAtributos().get(j).getNome())) {
					    	valorMaximo = pair.getValue();
							valorMinimo = pair.getValue();
							valores = pair.getValue();
							primeiraInteracao = false;
					    }
					}
				} 
				
					
				if (valor < valorMinimo) {
					valorMinimo = valor;
				} else if (valor > valorMaximo) {
					valorMaximo = valor;	
				}
			}
			
			for (Servico servico : servicos.getServicos()) {
				this.normalizacao.normalizarListaDeAtributos(servico,valorMaximo,valorMinimo,j);
			}
			
			valores = this.normalizacao.normalizarAtributoParaDistanciaEuclidiana(valorMaximo, valorMinimo, servicos.
						getServicos().get(0).getAtributos().get(j).getEMelhorOValorMaior(), valores);
			
			Map<String,Double> temporario = servicos.getValores();
			temporario.put(servicos.getServicos().get(0).getAtributos().get(j).getNome(), valores);
			servicos.setValores(temporario);
			
		}
		
		this.agregacao.simplesAdicaoDePeso(servicos);
		ordenarServicosDistanciaEuclidiana(servicos);
		
	}
	
	
	
}
