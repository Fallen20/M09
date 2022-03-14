package EjerciciosThread;

public class DeptCienciaProductor_v1 extends Thread{
	private MagatzemCombustible_v1 magatzemCombustible_v1;

	public DeptCienciaProductor_v1(MagatzemCombustible_v1 magatzemCombustible_v1) {
		super();
		this.magatzemCombustible_v1 = magatzemCombustible_v1;
	}
	
	public void run(){	
		System.out.println("DeptCienciaProductor_v1: "+Thread.currentThread().getName()+" - Inicio");
		
		for(int contador=1;contador<=20;contador++) {
						

			if(magatzemCombustible_v1.numContenidorsAlMagatzem()<=magatzemCombustible_v1.getPosicionsEnMagatzem().length || magatzemCombustible_v1.numContenidorsAlMagatzem()==0) {//solo ejecuta si todos los sitios no estan llenos
				System.out.println("DeptCienciaProductor_v1: Ejecutando produirContenidorDeCombustible() por "+contador+" vez");
				magatzemCombustible_v1.produirContenidorDeCombustible();
				System.out.println("DeptCienciaProductor_v1: Ejecuci�n numero "+contador+"- numContenidorsAlMagatzem(): "+magatzemCombustible_v1.numContenidorsAlMagatzem());

			}
		}
		System.out.println("DeptCienciaProductor_v1: "+Thread.currentThread().getName()+" - Fin");

	}
	

}
