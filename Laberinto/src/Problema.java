import java.util.ArrayList;
import java.util.Arrays;

public class Problema {
	private int[] inicial;
	private int[] objetivo;
	private Laberinto laberinto;
	
	public Problema(int[] inicial, int[] objetivo, Laberinto laberinto) {
		this.inicial = inicial;
		this.objetivo = objetivo;
		this.laberinto = laberinto;
	}
	
	public Problema() {
	}

	public int[] getInicial() {
		return inicial;
	}

	public void setInicial(int[] inicial) {
		this.inicial = inicial;
	}

	public int[] getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(int[] objetivo) {
		this.objetivo = objetivo;
	}

	public Laberinto getLaberinto() {
		return laberinto;
	}

	public void setLaberinto(Laberinto laberinto) {
		this.laberinto = laberinto;
	}
	
	public ArrayList<Sucesor> sucesores(int[] estado, Laberinto lab) {
		ArrayList<Sucesor> sucesores = new ArrayList<Sucesor>();
		
		if (comprobarLimite(lab, estado, 0)) { // NORTE
			Sucesor sucesor0 = new Sucesor();
			int[] estado0 = {estado[0]-1, estado[1]};
			sucesor0.setAccion("N");
			sucesor0.setEstado(estado0);
			sucesor0.setCoste(lab.getCeldas()[estado[0]-1][estado[1]].getValor());
			sucesores.add(sucesor0);
		}
		if (comprobarLimite(lab, estado, 1)) { // ESTE
			Sucesor sucesor1 = new Sucesor();
			int[] estado1 = {estado[0], estado[1]+1};
			sucesor1.setAccion("E");
			sucesor1.setEstado(estado1);
			sucesor1.setCoste(lab.getCeldas()[estado[0]][estado[1]+1].getValor());
			sucesores.add(sucesor1);
		}
		if (comprobarLimite(lab, estado, 2)) { // SUR
			Sucesor sucesor2 = new Sucesor();
			int[] estado2 = {estado[0]+1, estado[1]};
			sucesor2.setAccion("S");
			sucesor2.setEstado(estado2);
			sucesor2.setCoste(lab.getCeldas()[estado[0]+1][estado[1]].getValor());
			sucesores.add(sucesor2);
		}
		if (comprobarLimite(lab, estado, 3)) { // OESTE
			Sucesor sucesor3 = new Sucesor();
			int[] estado3 = {estado[0], estado[1]-1};
			sucesor3.setAccion("O");
			sucesor3.setEstado(estado3);
			sucesor3.setCoste(lab.getCeldas()[estado[0]][estado[1]-1].getValor());
			sucesores.add(sucesor3);
			
		}
		
		return sucesores;
	}
	
	public static boolean comprobarLimite(Laberinto lab, int[] estado, int direccion) { // COMPROBAR LIMITES
		if (direccion == 0 && estado[0] - 1 > -1) {
			return true;
		} else if (direccion == 1 && estado[1] + 1 < lab.getColumnas()) {
			return true;
		} else if (direccion == 2 && estado[0] + 1 < lab.getFilas()) {
			return true;
		} else if (direccion == 3 && estado[1] - 1 > -1) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return "Problema [inicial = " + Arrays.toString(inicial) + ", objetivo = " + Arrays.toString(objetivo)
				+ ", laberinto = ...]";
	}
}
