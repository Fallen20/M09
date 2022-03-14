package EjerciciosThread;

public class CapDeDepartament implements Runnable{

	private Magatzem magatzem;
	private int movimentRacions;
	
	public CapDeDepartament(Magatzem magatzem, int movimentRacions) {
		super();
		this.magatzem = magatzem;
		this.movimentRacions = movimentRacions;
	}

	@Override
	public void run() {
		//despues del start haces run y tras eso AQUI le dices que hace
		System.out.println("\tDEPARTAMENTO "+Thread.currentThread().getName()+": Se ha iniciado la accion del thread "+Thread.currentThread().getName());
		System.out.println("\tDEPARTAMENTO "+Thread.currentThread().getName()+": Raciones> " + this.movimentRacions);
		
		System.out.println("\tDEPARTAMENTO "+Thread.currentThread().getName()+": magatzem.comprovarQuantitatRacions()> "+magatzem.comprovarQuantitatRacions());
		
		//si es negativo, pilla
		if(this.movimentRacions<0) {
			magatzem.agafaRacions(-movimentRacions);
			System.out.println("\tDEPARTAMENTO "+Thread.currentThread().getName()+": AgafaRacions() ");
						
		}
		else {//si es positivo, anade
			magatzem.retornarRacions(movimentRacions);
			System.out.println("\tDEPARTAMENTO "+Thread.currentThread().getName()+": retornarRacions() ");
				
		}
		
		System.out.println("\tDEPARTAMENTO "+Thread.currentThread().getName()+": magatzem.comprovarQuantitatRacions() TRAS SU ACCION> "+magatzem.comprovarQuantitatRacions());

		
		
		System.out.println("\tDEPARTAMENTO "+Thread.currentThread().getName()+": FIN");
	}

}
