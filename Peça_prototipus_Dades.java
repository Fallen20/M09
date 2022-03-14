package ExamenThread_Gordillo_Montse;

public abstract class Peça_prototipus_Dades implements Comparable<Peça_prototipus_Dades>{
	private String peça_ID;				// Identifica la peça (és com el DNI).
	private int peça_num_serie;			// Hi pot haver més d'una peça fabricada del mateix ID. Aquest nº les diferenciarà si tenen el mateix ID.
	private String peça_nom;
	private String fabricant_ID;
	private boolean peça_reparable;
	private boolean peça_trencada;
	

	public Peça_prototipus_Dades(String peça_ID, int peça_num_serie, String peça_nom, String fabricant_ID, boolean peça_reparable, boolean peça_trancada) {
		this.peça_ID = peça_ID;
		this.peça_num_serie = peça_num_serie;
		this.peça_nom = peça_nom;
		this.fabricant_ID = fabricant_ID;
		this.peça_reparable = peça_reparable;
		this.peça_trencada = peça_trancada;
	}
	

	public Peça_prototipus_Dades() {
	}


	public boolean isPeça_reparable() {
		return peça_reparable;
	}


	abstract protected boolean esReparable();


	public String getPeça_ID() {
		return peça_ID;
	}

	public void setPeça_ID(String peça_ID) {
		this.peça_ID = peça_ID;
	}

	public int getPeça_num_serie() {
		return peça_num_serie;
	}

	public void setPeça_num_serie(int peça_num_serie) {
		this.peça_num_serie = peça_num_serie;
	}

	public String getPeça_nom() {
		return peça_nom;
	}

	public void setPeça_nom(String peça_nom) {
		this.peça_nom = peça_nom;
	}

	public String getFabricant_ID() {
		return fabricant_ID;
	}

	public void setFabricant_ID(String fabricant_ID) {
		this.fabricant_ID = fabricant_ID;
	}

	public boolean isPeça_trencada() {
		return peça_trencada;
	}

	public void setPeça_trencada(boolean peça_trancada) {
		this.peça_trencada = peça_trancada;
	}

	public void setPeça_reparable(boolean peça_reparable) {
		this.peça_reparable = peça_reparable;
	}


	@Override
	public String toString() {
		return "Peça ID: " + peça_ID + " (nº serie: " + peça_num_serie + "), nom: "
				+ peça_nom + ", fabricant: " + fabricant_ID + ", reparable?: " + peça_reparable
				+ ", trencada?: " + peça_trencada;
	}
	
	
}
