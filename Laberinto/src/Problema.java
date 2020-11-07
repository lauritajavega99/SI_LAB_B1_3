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
	
	@Override
	public String toString() {
		return "Problema [inicial = " + Arrays.toString(inicial) + ", objetivo = " + Arrays.toString(objetivo)
				+ ", laberinto = ...]";
	}
}
