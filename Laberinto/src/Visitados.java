import java.util.ArrayList;
import java.util.Arrays;

public class Visitados {

	ArrayList<int[]> visitados;

	public Visitados() {
		visitados = new ArrayList<int[]>();
	}

	public ArrayList<int[]> getVisitados() {
		return visitados;
	}

	public void setVisitados(ArrayList<int[]> visitados) {
		this.visitados = visitados;
	}

	public void insertar(int[] estado) {
		visitados.add(estado);
	}

	public void crear_vacio() {
		visitados.clear();
	}

	public boolean pertenece(int[] estado) {
		boolean pertenece = false;
		for (int i = 0; i < visitados.size(); i++) {
			pertenece = Arrays.equals(visitados.get(i), estado);
			if (pertenece) {
				return pertenece;
			}
		}
		return pertenece;
	}
}
