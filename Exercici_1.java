package EjerciciosThread;

public class Exercici_1 {
	public static void inicialitzarPrograma(){
		Magatzem magatzem=new Magatzem();//clase padre
		
		
		//hijos, heredan del mismo
		CapDeDepartament comandament=new CapDeDepartament(magatzem,100);
		CapDeDepartament armes=new CapDeDepartament(magatzem,100);
		CapDeDepartament timoNavegacio=new CapDeDepartament(magatzem,-30);
		CapDeDepartament engenyeria=new CapDeDepartament(magatzem,1000);
		CapDeDepartament ciencia=new CapDeDepartament(magatzem,-50);
		
		Thread comandamentThread=new Thread(comandament,IKSRotarranConstants.DEPARTAMENTS[1]);//thread
		Thread armesThread=new Thread(armes,IKSRotarranConstants.DEPARTAMENTS[2]);//thread
		Thread timoNavegacioThread=new Thread(timoNavegacio,IKSRotarranConstants.DEPARTAMENTS[3]);//thread
		Thread engenyeriaThread=new Thread(engenyeria,IKSRotarranConstants.DEPARTAMENTS[4]);//thread
		Thread cienciaThread=new Thread(ciencia,IKSRotarranConstants.DEPARTAMENTS[5]);//thread
		
		
		//le damos nombre pero por separado ejemplo	
		//armesThread.setName(IKSRotarranConstants.DEPARTAMENTS[2]);
		
		System.out.println("Exercici_1.inicialitzarPrograma()> INICI");
		//iniciamos
		comandamentThread.start();
		armesThread.start();
		timoNavegacioThread.start();
		engenyeriaThread.start();
		cienciaThread.start();


		//corremos
		try {
			comandamentThread.join();
			armesThread.join();
			timoNavegacioThread.join();
			engenyeriaThread.join();
			cienciaThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		
		
		System.out.println("Exercici_1.magatzem.comprovarQuantitatRacions()> "+magatzem.comprovarQuantitatRacions());
		System.out.println("Exercici_1.inicialitzarPrograma()> FIN");
		
	}
}
