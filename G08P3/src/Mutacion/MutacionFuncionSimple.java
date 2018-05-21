package Mutacion;

import java.util.ArrayList;
import java.util.Random;

import Base.Arbol;
import Base.Cromosoma;
import Base.Funcion;
import Base.Nodo;

public class MutacionFuncionSimple extends Mutacion {

	public  MutacionFuncionSimple(double porcentajeMutacion) {
		this.porcentajeMutacion = porcentajeMutacion;
	}

	/*
	 * Seg�n un porcentaje de mutaci�n, elegimos si mutar el cromosoma.
	 */
	@Override
	public void cualMutaYMutar(Cromosoma[] poblacion) {
		// TODO Auto-generated method stub
		for (int i = 0; i < poblacion.length; i++) {
			Random r = new Random();
			double porcentaje = r.nextDouble();

			if(porcentaje < this.porcentajeMutacion) {
				poblacion[i].setArbol(mutar(poblacion[i].getArbol(), 
						poblacion[i].getNodosFuncion(), 
						poblacion[i].getNodosTerminales()));
			}

			poblacion[i].setFitness(poblacion[i].contarAciertos());
		}
	}
	
	/*
	 * Extraemos los nodos funcion y mutamos un nodo Funcion aleatorio.
	 */
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