package br.ufpe.activiti.behaviour.delegate;

public class TemporizadorThread implements Runnable{
	int contador; 
	
	public TemporizadorThread() {
		contador = 0;
	}
	
	public void run(){
		while(true){
			try {
				Thread.sleep(3000);
				contador++;
				if(contador==1){
					throw new java.lang.Error("this is very bad");
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		
	}
}
