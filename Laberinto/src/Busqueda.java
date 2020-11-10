import java.util.InputMismatchException;
import java.util.Scanner;

public class Busqueda {

	public Busqueda(){
		
	}
	
	public static void algoritmosBusqueda(Problema problem) {
		Scanner sn = new Scanner(System.in);
		boolean salir = false;
		int opcion; // Guardaremos la opcion del usuario
		while (!salir) {
			System.out.println("Introduce una de las opciones de busqueda:");
			System.out.println("1. Anchura (BREADTH)");
			System.out.println("2. Profundidad Acotada (DEPTH)");
			System.out.println("3. Coste uniforme (UNIFORM)");
			System.out.println("4. Voraz (GREEDY)");
			System.out.println("5. A* (A)");
			System.out.println("6. Salir");
			try {

				opcion = sn.nextInt();
				switch (opcion) {
				case 1:
					AlgoritAnchura(problem);
					salir = true;
					break;
				case 2:
					AlgoritProfundidadAcotada(problem);
					salir = true;
					break;
				case 3:
					AlgoritCosteUniforme(problem);
					salir = true;
					break;
				case 4:
					AlgoritVoraz(problem);
					salir = true;
					break;
				case 5:
					Algorit_A(problem);
					salir = true;
					break;
				default:
					System.out.println("Opción no válida.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Debes insertar un número.");
				sn.next();
			}
		}
		
		
		
	}
	
	private static void Algorit_A(Problema problem) {
		Nodo nodo = new Nodo();
		ponerHeuristica(problem.getObjetivo(), nodo);
		
	}

	private static void AlgoritVoraz(Problema problem) {
		Nodo nodo = new Nodo();
		ponerHeuristica(problem.getObjetivo(), nodo);
		
	}

	private static void AlgoritCosteUniforme(Problema problem) {
		Nodo nodo = new Nodo();
		ponerHeuristica(problem.getObjetivo(), nodo);
		
	}

	private static void AlgoritProfundidadAcotada(Problema problem) {
		Nodo nodo = new Nodo();
		ponerHeuristica(problem.getObjetivo(), nodo);
		
	}

	private static void AlgoritAnchura(Problema problem) {
		Nodo nodo = new Nodo();
		ponerHeuristica(problem.getObjetivo(), nodo);
		
	}

	public static void ponerHeuristica(int[] objetivo, Nodo nodo) {
		int manhattan = 0;
		manhattan = Math.abs(nodo.getEstado()[0]-objetivo[0]) + Math.abs(nodo.getEstado()[1]-objetivo[1]);
		nodo.setHeuristica(manhattan);
	}
}
