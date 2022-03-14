package ExamenThread_Gordillo_Montse;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class SimularRecollidaPecesElectronica {

	public static void main(String[] args) {//se ejecuta esto
		System.out.println("MAIN: INICI");
		Magatzem_peces_Dades magatzem=new Magatzem_peces_Dades();
		
		
		//Drassana drassana=new Drassana(magatzem);
		
		Drassana_Dades drasana=new Drassana_Dades(magatzem);
		Thread thread=new Thread(drasana);
				
		Drassana.inicialitzarNaus(drasana);
		Drassana.inicialitzarPecesNaus(drasana);
		Drassana.inicialitzarPecesDrassana(drasana);
		
		Drassana.inicialitzarNausAcabades(drasana);
		
		
		//iniciar
		thread.start();
		
		//naves
		for(Nau_Dades aDades : drasana.getLlistaNausEnDrassana()) {
			Thread naveThread=new Thread(aDades, aDades.getNau_nom());
			naveThread.start();
		}
		
		//join
		try {
			thread.join();
		} catch (InterruptedException e) {e.printStackTrace();}
		
		
		
		System.out.println("MAIN:");
		magatzem.veureContingutDelMagatzem();
		System.out.println("MAIN: FI");
	}

}
