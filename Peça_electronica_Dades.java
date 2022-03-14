package ExamenThread_Gordillo_Montse;

import java.text.Collator;
import java.util.Locale;

public class Peça_electronica_Dades extends Peça_prototipus_Dades{
	private int peça_consumEnergetic;
	private String peça_consumEnergetic_Unitat_de_mesura;

	
	public Peça_electronica_Dades(String peça_ID, int peça_num_serie, String peça_nom, String fabricant_ID, boolean peça_reparable, 
			boolean peça_trancada, int peça_consumEnergetic, String peça_consumEnergetic_Unitat_de_mesura) {
		
		super(peça_ID, peça_num_serie, peça_nom, fabricant_ID, peça_reparable, peça_trancada);
		this.peça_consumEnergetic = peça_consumEnergetic;
		this.peça_consumEnergetic_Unitat_de_mesura = peça_consumEnergetic_Unitat_de_mesura;
	}


	@Override
	protected boolean esReparable() {
		return isPeça_reparable();
	}


	public int getPeça_consumEnergetic() {
		return peça_consumEnergetic;
	}

	public void setPeça_consumEnergetic(int peça_consumEnergetic) {
		this.peça_consumEnergetic = peça_consumEnergetic;
	}

	public String getPeça_consumEnergetic_Unitat_de_mesura() {
		return peça_consumEnergetic_Unitat_de_mesura;
	}

	public void setPeça_consumEnergetic_Unitat_de_mesura(String peça_consumEnergetic_Unitat_de_mesura) {
		this.peça_consumEnergetic_Unitat_de_mesura = peça_consumEnergetic_Unitat_de_mesura;
	}

	
	
	

	


	@Override
	public String toString() {
		return super.toString() + ", consum energetic: " + peça_consumEnergetic + " " + peça_consumEnergetic_Unitat_de_mesura;
	}


	// compareTo() és lo que es fa servir quan s'executa un sort() per a comparar els waypoints.
	// 2 peces són iguals si tenen el mateix ID (String peça_ID) i nº de serie (int peça_num_serie).
	@Override
	public int compareTo(Peça_prototipus_Dades o) {
		int result = 0;
		
		/*
		result = -1 si volem que this.coordenades sigui anterior a o.coordenades .
        result = 0 si volem que this.coordenades i o.coordenades siguin iguals.
        result = 1 si volem que this.coordenades sigui posterior a o.coordenades . 
          
        result = 0 si this.getNom() i o.getNom() són iguals.
		result = -1 si this.getNom() és diferent a o.getNom() i anterior alfabèticament.
		result = 1 si this.getNom() és diferent a o.getNom() i posterior alfabèticament.
		*/

		//Anem a ordenar alfabèticament independenment de si hi ha majúscules o accents.
		Collator tertiaryCollator = Collator.getInstance(new Locale("es"));
        tertiaryCollator.setStrength(Collator.TERTIARY);
        result = tertiaryCollator.compare(this.getPeça_ID(), o.getPeça_ID());
        //tertiaryCollator retorna valors tal com o fa el compareTo().
		
		if (result == 0) {
			// A llavors s'han de diferenciar pel nº de serie.
			if (this.getPeça_num_serie() < o.getPeça_num_serie()) {
				result = -1;
			} else {
				if (this.getPeça_num_serie() == o.getPeça_num_serie()) {
					result = 0;
				} else {
					result = 1;
				}
			}
		}
		
		return result;
	}


	@Override
	public int hashCode() {
		return this.getPeça_ID().hashCode();
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Peça_electronica_Dades other = (Peça_electronica_Dades) obj;
		if (this.getPeça_num_serie() != other.getPeça_num_serie())
			return false;
		return true;
	}


}
