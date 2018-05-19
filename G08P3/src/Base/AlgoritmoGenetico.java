package Base;

import GestionArchivos.Leer;

public class AlgoritmoGenetico {
	private Cromosoma[] poblacion;
	private int lPoblacion;
	private int numGeneraciones;
	private double fitnessMejorAbsoluto;
	private double fitnessMejor;
	private int posFitnessMejor;
	private Cromosoma cromosomaMejor;
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
	
	public AlgoritmoGenetico(Cromosoma[] poblacion, int lPoblacion, int numGeneraciones, double fitnessMejorAbsoluto,
			double fitnessMejor, Cromosoma cromosomaMejor, Cromosoma cromosomaMejorAbsoluto, double porcentajeCruce,
			double porcentajeMutacion, double porcentajeEli, boolean elitista, double media, int profundidadMaxima,
			boolean funcionIf, int tipoSeleccion, int tipoCruce, int tipoMutacion) {
		super();
		this.poblacion = poblacion;
		this.lPoblacion = lPoblacion;
		this.numGeneraciones = numGeneraciones;
		this.fitnessMejorAbsoluto = fitnessMejorAbsoluto;
		this.fitnessMejor = fitnessMejor;
		cromosomaMejor = cromosomaMejor;
		cromosomaMejorAbsoluto = cromosomaMejorAbsoluto;
		this.porcentajeCruce = porcentajeCruce;
		this.porcentajeMutacion = porcentajeMutacion;
		this.porcentajeEli = porcentajeEli;
		this.elitista = elitista;
		this.media = media;
		this.profundidadMaxima = profundidadMaxima;
		this.funcionIf = funcionIf;
		this.tipoSeleccion = tipoSeleccion;
		this.tipoCruce = tipoCruce;
		this.tipoMutacion = tipoMutacion;
		
		Leer leer = new Leer();
		leer.leerCasos();
	}

	public void iniciarAg() {
		poblacion = new Cromosoma[lPoblacion];
		
		for(int i = 0; i < lPoblacion; i++  ) {
			poblacion[i]= new Cromosoma(tipoInicializacion,funcionIf,profundidadMaxima);
			poblacion[i]= new Cromosoma(tipoInicializacion,funcionIf,profundidadMaxima);
			
			poblacion[i].setFitness(poblacion[i].contarAciertos());
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
				cromosomaMejor = poblacion[i]; //hay que hacer el copy
			}
		}
		
		if(fitnessMejor > cromosomaMejorAbsoluto.getFitness() ) {
			cromosomaMejorAbsoluto = cromosomaMejor; //Habria que hacer copy
		}
	}
}
