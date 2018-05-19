package Base;

import java.util.ArrayList;
/**
 *cruce?
 *mutacionTerminalSimple
 *
 */
public class Cromosoma {
	private Arbol arbol;
	/**
	 * Aptitud es fitness
	 */
	private int fitness;
	private double puntuacion;
	private double puntuacionAcumulada;
	private boolean funcionIf;
	private String inicializacion;
	private ArrayList<Arbol> nodosFuncion;
	private ArrayList<Arbol> nodosTerminales;
	private boolean [][] casosDePrueba;
	private int profundidadTotal;
	private int aciertos;//evaluacion
	
	
	private double puntEscaladoSimple;
	private double puntAcuEscaladoSimple;
	
	public Cromosoma(String inicializacion, boolean funcionIf, int alturaMaxima){
		this.fitness = 0;
		this.aciertos = 0;
		this.puntuacion = 0;
		this.puntuacionAcumulada = 0;
		this.funcionIf = funcionIf;
		this.profundidadTotal = alturaMaxima;
		this.nodosFuncion = new ArrayList<Arbol>();
		this.nodosTerminales = new ArrayList<Arbol>();
		this.arbol = arbol.inicializarArbol(inicializacion, funcionIf, profundidadTotal);
		
	}
	
	public int contarAciertos(){
		
		int aciertos = 0;
		
		for (int i = 0; i < casosDePrueba.length; i++){
			aciertos += calcularFitness(casosDePrueba[i], casosDePrueba[i][6]);
		}
		 return aciertos;
	}

	public int calcularFitness(boolean[] casosDePrueba, boolean salida) {
		int fitness = 0;
			if(arbol.calcularFitnessRecursivo(casosDePrueba)==salida)
				fitness++;
		return fitness;
		
	}
	
	
	/**
	 * GETTER AND SETTER
	 */
	public Arbol getArbol() {
		return arbol;
	}

	public void setArbol(Arbol arbol) {
		this.arbol = arbol;
	}

	public double getFitness() {
		return fitness;
	}

	public void setFitness(int fitness) {
		this.fitness = fitness;
	}

	public ArrayList<Arbol> getNodosFuncion() {
		return nodosFuncion;
	}

	public void setNodosFuncion(ArrayList<Arbol> nodosFuncion) {
		this.nodosFuncion = nodosFuncion;
	}

	public ArrayList<Arbol> getNodosTerminales() {
		return nodosTerminales;
	}

	public void setNodosTerminales(ArrayList<Arbol> nodosTerminales) {
		this.nodosTerminales = nodosTerminales;
	}
	
	
}
