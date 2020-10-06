
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
			
			do {
				direccion = (int) Math.floor(Math.random()*4);
				if(direccion == NORTE && comprobarLimite(lab, nextC, NORTE)) {
					if(celdaVisitada(lab, nextC, NORTE)) {
						
					}else {
						
					}
					
				}else if(direccion == ESTE && comprobarLimite(lab, nextC, ESTE)) {
					
				}else if(direccion == SUR && comprobarLimite(lab, nextC, SUR)) {
					
				}else if(direccion == OESTE && comprobarLimite(lab, nextC, OESTE)) {
					
				}
			}while(true);
		}
	}
	
	public static boolean celdaVisitada(Laberinto lab, Celda c, int direccion) {
		
		
		
		return true;
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
