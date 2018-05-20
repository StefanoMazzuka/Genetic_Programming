package Mutacion;

import java.util.ArrayList;
import java.util.Random;

import Base.Arbol;
import Base.Funcion;
import Base.Nodo;

public class MutacionFuncionSimple extends Mutacion {

	@Override
	public Arbol mutar(Arbol arbol, ArrayList<Arbol> nodosTipoFuncion, ArrayList<Arbol> nodosTipoTerminal) {
		nodosTipoFuncion.clear();
		nodosTipoTerminal.clear();

		arbol.getNodosArbol(nodosTipoFuncion, nodosTipoTerminal);

		if (nodosTipoFuncion.size() > 0) {
			
			Random r = new Random();

			int pos = r.nextInt(nodosTipoFuncion.size());
			Funcion mutado = (Funcion) nodosTipoFuncion.get(pos).getNodo();

			Nodo nuevoNodo = mutado.funcionRandom();
			mutado.setValor(nuevoNodo.getValor());
		}

		return arbol;
	}
}