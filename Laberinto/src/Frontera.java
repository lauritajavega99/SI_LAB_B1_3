
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Frontera {
	Queue<Nodo> frontera;
	ArrayList<Nodo> front = new ArrayList<Nodo>();

	public Frontera() {

		frontera = new PriorityQueue<Nodo>();

	}

	public Queue<Nodo> getFrontera() {
		return frontera;
	}

	public void setFrontera(Queue<Nodo> frontera) {
		this.frontera = frontera;
	}

	public void insertarNodo(Nodo nodo) {
		frontera.add(nodo);

	}

	public Nodo primerElemento() {
		Nodo n = frontera.poll();
		return n;
	}

	public Nodo eliminarNodo() {

		Nodo n = frontera.remove();
		return n;
	}

	public boolean esVacia() {
		return frontera.isEmpty();
	}

	public ArrayList<Nodo> getFront() {
		return front;
	}

	public void setFront(ArrayList<Nodo> front) {
		this.front = front;
	}

	public void recorrerCola() {
		for (Nodo n : frontera) {
			System.out.println(n.toString());
		}
	}

}