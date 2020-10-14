import java.util.*;

public class ClasePrincipal {

	private static final int NORTE = 0;
	private static final int ESTE = 1;
	private static final int SUR = 2;
	private static final int OESTE = 3;

	public static void main(String[] args) {

		construirLab();
	}

	public static void construirLab() {
		int n = 2;//columnas
		int m=2;//filas
		Laberinto laberinto = new Laberinto();
		laberinto = introducirCeldas(laberinto, n, m);
		laberinto = wilson(laberinto);

		mostrarCeldas(laberinto);

		Dibujar d = new Dibujar();
		d.dibujar(laberinto, m, n);
		
		EscribirJSON e = new EscribirJSON();
		e.EscribirJson(laberinto);

	}

	public static Laberinto introducirCeldas(Laberinto lab, int n , int m ) { // Metodo que inicializa el laberinto.
		lab.setColumnas(n);
		lab.setFilas(m);
		int[] posicion;
		Celda celdas[][] = new Celda[m][n];
		for (int i = 0; i < celdas.length; i++) {
			for (int j = 0; j < celdas[0].length; j++) {

				posicion = new int[2];
				posicion[0] = i;
				posicion[1] = j;
				Celda c = new Celda(posicion, 0, false);
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
		if (estaEnCamino(veciC, camino)) { // DETECTAR BUCLES
			camino = quitarDeCamino(camino, veciC); // QUITO DEL CAMINO LAS DEL BUCLE
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

	/*
	 * METODO QUE PERMITE MOSTAR EL CAMINO SEGUIDO POR WILSON private static void
	 * mostrarCamino(ArrayList<Celda> camino) { System.out.println("CAMINO"); for
	 * (int i = 0; i < camino.size(); i++) {
	 * System.out.print(camino.get(i).getPosicion()[0] + " " +
	 * camino.get(i).getPosicion()[1] + "\n"); } }
	 */

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
