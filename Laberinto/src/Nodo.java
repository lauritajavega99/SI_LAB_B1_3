import java.util.Arrays;

public class Nodo implements Comparable<Nodo> {
	private int id;
	private Nodo Padre;
	private int[] estado;
	private double valor;
	private int profundidad;
	private int heuristica;
	private String accion;
	private int costo;

	public Nodo(int id, Nodo Padre, int[] estado, double valor, int profundidad, int heuristica, String accion, int costo) {
		this.id = id;
		this.Padre = Padre;
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

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
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

	public Nodo getPadre() {
		return Padre;
	}

	public void setPadre(Nodo padre) {
		Padre = padre;
	}

	@Override
	public String toString() {
		return "Nodo [id=" + id + ", Padre=" + Padre + ", estado=" + Arrays.toString(estado) + ", valor=" + valor
				+ ", profundidad=" + profundidad + ", heuristica=" + heuristica + ", accion=" + accion + ", costo="
				+ costo + "]";
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

			} else { // si tienen el mismo valor de fila se ordenarán por valor de columna (menor a
						// mayor).

				if (estado[1] < n.estado[1]) {

					return -1;

				} else if (estado[1] > n.estado[1]) {

					return 1;

				} else { // en el caso que tengan la misma columna ordenamos por ID
					if (id < n.id) {
						return -1;
					} else {
						return 1;
					}
				}
			}
		}
	}
}
