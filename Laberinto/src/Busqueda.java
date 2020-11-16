import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Busqueda {
	
	private static int ID = 1;
	
	public Busqueda(){
	}
	
	public ArrayList<Nodo> algoritmosBusqueda(Problema problem) throws IOException {
		int profundidad = problem.getLaberinto().getFilas() * problem.getLaberinto().getColumnas(); //LA PROFUNDIDAD DEL RPOBLEMA NO ES ZERO
		
		EscribirSolucion es = new EscribirSolucion();
		ArrayList<Nodo> solucion = new ArrayList<Nodo>();
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
					solucion = AlgoritmoBusqueda(problem,"BREADTH", profundidad);
					es.escribirSolucion(solucion, problem, "BREADTH");
					salir = true;
					break;
				case 2:
					solucion = AlgoritmoBusqueda(problem,"DEPTH", profundidad);
					es.escribirSolucion(solucion, problem, "DEPTH");
					salir = true;
					break;
				case 3:
					solucion = AlgoritmoBusqueda(problem,"UNIFORM", profundidad);
					es.escribirSolucion(solucion, problem, "UNIFORM");
					salir = true;
					break;
				case 4:
					solucion = AlgoritmoBusqueda(problem,"GREEDY", profundidad);
					es.escribirSolucion(solucion, problem, "GREEDY");
					salir = true;
					break;
				case 5:
					solucion = AlgoritmoBusqueda(problem,"A", profundidad);
					es.escribirSolucion(solucion, problem, "A");
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
		return solucion;
	}

	private static ArrayList<Nodo> AlgoritmoBusqueda(Problema problem, String estrategia, int profundidad) {
		Visitados visitados = new Visitados();
		Frontera frontera = new Frontera();
		boolean solucion = false;
		visitados.crear_vacio();
		Nodo nodo = new Nodo();
		nodo.setId(0);
		nodo.setPadre(null); //TIENE QUE SER NADIE
		nodo.setEstado(problem.getInicial());
		nodo.setCosto(0);
		nodo.setProfundidad(0);
		nodo.setAccion("None");
		nodo.setHeuristica(ponerHeuristica(problem.getObjetivo(), nodo.getEstado()));
		nodo.setValor(ponerValor(estrategia, nodo));
		
		frontera.insertarNodo(nodo);
		
		while(!frontera.esVacia() && !solucion) {
			nodo = frontera.primerElemento();
			if(Arrays.equals(problem.getObjetivo(), nodo.getEstado())){
				solucion = true;
			}else if(!visitados.pertenece(nodo.getEstado()) && (nodo.getProfundidad() < profundidad)) {
				visitados.insertar(nodo.getEstado());
				ArrayList<Nodo> listaNodosHijos = expandirNodo(problem, nodo, estrategia);
				for(int i = 0; i<listaNodosHijos.size();i++) {
					
					frontera.insertarNodo(listaNodosHijos.get(i));
				}
			}
		}
		return Camino(nodo);
	}

	private static ArrayList<Nodo> Camino(Nodo nodo) {
		ArrayList<Nodo> caminoSolucion = new ArrayList<Nodo>();
		caminoSolucion.add(nodo);
		
		while(nodo.getPadre()!=null) {
			nodo = nodo.getPadre();
			caminoSolucion.add(nodo);
		}
		return caminoSolucion;
	}

	private static ArrayList<Nodo> expandirNodo(Problema problem, Nodo nodo, String estrategia) {
		ArrayList<Nodo> listaNodosHijos = new ArrayList<Nodo>();
		ArrayList<Sucesor> sucesores = problem.sucesores(nodo.getEstado(), problem.getLaberinto());
		quitarDireccionLlegada(nodo, sucesores);
		for(int i = 0; i < sucesores.size() ; i++) {
			Nodo nodohijo = new Nodo();
			nodohijo.setId(ID);
			nodohijo.setEstado(sucesores.get(i).getEstado());
			nodohijo.setPadre(nodo);
			nodohijo.setAccion(sucesores.get(i).getAccion());
			nodohijo.setProfundidad(nodo.getProfundidad()+1);
			nodohijo.setCosto(nodo.getCosto() + sucesores.get(i).getCoste());
			nodohijo.setHeuristica(ponerHeuristica(problem.getObjetivo(), sucesores.get(i).getEstado()));
			nodohijo.setValor(ponerValor(estrategia, nodohijo));
			listaNodosHijos.add(nodohijo);
			ID++;
		}
		return listaNodosHijos;
	}

	private static void quitarDireccionLlegada(Nodo nodo, ArrayList<Sucesor> sucesores) {
		if(nodo.getAccion().equals("N") && pertenece("S", sucesores)) {
			sucesores.removeIf(sucesor -> sucesor.getAccion().equals("S"));
		}
		if(nodo.getAccion().equals("E") && pertenece("O", sucesores)) {
			sucesores.removeIf(sucesor -> sucesor.getAccion().equals("O"));
		}
		if(nodo.getAccion().equals("S") && pertenece("N", sucesores)) {
			sucesores.removeIf(sucesor -> sucesor.getAccion().equals("N"));
		}
		if(nodo.getAccion().equals("O") && pertenece("E", sucesores)) {
			sucesores.removeIf(sucesor -> sucesor.getAccion().equals("E"));
		}
		
	}
	
	public static boolean pertenece(String accion, ArrayList<Sucesor> sucesores) {
		boolean pertenece = false;
		for (int i = 0; i < sucesores.size(); i++) {
			pertenece = sucesores.get(i).getAccion().equals(accion);
			if (pertenece) {
				return pertenece;
			}
		}
		return pertenece;
	}

	private static int ponerValor(String estrategia, Nodo nodo) {
		int valor = 0;
		switch (estrategia) {
		case "BREADTH":
			valor = nodo.getProfundidad();
			break;
		case "DEPTH":
			valor = 1/(nodo.getProfundidad()+1);
			break;
		case "UNIFORM":
			valor = nodo.getCosto();
			break;
		case "GREEDY":
			valor = nodo.getHeuristica();
			break;
		case "A":
			valor = nodo.getCosto()+nodo.getHeuristica();
			break;
		}
		return valor;
	}

	public static int ponerHeuristica(int[] objetivo, int[] estado) {
		int manhattan = 0;
		manhattan = Math.abs(estado[0]-objetivo[0]) + Math.abs(estado[1]-objetivo[1]);
		return manhattan;
	}
}
