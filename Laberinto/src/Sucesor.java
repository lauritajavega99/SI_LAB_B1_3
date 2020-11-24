
public class Sucesor {

	private int coste;
	private String accion;
	private int[] estado;

	public Sucesor(int coste, String accion, int[] estado) {
		this.coste = coste;
		this.accion = accion;
		this.estado = estado;
	}

	public Sucesor() {
	}

	public int getCoste() {
		return coste;
	}

	public void setCoste(int coste) {
		this.coste = coste;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public int[] getEstado() {
		return estado;
	}

	public void setEstado(int[] estado) {
		this.estado = estado;
	}

}
