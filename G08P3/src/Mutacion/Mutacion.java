package Mutacion;

import java.util.ArrayList;

import Base.Arbol;
import Base.Cromosoma;

public abstract class Mutacion {
	protected double porcentajeMutacion;
	
	/*
	 * Según un porcentaje de mutación, elegimos si mutar el cromosoma.
	 */
	public abstract void cualMutaYMutar(Cromosoma[] poblacion);
	public abstract Arbol mutar(Arbol arbol, ArrayList<Arbol> nodosTipoFuncion, ArrayList<Arbol> nodosTipoTerminal);	
}
