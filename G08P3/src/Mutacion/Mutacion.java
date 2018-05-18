package Mutacion;

import java.util.ArrayList;

import Base.Arbol;

public abstract class Mutacion {
	protected ArrayList<Arbol> nodosTipoFuncion;
	protected ArrayList<Arbol> nodosTipoTerminal;
	
	public abstract Arbol mutar(Arbol arbol);
}
