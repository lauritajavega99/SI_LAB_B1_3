import java.util.*;

public class Prueba_f {

	public static void main(String[] args) {
		Frontera front = new Frontera();
		Random r = new Random();
		
		
	for (int i =0 ; i< 12 ;i++ ) {
		int[] estado = {0,0};
		estado[0]=r.nextInt(100);
		estado[1]=r.nextInt(100);
		Nodo n1 = new Nodo(i,null, estado, r.nextInt(100),r.nextInt(100), r.nextInt(100), null, r.nextInt(100));
		front.insertarNodo(n1);
	}
		
	 while (!front.esVacia()) {
         Nodo o = front.eliminarNodo();
         System.out.println(o.toString());
     }
	

	}

}
