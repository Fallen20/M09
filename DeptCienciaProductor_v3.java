package EjerciciosThread;

public class DeptCienciaProductor_v3 extends Thread{
	private MagatzemCombustible_v3 magatzemCombustible_v2;

	public DeptCienciaProductor_v3(MagatzemCombustible_v3 magatzemCombustible_v2) {
		super();
		this.magatzemCombustible_v2 = magatzemCombustible_v2;
	}
	
	public void run(){	
		System.out.println("DeptCIENCIAProductor_v3: "+Thread.currentThread().getName()+" - Inicio");
		
		for(int contador=1;contador<=20;contador++) {
						

			if(magatzemCombustible_v2.numContenidorsAlMagatzem()<=10 || magatzemCombustible_v2.numContenidorsAlMagatzem()==0) {//solo ejecuta si todos los sitios no estan llenos
				System.out.println("DeptCIENCIAProductor_v3: Ejecutando produirContenidorDeCombustible() por "+contador+" vez");
				magatzemCombustible_v2.produirContenidorDeCombustible();
				System.out.println("DeptCIENCIAProductor_v3: Ejecucion numero "+contador+"- numContenidorsAlMagatzem(): "+magatzemCombustible_v2.numContenidorsAlMagatzem());

			}
		}
		System.out.println("DeptCIENCIAProductor_v3: "+Thread.currentThread().getName()+" - Fin");

		
	}
	

}
