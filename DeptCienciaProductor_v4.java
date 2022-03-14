package EjerciciosThread;

public class DeptCienciaProductor_v4 implements Runnable{
	private MagatzemCombustible_v4 magatzemCombustible_v2;

	public DeptCienciaProductor_v4(MagatzemCombustible_v4 magatzemCombustible_v2) {
		super();
		this.magatzemCombustible_v2 = magatzemCombustible_v2;
	}
	
	public void run(){	
		System.out.println("DeptCIENCIAProductor_v4: "+Thread.currentThread().getName()+" - Inicio");
		
		for(int contador=1;contador<=20;) {
						

				
				
				if(magatzemCombustible_v2.produirContenidorDeCombustible()==true) {
				
					System.out.println("DeptCIENCIAProductor_v4: Ejecutando produirContenidorDeCombustible() por "+contador+" vez");
					System.out.println("DeptCIENCIAProductor_v4: Ejecucion numero "+contador+"- numContenidorsAlMagatzem(): "+magatzemCombustible_v2.numContenidorsAlMagatzem());
					contador++;

				}
			
		}
		System.out.println("DeptCIENCIAProductor_v4: "+Thread.currentThread().getName()+" - Fin");

		
	}
	

}
