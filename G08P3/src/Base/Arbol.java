package Base;

import java.util.ArrayList;
import java.util.Random;

/*
 * Falta:
 * cruce();
 * toString();
 */

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
	 * 
	 * Par�metros de entrada:
	 * padre: 				El arbol padre.
	 * funcionIf:			Si tenemos en cuenta el IF o no.
	 * profundidad:			La profundidad actual.
	 * profundidadTotal:	La profundidad total del arbol.
	 * 
	 * L�gica:
	 * 1. Creamos un arbol con los parametros de entrada.
	 * 2. Compromabos que la profundidad de este arbol sea menor a la profundidad m�xima.
	 * 	2.a FALSE. 
	 * 		2.a.1 Entonces necesita crear m�s hijos.
	 * 		2.a.2 Cogemos un aleatorio entre 0 y 1. Comprobamos si es igual a 0.
	 * 			2.a.2.a TRUE. 
	 * 				2.a.2.a.1 Creamos un nodo con una funci�n.
	 * 				2.a.2.a.2 Ageregamos un hijo al arbol recursivamente aumentando la profundidad.
	 * 				2.a.2.a.3 Hay que agregarle m�s hijos y para saber si tenemos que agregar 1 hijo o 2. Comprobamos que sea 
	 * 					  	  OR, AND o IF. Si es OR o AND, agregamos 1 hijo y si es IF agregamos 2.
	 * 				2.a.2.a.4 A nuestro arbol le a�adimos cuantos nodos tiene recorriendo sus hijos y cogiendo cuantos nodos tiene cada uno.
 	 * 			2.a.2.b FALSE. 
 	 * 				2.a.2.b.1 Creamos una hoja con un terminal aleatorio.
	 * 	2.b TRUE. 
	 * 		2.b.1 Entonces ya llegamos a la profundidad m�xima y en este punto creamos una hoja con un terminal aleatorio.
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
	 * 
	 * Par�metros de entrada:
	 * padre: 				El arbol padre.
	 * funcionIf:			Si tenemos en cuenta el IF o no.
	 * profundidad:			La profundidad actual.
	 * profundidadTotal:	La profundidad total del arbol.
	 * 
	 * L�gica:
	 * 1. Creamos un arbol con los par�metros de entrada.
	 * 2. Compromabos que la profundidad de este arbol sea menor a la profundidad m�xima.
	 * 	2.a FALSE. 
	 * 		2.a.1 Entonces necesita crear m�s hijos.
	 * 		2.a.2 Ageregamos un hijo al arbol recursivamente aumentando la profundidad.
	 * 		2.a.3 Hay que agregarle m�s hijos y para saber si tenemos que agregar 1 hijo o 2. Comprobamos que sea 
	 * 			  OR, AND o IF. Si es OR o AND, agregamos 1 hijo y si es IF agregamos 2.
	 * 		2.a.4 A nuestro arbol le a�adimos cuantos nodos tiene recorriendo sus hijos y cogiendo cuantos nodos tiene cada uno.			
	 * 	2.b TRUE. 
	 * 		2.b.1 Entonces ya llegamos a la profundidad m�xima y en este punto creamos una hoja con un terminal aleatorio.
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
	 * 
	 * Par�metros de entrada:
	 * inicializacion: 		Un String que nos dice que si es inicializaci�n CRECIENTE o COMPLETO.
	 * funcionIf:			Si tenemos en cuenta el IF o no.
	 * profundidadTotal:	La profundidad total del arbol.
	 * 
	 * L�gica:
	 * 1. Creamos un arbol que solo tiene un nodo raiz de tipo funci�n.
	 * 2. Compromabos qu� tipo de inicializaci�n nos piden.
	 * 	2.a CRECIENTE. 
	 * 		2.a.1 Entonces necesita crear m�s hijos.
	 * 		2.a.2 Ageregamos un hijo al arbol recursivamente por m�todo CRECIENTE aumentando la profundidad.
	 * 		2.a.3 Hay que agregarle m�s hijos y para saber si tenemos que agregar 1 hijo o 2. Comprobamos que sea 
	 * 			  OR, AND o IF. Si es OR o AND, agregamos 1 hijo y si es IF agregamos 2.
	 * 		2.a.4 A nuestro arbol le a�adimos cuantos nodos tiene recorriendo sus hijos y cogiendo cuantos nodos tiene cada uno.
	 * 	2.b COMPLETO. 
	 * 		2.b.1 Entonces ya llegamos a la profundidad m�xima y en este punto creamos una hoja con un terminal aleatorio.
	 * 		2.b.2 Ageregamos un hijo al arbol recursivamente por m�todo COMPLETO aumentando la profundidad.
	 * 		2.b.3 Hay que agregarle m�s hijos y para saber si tenemos que agregar 1 hijo o 2. Comprobamos que sea 
	 * 			  OR, AND o IF. Si es OR o AND, agregamos 1 hijo y si es IF agregamos 2.
	 * 		2.b.4 A nuestro arbol le a�adimos cuantos nodos tiene recorriendo sus hijos y cogiendo cuantos nodos tiene cada uno.
	 */
	public static Arbol inicializarArbol(String inicializacion, boolean IFagregado, int profundidadTotal) {
		Arbol a = new Arbol(null, 0, profundidadTotal);
		a.nodo = new Funcion(IFagregado);
		a.esHoja = false;
		a.esRaiz = true;

		if (inicializacion.equals("CRECIENTE")) {
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
		
		else if (inicializacion.equals("COMPLETO")) {
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
	 * 
	 * Par�metros de entrada:
	 * casoDePrueba:	Un array de boolean con valores 0 o 1 que previamente hemos le�do de un fichero.
	 * 
	 * L�gica:
	 * 1. Comprobamos si el �rbol es una hoja.
	 * 	1.a TRUE
	 * 		1.a.1 El valor de fitness ser� el valor del caso de prueba en la posici�n en la que se encuentre el terminal,
	 * 			  es decir, los teminales son A1, A0, D3, D2, D1, D0 y si el caso de prueba es 1,0,1,1,1,0, y la hoja es por ejemplo
	 * 			  D0, (pos 6) entonces el valor del fitness ser� la posicion 6 de los casos de prueba, en este caso 0.
	 * 	1.b FALSE
	 * 		1.b.1 Comprobamos el valor del nodo en el que nos encontramos.
	 * 			1.b.1.a es AND
	 * 				1.b.1.a.1 Calculamos el fitness recursivamente de los dos hijos que tenemos y el fitness ser� el AND de ambos.
	 * 			1.b.1.b es NOT
	 * 				1.b.1.b.1 Calculamos el fitness recursivamente del hijo que tenemos y el fitness ser� el mismo fitness pero negado.
	 * 			1.b.1.c es OR
	 * 				1.b.1.c.1 Calculamos el fitness recursivamente de los dos hijos que tenemos y el fitness ser� el OR de ambos.
	 * 			1.b.1.d es IF
	 * 				1.b.1.d.1 Calculamos el fitness recursivamente del hijo que tenemos y comprobamos que sea TRUE.
	 * 					1.b.1.d.1.a TRUE
	 * 						1.b.1.d.1.a.1 El fitness ser� el fitness del primer hijo.
	 * 					1.b.1.d.1.b FALSE
	 * 						1.b.1.d.1.b.1 El fitness ser� el fitness del segundo hijo. 
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
	 * 
	 * Par�metros de entrada:
	 * funciones:	Un arbol vac�o que rellenaremos con las funciones que tenga nuestro arbol.
	 * terminales:	Un arbol vac�o que rellenaremos con los terminales que tenga nuestro arbol.
	 * 
	 * L�gica:
	 * 1. Comprobamos si nuestro arbol es hoja.
	 * 	1.a TRUE:
	 * 		1.a.1 Agregamos el terminal a la lista de terminales.
	 * 	1.b FALSE:
	 * 		1.b.1 Recorremos los hijos que tenga nuestro nodo. Si es un OR tendr� 2, si es un IF tendr� 3, etc.
	 * 		1.b.2 Por cada hijo, extraemos las funciones y terminales que tenga llamando al m�todo getNodosArbol recursivamente.
	 * 		1.b.3 Al terminar de recorrer los hijos, comprobamos si nuestro arbol es una raiz.
	 * 			1.b.3.a TRUE:
	 * 				Agregamos este nodo (funci�n) al arbol de funciones.
	 * 			1.b.3.b FALSE:
	 * 				Salimos de la recursividad hacia un nivel superior.
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

}