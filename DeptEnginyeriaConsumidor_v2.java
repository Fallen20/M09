package EjerciciosThread;

public class DeptEnginyeriaConsumidor_v2 extends Thread{
	
	private MagatzemCombustible_v2 magatzemCombustible_v2;

	public DeptEnginyeriaConsumidor_v2(MagatzemCombustible_v2 magatzemCombustible_v2) {
		super();
		this.magatzemCombustible_v2 = magatzemCombustible_v2;
	}
	
	public void run(){	
		System.out.println("DeptENGINYERIAConsumidor_v2: "+Thread.currentThread().getName()+" - Inicio");
		
		for(int contador=1;contador<=13;contador++) {

			
			if(magatzemCombustible_v2.numContenidorsAlMagatzem()>=1) {//solo ejecuta si hay
				System.out.println("DeptENGINYERIAConsumidor_v2: Ejecutando consumirContenidorDeCombustible() por "+contador+" vez");
				magatzemCombustible_v2.consumirContenidorDeCombustible();
				System.out.println("DeptENGINYERIAConsumidor_v2: Ejecucion numero "+contador+"- numContenidorsAlMagatzem(): "+magatzemCombustible_v2.numContenidorsAlMagatzem());

			}
				
			
		}
		
		System.out.println("DeptENGINYERIAConsumidor_v2: "+Thread.currentThread().getName()+" - Fin");

	}
	

}
