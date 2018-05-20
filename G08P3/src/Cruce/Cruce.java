package Cruce;

import java.util.Random;

import Base.Arbol;
import Base.Cromosoma;

public class Cruce {
	private double probCruce;
	private int profundidadTotal;
	
	public Cruce(double probCruce, int profundidadTotal) {
		super();
		this.probCruce = probCruce;
		this.profundidadTotal = profundidadTotal;
	}
	
	@SuppressWarnings("unused")
	private void cualCruzaYCruzar (int lPoblacion,Cromosoma[] poblacion) {
		
		int[] seleccionados = new int[lPoblacion];
		int numSeleccionados = 0;
		double probAux;
		Random r = new Random();
		
		for(int i = 0; i < lPoblacion; i++) {
			probAux = r.nextDouble();
			
			if(probAux < probCruce) {
				seleccionados[numSeleccionados]=i;
				numSeleccionados++;
			}
		}
		
		if(numSeleccionados % 2 == 1) {
			numSeleccionados--;
		}
		
		for(int i = 0; i < numSeleccionados; i+=2) {
			cruzar(poblacion[seleccionados[i]],poblacion[seleccionados[i+1]], probCruce);
		}
	}
	
	public void cruzar(Cromosoma padreUno, Cromosoma padreDos, double probCruce){
		padreUno.getNodosFuncion().clear();
		padreDos.getNodosFuncion().clear();
		padreDos.getNodosTerminales().clear();
		padreUno.getNodosTerminales().clear();
		
		padreUno.getArbol().getNodosArbol(padreUno.getNodosFuncion(), padreUno.getNodosTerminales());
		padreDos.getArbol().getNodosArbol(padreDos.getNodosFuncion(), padreDos.getNodosTerminales());
		
		Random r = new Random();
		double probAux = r.nextDouble();
		
		Arbol puntCruceUno;
		Arbol puntCruceDos;
		
		if(probAux < probCruce) {
			if(padreUno.getNodosFuncion().size() !=0 && padreDos.getNodosFuncion().size() != 0) {
				int corte = r.nextInt(padreUno.getNodosFuncion().size());
				puntCruceUno = padreUno.getNodosFuncion().get(corte);
				
				corte = r.nextInt(padreDos.getNodosFuncion().size());
				puntCruceDos = padreDos.getNodosFuncion().get(corte);
				
				cruzarNodos(puntCruceUno,puntCruceDos);
				
			}
		}
		else {
			if(padreUno.getNodosTerminales().size() !=0 && padreDos.getNodosTerminales().size() != 0) {
				int corte = r.nextInt(padreUno.getNodosTerminales().size());
				puntCruceUno = padreUno.getNodosTerminales().get(corte);
				
				corte = r.nextInt(padreDos.getNodosTerminales().size());
				puntCruceDos = padreDos.getNodosTerminales().get(corte);
				
				cruzarNodos(puntCruceUno,puntCruceDos);
			}
		}
		
		padreUno.getArbol().getNodosArbol(padreUno.getNodosFuncion(), padreUno.getNodosTerminales());
		padreDos.getArbol().getNodosArbol(padreDos.getNodosFuncion(), padreDos.getNodosTerminales());
		
		padreUno.setFitness(padreUno.contarAciertos());
		padreDos.setFitness(padreDos.contarAciertos());
	}
	
	/*
	 * Cruzamos dos arboles por un punto.
	 * 
	 * Parámetros de entrada:
	 * puntoUno:	Punto de corte del primer arbol.
	 * puntoDos:	Punto de corte del segundo arbol.
	 * 
	 * 1. Recorremos los hijos del padre uno.
	 * 2. Recorremos los hijos del padre dos.
	 * 3. Comprobamos que coincidan los dos hijos en los puntos asignados.
	 * 	3.a TRUE
	 * 		3.a.1 Comprobamos que 
	 * 	3.b FALSE
	 */
	public void cruzarNodos(Arbol puntoUno, Arbol puntoDos) {
		for (int i = 0; i < puntoUno.getPadre().getHijos().size(); i++) {
			for (int j = 0; j < puntoDos.getPadre().getHijos().size(); j++) {
				if (puntoUno.getPadre().getHijos().get(i).equals(puntoUno) &&
				puntoDos.getPadre().getHijos().get(j).equals(puntoDos)) {
					if (((this.profundidadTotal - puntoDos.getProfundidad()) + 
							puntoUno.getPadre().getProfundidad() < this.profundidadTotal) &&
							((this.profundidadTotal - puntoUno.getProfundidad()) + 
									puntoDos.getPadre().getProfundidad() < this.profundidadTotal)) {
						puntoUno.getPadre().getHijos().set(i, puntoDos);
						puntoDos.getPadre().getHijos().set(j, puntoUno);
						
						Arbol a = puntoUno.getPadre();
						puntoUno.setPadre(puntoDos.getPadre());
						puntoDos.setPadre(a);
						return;
					}
				}
			}
		}
	}
}

