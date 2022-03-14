package EjerciciosThread;

public class Exercici_2_v4{
	public static void  inicialitzarPrograma(){
		MagatzemCombustible_v4 magatzemCombustible_v3=new MagatzemCombustible_v4();
		
		DeptCienciaProductor_v4 deptCienciaProductor_v1=new DeptCienciaProductor_v4(magatzemCombustible_v3);
		DeptEnginyeriaConsumidor_v4 deptEnginyeriaConsumidor_v1=new DeptEnginyeriaConsumidor_v4(magatzemCombustible_v3);
		
		Thread cienciaThread=new Thread(deptCienciaProductor_v1,"ciencia");
		Thread ingenieriaThread=new Thread(deptEnginyeriaConsumidor_v1,"engenieria");
		
		System.out.println("Exercici_2_v4: Inicio");
		cienciaThread.start();
		ingenieriaThread.start();
		
		try {
			cienciaThread.join();
			ingenieriaThread.join();
		} catch (InterruptedException e) {e.printStackTrace();}
		
		
		System.out.println("Exercici_2_v4: FIN");
	}

}
