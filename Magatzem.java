package EjerciciosThread;

public class Magatzem {
	private static int quantitatRacions;

	public Magatzem() {
		this.quantitatRacions = 1000;
	}

	//GETTER SETTER
	public static int getQuantitatRacions() {
		return quantitatRacions;
	}

	public static void setQuantitatRacions(int quantitatRacions) {
		Magatzem.quantitatRacions = quantitatRacions;
	}



	//METODOS
	public static void retornarRacions(int numRacions) {
		
		
		if(Thread.currentThread().getName()==IKSRotarranConstants.DEPARTAMENTS[1]) {
			//lo de id==1 no funciona porque se le da un id random
			try {
				System.out.println("\nDurmiendo a "+Thread.currentThread().getName()+" por ser el primero\n");
				Thread.currentThread().sleep(5000);
			}
			catch (InterruptedException e) {e.printStackTrace();}
		}

		
		quantitatRacions+=numRacions;
		
	}

	public static void agafaRacions(int numRacions) {
		
		quantitatRacions-=numRacions;
		
	}

	public static int comprovarQuantitatRacions() {
		return quantitatRacions;
		
	}

}
