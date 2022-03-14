package ExamenThread_Gordillo_Montse;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.concurrent.Semaphore;



public class Magatzem_peces_Dades {
	// Aquí s'emmagatzemaran totes les peces electròniques que hi ha en les naus i en la drassana. 
	// Totes les naus i la drassana han d'enviar les seves peces a aquest magatzem.
	
	// És una llista inicialitzada per Drassana que contindrà tants false's com naus hi hagi en la drassana.
	private ArrayList <Boolean> nausAcabades = new ArrayList<Boolean>();
	
	// La clau serà peça_ID. El valor seran els objecte de tipus Peça_electronica_Dades i es diferenciaran pel peça_num_serie.
	private LinkedHashMap<String, LinkedList<Peça_electronica_Dades>> mapaPecesElectronicaOperatives = new LinkedHashMap<String, LinkedList<Peça_electronica_Dades>>();
	
	// La clau serà peça_ID. El valor seran els objecte de tipus Peça_electronica_Dades i es diferenciaran pel peça_num_serie.
	private LinkedHashMap<String, LinkedList<Peça_electronica_Dades>> mapaPecesElectronicaTrencades = new LinkedHashMap<String, LinkedList<Peça_electronica_Dades>>();
	
	private Semaphore semaphore4=new Semaphore(4);
	private Semaphore syncro=new Semaphore(1);
	

	public Magatzem_peces_Dades() {
		this.nausAcabades = new ArrayList<Boolean>();
		this. mapaPecesElectronicaOperatives = new LinkedHashMap<String, LinkedList<Peça_electronica_Dades>>();
		this.mapaPecesElectronicaTrencades = new LinkedHashMap<String, LinkedList<Peça_electronica_Dades>>();
	}
	public Magatzem_peces_Dades(ArrayList<Boolean> nausAcabades,
			LinkedHashMap<String, LinkedList<Peça_electronica_Dades>> mapaPecesElectronicaOperatives,
			LinkedHashMap<String, LinkedList<Peça_electronica_Dades>> mapaPecesElectronicaTrencades) {
		super();
		this.nausAcabades = nausAcabades;
		this.mapaPecesElectronicaOperatives = mapaPecesElectronicaOperatives;
		this.mapaPecesElectronicaTrencades = mapaPecesElectronicaTrencades;
	}

	public ArrayList<Boolean> getNausAcabades() {
		return nausAcabades;
	}

	public LinkedHashMap<String, LinkedList<Peça_electronica_Dades>> getMapaPecesElectronicaOperatives() {
		return mapaPecesElectronicaOperatives;
	}

	public void setMapaPecesElectronicaOperatives(LinkedHashMap<String, LinkedList<Peça_electronica_Dades>> mapaPecesElectronica) {
		this.mapaPecesElectronicaOperatives = mapaPecesElectronica;
	}

	public LinkedHashMap<String, LinkedList<Peça_electronica_Dades>> getMapaPecesElectronicaTrencades() {
		return mapaPecesElectronicaTrencades;
	}

	public void setMapaPecesElectronicaTrencades(
			LinkedHashMap<String, LinkedList<Peça_electronica_Dades>> mapaPecesElectronicaTrencades) {
		this.mapaPecesElectronicaTrencades = mapaPecesElectronicaTrencades;
	}


	
	
	
	public void notificaNauAcabada() {
		for(int contador=0;contador<nausAcabades.size();contador++) {
			if(nausAcabades.get(contador)==false) {
				nausAcabades.set(contador, true);
				break;
			}
		}
	}

	
	public void processarPecesElectronica(Peça_electronica_Dades peçaRebuda) {
		
		//semaforo de 4
		try {
			semaphore4.acquire();
		} catch (InterruptedException e1) {e1.printStackTrace();}
		
		LinkedList<Peça_electronica_Dades> piezasDades=new LinkedList<>();
		piezasDades.add(peçaRebuda);
		
		if(peçaRebuda.isPeça_trencada()==true) {

			try {
				Thread.currentThread().sleep(5000);
			} catch (InterruptedException e) {e.printStackTrace();}//dormir
			
			if(mapaPecesElectronicaTrencades.containsKey(peçaRebuda.getPeça_ID())){
				mapaPecesElectronicaTrencades.get(peçaRebuda.getPeça_ID()).add(peçaRebuda);
			}
			else {
				mapaPecesElectronicaTrencades.put(peçaRebuda.getPeça_ID(), piezasDades);
			}
				
			
		}
		else if(peçaRebuda.isPeça_trencada()==false) {
			
			
			if(mapaPecesElectronicaOperatives.containsKey(peçaRebuda.getPeça_ID())){
				mapaPecesElectronicaOperatives.get(peçaRebuda.getPeça_ID()).add(peçaRebuda);
			}
			
			else {
				mapaPecesElectronicaOperatives.put(peçaRebuda.getPeça_ID(), piezasDades);
			}

		}
		//relase
		semaphore4.release();
	}
	
	
	public void veureContingutDelMagatzem() {
		try {//semaforo
			syncro.acquire();
		} catch (InterruptedException e) {e.printStackTrace();}
		
		
		System.out.println("Contingut del magatzem: ");
		
		System.out.println("Operatives:");
		if(mapaPecesElectronicaOperatives.entrySet().size()==0) {System.out.println("\tmapa vacio");}
		else {
			for(Entry<String, LinkedList<Peça_electronica_Dades>> a:mapaPecesElectronicaOperatives.entrySet()) {
				System.out.println("\t"+a.getKey()+": "+a.getValue().size());
			}
		}
		
		
		System.out.println("Trencades:");
		if(mapaPecesElectronicaTrencades.entrySet().size()==0) {System.out.println("\t"+"mapa vacio");}
		else{
			for(Entry<String, LinkedList<Peça_electronica_Dades>> a:mapaPecesElectronicaTrencades.entrySet()) {
				System.out.println("\t"+a.getKey()+": "+a.getValue().size());
			}
		}
		
		
//relase
		syncro.release();
	}
	
}
