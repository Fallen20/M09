package EjerciciosThread;

public class DeptCienciaProductor_v5 implements Runnable{
	private MagatzemCombustible_v5 magatzemCombustible_v2;

	public DeptCienciaProductor_v5(MagatzemCombustible_v5 magatzemCombustible_v2) {
		super();
		this.magatzemCombustible_v2 = magatzemCombustible_v2;
	}
	
	public void run(){	
		System.out.println("DeptCIENCIAProductor_v5: "+Thread.currentThread().getName()+" - Inicio");
		boolean ok=false;
		
		for(int contador=1;contador<=20;) {			
			ok=magatzemCombustible_v2.produirContenidorDeCombustible();
				if(ok==true) {
					//System.out.println("\tDeptCIENCIAProductor_v5:"+Thread.currentThread().getName()+" Ejecutando produirContenidorDeCombustible() por "+contador+" vez");
					//System.out.println("DeptCIENCIAProductor_v5:"+Thread.currentThread().getName()+" Ejecucion numero "+contador+"- numContenidorsAlMagatzem(): "+magatzemCombustible_v2.numContenidorsAlMagatzem());
					contador++;

				}
			
		}
		System.out.println("DeptCIENCIAProductor_v5: "+Thread.currentThread().getName()+" - Fin");

		
	}
	

}
