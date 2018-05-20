package Base;

import Cruce.Cruce;
import GestionArchivos.Leer;
import Mutacion.Mutacion;
import Mutacion.MutacionFuncionSimple;
import Mutacion.MutacionTerminalSimple;
import Seleccion.Estocastico;
import Seleccion.Ruleta;
import Seleccion.Seleccion;
import Seleccion.Torneo;

public class AlgoritmoGenetico {
	private Cromosoma[] poblacion;
	private int lPoblacion;
	private int numGeneraciones;
	private double fitnessMejorAbsoluto;
	private double fitnessMejor;
	private int posFitnessMejor;
	private Cromosoma cromosomaMejor;
	private int posCromosomaMejor;
	private Cromosoma cromosomaMejorAbsoluto;
	private double porcentajeCruce;
	private double porcentajeMutacion;
	private double porcentajeEli;
	private boolean elitista;
	private double media;
	private double[] listaFitnessMejorAbsoluto;
	private double[] listaFitnessMejor;
	private double[] listaMedias;
	private int profundidadMaxima;
	boolean funcionIf;
	private int numElegidosEli;
	private Cromosoma[] poblacionEli;
	private int tipoSeleccion;	
	private int tipoCruce;
	private int tipoMutacion;
	private String tipoInicializacion;//cambiar a int
	
	public AlgoritmoGenetico(int tipoSeleccion, int tipoMutacion, String tipoInicializacion, int lPoblacion, int numGeneraciones,
			double porcentajeCruce, double porcentajeMutacion, int profundidadMaxima, boolean elitista, boolean funcionIf) {
		super();
		this.tipoSeleccion = tipoSeleccion;
		this.tipoMutacion = tipoMutacion;
		this.tipoInicializacion = tipoInicializacion;
		this.lPoblacion = lPoblacion;
		this.numGeneraciones = numGeneraciones;
		this.porcentajeCruce = porcentajeCruce;
		this.porcentajeMutacion = porcentajeMutacion;
		this.profundidadMaxima = profundidadMaxima;
		this.elitista = elitista;
		this.funcionIf = funcionIf;
	}
	
	public void ejecutar() {
		Seleccion seleccion = new Torneo();
		if (this.tipoSeleccion == 1) seleccion = new Ruleta();
		else if (this.tipoSeleccion == 2) seleccion = new Estocastico();
		
		Cruce cruce = new Cruce(this.porcentajeCruce, profundidadMaxima);
			
		Mutacion mutacion = new MutacionFuncionSimple(porcentajeMutacion);
		if (this.tipoMutacion == 1) mutacion = new MutacionTerminalSimple(porcentajeMutacion);
	
		iniciarAg();
		calcularFitnessPoblacion();
		
		for(int i = 0 ; i< numGeneraciones; i++) {
			poblacion = seleccion.ejecutar(poblacion, numGeneraciones);		
			cruce.cualCruzaYCruzar(poblacion);
			mutacion.cualMutaYMutar(poblacion);
			System.out.println(i);
		}		
	}

	public void iniciarAg() {
		poblacion = new Cromosoma[lPoblacion];
		Leer leer = new Leer();
		boolean[][] casos = leer.leerCasos();
		for(int i = 0; i < lPoblacion; i++  ) {
			poblacion[i]= new Cromosoma(tipoInicializacion, funcionIf, profundidadMaxima, casos);
			poblacion[i]= new Cromosoma(tipoInicializacion, funcionIf, profundidadMaxima, casos);
			
			poblacion[i].setFitness(poblacion[i].contarAciertos());
			
			if (poblacion[posCromosomaMejor].getFitness() < poblacion[i].getFitness()) {
				posCromosomaMejor = i;
				cromosomaMejorAbsoluto = poblacion.clone()[posCromosomaMejor].copy();
			}
		}
		
		//Elitismo aqui
		
		//syso de pob[i] aqui
		
	}
	
	public void ordenar() {
		quickSort(poblacion, 0, lPoblacion - 1);
	}
	
	
	public void quickSort(Cromosoma[] poblacion, int izq, int der) {
		if(der <= izq)
			return;
		int i = dividir(poblacion,izq,der);
		
		quickSort(poblacion, izq, i-1);
		quickSort(poblacion, i + 1, der);
	}
	
	public int dividir(Cromosoma[] poblacion, int izq, int der) {
		int i = izq -1;
		int j = der;
		while(true) {
			
			while(esMenor(poblacion[++i], poblacion[der]));
			while(esMenor(poblacion[der], poblacion[--j]));			
				if (j == izq) 
					break;
			if (i >= j)
				break;
			
			swap(poblacion,i,j);	
		}
		swap(poblacion, i, der);
		
		return i;
	}

	public boolean esMenor(Cromosoma c1, Cromosoma c2) {
		if(c1.getFitness() < c2.getFitness())
			return true;
		else
			return false;
	}
	
	private void swap(Cromosoma[] poblacion, int i, int j) {
		Cromosoma aux = poblacion[i];
		poblacion[i] = poblacion[j];
		poblacion[j] = aux;
	}

	public void calcularFitnessPoblacion() {
		double puntuacionAcumulada = 0;
		double fitnessMejor = 0;
		double fitnessAcumulado = 0;
		
		for(int i = 0 ; i < lPoblacion; i++) {
			fitnessAcumulado = fitnessAcumulado + this.poblacion[i].getFitness();
			
			if(poblacion[i].getFitness() > fitnessMejor) {
				posFitnessMejor = i;
				fitnessMejor = poblacion[i].getFitness();
				cromosomaMejor = poblacion[i].copy();
			}
		}
		
		if(fitnessMejor > cromosomaMejorAbsoluto.getFitness() ) {
			cromosomaMejorAbsoluto = cromosomaMejor.copy();
		}
	}
}
