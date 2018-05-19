package Mutacion;

import java.util.ArrayList;

import Base.Arbol;
import Base.Cromosoma;

public abstract class Mutacion {
	
	public abstract Arbol mutar(Arbol arbol, ArrayList<Arbol> nodosTipoFuncion, ArrayList<Arbol> nodosTipoTerminal);
}
