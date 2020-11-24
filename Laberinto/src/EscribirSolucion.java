import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class EscribirSolucion {

	public EscribirSolucion() {
	}

	public void escribirSolucion(ArrayList<Nodo> solucion, Problema problem, String estrategia) throws IOException {
		String ruta = "src\\generados\\sol_" + problem.getLaberinto().getFilas() + "x"
				+ problem.getLaberinto().getColumnas() + "_" + estrategia + ".txt";
		File archivo = new File(ruta);

		BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
		bw.write("[id][cost,state,father_id,action,depth,h,value]");
		for (int i = solucion.size() - 1; i >= 0; i--) {
			String linea = crearLineaNodo(solucion.get(i), estrategia);
			bw.write(linea);
		}
		bw.close();

	}

	private String crearLineaNodo(Nodo nodo, String estrategia) {
		String linea = "";
		if (estrategia.equals("DEPTH")) {
			linea = "\n[" + nodo.getId() + "][" + nodo.getCosto() + "," + ponerEstado(nodo.getEstado()) + ","
					+ ponerPadre(nodo) + "," + nodo.getAccion() + "," + nodo.getProfundidad() + ","
					+ nodo.getHeuristica() + "," + nodo.getValor() + "]";
		} else {
			linea = "\n[" + nodo.getId() + "][" + nodo.getCosto() + "," + ponerEstado(nodo.getEstado()) + ","
					+ ponerPadre(nodo) + "," + nodo.getAccion() + "," + nodo.getProfundidad() + ","
					+ nodo.getHeuristica() + "," + (int) nodo.getValor() + "]";
		}
		return linea;
	}

	private String ponerEstado(int[] estado) {
		String valor = "(" + estado[0] + ", " + estado[1] + ")";
		return valor;
	}

	private String ponerPadre(Nodo nodo) {
		String idPadre = "";
		if (nodo.getPadre() == null) {
			idPadre = "None";
		} else
			idPadre = "" + nodo.getPadre().getId();
		return idPadre;
	}

}
