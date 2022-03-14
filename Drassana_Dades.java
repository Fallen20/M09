package ExamenThread_Gordillo_Montse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;




public class Drassana_Dades implements Runnable{
	public static final String nomDrassana = "MCRN Calisto "; 
	
	private LinkedList<Nau_Dades> llistaNausEnDrassana = new LinkedList<Nau_Dades>();		// Cua de tipus FIFO que suporti null's
	
	// La clau serà peça_ID. El valor serà la quantitat de peces que hi ha del propi model.
	private LinkedHashMap<String, Integer> mapaStockPeces = new LinkedHashMap<String, Integer>();
	
	// La clau serà peça_ID. El valor seran els objecte de tipus Peça_electronica_Dades i es diferenciaran pel peça_num_serie.
	private LinkedHashMap<String, LinkedList<Peça_electronica_Dades>> mapaPecesElectronica = new LinkedHashMap<String, LinkedList<Peça_electronica_Dades>>();
	
	Magatzem_peces_Dades magatzem_peces_Dades;
	
	

	public Drassana_Dades(LinkedList<Nau_Dades> llistaNausEnDrassana, LinkedHashMap<String, Integer> mapaStockPeces,
			LinkedHashMap<String, LinkedList<Peça_electronica_Dades>> mapaPecesElectronica, Magatzem_peces_Dades magatzem_peces_Dades) {
		super();
		this.llistaNausEnDrassana = llistaNausEnDrassana;
		this.mapaStockPeces = mapaStockPeces;
		this.mapaPecesElectronica = mapaPecesElectronica;
		this.magatzem_peces_Dades=magatzem_peces_Dades;
	}

	
	public Drassana_Dades(Magatzem_peces_Dades magatzem_peces_Dades) {
		this.llistaNausEnDrassana = new LinkedList<>();
		this.mapaStockPeces = new LinkedHashMap<>();
		this.mapaPecesElectronica = new LinkedHashMap<>();
		this.magatzem_peces_Dades=magatzem_peces_Dades;
	}


	public LinkedList<Nau_Dades> getLlistaNausEnDrassana() {
		return llistaNausEnDrassana;
	}

	public void setLlistaNausEnDrassana(LinkedList<Nau_Dades> llistaNausEnDrassana) {
		this.llistaNausEnDrassana = llistaNausEnDrassana;
	}

	public LinkedHashMap<String, Integer> getMapaStockPeces() {
		return mapaStockPeces;
	}

	public void setMapaStockPeces(LinkedHashMap<String, Integer> mapaStockPeces) {
		this.mapaStockPeces = mapaStockPeces;
	}

	public LinkedHashMap<String, LinkedList<Peça_electronica_Dades>> getMapaPecesElectronica() {
		return mapaPecesElectronica;
	}

	public void setMapaPecesElectronica(LinkedHashMap<String, LinkedList<Peça_electronica_Dades>> mapaPecesElectronica) {
		this.mapaPecesElectronica = mapaPecesElectronica;
	}


	public Magatzem_peces_Dades getMagatzem_peces_Dades() {
		return magatzem_peces_Dades;
	}


	public void setMagatzem_peces_Dades(Magatzem_peces_Dades magatzem_peces_Dades) {
		this.magatzem_peces_Dades = magatzem_peces_Dades;
	}
	
	public void veureContingutMapaPecesElectronica() {
		System.out.println("Peces electronica de la drassana:");
		
		
		if(mapaPecesElectronica.entrySet().size()==0) {System.out.println("\tmapa vacio");}
		else {
			for(Entry<String, LinkedList<Peça_electronica_Dades>> a:mapaPecesElectronica.entrySet()) {
				System.out.println("\t"+a.getKey()+": "+a.getValue().size());
			}
		}
		
		
	}


	@Override
	public void run() {
		System.out.println("-------------------------------------");
		String total="[";
		for(int contador=0; contador<magatzem_peces_Dades.getNausAcabades().size();contador++) {
			if(contador==magatzem_peces_Dades.getNausAcabades().size()-1) {total+=magatzem_peces_Dades.getNausAcabades().get(contador);}
			else {
				total+=magatzem_peces_Dades.getNausAcabades().get(contador)+", ";
			}
			
		}
		total+="]";
		
		System.out.println(nomDrassana+"> INICIO "+total);
		System.out.print(nomDrassana+"> INICIO ");
		veureContingutMapaPecesElectronica();
		
		System.out.println("----------------------------");
		
		//enviar piezas y borrar		
        Set set = mapaPecesElectronica.entrySet();

        // Get an iterator.
        Iterator<String> it1 = mapaPecesElectronica.keySet().iterator();
        String llaveString;
        while(it1.hasNext()) {
           llaveString=it1.next();
           
           for(Peça_electronica_Dades aDades :mapaPecesElectronica.get(llaveString)) {//con esto tenemos la lista de piezas
        	   //System.out.println("enviando la pieza "+aDades.getPeça_nom());
        	   magatzem_peces_Dades.processarPecesElectronica(aDades);//envio
               
           }
           
           //System.out.println("Borrando el mapa con las piezas "+mapaPecesElectronica.get(llaveString));
           it1.remove();//borramos
           
        }
        
        int llenas=0;
		
		while(llenas!=magatzem_peces_Dades.getNausAcabades().size()) {
			String arrayDatos="[";
			
			try {Thread.currentThread().sleep(500);}//cada .5seg
			catch (InterruptedException e) {e.printStackTrace();}
			
			for(int contador=0; contador<magatzem_peces_Dades.getNausAcabades().size();contador++) {
				if(contador==magatzem_peces_Dades.getNausAcabades().size()-1) {arrayDatos+=magatzem_peces_Dades.getNausAcabades().get(contador);}
				else {arrayDatos+=magatzem_peces_Dades.getNausAcabades().get(contador)+", ";}
				
				if(magatzem_peces_Dades.getNausAcabades().get(contador)==true) {llenas++;}
				else {llenas=0;}//si hay algo false, reset
			}
			
			arrayDatos+="]";
			System.out.println(nomDrassana+": "+arrayDatos);
			arrayDatos="";//reset
		}
		System.out.println();
		
		
		
		System.out.println("----------------------------");	
		
		total="[";
		for(int contador=0; contador<magatzem_peces_Dades.getNausAcabades().size();contador++) {
			if(contador==magatzem_peces_Dades.getNausAcabades().size()-1) {total+=magatzem_peces_Dades.getNausAcabades().get(contador);}
			else {
				total+=magatzem_peces_Dades.getNausAcabades().get(contador)+", ";
			}
		}
		total+="]";
		
		System.out.println(nomDrassana+"> FIN "+total);
		
		System.out.print(nomDrassana+"> FIN ");
		veureContingutMapaPecesElectronica();
		
		System.out.println("-------------------------------------");
	}
	

	
	
	

}
