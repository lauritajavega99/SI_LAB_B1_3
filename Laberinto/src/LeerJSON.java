

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
    	
		leerJson();
		
    }
    
    public static void leerJson() {
    	
    	File archivo = new File ("src/celdas.json");
    	FileReader archivojson = null;
    	
		try {
			archivojson = new FileReader (archivo);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    	
    	JsonParser parser = new JsonParser();
        JsonObject gsonObj = parser.parse(archivojson).getAsJsonObject();
        
        int filas = gsonObj.get("rows").getAsInt();
        int columnas = gsonObj.get("cols").getAsInt();
        
        JsonObject cells = gsonObj.get("cells").getAsJsonObject();
        
        leerLaberinto(filas, columnas, cells);
        
        
    }
    
    public static void leerLaberinto(int filas, int columnas, JsonObject cells) {
    	Laberinto laberinto = new Laberinto();
    	
    	laberinto.setFilas(filas);
    	laberinto.setColumnas(columnas);
    	leerCeldas(filas, columnas, cells, laberinto);
    	
    }
    
	public static void leerCeldas(int filas, int columnas, JsonObject cells, Laberinto laberinto) {
    	
    	String posCelda = "";
    	int[] vectorPosicion;
    	Celda[][] celdas = new Celda[filas][columnas];
    	Celda c;
    	int k;
    	
    	for(int i = 0; i < filas ; i++) {
        	for (int j = 0; j < columnas ; j++) {
        		vectorPosicion = new int[2];
            	c = new Celda();
        		posCelda = "("+i+","+j+")";
        		JsonObject cell = cells.get(posCelda).getAsJsonObject();
        		vectorPosicion[0] = i;
        		vectorPosicion[1] = j;
        		c.setPosicion(vectorPosicion);
        		c.setValor(cell.get("value").getAsInt());
        		JsonArray vecinos = cell.get("neighbors").getAsJsonArray();
        		k = 0;
        		for (JsonElement vecino : vecinos) {
        			if(k == 0) {
        				c.setVecinoN(vecino.getAsBoolean());
        			}
        			if(k == 1) {
        				c.setVecinoE(vecino.getAsBoolean());
        			}
        			if(k == 2) {
        				c.setVecinoS(vecino.getAsBoolean());
        			}
        			if(k == 3) {
        				c.setVecinoO(vecino.getAsBoolean());
        			}
                    k++;
                }
        		celdas[i][j] = c;
        	}
        }
    	
    	laberinto.setCeldas(celdas);
    	System.out.println("--------------------------------");
    	for(int w = 0; w < celdas.length ; w++) {
        	for (int q = 0; q < celdas[0].length ; q++) {
        		System.out.println(celdas[w][q]); 
        	}
    	}
    }
}