package Mutacion;

import java.util.ArrayList;

import Base.Arbol;

public abstract class Mutacion {

	public abstract Arbol mutar(Arbol arbol, ArrayList<Arbol> nodosTipoFuncion, ArrayList<Arbol> nodosTipoTerminal);
}
