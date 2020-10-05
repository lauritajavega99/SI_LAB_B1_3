
public class ClasePrincipal {

	public static void main(String[] args) {
		
		construirLab();
	}
	
	public static void construirLab() {
		Laberinto laberinto = new Laberinto();
		laberinto = introducirCeldas(laberinto, 3);
		mostrarCeldas(laberinto);
		
	}
	
	public static Laberinto introducirCeldas(Laberinto lab, int tamañoLab) { //introducir tamaño y celdas
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
				celdas[i][j] = c;
			}
		}
		lab.setCeldas(celdas);
		return lab;
		
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
