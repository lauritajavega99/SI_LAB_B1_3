import java.util.Collections;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Frontera front = new Frontera();
		int[] estado1 = { 0, 0 };
		int[] estado2 = { 1, 3 };
		int[] estado3 = { 1, 0 };
		int[] estado4 = { 5, 1 };
		int[] estado5 = { 4, 0 };

		Nodo n1 = new Nodo(0, estado1, 3, 0, 3, null, 8);
		front.insertarNodo(n1);
		Nodo n2 = new Nodo(1, estado2, 5, 0, 3, null, 9);
		front.insertarNodo(n2);
		Nodo n3 = new Nodo(2, estado3, 5, 0, 3, null, 7);
		front.insertarNodo(n3);
		Nodo n4 = new Nodo(3, estado4, 8, 0, 3, null, 7);
		front.insertarNodo(n4);
		Nodo n5 = new Nodo(4, estado5, 15, 0, 3, null, 7);
		front.insertarNodo(n5);
		
		Collections.sort(front.getFrontera());
	
		front.recorrerLista();

	}

}
