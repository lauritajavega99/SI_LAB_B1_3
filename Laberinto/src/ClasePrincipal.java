
public class ClasePrincipal {
	
	private static final int NORTE = 0;
	private static final int ESTE = 1;
	private static final int SUR = 2;
	private static final int OESTE = 3;
	
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
			nextC = celdaAleatoria(lab);
			nextC.setVisitado(true);
			celdas[nextC.getPosicion()[0]][nextC.getPosicion()[1]] = nextC;
			lab.setCeldas(celdas); 
			int direccion;
			Celda[] camino = new Celda[lab.getColumnas()*lab.getFilas()];
			camino[0] = nextC;
			mostrarCeldas(lab);
			
			do { //HAY QUE HACER UN METODO Y UNICAMENTE DIFERENCIAR LA DIRECCION POR EL RAMDON
				direccion = (int) Math.floor(Math.random()*4);
				if(direccion == NORTE && comprobarLimite(lab, nextC, NORTE)) { //TRUE SI ESTÁ DENTRO DEL LIMITE
					
					veciC = celdas[nextC.getPosicion()[0] - 1][nextC.getPosicion()[1]];
					
					if(estaEnCamino(veciC, camino)) { //DETECTAR BUCLES
						camino = quitarDeCamino(camino, veciC); //QUITO DEL CAMINO LAS DEL BUCLE
						veciC = cogerUltimaCelda(camino); //OBTENGO LA ULTIMA DE LA LISTA
					}else {
						quitarPared(nextC, veciC, NORTE); //COMPROBAR ESTO POR SI DEBO DEVOLVER EL LABERINTO.
						camino = meterEnCamino(veciC, camino);
					}
					
					if(celdaVisitada(veciC, NORTE)) { //TRUE SI LA SIGUIENTE EN LA DIRECCION ESTA VISITADA
						
						camino = ponerAVisitadas(camino);
						lab.setCeldas(introducirAlLaberinto(celdas, camino));
						
						return wilson(lab);
					}
					
				}else if(direccion == ESTE && comprobarLimite(lab, nextC, ESTE)) {
					
					veciC = celdas[nextC.getPosicion()[0]][nextC.getPosicion()[1] + 1];
					
					if(estaEnCamino(veciC, camino)) { //DETECTAR BUCLES
						camino = quitarDeCamino(camino, veciC); //QUITO DEL CAMINO LAS DEL BUCLE
						veciC = cogerUltimaCelda(camino); //OBTENGO LA ULTIMA DE LA LISTA
					}else {
						quitarPared(nextC, veciC, ESTE); //COMPROBAR ESTO POR SI DEBO DEVOLVER EL LABERINTO.
						camino = meterEnCamino(veciC, camino);
					}
					
					if(celdaVisitada(veciC, ESTE)) { //TRUE SI LA SIGUIENTE EN LA DIRECCION ESTA VISITADA
						
						camino = ponerAVisitadas(camino);
						lab.setCeldas(introducirAlLaberinto(celdas, camino));
						
						return wilson(lab);
					}
					
				}else if(direccion == SUR && comprobarLimite(lab, nextC, SUR)) {
					
					veciC = celdas[nextC.getPosicion()[0] + 1][nextC.getPosicion()[1]];
					
					if(estaEnCamino(veciC, camino)) { //DETECTAR BUCLES
						camino = quitarDeCamino(camino, veciC); //QUITO DEL CAMINO LAS DEL BUCLE
						veciC = cogerUltimaCelda(camino); //OBTENGO LA ULTIMA DE LA LISTA
					}else {
						quitarPared(nextC, veciC, SUR); //COMPROBAR ESTO POR SI DEBO DEVOLVER EL LABERINTO.
						camino = meterEnCamino(veciC, camino);
					}
					
					if(celdaVisitada(veciC, SUR)) { //TRUE SI LA SIGUIENTE EN LA DIRECCION ESTA VISITADA
						
						camino = ponerAVisitadas(camino);
						lab.setCeldas(introducirAlLaberinto(celdas, camino));
						
						return wilson(lab);
					}
					
				}else if(direccion == OESTE && comprobarLimite(lab, nextC, OESTE)) {
					
					veciC = celdas[nextC.getPosicion()[0]][nextC.getPosicion()[1] - 1];
					
					if(estaEnCamino(veciC, camino)) { //DETECTAR BUCLES
						camino = quitarDeCamino(camino, veciC); //QUITO DEL CAMINO LAS DEL BUCLE
						veciC = cogerUltimaCelda(camino); //OBTENGO LA ULTIMA DE LA LISTA
					}else {
						quitarPared(nextC, veciC, OESTE); //COMPROBAR ESTO POR SI DEBO DEVOLVER EL LABERINTO.
						camino = meterEnCamino(veciC, camino);
					}
					
					if(celdaVisitada(veciC, OESTE)) { //TRUE SI LA SIGUIENTE EN LA DIRECCION ESTA VISITADA
						
						camino = ponerAVisitadas(camino);
						lab.setCeldas(introducirAlLaberinto(celdas, camino));
						
						return wilson(lab);
					}
					
				}
			}while(true);
		}
	}
	
	
	private static Celda[][] introducirAlLaberinto(Celda[][] celdas, Celda[] camino) {
		
		for(int i = 0; i < camino.length; i++) {
			if(camino[i] != null) {
				celdas[camino[i].getPosicion()[0]][camino[i].getPosicion()[1]] = camino[i];
			}else {
				return celdas;
			}
		}
		
		return celdas;
	}

	private static Celda[] ponerAVisitadas(Celda[] camino) {
		for(int i = 0; i < camino.length; i++) {
			if(camino[i] != null) {
				camino[i].setVisitado(true);
			}else {
				return camino;
			}
		}
		return camino;
		
	}

	private static Celda[] meterEnCamino(Celda veciC, Celda[] camino) {
		for(int i = 0; i < camino.length; i++) {
			if(camino[i] == null) {
				camino[i] = veciC;
				return camino;
			}
		}
		return camino;
	}

	private static Celda cogerUltimaCelda(Celda[] camino) {
		Celda c = new Celda();
		for(int i = 0; i < camino.length; i++) {
			if(camino[i] == null) {
				c = camino[i-1];
				return c;
			}
		}
		return c;
	}

	private static Celda[] quitarDeCamino(Celda[] camino, Celda veciC) {
		
		for(int i = camino.length - 1; i > 0; i--) { //quitando hacia atras
			if(camino[i] != null) {
				if(veciC.getPosicion()[0] == camino[i].getPosicion()[0] && veciC.getPosicion()[1] == camino[i].getPosicion()[1]) {
					return camino;
				}
				camino[i] = null;
			}
		}
		return camino;
	}

	private static boolean estaEnCamino(Celda veciC, Celda[] camino) {
		for(int i = 0; i < camino.length; i++) {
			if(camino[i] != null) {
				if(veciC.getPosicion()[0] == camino[i].getPosicion()[0] && veciC.getPosicion()[1] == camino[i].getPosicion()[1]) {
					return true;
				}
			}
		}
		return false;
	}

	public static void quitarPared(Celda nextC, Celda veciC, int direccion) {
		boolean[] vecinosNext = nextC.getVecinos();
		boolean[] vecinosVeci = veciC.getVecinos();
		
		switch(direccion){
		case NORTE:
			vecinosNext[NORTE] = true;
			vecinosVeci[SUR] = true;
			break;
		case ESTE:
			vecinosNext[ESTE] = true;
			vecinosVeci[OESTE] = true;
			break;
		case SUR:
			vecinosNext[SUR] = true;
			vecinosVeci[NORTE] = true;
			break;
		case OESTE:
			vecinosNext[OESTE] = true;
			vecinosVeci[ESTE] = true;
			break;
		}
		
		nextC.setVecinos(vecinosNext);
		veciC.setVecinos(vecinosVeci);
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
