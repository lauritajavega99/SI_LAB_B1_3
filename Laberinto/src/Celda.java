import java.util.Arrays;

public class Celda {

	private int[] posicion;
	private int valor;
	private boolean visitado;
	private boolean vecinoN;
	private boolean vecinoS;
	private boolean vecinoE;
	private boolean vecinoO;

	public Celda(int[] posicion, int valor, boolean visitado) {
		this.posicion = posicion;
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

	public boolean getVecinoN() {
		return vecinoN;
	}

	public void setVecinoN(boolean vecinoN) {
		this.vecinoN = vecinoN;
	}

	public boolean getVecinoS() {
		return vecinoS;
	}

	public void setVecinoS(boolean vecinoS) {
		this.vecinoS = vecinoS;
	}

	public boolean getVecinoE() {
		return vecinoE;
	}

	public void setVecinoE(boolean vecinoE) {
		this.vecinoE = vecinoE;
	}

	public boolean getVecinoO() {
		return vecinoO;
	}

	public void setVecinoO(boolean vecinoO) {
		this.vecinoO = vecinoO;
	}

	@Override
	public String toString() {
		return "Celda [posicion=" + Arrays.toString(posicion) + ", vecinos=" + "[" + vecinoN + ", " + vecinoE + ", "
				+ vecinoS + ", " + vecinoO + "] " + "valor=" + valor + ", visitado=" + visitado + "]";
	}

}
