package EjerciciosThread;

public class DeptEnginyeriaConsumidor_v1 extends Thread{
	
	private MagatzemCombustible_v1 magatzemCombustible_v1;

	public DeptEnginyeriaConsumidor_v1(MagatzemCombustible_v1 magatzemCombustible_v1) {
		super();
		this.magatzemCombustible_v1 = magatzemCombustible_v1;
	}
	
	public void run(){	
		System.out.println("DeptEnginyeriaConsumidor_v1: "+Thread.currentThread().getName()+" - Inicio");
		
		for(int contador=1;contador<=13;contador++) {

			
			if(magatzemCombustible_v1.numContenidorsAlMagatzem()>=1) {//solo ejecuta si hay
				System.out.println("DeptEnginyeriaConsumidor_v1: Ejecutando consumirContenidorDeCombustible() por "+contador+" vez");
				magatzemCombustible_v1.consumirContenidorDeCombustible();
				System.out.println("DeptEnginyeriaConsumidor_v1: Ejecucin numero "+contador+"- numContenidorsAlMagatzem(): "+magatzemCombustible_v1.numContenidorsAlMagatzem());

			}
			
		}
		System.out.println("DeptEnginyeriaConsumidor_v1: "+Thread.currentThread().getName()+" - Fin");

	}
	

}
