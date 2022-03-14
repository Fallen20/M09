package ExamenThread_Gordillo_Montse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map.Entry;



public class Drassana {
	
	
	//1. Inicialitzar naus (part I)
	public static void inicialitzarNaus(Drassana_Dades drassana_MCRN) {
		LocalDate dataTmp;
		LocalTime tempsTmp;
		LocalDateTime dataCompletaTmp;
		
		
		dataTmp = LocalDate.of(2101, 1, 1);
		tempsTmp = LocalTime.now();
		dataCompletaTmp = LocalDateTime.of(dataTmp, tempsTmp);
		Nau_Dades Donnager = new Nau_Dades("mcrn 101", "Donnager", 11, dataCompletaTmp, drassana_MCRN.getMagatzem_peces_Dades());
		
		dataTmp = LocalDate.of(2102, 2, 2);
		tempsTmp = LocalTime.now();
		dataCompletaTmp = LocalDateTime.of(dataTmp, tempsTmp);
		Nau_Dades Pella = new Nau_Dades("mcrn 202", "Pella", 22, dataCompletaTmp, drassana_MCRN.getMagatzem_peces_Dades());
		
		dataTmp = LocalDate.of(2103, 3, 3);
		tempsTmp = LocalTime.now();
		dataCompletaTmp = LocalDateTime.of(dataTmp, tempsTmp);
		Nau_Dades Carcassonne = new Nau_Dades("mcrn 303", "Carcassonne", 33, dataCompletaTmp, drassana_MCRN.getMagatzem_peces_Dades());
		
		dataTmp = LocalDate.of(2104, 4, 4);
		tempsTmp = LocalTime.now();
		dataCompletaTmp = LocalDateTime.of(dataTmp, tempsTmp);
		Nau_Dades Africanus = new Nau_Dades("mcrn 404", "Africanus", 44, dataCompletaTmp, drassana_MCRN.getMagatzem_peces_Dades());
		
		dataTmp = LocalDate.of(2105, 5, 5);
		tempsTmp = LocalTime.now();
		dataCompletaTmp = LocalDateTime.of(dataTmp, tempsTmp);
		Nau_Dades Anubis = new Nau_Dades("mcrn 505", "Anubis", 55, dataCompletaTmp, drassana_MCRN.getMagatzem_peces_Dades());
		
		//System.out.println("LocalDateTime.now() = " + LocalDateTime.now());
		
		drassana_MCRN.getLlistaNausEnDrassana().clear();
		drassana_MCRN.getLlistaNausEnDrassana().add(Donnager);
		drassana_MCRN.getLlistaNausEnDrassana().add(Pella);
		drassana_MCRN.getLlistaNausEnDrassana().add(0, Carcassonne);
		drassana_MCRN.getLlistaNausEnDrassana().add(Africanus);
		drassana_MCRN.getLlistaNausEnDrassana().add(Anubis);
		
		/*
		for(Nau_Dades nauTmp: drassana_MCRN.getLlistaNausEnDrassana()) {
			System.out.println("Drassana " + Drassana_Dades.nomDrassana + ": AFEGIDA LA NAU " + nauTmp.getNau_nom() + " AMB ID " + nauTmp.getNau_ID() + ".");
		}
		*/
	}

	
	//1. Inicialitzar naus (part II)
	public static void inicialitzarPecesNaus(Drassana_Dades drassana_MCRN) {
		// Creem les peces electròniques que aniran en les naus:
		Peça_electronica_Dades radar_000 = new Peça_electronica_Dades("Cyrano IV", 0, "radar monopulso Thomson-CSF Cyrano IV", "Thomson-CSF", true, true, 120, "kW");
		Peça_electronica_Dades radar_001 = new Peça_electronica_Dades("Cyrano IV", 1, "radar monopulso Thomson-CSF Cyrano IV", "Thomson-CSF", false, true, 120, "kW");
		Peça_electronica_Dades radar_002 = new Peça_electronica_Dades("Cyrano IV", 2, "radar monopulso Thomson-CSF Cyrano IV", "Thomson-CSF", true, false, 120, "kW");
		Peça_electronica_Dades radar_003 = new Peça_electronica_Dades("Cyrano IV", 3, "radar monopulso Thomson-CSF Cyrano IV", "Thomson-CSF", true, false, 120, "kW");
		Peça_electronica_Dades radar_004 = new Peça_electronica_Dades("Cyrano IV", 4, "radar monopulso Thomson-CSF Cyrano IV", "Thomson-CSF", false, false, 120, "kW");
		
		ArrayList <Peça_electronica_Dades> llista_radars = new ArrayList<Peça_electronica_Dades>(Arrays.asList(radar_000, radar_001, radar_002, radar_003, radar_004));
		
		Peça_electronica_Dades visorIR_000 = new Peça_electronica_Dades("SAT SCM2400 Super Cyclone", 0, "cámara d'infrarojos SAT SCM2400 Super Cyclone", "Thomson-CSF", true, false, 5, "kW");
		Peça_electronica_Dades visorIR_001 = new Peça_electronica_Dades("SAT SCM2400 Super Cyclone", 1, "cámara d'infrarojos SAT SCM2400 Super Cyclone", "Thomson-CSF", true, true, 5, "kW");
		Peça_electronica_Dades visorIR_002 = new Peça_electronica_Dades("SAT SCM2400 Super Cyclone", 2, "cámara d'infrarojos SAT SCM2400 Super Cyclone", "Thomson-CSF", true, false, 5, "kW");
		Peça_electronica_Dades visorIR_003 = new Peça_electronica_Dades("SAT SCM2400 Super Cyclone", 3, "cámara d'infrarojos SAT SCM2400 Super Cyclone", "Thomson-CSF", true, false, 5, "kW");
		Peça_electronica_Dades visorIR_004 = new Peça_electronica_Dades("SAT SCM2400 Super Cyclone", 4, "cámara d'infrarojos SAT SCM2400 Super Cyclone", "Thomson-CSF", false, false, 5, "kW");
		
		ArrayList <Peça_electronica_Dades> llista_visorsIR = new ArrayList<Peça_electronica_Dades>(Arrays.asList(visorIR_000, visorIR_001, visorIR_002, visorIR_003, visorIR_004));
		
		
		// Assignem les peces a les naus:
		int i = 0;
		ListIterator<Nau_Dades> it_naus = drassana_MCRN.getLlistaNausEnDrassana().listIterator(drassana_MCRN.getLlistaNausEnDrassana().size());
		while (it_naus.hasPrevious()) {
			Nau_Dades nauTmp = it_naus.previous(); 
			
			Peça_electronica_Dades radarTmp = llista_radars.get(i);
			Peça_electronica_Dades visorIRTmp = llista_visorsIR.get(i);
			
			nauTmp.getLlistaPecesElectronica().add(radarTmp);
			nauTmp.getLlistaPecesElectronica().add(visorIRTmp);
			/*
			System.out.println("Nau " + nauTmp.getNau_nom() + " (ID " + nauTmp.getNau_ID() + "):");
			System.out.println("    AFEGIDES LES PECES ELECTRÓNIQUES:");
			System.out.println("        " + radarTmp.getPeça_nom() + " Nº SERIE " + radarTmp.getPeça_num_serie());
			System.out.println("        " + visorIRTmp.getPeça_nom() + " Nº SERIE " + visorIRTmp.getPeça_num_serie());
			*/
			i++;
		}
	}	
	
	
	//2. Inicialitzar drassana
	public static void inicialitzarPecesDrassana(Drassana_Dades drassana_MCRN) {
		// Creem i assignem les peces a la drassana.
		Peça_electronica_Dades radar_003 = new Peça_electronica_Dades("Cyrano IV", 3, "radar monopulso Thomson-CSF Cyrano IV", "Thomson-CSF", true, true, 120, "kW");
		Peça_electronica_Dades radar_004 = new Peça_electronica_Dades("Cyrano IV", 4, "radar monopulso Thomson-CSF Cyrano IV", "Thomson-CSF", false, true, 120, "kW");
		Peça_electronica_Dades radar_005 = new Peça_electronica_Dades("Cyrano IV", 5, "radar monopulso Thomson-CSF Cyrano IV", "Thomson-CSF", true, false, 120, "kW");
		Peça_electronica_Dades radar_006 = new Peça_electronica_Dades("Cyrano IV", 6, "radar monopulso Thomson-CSF Cyrano IV", "Thomson-CSF", true, false, 120, "kW");
		Peça_electronica_Dades radar_007 = new Peça_electronica_Dades("Cyrano IV", 7, "radar monopulso Thomson-CSF Cyrano IV", "Thomson-CSF", true, false, 120, "kW");
		
		drassana_MCRN.getMapaStockPeces().put("Cyrano IV", 5);
		drassana_MCRN.getMapaPecesElectronica().put("Cyrano IV", new LinkedList<Peça_electronica_Dades>(Arrays.asList(radar_003, radar_004, radar_005, radar_006, radar_007)));
		
		
		Peça_electronica_Dades radio_000 = new Peça_electronica_Dades("radio UHF/VHF", 0, "radio salto frecuencias UHF/VHF", "Thomson-CSF", true, false, 10, "kW");
		Peça_electronica_Dades radio_001 = new Peça_electronica_Dades("radio UHF/VHF", 1, "radio salto frecuencias UHF/VHF", "Thomson-CSF", true, false, 10, "kW");
		
		drassana_MCRN.getMapaStockPeces().put("radio UHF/VHF", 2);
		drassana_MCRN.getMapaPecesElectronica().put("radio UHF/VHF", new LinkedList<Peça_electronica_Dades>(Arrays.asList(radio_000, radio_001)));
		
		
		Peça_electronica_Dades visorIR_000 = new Peça_electronica_Dades("SAT SCM2400 Super Cyclone", 0, "cámara d'infrarojos SAT SCM2400 Super Cyclone", "Thomson-CSF", true, false, 5, "kW");
		Peça_electronica_Dades visorIR_001 = new Peça_electronica_Dades("SAT SCM2400 Super Cyclone", 1, "cámara d'infrarojos SAT SCM2400 Super Cyclone", "Thomson-CSF", true, false, 5, "kW");
		Peça_electronica_Dades visorIR_002 = new Peça_electronica_Dades("SAT SCM2400 Super Cyclone", 2, "cámara d'infrarojos SAT SCM2400 Super Cyclone", "Thomson-CSF", true, false, 5, "kW");
		
		drassana_MCRN.getMapaStockPeces().put("SAT SCM2400 Super Cyclone", 3);
		drassana_MCRN.getMapaPecesElectronica().put("SAT SCM2400 Super Cyclone", new LinkedList<Peça_electronica_Dades>(Arrays.asList(visorIR_000, visorIR_001, visorIR_002)));
		
		
		/*
		// MOSTRAR LES QUANTITATS DE PECES PER PANTALLA --> SEL·LECCIONAR 1 --> MOSTRAR LES DADES DELS OBJECTES (DE LES PECES).
		System.out.println("Drassana " + Drassana_Dades.nomDrassana + ": ");
		System.out.println("    AFEGIDES LES PECES ELECTRÓNIQUES:");
		for (Entry<String, LinkedList<Peça_electronica_Dades>> entradaMapaTmp : drassana_MCRN.getMapaPecesElectronica().entrySet()) {
			for (Peça_electronica_Dades peçaTmp : entradaMapaTmp.getValue()) {
				System.out.println("        " + peçaTmp.getPeça_ID() + " Nº SERIE " + peçaTmp.getPeça_num_serie());	
			}
		}
		*/
	}


	
	public static void inicialitzarNausAcabades(Drassana_Dades drassana_MCRN) {
		for(int contador=0;contador<drassana_MCRN.getLlistaNausEnDrassana().size();contador++) {
			drassana_MCRN.magatzem_peces_Dades.getNausAcabades().add(false);
		}
		
	}
	
	
}