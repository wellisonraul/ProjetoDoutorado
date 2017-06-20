package br.ufpe.activiti.behaviour.random;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class ThreadGeradorErros implements Runnable{
	Runtime r = Runtime.getRuntime();
    Random aleartorio = new Random();
    int resultado = 0;
	int contadorDeployUndeploy = 0;
    
	public void run() {
		// TEMPO DE INICIALIZAÇÃO
		try {
			Thread.sleep(40000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		// GERADOR DE ERROS
		while(true){
			try {
				// DUAS FORMAS DE ERROS
				/* DERRUBA TUDO NO DEPLOY 
				 * DERRUBA ESPECIFICO DA MANEIRA ANTIGA (REVER ESTE) // NÃO UTILIZADO NO DIA 19/JUNHO 
				 * VALORES PRÉ-DEFINIDOS DE ACORDO COM O PROGRAMADOR (REVISA-LOS)
				 */
				
				resultado = aleartorio.nextInt(29);
				if(resultado>=15 && resultado <= 20){
					contadorDeployUndeploy++;
					
					System.out.println("Caiu o serviço!");
					Process p = r.exec(new String[]{"/bin/bash", "-c", "cd /home/wellisonraul/GlassFish_Server/glassfish/bin/ && ./asadmin undeploy ICMSRateProcess"});
					BufferedReader reader =  new BufferedReader(new InputStreamReader(p.getInputStream()));
					String line = "";
				    while((line = reader.readLine()) != null) {
				       System.out.print(line + "\n");
				    }
				    Thread.sleep(10000);
				    p = r.exec(new String[]{"/bin/bash", "-c", "cd /home/wellisonraul/GlassFish_Server/glassfish/bin/ && ./asadmin deploy /home/wellisonraul/Downloads/ICMSRateProcess/ICMSRateProcess/dist/ICMSRateProcess.war"});
				    reader =  new BufferedReader(new InputStreamReader(p.getInputStream()));
				    line = "";
				    while((line = reader.readLine()) != null) {
				       System.out.print(line + "\n");
				    }
				    Thread.sleep(10000);
				    
				}else if(contadorDeployUndeploy%100==0){
					Thread.sleep(10000);
					Process p = r.exec(new String[]{"/bin/bash", "-c", "cd /home/wellisonraul/GlassFish_Server/glassfish/bin/ && ./asadmin restart-domain"});
				    Thread.sleep(25000);
				}else{
					System.out.println("Nenhum erro");
					Thread.sleep(3000);
				}
				  
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
