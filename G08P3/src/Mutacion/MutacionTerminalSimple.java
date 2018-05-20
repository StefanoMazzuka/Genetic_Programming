package Mutacion;

import java.util.ArrayList;
import java.util.Random;

import Base.Arbol;
import Base.Terminal;
import Base.Nodo;

public class MutacionTerminalSimple extends Mutacion {
	
	public MutacionTerminalSimple() {}
	
	@Override
	public Arbol mutar(Arbol arbol, ArrayList<Arbol> nodosTipoFuncion, ArrayList<Arbol> nodosTipoTerminal) {
		nodosTipoFuncion.clear();
		nodosTipoTerminal.clear();
		
		arbol.getNodosArbol(nodosTipoFuncion, nodosTipoTerminal);
		
		Random r = new Random();
		
		int pos = r.nextInt(nodosTipoTerminal.size());
		Terminal mutado = (Terminal) nodosTipoTerminal.get(pos).getNodo();
		
		Nodo nuevoNodo = mutado.terminalRandom();
		mutado.setValor(nuevoNodo.getValor());
	
		return arbol;
	}
}