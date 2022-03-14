package EjerciciosThread;

import java.util.ArrayList;
import java.util.Arrays;

public class MagatzemCombustible_v3 extends Thread{
	

		private ArrayList<Character> posicionsEnMagatzem ;

		
		public MagatzemCombustible_v3() {
			super();
			this.posicionsEnMagatzem = new ArrayList<>(Arrays.asList('0','0','0','0','0','0','0','0','0','0'));
		}

		public int numContenidorsAlMagatzem() {
			int ocupados=0;
			for(char a :posicionsEnMagatzem) {
				if(a=='1') {ocupados++;}
			}
			
			return ocupados;
		}
		
		public synchronized void produirContenidorDeCombustible() {
			if(numContenidorsAlMagatzem()<10) {//si hay hueco para producir
				for(int contador=0;contador<posicionsEnMagatzem.size();contador++) {
					if(posicionsEnMagatzem.get(contador)=='0') {
						posicionsEnMagatzem.set(contador, '1');
						notify();
						break;
					}
				}
			}
			else {
				try {
					wait(5000);
				}
				catch (InterruptedException e) {e.printStackTrace();}
			}
			
			
			
		}
		
		public synchronized void consumirContenidorDeCombustible() {
			if(numContenidorsAlMagatzem()>0) {//si hay para consumir
				for(int contador=0;contador<posicionsEnMagatzem.size();contador++) {
					if(posicionsEnMagatzem.get(contador)=='1') {
						posicionsEnMagatzem.set(contador, '0');
						notify();//para que siga su curso
						break;
					}
				}
			}
			else {
				try {
					wait(5000);//como hereda de thread no hace falta poner lo de current
				}
				catch (InterruptedException e) {e.printStackTrace();}
			}
			
			
			
		}

		public ArrayList<Character> getPosicionsEnMagatzem() {
			return posicionsEnMagatzem;
		}


		public void setPosicionsEnMagatzem(ArrayList<Character> posicionsEnMagatzem) {
			this.posicionsEnMagatzem = posicionsEnMagatzem;
		}


		
		
		
	}



