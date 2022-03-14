package EjerciciosThread;

public class Exercici_2_v3{
	public static void  inicialitzarPrograma(){
		MagatzemCombustible_v3 magatzemCombustible_v3=new MagatzemCombustible_v3();
		
		DeptCienciaProductor_v3 deptCienciaProductor_v1=new DeptCienciaProductor_v3(magatzemCombustible_v3);
		DeptEnginyeriaConsumidor_v3 deptEnginyeriaConsumidor_v1=new DeptEnginyeriaConsumidor_v3(magatzemCombustible_v3);
		
		Thread cienciaThread=new Thread(deptCienciaProductor_v1,"ciencia");
		Thread ingenieriaThread=new Thread(deptEnginyeriaConsumidor_v1,"engenieria");
		
		System.out.println("Exercici2_v3: Inicio");
		cienciaThread.start();
		ingenieriaThread.start();
		
		//el join es para que lo de fin de ej1 se ejecute al final del todo, sino en cuantoa acabe uno va a sacarlo
		//tambien para que tras X segundos vuelva a ejecutarse
		try {
			cienciaThread.join(5000);
			ingenieriaThread.join(5000);
			//ahora tras 5seg, independientemente de lo que este haciendo, va a seguir la ejecucion del MAIN
			//si lo pones sin: el hijo actual, hijo de main?, se ejecuta tras acabar ambos
			//si le pones con: el hijo actual se duerme durante Xseg de join y entonces sigue
		} catch (InterruptedException e) {e.printStackTrace();}
		
		
		System.out.println("Exercici2_v3: FIN");
	}

}
