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

	private double puntEscaladoSimple;
	private double puntAcuEscaladoSimple;
	
	public Cromosoma() {
		
	}
	
	public Cromosoma(String inicializacion, boolean funcionIf, int alturaMaxima){
		this.fitness = 0;

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
		
		for (int i = 0; i < casosDePrueba.length; i++) {
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
	
	public Cromosoma copy() {
		
		Arbol arbol = this.arbol.copy();
		ArrayList<Arbol> nodosFuncion = new ArrayList<Arbol>();
		for (int i = 0; i < this.nodosFuncion.size(); i++) {
			nodosFuncion.add(this.nodosFuncion.get(i).copy());
		}
		
		ArrayList<Arbol> nodosTerminales = new ArrayList<Arbol>();
		for (int i = 0; i < this.nodosTerminales.size(); i++) {
			nodosTerminales.add(this.nodosTerminales.get(i).copy());
		}		
		
		Cromosoma c = new Cromosoma();
		
		c.setArbol(arbol);
		c.setNodosFuncion(nodosFuncion);
		c.setNodosTerminales(nodosTerminales);
		c.setCasosDePrueba(this.casosDePrueba);
		c.setFitness(this.fitness);
		c.setPuntuacion(this.puntuacion);
		c.setPuntuacionAcumulada(this.puntuacionAcumulada);
		c.setFuncionIf(this.funcionIf);
		c.setInicializacion(this.inicializacion);
		c.setProfundidadTotal(this.profundidadTotal);
		c.setPuntEscaladoSimple(this.puntEscaladoSimple);
		c.setPuntAcuEscaladoSimple(this.puntAcuEscaladoSimple);
		
		return c;
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
	public void setPuntuacion(double puntuacion) {
		this.puntuacion = puntuacion;
	}
	public void setPuntuacionAcumulada(double puntuacionAcumulada) {
		this.puntuacionAcumulada = puntuacionAcumulada;
	}
	public void setFuncionIf(boolean funcionIf) {
		this.funcionIf = funcionIf;
	}
	public void setInicializacion(String inicializacion) {
		this.inicializacion = inicializacion;
	}
	public void setCasosDePrueba(boolean[][] casosDePrueba) {
		this.casosDePrueba = casosDePrueba;
	}
	public void setProfundidadTotal(int profundidadTotal) {
		this.profundidadTotal = profundidadTotal;
	}

	public void setPuntEscaladoSimple(double puntEscaladoSimple) {
		this.puntEscaladoSimple = puntEscaladoSimple;
	}
	public void setPuntAcuEscaladoSimple(double puntAcuEscaladoSimple) {
		this.puntAcuEscaladoSimple = puntAcuEscaladoSimple;
	}
}
