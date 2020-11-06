
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Frontera {

	List<Nodo>frontera;
	private int contador = 0;

	public Frontera() {

		frontera =  new ArrayList<Nodo>();

	}


	public List<Nodo> getFrontera() {
		return frontera;
	}


	public void setFrontera(List<Nodo> frontera) {
		this.frontera = frontera;
	}


	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}

	public void insertarNodo(Nodo nodo) {
		frontera.add(nodo);

	}

	public Nodo eliminarNodo() {

		Nodo n = frontera.remove(0);
		return n;
	}

	public boolean esVacia() {
		return frontera.isEmpty();
	}

	public void InsertarLista(ArrayList<Nodo> listaNodos) {
		for (int i = 0; i < listaNodos.size(); i++) {
			frontera.add(listaNodos.get(i));
		}
	}

	public void recorrerLista() {
		for (Nodo n : frontera) {
			System.out.println(n.toString());
		}
	}


}