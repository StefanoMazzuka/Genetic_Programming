package Base;

import java.util.ArrayList;

public class Arbol {
	
	private Nodo nodo;
	private ArrayList<Arbol> hijos;
	private Arbol padre;
	private int profundidad;
	private int profundidadTotal;
	private int numNodos;
	private boolean esHoja;
	private boolean esRaiz;
	
	public Arbol() {
		this.nodo = null;
		this.hijos = new ArrayList<Arbol>();
		this.profundidad = 0;
		this.numNodos = 1;
		this.esHoja = false;
		this.esRaiz = true;
	}
	
	public Arbol(Arbol padre, int profundidad, int profundidadTotal) {
		this.hijos = new ArrayList<Arbol>();
		this.padre = padre;
		this.profundidad = profundidad;
		this.profundidadTotal = profundidadTotal;
		this.numNodos = 1;
		if (padre.equals(null))
			this.esRaiz = true;
		else
			this.esRaiz = false;
	}
	
	public Arbol inicializarArbol(String inicializacion, ) {
		
	}
}
