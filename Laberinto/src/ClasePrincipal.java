import java.util.ArrayList;

public class ClasePrincipal {
	
	private static final int NORTE = 0;
	private static final int ESTE = 1;
	private static final int SUR = 2;
	private static final int OESTE = 3;
	private static final ArrayList<Celda> CAMINO = new ArrayList<Celda>();
	
	public static void main(String[] args) {
		
		construirLab();
	}
	
	public static void construirLab() {
		Laberinto laberinto = new Laberinto();
		laberinto = introducirCeldas(laberinto, 3); //Tamaño del laberinto
		laberinto = wilson(laberinto);
		
		mostrarCeldas(laberinto);
	}
	
	public static Laberinto introducirCeldas(Laberinto lab, int tamañoLab) { //Metodo que inicializa el laberinto.
		lab.setColumnas(tamañoLab);
		lab.setFilas(tamañoLab);
		int[] posicion;
		boolean[] vecinos = new boolean[4];
		Celda celdas[][] = new Celda[tamañoLab][tamañoLab];
		for (int i = 0; i < celdas.length; i++) {
			for (int j = 0; j < celdas[0].length; j++) {
				Celda c = new Celda();
				posicion = new int[2];
				posicion[0] = i;
				posicion[1] = j;
				c.setPosicion(posicion);
				c.setVecinos(vecinos);
				c.setValor(0);
				c.setVisitado(false);
				celdas[i][j] = c;
			}
		}
		lab.setCeldas(celdas);
		return lab;
		
	}
	
	public static Laberinto wilson(Laberinto lab) {
		Celda nextC, veciC, iniC = new Celda();
		Celda[][] celdas = lab.getCeldas();
		
		if(esComienzo(lab)) { // true si estan todas ls celdas NO visitadas
			System.out.println("El laberinto se empezará a formar...");
			iniC = celdaAleatoria(lab);
			iniC.setVisitado(true);
			celdas[iniC.getPosicion()[0]][iniC.getPosicion()[1]] = iniC;
			lab.setCeldas(celdas); 
			return wilson(lab);
			
		}else if (estaCompletado(lab)) { //True si no quedan celdas por visitar
			System.out.println("El laberinto esta completo");
			return lab;
			
		}else { //Las iteraciones...
			nextC = celdaAleatoria(lab); //CELDA ALEATORIA NO VISITADA
			nextC.setVisitado(true);
			celdas[nextC.getPosicion()[0]][nextC.getPosicion()[1]] = nextC;
			lab.setCeldas(celdas); 
			int direccion;
			CAMINO.clear();
			
			do { //HAY QUE HACER UN METODO Y UNICAMENTE DIFERENCIAR LA DIRECCION POR EL RAMDON
				mostrarCeldas(lab);
				System.out.println("--");
				direccion = (int) Math.floor(Math.random()*4);
				if(direccion == NORTE && comprobarLimite(lab, nextC, NORTE)) { //TRUE SI ESTÁ DENTRO DEL LIMITE
					
					veciC = celdas[nextC.getPosicion()[0] - 1][nextC.getPosicion()[1]];
					
					if(estaEnCamino(veciC)) { //DETECTAR BUCLES
						quitarDeCamino(veciC); //QUITO DEL CAMINO LAS DEL BUCLE
						veciC = cogerUltimaCelda(); //OBTENGO LA ULTIMA DE LA LISTA
					}else {
						nextC = quitarPared(nextC, NORTE);
						veciC = quitarPared(veciC, SUR);//COMPROBAR ESTO POR SI DEBO DEVOLVER EL LABERINTO.
						meterEnCamino(nextC);
						meterEnCamino(veciC);
					}
					
					if(celdaVisitada(veciC, NORTE)) { //TRUE SI LA SIGUIENTE EN LA DIRECCION ESTA VISITADA
						
						ponerAVisitadas();
						lab.setCeldas(introducirAlLaberinto(celdas));
						
						return wilson(lab);
					}
					
				}else if(direccion == ESTE && comprobarLimite(lab, nextC, ESTE)) {
					
					
				}else if(direccion == SUR && comprobarLimite(lab, nextC, SUR)) {
					
					
				}else if(direccion == OESTE && comprobarLimite(lab, nextC, OESTE)) {
					
					
				}
			}while(true);
		}
	}
	
	
	private static Celda[][] introducirAlLaberinto(Celda[][] celdas) {
		Celda[] caminoAux = ponerVector();
		for(int i = 0; i < CAMINO.size(); i++) {
			celdas[caminoAux[i].getPosicion()[0]][caminoAux[i].getPosicion()[1]] = caminoAux[i];
		}
		return celdas;
	}

	private static void ponerAVisitadas() {
		Celda[] caminoAux = ponerVector();
		for(int i = 0; i < CAMINO.size(); i++) {
			caminoAux[i].setVisitado(true);
		}
		CAMINO.clear();
		for(int i = 0; i < CAMINO.size(); i++) {
			CAMINO.add(caminoAux[i]);
		}
	}
	
	public static Celda[] ponerVector() {
		Celda[] caminoAux = new Celda[CAMINO.size()];
		for(int i = 0; i < CAMINO.size(); i++) {
			caminoAux[i] = CAMINO.get(i);
		}
		return caminoAux;
	}

	private static void meterEnCamino(Celda veciC) {
		CAMINO.add(veciC);
	}

	private static Celda cogerUltimaCelda() {
		return CAMINO.get(CAMINO.size() - 1);
	}

	private static void quitarDeCamino(Celda veciC) {
		Celda[] caminoAux = ponerVector();
		boolean[] vecinos = new boolean[4];
		
		for(int i = CAMINO.size() - 1; i >= 0; i--) { //quitando hacia atras
			caminoAux[i].setVecinos(vecinos);
			if(veciC.getPosicion()[0] == caminoAux[i].getPosicion()[0] && veciC.getPosicion()[1] == caminoAux[i].getPosicion()[1]) {
				break;
			}
			caminoAux[i] = null;
		}
		
		CAMINO.clear();
		for(int i = 0; i < CAMINO.size(); i++) {
			if(caminoAux[i] != null) {
				CAMINO.add(caminoAux[i]);
			}else {
				break;
			}
		}
	}

	private static boolean estaEnCamino(Celda veciC) {
		for(int i = 0; i < CAMINO.size(); i++) {
			if(veciC.getPosicion()[0] == CAMINO.get(i).getPosicion()[0] && veciC.getPosicion()[1] == CAMINO.get(i).getPosicion()[1]) {
				return true;
			}
		}
		return false;
	}

	public static Celda quitarPared(Celda nextC, int direccion) {
		boolean[] vecinosNext = nextC.getVecinos();
		
		switch(direccion){
		case NORTE:
			vecinosNext[NORTE] = true;
			break;
		case ESTE:
			vecinosNext[ESTE] = true;
			break;
		case SUR:
			vecinosNext[SUR] = true;
			break;
		case OESTE:
			vecinosNext[OESTE] = true;
			break;
		}
		
		nextC.setVecinos(vecinosNext);
		return nextC;
	}
	
	public static boolean celdaVisitada(Celda c, int direccion) {
		if(direccion == NORTE && c.isVisitado()) {
			return true;
		}else if(direccion == ESTE && c.isVisitado()) {
			return true;
		}else if(direccion == SUR && c.isVisitado()) {
			return true;
		}else if(direccion == OESTE && c.isVisitado()) {
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean comprobarLimite(Laberinto lab, Celda c, int direccion) { //COMPROBAR LIMITES
		if(direccion == NORTE && c.getPosicion()[0] - 1 > -1) {
			return true;
		}else if(direccion == ESTE && c.getPosicion()[1] + 1 < lab.getFilas()) {
			return true;
		}else if(direccion == SUR && c.getPosicion()[0] + 1 < lab.getColumnas()) {
			return true;
		}else if(direccion == OESTE && c.getPosicion()[1] - 1 > -1) {
			return true;
		}else {
			return false;
		}
	}
	
	public static Celda celdaAleatoria(Laberinto lab) { //NOS DEVUELVE UNA CELDA ALEATORIA NO VISITADA
		Celda[][] celdas = lab.getCeldas();
		int fila, columna;
		while(true) {
			fila = (int) Math.floor(Math.random()*lab.getFilas());
			columna = (int) Math.floor(Math.random()*lab.getColumnas());
			if(celdas[fila][columna].isVisitado() == false) {
				break;
			}
		}
		return celdas[fila][columna];
	}
	
	public static boolean estaCompletado(Laberinto lab) {
		Celda[][] celdas = lab.getCeldas();
		for(int i = 0; i < celdas.length; i++) {
			for(int j = 0; j < celdas[0].length; j++) {
				if (!celdas[i][j].isVisitado()) {
					return false;
				}
			}
		}
		return true; 
	}
	
	public static boolean esComienzo(Laberinto lab) {
		Celda[][] celdas = lab.getCeldas();
		for(int i = 0; i < celdas.length; i++) {
			for(int j = 0; j < celdas[0].length; j++) {
				if (celdas[i][j].isVisitado()) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static void mostrarCeldas(Laberinto lab) {
		Celda[][] celdas = lab.getCeldas();
		for(int i = 0; i < celdas.length; i++) {
			for(int j = 0; j < celdas[0].length; j++) {
				System.out.println(celdas[i][j].toString());
			}
		}
	}
}
