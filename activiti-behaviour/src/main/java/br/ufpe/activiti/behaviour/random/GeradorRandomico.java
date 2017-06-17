package br.ufpe.activiti.behaviour.random;

import java.util.Random;

import br.ufpe.activiti.behaviour.model.Servico;

public class GeradorRandomico {
	Random ran = new Random();
	int retorno = 0;
	public Servico retornaValorAleartorio(){
		retorno = ran.nextInt(30);
		if(retorno < 15){
			return null;
		}else if(retorno >=15 && retorno <= 20){
			return null;
		}else{
			return null;
		}
	}
}
