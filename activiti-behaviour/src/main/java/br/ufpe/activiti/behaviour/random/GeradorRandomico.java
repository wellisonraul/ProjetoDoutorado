package br.ufpe.activiti.behaviour.random;

import java.util.Random;

import br.ufpe.activiti.behaviour.model.Servico;

public class GeradorRandomico {
	Random ran = new Random();
	int retorno;
	Servico servicoAuxiliar = new Servico();
	
	public Servico retornaValorAleartorio(Servico servico, String nome){
		retorno = ran.nextInt(29);
		
		if(retorno < -1){
			return servico;
		}else if(retorno >=0 && retorno <= 20){
			servicoAuxiliar.setWsdl("WWW:www");
			servicoAuxiliar.setNome(nome);
			return servicoAuxiliar;
		}else{
			servicoAuxiliar.setWsdl("WWW:wwww");
			servicoAuxiliar.setNome(nome);
			return servicoAuxiliar;
		}
	}
	
}
