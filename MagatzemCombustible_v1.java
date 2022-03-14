package EjerciciosThread;

public class MagatzemCombustible_v1 extends Thread{
	private char[] posicionsEnMagatzem = {'0','0','0','0','0','0','0','0','0','0'};
	private int posicio = -1;

	public int numContenidorsAlMagatzem() {
		int ocupados=0;
		for(char a :posicionsEnMagatzem) {
			if(a=='1') {ocupados++;}
		}
		
		return ocupados;
	}
	
	public synchronized void produirContenidorDeCombustible() {
		for(int contador=0;contador<posicionsEnMagatzem.length;contador++) {
			if(posicionsEnMagatzem[contador]=='0') {
				posicionsEnMagatzem[contador]='1';
				break;
			}
		}
		
		
	}
	
	public synchronized void consumirContenidorDeCombustible() {
		
		for(int contador=0;contador<posicionsEnMagatzem.length;contador++) {
			if(posicionsEnMagatzem[contador]=='1') {
				posicionsEnMagatzem[contador]='0';
				break;
			}
		}
		
		
		
	}

	public char[] getPosicionsEnMagatzem() {
		return posicionsEnMagatzem;
	}

	public int getPosicio() {
		return posicio;
	}

	public void setPosicionsEnMagatzem(char[] posicionsEnMagatzem) {
		this.posicionsEnMagatzem = posicionsEnMagatzem;
	}

	public void setPosicio(int posicio) {
		this.posicio = posicio;
	}
	
	
	
}
