
public class ClasePrincipal {

	public static void main(String[] args) {
		
		construirLab();
	}
	
	public static void construirLab() {
		Laberinto laberinto = new Laberinto();
		laberinto = introducirCeldas(laberinto, 4); //Tamaño del laberinto
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
		Celda nextC, siguiC = new Celda();
		if(esComienzo(lab)) { // true si estan todas ls celdas NO visitadas
			System.out.println("El laberinto se empezará a formar...");
			lab.getCeldas()[celdaAleatoria(lab).getPosicion()[0]][celdaAleatoria(lab).getPosicion()[1]].setVisitado(true); //se puede poner el metodo pero se alarga mucho...
			wilson(lab);
			
		}else if (estaCompletado(lab)) { //True si no quedan celdas por visitar
			System.out.println("El laberinto esta completo");
			return lab;
			
		}else { //Las iteraciones...
			
			//LA MAGIA
			
		}
		
		return lab;
	}
	
	public static Celda celdaAleatoria(Laberinto lab) {
		Celda c = new Celda();
		Celda[][] celdas = lab.getCeldas();
		int fila, columna;
		int[] posicion = new int[2];
		while(true) {
			fila = (int) Math.floor(Math.random()*lab.getFilas());
			columna = (int) Math.floor(Math.random()*lab.getColumnas());
			if(celdas[fila][columna].isVisitado() == false) {
				posicion[0] = fila;
				posicion[1] = columna;
				c.setPosicion(posicion);
				break;
			}
		}
		return c;
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
