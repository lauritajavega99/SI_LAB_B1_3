import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.util.Iterator;

public class EscribirJSON {

	 
	public void EscribirJson(Laberinto lab) {

		JSONObject innerObj = new JSONObject();
		Celda[][] c = lab.getCeldas();
		for (int i = 0; i < c.length; i++) {
			for (int j = 0; j < c[0].length; j++) {
				innerObj.put("(" + Integer.toString(i) + ", " + Integer.toString(j) + ")",
						"value:" + Integer.toString(c[i][j].getValor())+","+"neighbours:["+c[i][j].getVecinoN()+","+c[i][j].getVecinoE()+","+c[i][j].getVecinoS()+","+c[i][j].getVecinoO()+"]");
				
			}
		}
		
		JSONObject obj = new JSONObject();
		obj.put("cells", innerObj);
		obj.put("rows", lab.getFilas());
		obj.put("cols", lab.getColumnas());
		obj.put("max_n", new Integer(4));

		JSONArray list = new JSONArray();
		list.add("[-1,0]");
		list.add("[0,1]");
		list.add("[1,0]");
		list.add("[0,-1]");

		obj.put("mov", list);

		JSONArray list1 = new JSONArray();
		list1.add("N");
		list1.add("E");
		list1.add("S");
		list1.add("0");

		obj.put("id_mov", list1);

	

		try {
			FileWriter file = new FileWriter("C:\\Users\\USUARIO\\git\\SI_LAB_B1_3\\Laberinto\\src\\prueba.json");
			file.write(obj.toJSONString());
			file.flush();
			file.close();

		} catch (Exception ex) {
			System.out.println("Error: " + ex.toString());
		} finally {
			System.out.print(obj);
		}

	}

}
