import java.io.IOException;
import java.util.*;

public class ClasePrincipal {

	private static final int NORTE = 0;
	private static final int ESTE = 1;
	private static final int SUR = 2;
	private static final int OESTE = 3;

	public static void main(String[] args) throws IOException {
		menu();
	}

	private static void menu() throws IOException {
		Scanner sn = new Scanner(System.in);
		boolean salir = false;
		String nombre;
		int opcion; // Guardaremos la opcion del usuario
		while (!salir) {
			System.out.println("Introduce una de las opciones disponibles:");
			System.out.println("1. Crear un laberinto aleatorio");
			System.out.println("2. Leer y dibujar laberinto");
			System.out.println("3. Resolver un problema");
			System.out.println("0. Salir");
			try {

				opcion = sn.nextInt();
				switch (opcion) {
				case 1:
					construirLab();
					salir = true;
					break;
				case 2:
					Dibujar d = new Dibujar();
					Problema problem = new Problema();
					//Busqueda b = new Busqueda();
					LeerJSON l = new LeerJSON();

					System.out.println("Introduzca el nombre del Problema que desea leer:");
					nombre = sn.next();

					problem = l.leerJson(nombre);
					//SOLUCION DEL LABERINTO
					////Frontera f = new Frontera();
					//f= b.obtenerFrontera();
					//while (!f.esVacia()) {
					//	int[] e = f.primerElemento().getEstado();
					//	System.out.print(e[0]);
					//	System.out.println(e[1]);
					//}
					Laberinto lab = problem.getLaberinto();
					
					try {
						d.dibujar(lab, lab.getFilas(), lab.getColumnas());

					} catch (NullPointerException e) {
						System.out
								.println("No se ha podido crear el laberinto debido a una inconsistencia del fichero.");
					}
					salir = true;

					break;
				case 3:
					Dibujar d3 = new Dibujar();
					Problema problem3 = new Problema();
					Busqueda b3 = new Busqueda();
					LeerJSON l3 = new LeerJSON();
					Frontera  f = new Frontera();

					System.out.println("Introduzca el nombre del Problema que desea resolver:");
					nombre = sn.next();

					problem = l3.leerJson(nombre);
					//SOLUCION DEL LABERINTO
					ArrayList<Nodo> solucion3 = b3.algoritmosBusqueda(problem);
					ArrayList<Nodo> front = new ArrayList();
					ArrayList<Nodo> arbol= new ArrayList();
					Frontera f3 = new Frontera();
					arbol= b3.obtenerArbol();
					f= b3.obtenerFrontera();
					while (!f.esVacia()) {
						front.add(f.primerElemento());
					}
					Laberinto lab3 = problem.getLaberinto();
					
					try {
						d3.solucion(lab3, lab3.getFilas(), lab3.getColumnas(), front  , solucion3 , arbol);

					} catch (NullPointerException e) {
						System.out
								.println("No se ha podido crear el laberinto debido a una inconsistencia del fichero.");
					}
					salir = true;

					break;


				case 0:
					salir = true;
					System.out.println("Saliendo..");
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

	public static void construirLab() {
		Scanner sn = new Scanner(System.in);
		boolean salir = false;
		while (!salir) {
			try {

				System.out.println("Introduce el número de filas:");
				int m = sn.nextInt(); // filas
				System.out.println("Introduce el número de columnas:");
				int n = sn.nextInt(); // columnas
				
				Problema problem = new Problema();
				Laberinto laberinto = new Laberinto();
				laberinto = introducirCeldas(laberinto, n, m);
				laberinto = wilson(laberinto);
				
				problem.setLaberinto(laberinto);
				
				añadirInicial(problem);
				añadirObjetivo(problem);
				
				mostrarCeldas(laberinto);
				
				System.out.println(problem.toString());

				Dibujar d = new Dibujar();
				d.dibujar(laberinto, m, n);

				EscribirJSON e = new EscribirJSON();
				e.escribirJSON(problem);
				
				salir = true;

			} catch (InputMismatchException e) {
				System.out.println("Debes insertar un número.\n");
				sn.next();

			}
		}

	}

	private static void añadirInicial(Problema problem) {
		int[] inicial = new int[2];
		Random r = new Random();
		int filas = problem.getLaberinto().getFilas();
		int columnas = problem.getLaberinto().getColumnas();
		filas = r.nextInt(filas);
		columnas = r.nextInt(columnas);
		inicial[0] = filas;
		inicial[1] = columnas;
		problem.setInicial(inicial);
	}

	private static void añadirObjetivo(Problema problem) {
		int[] objetivo = new int[2];
		int filas = problem.getLaberinto().getFilas();
		int columnas = problem.getLaberinto().getColumnas();
		objetivo[0] = filas;
		objetivo[1] = columnas;
		problem.setObjetivo(objetivo);
	}

	public static Laberinto introducirCeldas(Laberinto lab, int n, int m) { // Metodo que inicializa el laberinto.
		lab.setColumnas(n);
		lab.setFilas(m);
		int[] posicion;
		Celda celdas[][] = new Celda[m][n];
		for (int i = 0; i < celdas.length; i++) {
			for (int j = 0; j < celdas[0].length; j++) {

				posicion = new int[2];
				posicion[0] = i;
				posicion[1] = j;
				Celda c = new Celda(posicion, valorAleatorio(), false);
				c.setVecinoN(false);
				c.setVecinoE(false);
				c.setVecinoS(false);
				c.setVecinoO(false);
				celdas[i][j] = c;
			}
		}
		lab.setCeldas(celdas);
		return lab;
	}

	private static int valorAleatorio() {
		Random r = new Random();
		return r.nextInt(4);
	}

	public static Laberinto wilson(Laberinto lab) {
		Celda nextC, veciC, iniC = new Celda();
		Random r = new Random();
		Celda[][] celdas = lab.getCeldas();

		if (esComienzo(lab)) { // true si estan todas las celdas NO visitadas
			System.out.println("El laberinto se empezará a formar...");
			iniC = celdaAleatoria(lab);
			iniC.setVisitado(true);
			celdas[iniC.getPosicion()[0]][iniC.getPosicion()[1]] = iniC;
			lab.setCeldas(celdas);
			return wilson(lab);

		} else if (estaCompletado(lab)) { // True si no quedan celdas por visitar
			System.out.println("El laberinto esta completo");
			return lab;

		} else { // ITERACIONES DEL CAMINO
			nextC = celdaAleatoria(lab);
			celdas[nextC.getPosicion()[0]][nextC.getPosicion()[1]] = nextC;
			int direccion;
			ArrayList<Celda> camino = new ArrayList<Celda>();
			camino.add(nextC);

			do { // HAY QUE HACER UN METODO Y UNICAMENTE DIFERENCIAR LA DIRECCION POR EL RAMDON
				direccion = r.nextInt(4);
				if (direccion == NORTE && comprobarLimite(lab, nextC, NORTE)) { // TRUE SI ESTÁ DENTRO DEL LIMITE

					veciC = celdas[nextC.getPosicion()[0] - 1][nextC.getPosicion()[1]];
					camino = caminoWilson(veciC, camino); // PARA TRATAR EL CAMINO Y LOS BUCLES
					nextC = cogerUltimaCelda(camino); // OBTENGO LA ULTIMA DE LA LISTA
					if (celdaVisitada(veciC, NORTE)) { // TRUE SI LA SIGUIENTE EN LA DIRECCION ESTA VISITADA
						camino = ponerAVisitadas(camino);
						quitarParedes(camino);
						return wilson(lab);
					}

				} else if (direccion == ESTE && comprobarLimite(lab, nextC, ESTE)) {

					veciC = celdas[nextC.getPosicion()[0]][nextC.getPosicion()[1] + 1];
					camino = caminoWilson(veciC, camino); // PARA TRATAR EL CAMINO Y LOS BUCLES
					nextC = cogerUltimaCelda(camino); // OBTENGO LA ULTIMA DE LA LISTA
					if (celdaVisitada(veciC, ESTE)) { // TRUE SI LA SIGUIENTE EN LA DIRECCION ESTA VISITADA
						camino = ponerAVisitadas(camino);
						quitarParedes(camino);
						return wilson(lab);
					}

				} else if (direccion == SUR && comprobarLimite(lab, nextC, SUR)) {

					veciC = celdas[nextC.getPosicion()[0] + 1][nextC.getPosicion()[1]];
					camino = caminoWilson(veciC, camino); // PARA TRATAR EL CAMINO Y LOS BUCLES
					nextC = cogerUltimaCelda(camino); // OBTENGO LA ULTIMA DE LA LISTA
					if (celdaVisitada(veciC, SUR)) { // TRUE SI LA SIGUIENTE EN LA DIRECCION ESTA VISITADA
						camino = ponerAVisitadas(camino);
						quitarParedes(camino);
						return wilson(lab);
					}

				} else if (direccion == OESTE && comprobarLimite(lab, nextC, OESTE)) {

					veciC = celdas[nextC.getPosicion()[0]][nextC.getPosicion()[1] - 1];
					camino = caminoWilson(veciC, camino); // PARA TRATAR EL CAMINO Y LOS BUCLES
					nextC = cogerUltimaCelda(camino); // OBTENGO LA ULTIMA DE LA LISTA
					if (celdaVisitada(veciC, OESTE)) { // TRUE SI LA SIGUIENTE EN LA DIRECCION ESTA VISITADA
						camino = ponerAVisitadas(camino);
						quitarParedes(camino);
						return wilson(lab);
					}
				}
			} while (true);
		}
	}

	private static ArrayList<Celda> caminoWilson(Celda veciC, ArrayList<Celda> camino) {
		if (estaEnCamino(veciC, camino)) {
			camino = quitarDeCamino(camino, veciC);
		} else {
			camino = meterEnCamino(veciC, camino);
		}
		return camino;
	}

	private static ArrayList<Celda> ponerAVisitadas(ArrayList<Celda> camino) {
		for (int i = 0; i < camino.size(); i++) {
			camino.get(i).setVisitado(true);
		}
		return camino;
	}

	private static ArrayList<Celda> meterEnCamino(Celda veciC, ArrayList<Celda> camino) {
		camino.add(veciC);
		return camino;
	}

	private static Celda cogerUltimaCelda(ArrayList<Celda> camino) {
		Celda c;
		c = camino.get(camino.size() - 1);
		return c;
	}

	private static ArrayList<Celda> quitarDeCamino(ArrayList<Celda> camino, Celda veciC) {

		for (int i = camino.size() - 1; i > 0; i--) { // QUITAMOS HACIA ATRÁS
			if (camino.get(i) != null) {
				if (veciC.getPosicion()[0] == camino.get(i).getPosicion()[0]
						&& veciC.getPosicion()[1] == camino.get(i).getPosicion()[1]) {
					return camino;
				}
				camino.remove(i);
			}
		}
		return camino;
	}

	private static boolean estaEnCamino(Celda veciC, ArrayList<Celda> camino) {
		for (int i = 0; i < camino.size(); i++) {
			if (camino.get(i) != null) {
				if (veciC.getPosicion()[0] == camino.get(i).getPosicion()[0]
						&& veciC.getPosicion()[1] == camino.get(i).getPosicion()[1]) {
					return true;
				}
			}
		}
		return false;
	}

	public static void quitarParedes(ArrayList<Celda> camino) {

		Celda c;
		Celda c2;
		for (int i = 0; i < camino.size() - 1; i++) {

			c = camino.get(i);
			c2 = camino.get(i + 1);

			// NORTE
			if (camino.get(i).getPosicion()[0] - 1 == camino.get(i + 1).getPosicion()[0]
					&& camino.get(i).getPosicion()[1] == camino.get(i + 1).getPosicion()[1]) {

				c.setVecinoN(true);
				c2.setVecinoS(true);

			}
			// ESTE
			if (camino.get(i).getPosicion()[0] == camino.get(i + 1).getPosicion()[0]
					&& camino.get(i).getPosicion()[1] + 1 == camino.get(i + 1).getPosicion()[1]) {

				c.setVecinoE(true);
				c2.setVecinoO(true);
			}

			// SUR
			if (camino.get(i).getPosicion()[0] + 1 == camino.get(i + 1).getPosicion()[0]
					&& camino.get(i).getPosicion()[1] == camino.get(i + 1).getPosicion()[1]) {

				c.setVecinoS(true);
				c2.setVecinoN(true);
			}

			// OESTE
			if (camino.get(i).getPosicion()[0] == camino.get(i + 1).getPosicion()[0]
					&& camino.get(i).getPosicion()[1] - 1 == camino.get(i + 1).getPosicion()[1]) {

				c.setVecinoO(true);
				c2.setVecinoE(true);

			}
		}
	}

	public static boolean celdaVisitada(Celda c, int direccion) {
		if (direccion == NORTE && c.isVisitado()) {
			return true;
		} else if (direccion == ESTE && c.isVisitado()) {
			return true;
		} else if (direccion == SUR && c.isVisitado()) {
			return true;
		} else if (direccion == OESTE && c.isVisitado()) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean comprobarLimite(Laberinto lab, Celda c, int direccion) { // COMPROBAR LIMITES
		if (direccion == NORTE && c.getPosicion()[0] - 1 > -1) {
			return true;
		} else if (direccion == ESTE && c.getPosicion()[1] + 1 < lab.getColumnas()) {
			return true;
		} else if (direccion == SUR && c.getPosicion()[0] + 1 < lab.getFilas()) {
			return true;
		} else if (direccion == OESTE && c.getPosicion()[1] - 1 > -1) {
			return true;
		} else {
			return false;
		}
	}

	public static Celda celdaAleatoria(Laberinto lab) { // NOS DEVUELVE UNA CELDA ALEATORIA NO VISITADA
		Celda[][] celdas = lab.getCeldas();
		int fila, columna;
		Random r = new Random();
		Random p = new Random();
		while (true) {
			fila = r.nextInt(lab.getFilas());
			columna = p.nextInt(lab.getColumnas());
			if (!celdas[fila][columna].isVisitado()) {
				break;
			}
		}
		return celdas[fila][columna];
	}

	public static boolean estaCompletado(Laberinto lab) {
		Celda[][] celdas = lab.getCeldas();
		for (int i = 0; i < celdas.length; i++) {
			for (int j = 0; j < celdas[0].length; j++) {
				if (!celdas[i][j].isVisitado()) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean esComienzo(Laberinto lab) {
		Celda[][] celdas = lab.getCeldas();
		for (int i = 0; i < celdas.length; i++) {
			for (int j = 0; j < celdas[0].length; j++) {
				if (celdas[i][j].isVisitado()) {
					return false;
				}
			}
		}
		return true;
	}

	public static void mostrarCeldas(Laberinto lab) {
		Celda[][] celdas = lab.getCeldas();
		for (int i = 0; i < celdas.length; i++) {
			for (int j = 0; j < celdas[0].length; j++) {
				System.out.println(celdas[i][j].toString());
			}
		}
	}
}
