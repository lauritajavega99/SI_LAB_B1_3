
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

	public static void main(String[] args) {

		Dibujar d = new Dibujar();
		Laberinto lab = new Laberinto();

		lab = leerJson();
		d.dibujar(lab, lab.getFilas(), lab.getColumnas());
	}

	public static Laberinto leerJson() {

		File archivo = new File("src/prueba.json");
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

		return lab;

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
		/*
		 * System.out.println("--------------------------------"); for(int w = 0; w <
		 * celdas.length ; w++) { for (int q = 0; q < celdas[0].length ; q++) {
		 * System.out.println(celdas[w][q]); } }
		 */
		return laberinto;
	}

}