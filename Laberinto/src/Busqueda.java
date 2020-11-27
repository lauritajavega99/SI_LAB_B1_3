import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Busqueda {

	private static int ID = 1;
	private static Frontera F = new Frontera();
	private static ArrayList<Nodo> ARBOL = new ArrayList<Nodo>();

	public Busqueda() {

	}

	public ArrayList<Nodo> algoritmosBusqueda(Problema problem) throws IOException {
		int profundidad = 1000000; // LA PROFUNDIDAD MAXIMA
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
					solucion = AlgoritmoBusqueda(problem, "BREADTH", profundidad);
					es.escribirSolucion(solucion, problem, "BREADTH");
					salir = true;
					break;
				case 2:
					solucion = AlgoritmoBusqueda(problem, "DEPTH", profundidad);
					es.escribirSolucion(solucion, problem, "DEPTH");
					salir = true;
					break;
				case 3:
					solucion = AlgoritmoBusqueda(problem, "UNIFORM", profundidad);
					es.escribirSolucion(solucion, problem, "UNIFORM");
					salir = true;
					break;
				case 4:
					solucion = AlgoritmoBusqueda(problem, "GREEDY", profundidad);
					es.escribirSolucion(solucion, problem, "GREEDY");
					salir = true;
					break;
				case 5:
					solucion = AlgoritmoBusqueda(problem, "A", profundidad);
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
		ArrayList<Nodo> arbol = new ArrayList<Nodo>();
		boolean solucion = false;
		visitados.crear_vacio();
		Nodo nodo = new Nodo();
		nodo.setId(0);
		nodo.setPadre(null); // TIENE QUE SER NADIE
		nodo.setEstado(problem.getInicial());
		nodo.setCosto(0);
		nodo.setProfundidad(0);
		nodo.setAccion("None");
		nodo.setHeuristica(ponerHeuristica(problem.getObjetivo(), nodo.getEstado()));
		nodo.setValor(ponerValor(estrategia, nodo)); 
		frontera.insertarNodo(nodo);

		while (!frontera.esVacia() && !solucion) {
			nodo = frontera.primerElemento();
			if (Arrays.equals(problem.getObjetivo(), nodo.getEstado())) {
				solucion = true;
			} else if (!visitados.pertenece(nodo.getEstado()) && (nodo.getProfundidad() < profundidad)) {
				visitados.insertar(nodo.getEstado());
				arbol.add(nodo);

				//solo se expadirán aquello que tengan a true el booleano(es decir, no pared)
				ArrayList<Nodo> listaNodosHijos = expandirNodo(problem, nodo, estrategia);
				for (int i = 0; i < listaNodosHijos.size(); i++) {
					
					//Insertamos los nodos hijos que se han generado de ese nodo
					frontera.insertarNodo(listaNodosHijos.get(i));

				}

			}
		
		}

		hacerArbolFrontera(arbol,frontera);
		return Camino(nodo);
	}

	private static void hacerArbolFrontera(ArrayList<Nodo> arbol, Frontera frontera) {
		while(!frontera.esVacia()) {
			Nodo nodo = frontera.primerElemento();
			int[] estado = nodo.getEstado();
			boolean pertenece = false;
			for(int i= 0 ; i < arbol.size();i++) {
				if(arbol.get(i).getEstado()[0]==estado[0] && arbol.get(i).getEstado()[1]==estado[1]) {
					pertenece = true;
				}
			}
			if(!pertenece) 
				F.insertarNodo(nodo);
			
			
		}
		for(int j=0; j< arbol.size();j++) {
		ARBOL.add(arbol.get(j));
		}
	}



	private static ArrayList<Nodo> Camino(Nodo nodo) {
		ArrayList<Nodo> caminoSolucion = new ArrayList<Nodo>();
		caminoSolucion.add(nodo);
		while (nodo.getPadre() != null) {
			nodo = nodo.getPadre();
			caminoSolucion.add(nodo);
		}
		return caminoSolucion;
	}

	private static ArrayList<Nodo> expandirNodo(Problema problem, Nodo nodo, String estrategia) {
		ArrayList<Nodo> listaNodosHijos = new ArrayList<Nodo>();
		ArrayList<Sucesor> sucesores = problem.sucesores(nodo.getEstado(), problem.getLaberinto());
		for (int i = 0; i < sucesores.size(); i++) {
			Nodo nodohijo = new Nodo();
			nodohijo.setId(ID);
			nodohijo.setEstado(sucesores.get(i).getEstado());
			nodohijo.setPadre(nodo);
			nodohijo.setAccion(sucesores.get(i).getAccion());
			nodohijo.setProfundidad(nodo.getProfundidad() + 1);
			nodohijo.setCosto(nodo.getCosto() + sucesores.get(i).getCoste());
			nodohijo.setHeuristica(ponerHeuristica(problem.getObjetivo(), sucesores.get(i).getEstado()));
			nodohijo.setValor(ponerValor(estrategia, nodohijo));
			listaNodosHijos.add(nodohijo);
			ID++;

		}
		return listaNodosHijos;
	}

	
	private static double ponerValor(String estrategia, Nodo nodo) {
		double valor = 0;
		switch (estrategia) {
		case "BREADTH":
			valor = nodo.getProfundidad();
			break;
		case "DEPTH":
			valor = redondeo((double) 1 / (nodo.getProfundidad() + 1));
			break;
		case "UNIFORM":
			valor = nodo.getCosto();
			break;
		case "GREEDY":
			valor = nodo.getHeuristica();
			break;
		case "A":
			valor = nodo.getCosto() + nodo.getHeuristica();
			break;
		}

		return valor;
	}

	private static double redondeo(double d) {
		return Math.round(d * Math.pow(10, 17)) / Math.pow(10, 17);
	}

	
	public static int ponerHeuristica(int[] objetivo, int[] estado) {
		int manhattan = 0;
		manhattan = Math.abs(estado[0] - objetivo[0]) + Math.abs(estado[1] - objetivo[1]);
		return manhattan;
	}

	public Frontera obtenerFrontera() {
		return F;
	}

	public ArrayList<Nodo> obtenerArbol() {
		return ARBOL;
	}

	public static boolean introducirFrontera(ArrayList<Nodo> arbol, Nodo nodo) {
		boolean pertenece = true;
		for (int i = 0; i < arbol.size(); i++) {

			if (arbol.get(i).getEstado()[0] == nodo.getEstado()[0]
					&& arbol.get(i).getEstado()[1] == nodo.getEstado()[1]) {
				pertenece = false;
			}
		}
		return pertenece;

	}
}
