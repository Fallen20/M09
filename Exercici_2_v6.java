package EjerciciosThread;

import java.util.Iterator;

public class Exercici_2_v6{
	public static void  inicialitzarPrograma(){
		SistemaDeGuiaDeTorpedes guia=new SistemaDeGuiaDeTorpedes();
		
		/*Torpede torpedo1=new Torpede(guia);
		Torpede torpedo2=new Torpede(guia);
		Torpede torpedo3=new Torpede(guia);
		Torpede torpedo4=new Torpede(guia);
		Torpede torpedo5=new Torpede(guia);
		Torpede torpedo6=new Torpede(guia);
		Torpede torpedo7=new Torpede(guia);
		Torpede torpedo8=new Torpede(guia);
		Torpede torpedo9=new Torpede(guia);
		Torpede torpedo10=new Torpede(guia);
		*/
		
		Thread[] thread=new Thread[10];//array de 10 torpedos
		
		
		System.out.println("Exercici_2_v6: Inicio");
		//metemos los torpedos
		for(int contador=0;contador<thread.length;contador++) {
			Torpede torpedo=new Torpede(guia);//creamos un torpedo por cada posicion de array
			thread[contador]=new Thread(torpedo,"torpede "+contador);//lo metemos
			thread[contador].start();
		}
						
		//join
		//si se pone junto con el start se lanzan uno a uno y no cuando quieren
		for(int contador=0;contador<thread.length;contador++) {
			try {
				thread[contador].join();
			} catch (InterruptedException e) {e.printStackTrace();}
		}
		
		
		
		String torpedosInfo="[";
		for(boolean a:guia.getUsDelSistemaDeGuia()) {torpedosInfo+=Boolean.toString(a)+",";}
		torpedosInfo+="]";
		
		System.out.println("Exercici_2_v6 usDelSistemaDeGuia actual:"+torpedosInfo);	
		System.out.println("Exercici_2_v6: FIN");
	}

}
