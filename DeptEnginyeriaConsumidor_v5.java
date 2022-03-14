package EjerciciosThread;

public class DeptEnginyeriaConsumidor_v5 implements Runnable{
	
	private MagatzemCombustible_v5 magatzemCombustible_v2;
	private int veces;

	public DeptEnginyeriaConsumidor_v5(MagatzemCombustible_v5 magatzemCombustible_v2, int veces) {
		super();
		this.magatzemCombustible_v2 = magatzemCombustible_v2;
		this.veces=veces;
	}
	
	public void run(){	
		System.out.println("DeptENGINYERIAConsumidor_v5: "+Thread.currentThread().getName()+" - Inicio");
		boolean ok=false;
		
		for(int contador=1;contador<=veces;) {
			//ok=magatzemCombustible_v2.consumirContenidorDeCombustible()
				if(magatzemCombustible_v2.consumirContenidorDeCombustible()==true) {
					//System.out.println("\tDeptENGINYERIAConsumidor_v5:"+Thread.currentThread().getName()+" Ejecutando consumirContenidorDeCombustible() por "+contador+" vez");
					//System.out.println("DeptENGINYERIAConsumidor_v5:"+Thread.currentThread().getName()+" Ejecucion numero "+contador+"- numContenidorsAlMagatzem(): "+magatzemCombustible_v2.numContenidorsAlMagatzem());
					contador++;
				}
				
			
			
		}
		
		System.out.println("DeptENGINYERIAConsumidor_v5: "+Thread.currentThread().getName()+" - Fin");

	}
	

}
