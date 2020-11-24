import java.io.FileWriter;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class EscribirJSON {

	public EscribirJSON() {

	}

	public void escribirJSON(Problema problem) {

		JsonObject gsonObj = new JsonObject();

		String inicial = construirAtributo(problem.getInicial());
		String objetivo = construirAtributo(problem.getObjetivo());
		String maze = construirTextMaze(problem);

		gsonObj.addProperty("INITIAL", inicial);
		gsonObj.addProperty("OBJETIVE", objetivo);
		gsonObj.addProperty("MAZE", maze);

		try {
			FileWriter file = new FileWriter("src\\generados\\problema_" + problem.getLaberinto().getFilas() + "x"
					+ problem.getLaberinto().getColumnas() + ".json");
			file.write(gsonObj.toString());
			file.flush();
			file.close();

		} catch (Exception ex) {
			System.out.println("Error: " + ex.toString());
		}

		escribirMaze(problem.getLaberinto());
	}

	private String construirTextMaze(Problema problem) {
		String valor = "problema_" + problem.getLaberinto().getFilas() + "x" + problem.getLaberinto().getColumnas()
				+ "_maze.json";
		return valor;
	}

	private String construirAtributo(int[] atributo) {
		String valor = "(" + atributo[0] + ", " + atributo[1] + ")";
		return valor;
	}

	public void escribirMaze(Laberinto lab) {

		JsonObject gsonObj = new JsonObject();
		JsonObject gsonCells = introducirCeldas(lab);
		gsonObj.addProperty("rows", lab.getFilas());
		gsonObj.addProperty("cols", lab.getColumnas());
		gsonObj.addProperty("max_n", 4);
		gsonObj.add("mov", introducirMov());
		gsonObj.add("id_mov", introducirIDMov());
		gsonObj.add("cells", gsonCells);

		try {
			FileWriter file = new FileWriter(
					"src\\generados\\problema_" + lab.getFilas() + "x" + lab.getColumnas() + "_maze.json");
			file.write(gsonObj.toString());
			file.flush();
			file.close();

		} catch (Exception ex) {
			System.out.println("Error: " + ex.toString());
		}
	}

	private JsonElement introducirIDMov() {
		JsonArray idmov = new JsonArray();
		idmov.add("N");
		idmov.add("E");
		idmov.add("S");
		idmov.add("O");
		return idmov;
	}

	private JsonElement introducirMov() {
		JsonArray mov = new JsonArray();
		JsonArray mov1 = new JsonArray();
		JsonArray mov2 = new JsonArray();
		JsonArray mov3 = new JsonArray();
		JsonArray mov4 = new JsonArray();
		mov1.add(-1);
		mov1.add(0);
		mov2.add(0);
		mov2.add(1);
		mov3.add(1);
		mov3.add(0);
		mov4.add(0);
		mov4.add(-1);
		mov.add(mov1);
		mov.add(mov2);
		mov.add(mov3);
		mov.add(mov4);
		return mov;
	}

	private static JsonObject introducirCeldas(Laberinto lab) {
		JsonObject posicion = new JsonObject();
		Celda[][] celdas = lab.getCeldas();
		String pos = "";
		for (int i = 0; i < celdas.length; i++) {
			for (int j = 0; j < celdas[0].length; j++) {
				JsonObject valores = new JsonObject();
				JsonArray vecinos = new JsonArray();
				valores.addProperty("value", celdas[i][j].getValor());
				vecinos.add(celdas[i][j].getVecinoN());
				vecinos.add(celdas[i][j].getVecinoE());
				vecinos.add(celdas[i][j].getVecinoS());
				vecinos.add(celdas[i][j].getVecinoO());
				valores.add("neighbors", vecinos);
				pos = "(" + i + ", " + j + ")";
				posicion.add(pos, valores);
			}
		}
		return posicion;
	}
}
