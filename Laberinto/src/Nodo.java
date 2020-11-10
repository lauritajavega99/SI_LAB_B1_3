import java.util.Arrays;
import java.util.Comparator;

public class Nodo implements Comparable<Nodo> {
	private int id;
	private int[] estado;
	private int valor;
	private int profundidad;
	private int heuristica;
	private String accion;
	private int costo;

	public Nodo(int id, int[] estado, int valor, int profundidad, int heuristica, String accion, int costo) {
		this.id = id;
		this.estado = estado;
		this.valor = valor;
		this.profundidad = profundidad;
		this.heuristica = heuristica;
		this.accion = accion;
		this.costo = costo;
	}
	
	public Nodo() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int[] getEstado() {
		return estado;
	}

	public void setEstado(int[] estado) {
		this.estado = estado;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public int getProfundidad() {
		return profundidad;
	}

	public void setProfundidad(int profundidad) {
		this.profundidad = profundidad;
	}

	public int getHeuristica() {
		return heuristica;
	}

	public void setHeuristica(int heuristica) {
		this.heuristica = heuristica;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public int getCosto() {
		return costo;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}

	public String toString() {
		return "Nodo [id=" + id + ", estado=" + Arrays.toString(estado) + ", valor=" + valor + ", profundidad="
				+ profundidad + ", heuristica=" + heuristica + ", accion=" + accion + ", costo=" + costo + "]";
	}

	@Override
	public int compareTo(Nodo n) {
		// se ordenan por el valor.
		if (valor < n.valor) {
			return -1;
		} else if (valor > n.valor) {
			return 1;

		} else { // en el caso de que tengan el mismo valor se guiará por el que tenga menor.
					// fila.

			if (estado[0] < n.estado[0]) {

				return -1;

			} else if (estado[0] > n.estado[0]) {

				return 1;

			} else { //si tienen el mismo valor de fila se ordenarán por valor de columna (menor a mayor).

				if (estado[1] < n.estado[1]) {

					return -1;

				} else {

					return 1;
				}
			}
		}
	}

}
