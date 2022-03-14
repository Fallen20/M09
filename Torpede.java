package EjerciciosThread;

public class Torpede implements Runnable{
	private SistemaDeGuiaDeTorpedes sistemaDeGuiaDeTorpedes;

	public Torpede(SistemaDeGuiaDeTorpedes sistemaDeGuiaDeTorpedes) {
		super();
		this.sistemaDeGuiaDeTorpedes = sistemaDeGuiaDeTorpedes;
	}

	@Override
	public void run() {
		
		
		
		System.out.println(Thread.currentThread().getName()+"> inicio");

		int posicionLanzada=sistemaDeGuiaDeTorpedes.adquirirSistemaDeGuia(Thread.currentThread().getName());

		//impacta, se duerme
		try {
			System.out.println(Thread.currentThread().getName()+" dormido por 5 seg.");
			Thread.sleep(5000);//hacer un sleep
		} catch (InterruptedException e) {e.printStackTrace();}

		//liberamos
		sistemaDeGuiaDeTorpedes.alliberarSistemaDeGuia(posicionLanzada, Thread.currentThread().getName());


		System.out.println(Thread.currentThread().getName()+"> FIN");

	}


}
