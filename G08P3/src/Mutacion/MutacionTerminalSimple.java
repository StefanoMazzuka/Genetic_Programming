package Mutacion;

import java.util.ArrayList;
import java.util.Random;

import Base.Arbol;
import Base.Cromosoma;
import Base.Terminal;
import Base.Nodo;

public class MutacionTerminalSimple extends Mutacion {
	
	public MutacionTerminalSimple(double porcentajeMutacion) {
		this.porcentajeMutacion = porcentajeMutacion;
	}
	
	@Override
	public void cualMutaYMutar(Cromosoma[] poblacion) {
		// TODO Auto-generated method stub
		for (int i = 0; i < poblacion.length; i++) {
			Random r = new Random();
			double porcentaje = r.nextDouble();
			
			if(porcentaje < this.porcentajeMutacion) {
				poblacion[i].setArbol(mutar(poblacion[i].getArbol(), 
						poblacion[i].getNodosFuncion(), poblacion[i].getNodosTerminales()));
			}
			
			poblacion[i].setFitness(poblacion[i].contarAciertos());
		}
	}
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