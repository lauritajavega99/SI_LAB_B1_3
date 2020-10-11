import java.util.Arrays;

public class Celda {
    
	private int[] posicion;
	private boolean[] vecinos;
	private int valor;
	private boolean visitado;
	
	public Celda(int[] posicion, boolean[] vecinos, int valor, boolean visitado) {
		this.posicion = posicion;
		this.vecinos = vecinos;
		this.valor = valor;
		this.visitado = visitado;
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

	public boolean isVisitado() {
		return visitado;
	}

	public void setVisitado(boolean visitado) {
		this.visitado = visitado;
	}

	@Override
	public String toString() {
		return "Celda [posicion=" + Arrays.toString(posicion) + ", vecinos=" + Arrays.toString(vecinos) + ", valor="
				+ valor + ", visitado=" + visitado + "]";
	}
	
}


