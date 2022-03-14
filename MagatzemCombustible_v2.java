package EjerciciosThread;

import java.util.ArrayList;
import java.util.Arrays;

public class MagatzemCombustible_v2 extends Thread{
	

		private ArrayList<Character> posicionsEnMagatzem ;

		
		public MagatzemCombustible_v2() {
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
			for(int contador=0;contador<posicionsEnMagatzem.size();contador++) {
				if(posicionsEnMagatzem.get(contador)=='0') {
					posicionsEnMagatzem.set(contador, '1');
					break;
				}
			}
			
			
		}
		
		public synchronized void consumirContenidorDeCombustible() {
			
			for(int contador=0;contador<posicionsEnMagatzem.size();contador++) {
				if(posicionsEnMagatzem.get(contador)=='1') {
					posicionsEnMagatzem.set(contador, '0');
					break;
				}
			}
			
			
			
		}

		public ArrayList<Character> getPosicionsEnMagatzem() {
			return posicionsEnMagatzem;
		}


		public void setPosicionsEnMagatzem(ArrayList<Character> posicionsEnMagatzem) {
			this.posicionsEnMagatzem = posicionsEnMagatzem;
		}


		
		
		
	}



