package EjerciciosThread;

public class Exercici_2_v1{
	public static void  inicialitzarPrograma(){
		MagatzemCombustible_v1 magatzemCombustible_v1=new MagatzemCombustible_v1();
		
		DeptCienciaProductor_v1 deptCienciaProductor_v1=new DeptCienciaProductor_v1(magatzemCombustible_v1);
		DeptEnginyeriaConsumidor_v1 deptEnginyeriaConsumidor_v1=new DeptEnginyeriaConsumidor_v1(magatzemCombustible_v1);
		
		Thread cienciaThread=new Thread(deptCienciaProductor_v1,"ciencia");
		Thread ingenieriaThread=new Thread(deptEnginyeriaConsumidor_v1,"engenieria");
		
		System.out.println("Exercici2_v2: Inicio");
		cienciaThread.start();
		ingenieriaThread.start();
		
		//el join es para que lo de fin de ej1 se ejecute al final del todo, sino en cuantoa acabe uno va a sacarlo
		
		try {
			cienciaThread.join();
			ingenieriaThread.join();
		} catch (InterruptedException e) {e.printStackTrace();}
		
		
		
		System.out.println("Exercici2_v2: FIN");
	}

}
