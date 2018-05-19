package Mutacion;

import java.util.ArrayList;
import java.util.Random;

import Base.Arbol;
import Base.Nodo;
import Base.Terminal;

public class MutacionTerminalSimple extends Mutacion {

	@Override
	public Arbol mutar(Arbol arbol, ArrayList<Arbol> nodosTipoFuncion, ArrayList<Arbol> nodosTipoTerminal) {
		nodosTipoFuncion.clear();
		nodosTipoTerminal.clear();
		
		arbol.getNodosFuncion(nodosTipoFuncion, nodosTipoTerminal);
		
		Random r = new Random();
		
		int pos  = r.nextInt(nodosTipoTerminal.size());
		Terminal mutado = (Terminal) nodosTipoTerminal.get(pos).getNodo();
		
		Nodo nuevoNodo = mutado.terminalRandom();
		mutado.setValor(nuevoNodo.getValor());
	
		return arbol;
	}
}