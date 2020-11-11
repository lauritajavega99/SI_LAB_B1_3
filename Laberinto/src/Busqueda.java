import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Busqueda {
	
	public static void main(String[] args){
		int[] incial = {0,0};
		int[] objetivo = {15,15};
		
		Problema problem = new Problema();
		problem.setInicial(incial);
		problem.setObjetivo(objetivo);
		algoritmosBusqueda(problem);
	}
	
	public Busqueda(){
		
	}
	
	public static void algoritmosBusqueda(Problema problem) {
		int profundidad = 0;
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
					AlgoritmoBusqueda(problem,"BREADTH", profundidad);
					salir = true;
					break;
				case 2:
					AlgoritmoBusqueda(problem,"DEPTH", profundidad);
					salir = true;
					break;
				case 3:
					AlgoritmoBusqueda(problem,"UNIFORM", profundidad);
					salir = true;
					break;
				case 4:
					AlgoritmoBusqueda(problem,"GREEDY", profundidad);
					salir = true;
					break;
				case 5:
					AlgoritmoBusqueda(problem,"A", profundidad);
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

	private static void AlgoritmoBusqueda(Problema problem, String estrategia, int profundidad) {
		Visitados visitados = new Visitados();
		Frontera frontera = new Frontera();
		boolean solucion = false;
		visitados.crear_vacio();
		Nodo nodo = new Nodo();
		nodo.setPadre(null); //TIENE QUE SER NADIE
		nodo.setEstado(problem.getInicial());
		nodo.setCosto(0);
		nodo.setProfundidad(0);
		nodo.setAccion(null);
		ponerHeuristica(problem.getObjetivo(), nodo);
		ponerValor(estrategia, nodo);
		
		frontera.insertarNodo(nodo);
		
		while(!frontera.esVacia() && !solucion) {
			nodo = frontera.primerElemento();
			if(Arrays.equals(problem.getObjetivo(), nodo.getEstado())){
				solucion = true;
			}else if(visitados.pertenece(nodo.getEstado()) && (nodo.getProfundidad() < profundidad)) {
				
			}
			
		}
		
	}

	private static void ponerValor(String estrategia, Nodo nodo) {
		switch (estrategia) {
		case "BREADTH":
			nodo.setValor(nodo.getProfundidad());
			break;
		case "DEPTH":
			//ARREGLAR O COMPROBAR ESTE CALCULO!!!!!!
			nodo.setValor(1/nodo.getProfundidad());
			break;
		case "UNIFORM":
			nodo.setValor(nodo.getCosto());
			break;
		case "GREEDY":
			nodo.setValor(nodo.getHeuristica());
			break;
		case "A":
			nodo.setValor(nodo.getCosto()+nodo.getHeuristica());
			break;
		}
	}

	public static void ponerHeuristica(int[] objetivo, Nodo nodo) {
		int manhattan = 0;
		manhattan = Math.abs(nodo.getEstado()[0]-objetivo[0]) + Math.abs(nodo.getEstado()[1]-objetivo[1]);
		nodo.setHeuristica(manhattan);
	}
}
