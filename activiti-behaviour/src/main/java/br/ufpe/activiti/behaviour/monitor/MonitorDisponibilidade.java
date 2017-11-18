package br.ufpe.activiti.behaviour.monitor;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import br.ufpe.activiti.behaviour.selection.SelecionaServicos;

public class MonitorDisponibilidade extends Thread{
	
	private SelecionaServicos listadeServicos;
	
	public SelecionaServicos getListadeServicos() {
		return listadeServicos;
	}

	public void setListadeServicos(SelecionaServicos listadeServicos) {
		this.listadeServicos = listadeServicos;
	}

	public MonitorDisponibilidade(SelecionaServicos lista) {
		listadeServicos = lista;
	}
	
	public void run(){
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();   
		 Client client;
		 Object[] res;
		while(true){
			try {
				Thread.sleep(5000);
				
				client = dcf.createClient("http://localhost:44801/ICMSRateProcess/ImportationRate?WSDL"); 
				res = client.invoke("toSayImportationTax",0.5,2);
				String populacao = res[0].toString();
				System.out.println(populacao);
				System.out.println("Lista de serviços: "+getListadeServicos().getServicos().size()+":"+(+getListadeServicos().getServicos().get(0).getListaParametros().size()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}
	
	public static void main(String []args) {
		
		 /*JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();   
		 Client client = dcf.createClient("http://localhost:44801/ICMSRateProcess/ImportationRate?WSDL"); 
		 Object[] res;
		try {
			res = client.invoke("toSayImportationTax",0.5,2);
			String populacao = res[0].toString();
			System.out.println(populacao);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   */
		
	}

}
