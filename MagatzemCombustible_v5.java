package EjerciciosThread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class MagatzemCombustible_v5{

	private Semaphore semaphore;
	private ArrayList<Character> posicionsEnMagatzem ;


	public MagatzemCombustible_v5() {
		super();
		this.posicionsEnMagatzem = new ArrayList<>(Arrays.asList('0','0','0','0','0','0','0','0','0','0'));
		semaphore=new Semaphore(1);//solo puede acceder uno a la vez
	}

	public int numContenidorsAlMagatzem() {
		int ocupados=0;
		for(char a :posicionsEnMagatzem) {
			if(a=='1') {ocupados++;}
		}

		return ocupados;
	}

	public  boolean produirContenidorDeCombustible() {

		try {
			semaphore.acquire();//le da permisos para acceder
			
		} catch (InterruptedException e) {e.printStackTrace();}
		
		if(numContenidorsAlMagatzem()<10) {//si hay hueco para producir					
			for(int contador=0;contador<posicionsEnMagatzem.size();contador++) {
				if(posicionsEnMagatzem.get(contador)=='0') {
					posicionsEnMagatzem.set(contador, '1');
					System.out.println("\t"+Thread.currentThread().getName()+"> Ha hecho el metodo de produirContenidorDeCombustible()");
					//System.out.println("\t\t"+Thread.currentThread().getName()+"> numero de contenedores: "+numContenidorsAlMagatzem());


					semaphore.release();//como ya ha hecho el trabajo, deja pasar al siguiente
					return true;
				}
			}
		}
		semaphore.release();
		
		return false;

	}

	public boolean consumirContenidorDeCombustible() {	//manera2 con un boolean
		boolean exito=false;

		try {
			semaphore.acquire();//le da permisos para acceder
		} catch (InterruptedException e) {e.printStackTrace();}
		//en este hemos de primero mirar el contenedor ya que no tiene syncro, asi que lo hemos de poner
		//al principio y no dentro, ya que sino se pueden meter los 3 a la vez y petar
		
		if(numContenidorsAlMagatzem()>0) {//si hay para consumir
			for(int contador=0;contador<posicionsEnMagatzem.size();contador++) {
				if(posicionsEnMagatzem.get(contador)=='1') {
					posicionsEnMagatzem.set(contador, '0');
					System.out.println("\t"+Thread.currentThread().getName()+"> Ha hecho el metodo de consumirContenidorDeCombustible()");
					exito=true;
					break;

				}
			}
		}
		semaphore.release();//se tiene que poner si o si porque sino no se liberan los demas y se quedan esperando, bloqueando todo
		return exito;
		



	}

	public ArrayList<Character> getPosicionsEnMagatzem() {
		return posicionsEnMagatzem;
	}


	public void setPosicionsEnMagatzem(ArrayList<Character> posicionsEnMagatzem) {
		this.posicionsEnMagatzem = posicionsEnMagatzem;
	}





}



