package Base;

import java.util.ArrayList;
import java.util.Random;

public class Arbol {

	private Nodo nodo;
	private ArrayList<Arbol> hijos;
	private Arbol padre;
	private int profundidad;
	private int profundidadTotal;
	private int numNodos;
	private boolean esHoja;
	private boolean esRaiz;

	/*
	 * Constructora por defecto.
	 * Inicializamos las variables.
	 */
	public Arbol() {
		this.nodo = null;
		this.hijos = new ArrayList<Arbol>();
		this.profundidad = 0;
		this.numNodos = 1;
		this.esHoja = false;
		this.esRaiz = true;
	}
	/*
	 * Constructora con parametros de entrada:
	 * Arbol, profundidad y profundidad total.
	 * 
	 * Si el Arbol es nulo quiere decir que es una raiz y ponemos el atributo raiz a true.
	 */
	public Arbol(Arbol padre, int profundidad, int profundidadTotal) {
		this.hijos = new ArrayList<Arbol>();
		this.padre = padre;
		this.profundidad = profundidad;
		this.profundidadTotal = profundidadTotal;
		this.numNodos = 1;
		
		if (padre == null)
			this.esRaiz = true;
		else
			this.esRaiz = false;
	}

	/*
	 * Functions
	 */
	
	/*
	 * Inicializamos por metodo CRECIENTE el arbol.
	 */
	private Arbol inicializarArbolCreciente(Arbol padre, boolean funcionIf, 
			int profundidad, int profundidadTotal) {	
		Arbol a = new Arbol(padre, profundidad, profundidadTotal);

		if (a.profundidad < a.profundidadTotal) {
			Random r = new Random();
			if (r.nextInt(2) == 0) {
				a.nodo = new Funcion(funcionIf);
				a.esHoja = false;
				a.hijos.add(a.inicializarArbolCreciente(a, funcionIf, profundidad + 1, profundidadTotal));

				if (a.nodo.getValor().equals("OR") || a.nodo.getValor().equals("AND"))
					a.hijos.add(a.inicializarArbolCreciente(a, funcionIf, profundidad + 1, profundidadTotal));
				
				else if (a.nodo.getValor().equals("IF")) {
					a.hijos.add(a.inicializarArbolCreciente(a, funcionIf, profundidad + 1, profundidadTotal));
					a.hijos.add(a.inicializarArbolCreciente(a, funcionIf, profundidad + 1, profundidadTotal));
				}

				for (int i = 0; i < a.hijos.size(); i++) {
					a.numNodos += a.hijos.get(i).numNodos;
				}
			}
			else {
				a.nodo = new Terminal();
				a.numNodos = 1;
				a.esHoja = true;
			}
		}
		else {
			a.nodo = new Terminal();
			a.numNodos = 1;
			a.esHoja = true;
		}

		return a;
	}
	/*
	 * Inicializamos por metodo COMPLETO el arbol.
	 */
	private Arbol inicializarArbolCompleto(Arbol padre, boolean IFagregado, 
			int profundidad, int profundidadTotal) {
		Arbol a = new Arbol(padre, profundidad, profundidadTotal);

		if (a.profundidad < a.profundidadTotal) {
			a.nodo = new Funcion(IFagregado);
			a.esHoja = false;
			a.hijos.add(a.inicializarArbolCompleto(a, IFagregado, profundidad + 1, profundidadTotal));

			if (a.nodo.getValor().equals("OR") || a.nodo.getValor().equals("AND"))
				a.hijos.add(a.inicializarArbolCompleto(a, IFagregado, profundidad + 1, profundidadTotal));
			
			else if (a.nodo.getValor().equals("IF")) {
				a.hijos.add(a.inicializarArbolCompleto(a, IFagregado, profundidad + 1, profundidadTotal));
				a.hijos.add(a.inicializarArbolCompleto(a, IFagregado, profundidad + 1, profundidadTotal));
			}

			for (int i = 0; i < a.hijos.size(); i++) {
				a.numNodos += a.hijos.get(i).numNodos;
			}
		}
		else {
			a.nodo = new Terminal();
			a.numNodos = 1;
			a.esHoja = true;
		}

		return a;
	}
	/*
	 * Inicializamos el arbol.
	 */
	public static Arbol inicializarArbol(String inicializacion, boolean IFagregado, int profundidadTotal) {
		Arbol a = new Arbol(null, 0, profundidadTotal);
		a.nodo = new Funcion(IFagregado);
		a.esHoja = false;
		a.esRaiz = true;

		if (inicializacion.equals("Creciente")) {
			a.hijos.add(a.inicializarArbolCreciente(a, IFagregado, 1, profundidadTotal));

			if (a.nodo.getValor().equals("OR") || a.nodo.getValor().equals("AND"))
				a.hijos.add(a.inicializarArbolCreciente(a, IFagregado, 1, profundidadTotal));
			
			else if (a.nodo.getValor().equals("IF")) {
				a.hijos.add(a.inicializarArbolCreciente(a, IFagregado, 1, profundidadTotal));
				a.hijos.add(a.inicializarArbolCreciente(a, IFagregado, 1, profundidadTotal));
			}

			for (int i = 0; i < a.hijos.size(); i++) {
				a.numNodos += a.hijos.get(i).numNodos;
			}
		}
		
		else if (inicializacion.equals("Completo")) {
			a.hijos.add(a.inicializarArbolCompleto(a, IFagregado, 1, profundidadTotal));

			if (a.nodo.getValor().equals("OR") || a.nodo.getValor().equals("AND"))
				a.hijos.add(a.inicializarArbolCompleto(a, IFagregado, 1, profundidadTotal));
			
			else if (a.nodo.getValor().equals("IF")) {
				a.hijos.add(a.inicializarArbolCompleto(a, IFagregado, 1, profundidadTotal));
				a.hijos.add(a.inicializarArbolCompleto(a, IFagregado, 1, profundidadTotal));
			}

			for (int i = 0; i < a.hijos.size(); i++) {
				a.numNodos += a.hijos.get(i).numNodos;
			}
		}
		
		for (int i = 0; i < a.hijos.size(); i++) {
			a.numNodos += a.hijos.get(i).numNodos;
		}

		return a;
	}
	/*
	 * Calculamos el fitness recorriendo todo el arbol.
	 */
	public boolean calcularFitnessRecursivo(boolean[] casoDePrueba) {
		boolean fitnessArbol = false;
		
		if (this.esHoja)
			fitnessArbol = casoDePrueba[this.nodo.getPosTerminal()];
		else {
			if (this.nodo.getValor().equals("AND"))
				fitnessArbol = this.hijos.get(0).calcularFitnessRecursivo(casoDePrueba) && 
				this.hijos.get(1).calcularFitnessRecursivo(casoDePrueba);
			
			else if (this.nodo.getValor().equals("NOT"))
				fitnessArbol = !this.hijos.get(0).calcularFitnessRecursivo(casoDePrueba);
			
			else if (this.nodo.getValor().equals("OR"))
				fitnessArbol = this.hijos.get(0).calcularFitnessRecursivo(casoDePrueba) || 
				this.hijos.get(1).calcularFitnessRecursivo(casoDePrueba);
			
			else if (this.nodo.getValor().equals("IF")) {
				if (this.hijos.get(0).calcularFitnessRecursivo(casoDePrueba))
					fitnessArbol = this.hijos.get(1).calcularFitnessRecursivo(casoDePrueba);
				else
					fitnessArbol = this.hijos.get(2).calcularFitnessRecursivo(casoDePrueba);
			}
		}		
		return fitnessArbol;
	}
	/*
	 * Calculamos los nodos funcion.
	 */
	public void getNodosArbol(ArrayList<Arbol> funciones, ArrayList<Arbol> terminales) {
		if (this.esHoja)
			terminales.add(this);
		else {
			for (int i = 0; i < this.hijos.size(); i++) {
				this.hijos.get(i).getNodosArbol(funciones, terminales);
			}
			
			if (!this.esRaiz)
				funciones.add(this);
		}
	}

	private Arbol copyArbol(Arbol padre, Arbol raiz) {
		Arbol a = new Arbol();
		a.nodo = this.nodo.copy();
		a.esHoja = this.esHoja;
		a.esRaiz = this.esRaiz;
		a.numNodos = this.numNodos;
		a.profundidad = this.profundidad;
		a.padre = padre;
		a.hijos = new ArrayList<Arbol>();
		
		for (int i = 0; i < this.hijos.size(); i++) {
			if (a.nodo.getTipo().equals("FUNCION"))
				a.hijos.add(this.hijos.get(i).copyArbol(a, raiz));
		}
		return a;
	}
	public Arbol copy() {
		Arbol a = new Arbol();
		a.nodo = this.nodo.copy();
		a.esHoja = this.esHoja;
		a.esRaiz = this.esRaiz;
		a.numNodos = this.numNodos;
		a.profundidad = this.profundidad;
		a.padre = null;
		a.hijos = new ArrayList<Arbol>();
		
		for (int i = 0; i < this.hijos.size(); i++) {
			a.hijos.add(this.hijos.get(i).copyArbol(a, a));
		}	
		return a;
	}
	
	/*
	 * Getters and Setters
	 */
	public Nodo getNodo() {
		return nodo;
	}
	public void setNodo(Nodo nodo) {
		this.nodo = nodo;
	}
	public ArrayList<Arbol> getHijos() {
		return hijos;
	}
	public void setHijos(ArrayList<Arbol> hijos) {
		this.hijos = hijos;
	}
	public Arbol getPadre() {
		return padre;
	}
	public void setPadre(Arbol padre) {
		this.padre = padre;
	}
	public int getProfundidad() {
		return profundidad;
	}
	public void setProfundidad(int profundidad) {
		this.profundidad = profundidad;
	}
	public int getProfundidadTotal() {
		return profundidadTotal;
	}
	public void setProfundidadTotal(int profundidadTotal) {
		this.profundidadTotal = profundidadTotal;
	}
	public int getNumNodos() {
		return numNodos;
	}
	public void setNumNodos(int numNodos) {
		this.numNodos = numNodos;
	}
	public boolean isEsHoja() {
		return esHoja;
	}
	public void setEsHoja(boolean esHoja) {
		this.esHoja = esHoja;
	}
	public boolean isEsRaiz() {
		return esRaiz;
	}
	public void setEsRaiz(boolean esRaiz) {
		this.esRaiz = esRaiz;
	}
	
	/*EQUALS*/
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	public String toString() {
		String cadena = "";
		
		cadena = "" + this.nodo.getValor();
		
		if(!this.esHoja) {
			cadena += "(" + this.hijos.get(0).toString();
			for (int i = 1; i < this.hijos.size(); i++) {
				cadena += ", " + this.hijos.get(i).toString();
			}
			cadena += ")";
		}
		
		return cadena;
	}
}