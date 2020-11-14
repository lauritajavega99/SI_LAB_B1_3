import java.util.*;
public class Sucesor {
	ArrayList <Nodo> listaNodos;
	private int coste;
	private String accion;
	private int[] estado;
	
	public Sucesor(int coste, String accion, int[] estado,Nodo padre, Busqueda b, Problema p, String estrategia) {
		
		Nodo nHijo = new Nodo();
		nHijo.setEstado(estado);
		nHijo.setCosto( padre.getCosto()+coste); 
		nHijo.setAccion(accion);
		nHijo.setPadre(padre);
		nHijo.setProfundidad(padre.getProfundidad()+1);
		nHijo.setHeuristica(b.ponerHeuristica(p.getObjetivo(), nHijo));
		nHijo.setValor(b.ponerValor(estrategia, nHijo));
		
		listaNodos.add(nHijo);
	}

	//Para devolver la lista.
	public ArrayList<Nodo> getListaNodos() {
		return listaNodos;
	}


	public int getCoste() {
		return coste;
	}

	public void setCoste(int coste) {
		this.coste = coste;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public int[] getEstado() {
		return estado;
	}

	public void setEstado(int[] estado) {
		this.estado = estado;
	}
	
	
	
}
