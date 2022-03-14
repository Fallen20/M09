package EjerciciosThread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class MagatzemCombustible_v4{
	
		private Semaphore semaphore;
		private ArrayList<Character> posicionsEnMagatzem ;

		
		public MagatzemCombustible_v4() {
			super();
			this.posicionsEnMagatzem = new ArrayList<>(Arrays.asList('0','0','0','0','0','0','0','0','0','0'));
			semaphore=new Semaphore(1);//solo puede acceder uno a la vez
		}

		public synchronized int numContenidorsAlMagatzem() {
			int ocupados=0;
			for(char a :posicionsEnMagatzem) {
				if(a=='1') {ocupados++;}
			}
			
			return ocupados;
		}
		
		public  boolean produirContenidorDeCombustible() {
			if(numContenidorsAlMagatzem()<10) {//si hay hueco para producir
				
				try {
					semaphore.acquire();//le da permisos para acceder
				} catch (InterruptedException e) {e.printStackTrace();}
				
				for(int contador=0;contador<posicionsEnMagatzem.size();contador++) {
					if(posicionsEnMagatzem.get(contador)=='0') {
						posicionsEnMagatzem.set(contador, '1');
						
						semaphore.release();//como ya ha hecho el trabajo, deja pasar al siguiente
						return true;
					}
				}
			}
			return false;
			
			
		}
		
		public boolean consumirContenidorDeCombustible() {
			if(numContenidorsAlMagatzem()>0) {//si hay para consumir
				try {
					semaphore.acquire();//le da permisos para acceder
				} catch (InterruptedException e) {e.printStackTrace();}
				
				for(int contador=0;contador<posicionsEnMagatzem.size();contador++) {
					if(posicionsEnMagatzem.get(contador)=='1') {
						posicionsEnMagatzem.set(contador, '0');
						semaphore.release();//como ya ha hecho el trabajo, deja pasar al siguiente
						
						return true;
					}
				}
			}
			return false;
			
			
			
		}

		public ArrayList<Character> getPosicionsEnMagatzem() {
			return posicionsEnMagatzem;
		}


		public void setPosicionsEnMagatzem(ArrayList<Character> posicionsEnMagatzem) {
			this.posicionsEnMagatzem = posicionsEnMagatzem;
		}


		
		
		
	}



