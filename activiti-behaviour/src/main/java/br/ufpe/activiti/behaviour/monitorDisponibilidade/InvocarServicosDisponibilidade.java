package br.ufpe.activiti.behaviour.monitorDisponibilidade;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import br.ufpe.activiti.behaviour.model.Servico;

public class InvocarServicosDisponibilidade {
	private static int qtdSolicitacoes = 5;
	
	public Double invocarServico(Servico servico) {
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();   
		Client client = null;
		try {
			client = dcf.createClient(servico.getWsdl()); 
		} catch (Exception e) {
			System.out.println("Falha ao criar o cliente a partir do WSDL!");
		}
		
		Object[] res = null;
		Double boaResposta = 0.0;
		Double respostaFinal = 0.0;
		for(int i=0; i<qtdSolicitacoes;i++) {
			try {
				
				if(servico.getListaParametros().size() == 0){
					res = client.invoke(servico.getOperacao());
				}else if(servico.getListaParametros().size() == 1){
					res = client.invoke(servico.getOperacao(), 
							servico.getListaParametrosDisponibilidade().get(0));
				}else if(servico.getListaParametros().size() == 2){
					res = client.invoke(servico.getOperacao(), 
							servico.getListaParametrosDisponibilidade().get(0), 
							servico.getListaParametrosDisponibilidade().get(1));
				}else if(servico.getListaParametros().size() == 3){
					res = client.invoke(servico.getOperacao(), 
							servico.getListaParametrosDisponibilidade().get(0), 
							servico.getListaParametrosDisponibilidade().get(1), 
							servico.getListaParametrosDisponibilidade().get(2));
				}else if(servico.getListaParametros().size() == 4){
					res = client.invoke(servico.getOperacao(), 
							servico.getListaParametrosDisponibilidade().get(0), 
							servico.getListaParametrosDisponibilidade().get(1),
							servico.getListaParametrosDisponibilidade().get(2),
							servico.getListaParametrosDisponibilidade().get(3));
				}else if(servico.getListaParametros().size() == 5) {
					res = client.invoke(servico.getOperacao(), 
							servico.getListaParametrosDisponibilidade().get(0), 
							servico.getListaParametrosDisponibilidade().get(1), 
							servico.getListaParametrosDisponibilidade().get(2),
							servico.getListaParametrosDisponibilidade().get(3), 
							servico.getListaParametrosDisponibilidade().get(4));
				}
				
				int codigoDeResposta = Integer.parseInt(client.getResponseContext().get("org.apache.cxf.message.Message.RESPONSE_CODE").toString());
				
				if(codigoDeResposta==200) {
					boaResposta++;
				}
				
				Thread.sleep(300);
			} catch (Exception e) {
				System.out.println("Não foi possível invocar o serviço "+servico.getNome()+"!");
			}
		}
		
		respostaFinal = (double) (boaResposta/qtdSolicitacoes);
		
		return respostaFinal;	
			
	}
	
}
