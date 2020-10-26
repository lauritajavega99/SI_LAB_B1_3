import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class LeerJSON {

	public LeerJSON() {
	}

	public Laberinto leerJson(String cadena) {

		File archivo = new File("src/" + cadena + ".json");
		FileReader archivojson = null;

		try {
			archivojson = new FileReader(archivo);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		JsonParser parser = new JsonParser();
		JsonObject gsonObj = parser.parse(archivojson).getAsJsonObject();

		int filas = gsonObj.get("rows").getAsInt();
		int columnas = gsonObj.get("cols").getAsInt();

		JsonObject cells = gsonObj.get("cells").getAsJsonObject();
		Laberinto lab = leerLaberinto(filas, columnas, cells);

		if (verificarVecinos(lab)) {

			return lab;
		} else {
			//Incosistencia del fichero
			return null;
		}
	}

	public static boolean verificarVecinos(Laberinto lab) {
		Celda[][] celdas = lab.getCeldas();
		boolean valido = true;
		for (int i = 0; i < celdas.length; i++) {
			for (int j = 0; j < celdas[0].length; j++) {
				if (celdas[i][j].getPosicion()[0] - 1 > -1) { // NORTE

					if (celdas[i][j].getVecinoN() != celdas[i - 1][j].getVecinoS()) {
						System.out.println("Se ha encontrado un error de inconsistencia en la celda [" + i + "][" + j
								+ "] con el vecino NORTE.");
						valido = false;
						break;
					}

				} else if (celdas[i][j].getPosicion()[1] + 1 > lab.getColumnas()) { // ESTE

					if (celdas[i][j].getVecinoE() != celdas[i][j + 1].getVecinoO()) {
						System.out.println("Se ha encontrado un error de inconsistencia en la celda [" + i + "][" + j
								+ "] con el vecino ESTE.");
						valido = false;
						break;

					}

				} else if (celdas[i][j].getPosicion()[0] + 1 > lab.getFilas()) { // SUR

					if (celdas[i][j].getVecinoS() != celdas[i + 1][j].getVecinoN()) {
						System.out.println("Se ha encontrado un error de inconsistencia en la celda [" + i + "][" + j
								+ "] con el vecino SUR.");
						valido = false;
						break;
					}

				} else if (celdas[i][j].getPosicion()[1] - 1 > -1) { // OESTE
					if (celdas[i][j].getVecinoO() != celdas[i][j - 1].getVecinoE()) {
						System.out.println("Se ha encontrado un error de inconsistencia en la celda [" + i + "][" + j
								+ "] con el vecino OESTE.");
						valido = false;
						break;

					}

				}

			}
		}

		return valido;
	}

	public static Laberinto leerLaberinto(int filas, int columnas, JsonObject cells) {
		Laberinto laberinto = new Laberinto();

		laberinto.setFilas(filas);
		laberinto.setColumnas(columnas);
		laberinto = leerCeldas(filas, columnas, cells, laberinto);
		return laberinto;

	}

	public static Laberinto leerCeldas(int filas, int columnas, JsonObject cells, Laberinto laberinto) {

		String posCelda = "";
		int[] vectorPosicion;
		Celda[][] celdas = new Celda[filas][columnas];
		Celda c;
		int k;

		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				vectorPosicion = new int[2];
				c = new Celda();
				posCelda = "(" + i + ", " + j + ")";
				JsonObject cell = cells.get(posCelda).getAsJsonObject();
				vectorPosicion[0] = i;
				vectorPosicion[1] = j;
				c.setPosicion(vectorPosicion);
				c.setValor(cell.get("value").getAsInt());
				JsonArray vecinos = cell.get("neighbors").getAsJsonArray();
				k = 0;
				for (JsonElement vecino : vecinos) {
					if (k == 0) {
						c.setVecinoN(vecino.getAsBoolean());
					}
					if (k == 1) {
						c.setVecinoE(vecino.getAsBoolean());
					}
					if (k == 2) {
						c.setVecinoS(vecino.getAsBoolean());
					}
					if (k == 3) {
						c.setVecinoO(vecino.getAsBoolean());
					}
					k++;
				}
				celdas[i][j] = c;
			}
		}

		laberinto.setCeldas(celdas);

		return laberinto;
	}

}