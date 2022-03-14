package EjerciciosThread;

import java.util.concurrent.Semaphore;

import javax.sound.midi.Soundbank;

public class SistemaDeGuiaDeTorpedes {
	private int numDeTorpedesQuePotGuiarSimultaneament=3;
	private boolean[] usDelSistemaDeGuia=new boolean[] {false, false, false};
	private Semaphore semaphore=new Semaphore(numDeTorpedesQuePotGuiarSimultaneament);
	private Semaphore semaphoreLiberar=new Semaphore(1);
	
	
	public int adquirirSistemaDeGuia(String nombreThread) {
		try {
			semaphore.acquire();
		} catch (InterruptedException e) {e.printStackTrace();}	
		
		int numero=-1	;
		while(numero==-1) {//mientras que no le den un slot
			numero=assignarSistemaDeGuia(nombreThread);
		}
		//si sale es que tiene un slot libre
		
		return numero;
		
	}

	
	public int assignarSistemaDeGuia(String nombreThread) {
		
		for(int contador=0; contador<usDelSistemaDeGuia.length;contador++) {
			if(usDelSistemaDeGuia[contador]==false) {
				usDelSistemaDeGuia[contador]=true;
				System.out.println("\t\t"+nombreThread+": Encontrado un torpedo libre en la posicion "+contador);
				
				imprimirUsDelSistemaDeGuia(nombreThread);
				
				return contador;
			}
		}
		return -1;
		
		
	}
	
	
	
	public synchronized void imprimirUsDelSistemaDeGuia(String nomThread) {
					
		String torpedosInfo="[";
		for(boolean a:usDelSistemaDeGuia) {torpedosInfo+=Boolean.toString(a)+",";}
		torpedosInfo+="]";
		System.out.println("\t"+nomThread+", usDelSistemaDeGuia actual:"+torpedosInfo);
		
		
				
	}
	
	public void alliberarSistemaDeGuia(int numUsDelSistemaDeGuia, String nomThread) {
		
		try {
			semaphoreLiberar.acquire();
		} catch (InterruptedException e) {e.printStackTrace();}
		
		System.out.println("\t"+nomThread+" Liberando torpedo en la posicion "+numUsDelSistemaDeGuia);
		usDelSistemaDeGuia[numUsDelSistemaDeGuia]=false;
		
		imprimirUsDelSistemaDeGuia(nomThread);
		
		semaphore.release();
		semaphoreLiberar.release();
		
	}
	
	
	public boolean[] getUsDelSistemaDeGuia() {
		return usDelSistemaDeGuia;
	}

	public void setUsDelSistemaDeGuia(boolean[] usDelSistemaDeGuia) {
		this.usDelSistemaDeGuia = usDelSistemaDeGuia;
	}

	public int getNumDeTorpedesQuePotGuiarSimultaneament() {
		return numDeTorpedesQuePotGuiarSimultaneament;
	}

	public void setNumDeTorpedesQuePotGuiarSimultaneament(int numDeTorpedesQuePotGuiarSimultaneament) {
		this.numDeTorpedesQuePotGuiarSimultaneament = numDeTorpedesQuePotGuiarSimultaneament;
	}
	
	
	
	

}
