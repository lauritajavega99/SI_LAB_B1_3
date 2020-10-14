import java.util.Arrays;

public class Laberinto {

	private int filas;
	private int columnas;
	private Celda[][] celdas;

	public Laberinto(int filas, int columnas, Celda[][] celdas) {
		this.filas = filas;
		this.columnas = columnas;
		this.celdas = celdas;
	}

	public Laberinto() {
	}

	public int getFilas() {
		return filas;
	}

	public void setFilas(int filas) {
		this.filas = filas;
	}

	public int getColumnas() {
		return columnas;
	}

	public void setColumnas(int columnas) {
		this.columnas = columnas;
	}

	public Celda[][] getCeldas() {
		return celdas;
	}

	public void setCeldas(Celda[][] celdas) {
		this.celdas = celdas;
	}

	@Override
	public String toString() {
		return "Laberinto [filas=" + filas + ", columnas=" + columnas + ", celdas=" + Arrays.toString(celdas) + "]";
	}

}
