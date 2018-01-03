package br.ufpe.activiti.behaviour.monitorDisponibilidade;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import br.ufpe.activiti.behaviour.model.Servico;

public class InvocarServicosDisponibilidade {
	private static int qtdSolicitacoes = 20;
		
	public Double invocarServico(Servico servico, int qtdParametros) {
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();   
		Client client = dcf.createClient(servico.getWsdl()); 
		Object[] res = null;
		Double boaResposta = 0.0;
		Double respostaFinal = 0.0;
		for(int i=0; i<qtdSolicitacoes;i++) {
			try {
				
				if(qtdParametros == 0){
					res = client.invoke(servico.getOperacao());
				}else if(qtdParametros == 1){
					res = client.invoke(servico.getOperacao(), "RN");
				}else if(qtdParametros == 2){
					res = client.invoke(servico.getOperacao(), 0, 1);
				}else if(qtdParametros == 3){
					res = client.invoke(servico.getOperacao(), 0, 1, 2);
				}else if(qtdParametros == 4){
					res = client.invoke(servico.getOperacao(), 0, 1, 2, 3);
				}else if(qtdParametros == 5){
					res = client.invoke(servico.getOperacao(), 0, 1, 2, 3, 4);
				}
				
				int codigoDeResposta = Integer.parseInt(client.getResponseContext().get("org.apache.cxf.message.Message.RESPONSE_CODE").toString());
				
				if(codigoDeResposta==200) {
					boaResposta++;
				}
				
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			respostaFinal = (double) (boaResposta/qtdSolicitacoes);
			
		}
		return respostaFinal;	
			
	}
	
	
	public Double invocarServicoDois(Servico servico) {
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();   
		Client client = dcf.createClient(servico.getWsdl()); 
		Object[] res = null;
		Double boaResposta = -1.0;
		Double respostaFinal = 0.0;
		for(int i=0; i<qtdSolicitacoes;i++) {
			try {
				
				if(servico.getListaParametros().size() == 0){
					res = client.invoke(servico.getOperacao());
				}else if(servico.getListaParametros().size() == 1){
					res = client.invoke(servico.getOperacao(), "RN");
				}else if(servico.getListaParametros().size() == 2){
					res = client.invoke(servico.getOperacao(), 0, 1);
				}else if(servico.getListaParametros().size() == 3){
					res = client.invoke(servico.getOperacao(), 0, 1, 2);
				}else if(servico.getListaParametros().size() == 4){
					res = client.invoke(servico.getOperacao(), 0, 1, 2, 3);
				}else if(servico.getListaParametros().size() == 5){
					res = client.invoke(servico.getOperacao(), 0, 1, 2, 3, 4);
				}
				
				int codigoDeResposta = Integer.parseInt(client.getResponseContext().get("org.apache.cxf.message.Message.RESPONSE_CODE").toString());
				
				if(codigoDeResposta==200) {
					boaResposta++;
				}
				
				Thread.sleep(300);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			respostaFinal = (double) (boaResposta/qtdSolicitacoes);
			
		}
		return respostaFinal;	
			
	}
	
}
