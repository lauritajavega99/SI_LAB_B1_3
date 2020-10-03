import java.util.Arrays;

public class Celda {
    
	private int[] posicion;
	private boolean[] vecinos;
	private int valor;
	
	public Celda(int[] posicion, boolean[] vecinos, int valor) {
		this.posicion = posicion;
		this.vecinos = vecinos;
		this.valor = valor;
	}
	
	public Celda() {
		
	}

	public int[] getPosicion() {
		return posicion;
	}

	public void setPosicion(int[] posicion) {
		this.posicion = posicion;
	}

	public boolean[] getVecinos() {
		return vecinos;
	}

	public void setVecinos(boolean[] vecinos) {
		this.vecinos = vecinos;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Celda [posicion=" + Arrays.toString(posicion) + ", vecinos=" + Arrays.toString(vecinos) + ", valor="
				+ valor + "]";
	}
	
	
}


