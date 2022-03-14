package EjerciciosThread;

import java.util.Iterator;

public class Exercici_2_v5{
	public static void  inicialitzarPrograma(){
		MagatzemCombustible_v5 magatzemCombustible_v3=new MagatzemCombustible_v5();
		
		DeptCienciaProductor_v5 deptCienciaProductor_v1=new DeptCienciaProductor_v5(magatzemCombustible_v3);
		DeptEnginyeriaConsumidor_v5 deptEnginyeriaConsumidor1=new DeptEnginyeriaConsumidor_v5(magatzemCombustible_v3,7);
		DeptEnginyeriaConsumidor_v5 deptEnginyeriaConsumidor2=new DeptEnginyeriaConsumidor_v5(magatzemCombustible_v3,10);
		
		
		Thread cienciaThread=new Thread(deptCienciaProductor_v1,"ciencia");
		Thread ingenieriaThread=new Thread(deptEnginyeriaConsumidor1,"engenieria1");
		Thread ingenieriaThread2=new Thread(deptEnginyeriaConsumidor2,"engenieria2");
		
		System.out.println("Exercici_2_v5: Inicio");
		cienciaThread.start();
		ingenieriaThread.start();
		ingenieriaThread2.start();
		
		try {
			cienciaThread.join();
			ingenieriaThread.join();
			ingenieriaThread.join();
		} catch (InterruptedException e) {e.printStackTrace();}
		
		System.out.println("Exercici_2_v5: PosicionsEnMagatzem(): "+magatzemCombustible_v3.numContenidorsAlMagatzem());
		System.out.println("Exercici_2_v5: FIN");
	}

}
