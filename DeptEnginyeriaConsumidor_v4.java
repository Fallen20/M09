package EjerciciosThread;

public class DeptEnginyeriaConsumidor_v4 implements Runnable{
	
	private MagatzemCombustible_v4 magatzemCombustible_v2;

	public DeptEnginyeriaConsumidor_v4(MagatzemCombustible_v4 magatzemCombustible_v2) {
		super();
		this.magatzemCombustible_v2 = magatzemCombustible_v2;
	}
	
	public void run(){	
		System.out.println("DeptENGINYERIAConsumidor_v4: "+Thread.currentThread().getName()+" - Inicio");
		
		for(int contador=1;contador<=13;) {
			
				
				if(magatzemCombustible_v2.consumirContenidorDeCombustible()==true) {
					System.out.println("DeptENGINYERIAConsumidor_v4: Ejecutando consumirContenidorDeCombustible() por "+contador+" vez");
					
					System.out.println("DeptENGINYERIAConsumidor_v4: Ejecucion numero "+contador+"- numContenidorsAlMagatzem(): "+magatzemCombustible_v2.numContenidorsAlMagatzem());
					contador++;
				}
				
			
			
		}
		
		System.out.println("DeptENGINYERIAConsumidor_v4: "+Thread.currentThread().getName()+" - Fin");

	}
	

}
