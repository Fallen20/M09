package EjerciciosThread;

public class Exercici_2_v2{
	public static void  inicialitzarPrograma(){
		MagatzemCombustible_v2 magatzemCombustible_v2=new MagatzemCombustible_v2();
		
		DeptCienciaProductor_v2 deptCienciaProductor_v1=new DeptCienciaProductor_v2(magatzemCombustible_v2);
		DeptEnginyeriaConsumidor_v2 deptEnginyeriaConsumidor_v1=new DeptEnginyeriaConsumidor_v2(magatzemCombustible_v2);
		
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
