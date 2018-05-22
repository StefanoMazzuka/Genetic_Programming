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
	private String fenotipoMejorAbsoluto;
	private Cromosoma cromosomaMejor;
	private Cromosoma cromosomaMejorAbsoluto;
	private double porcentajeCruce;
	private double porcentajeMutacion;
	private double porcentajeEli;
	private boolean elitista;
	private double[] listaFitnessMejorAbsoluto;
	private double[] listaFitnessMejor;
	private double[] listaMedias;
	private int profundidadMaxima;
	boolean funcionIf;
	private int numElegidosEli;
	private Cromosoma[] poblacionEli;
	private int tipoSeleccion;	
	private int tipoMutacion;
	private String tipoInicializacion;

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
		this.listaFitnessMejorAbsoluto = new double[numGeneraciones];
		this.listaFitnessMejor = new double[numGeneraciones];
		this.listaMedias = new double[numGeneraciones];
		this.porcentajeEli = 0.2;
	}

	public void ejecutar() {	
		Seleccion seleccion = new Torneo();
		if (this.tipoSeleccion == 1) seleccion = new Ruleta();
		else if (this.tipoSeleccion == 2) seleccion = new Estocastico();

		Cruce cruce = new Cruce(this.porcentajeCruce, profundidadMaxima);

		Mutacion mutacion = new MutacionFuncionSimple(porcentajeMutacion);
		if (this.tipoMutacion == 1) mutacion = new MutacionTerminalSimple(porcentajeMutacion);

		iniciarAg();

		if (this.elitista) {
			this.numElegidosEli = (int) Math.round((this.porcentajeEli * this.lPoblacion));
			this.poblacionEli = new Cromosoma[this.numElegidosEli];
			seleccionarEli();
		}

		for(int i = 0 ; i< numGeneraciones; i++) {
			poblacion = seleccion.ejecutar(poblacion, numGeneraciones);		
			cruce.cualCruzaYCruzar(poblacion);
			mutacion.cualMutaYMutar(poblacion);

			if (this.elitista) {
				insertarPobEli();
				seleccionarEli();
			}

			calcularCromosomaMejor();

			this.listaMedias[i] = this.calcularMediaGeneracion();
			this.listaFitnessMejor[i] = (double) this.cromosomaMejor.getFitness();
			this.listaFitnessMejorAbsoluto[i] = (double) this.cromosomaMejorAbsoluto.getFitness();

			System.out.println(i);
		}
		this.fenotipoMejorAbsoluto = this.cromosomaMejorAbsoluto.getArbol().toString();
	}
	public void iniciarAg() {
		int posCromosomaMejor = 0;
		poblacion = new Cromosoma[lPoblacion];
		Leer leer = new Leer();
		boolean[][] casos = leer.leerCasos();
		for(int i = 0; i < lPoblacion; i++  ) {
			poblacion[i]= new Cromosoma(tipoInicializacion, funcionIf, profundidadMaxima, casos);
			poblacion[i]= new Cromosoma(tipoInicializacion, funcionIf, profundidadMaxima, casos);

			poblacion[i].setFitness(poblacion[i].contarAciertos());

			if (poblacion[posCromosomaMejor].getFitness() <= poblacion[i].getFitness()) {
				posCromosomaMejor = i;
				cromosomaMejorAbsoluto = poblacion[posCromosomaMejor].copy();
			}
		}
	}
	public void ordenar() {
		Cromosoma c1;
		Cromosoma c2;

		for (int i = 0; i < this.lPoblacion - 1; i++) {
			for (int j = i; j < this.lPoblacion; j++) {
				if (this.poblacion[i].getFitness() <
						this.poblacion[j].getFitness()) {
					c1 = this.poblacion[i].copy();
					c2 = this.poblacion[j].copy();

					this.poblacion[i] = c2;
					this.poblacion[j] = c1;
				}
			}
		}
	}
	public void seleccionarEli() {
		ordenar();
		Cromosoma c;
		for (int i = 0; i < this.numElegidosEli; i++) {
			c = this.poblacion[i].copy();
			this.poblacionEli[i] =  c;
		}
	}
	public void insertarPobEli() {
		Cromosoma c;
		for (int i = 0; i < this.numElegidosEli; i++) {
			c = this.poblacionEli[i].copy();
			this.poblacion[(this.lPoblacion - 1) - i] = c;
		}
	}
	public void calcularCromosomaMejor() {
		double fitnessMejor;
		double fitness;

		fitnessMejor = 0;
		for (int i = 0; i < this.lPoblacion; i++) {
			fitness = this.poblacion[i].getFitness();
			if (fitnessMejor < fitness) {
				fitnessMejor = fitness;
				this.cromosomaMejor = this.poblacion[i].copy();
			}
		}

		if (this.cromosomaMejorAbsoluto.getFitness() < fitnessMejor) {
			this.cromosomaMejorAbsoluto = this.cromosomaMejor.copy();
		}
	}
	public double calcularMediaGeneracion() {
		double media = 0.00;
		double sumatorio = 0.00;
		double fitness;

		for (int i = 0; i < this.lPoblacion; i++) {
			fitness = this.poblacion[i].getFitness();
			sumatorio += fitness;
		}

		media = sumatorio / this.lPoblacion;

		return media;
	}

	//GETTERS AND SETTER
	public int getAciertos() {
		return cromosomaMejorAbsoluto.getAciertos();
	}
	public String getFenotipoMejorAbsoluto() {
		return fenotipoMejorAbsoluto;
	}
	public double getFitnessMejorAbsoluto() {
		return cromosomaMejorAbsoluto.getFitness();
	}
	public double[] getListaFitnessMejorAbsoluto() {
		return listaFitnessMejorAbsoluto;
	}
	public double[] getListaFitnessMejor() {
		return listaFitnessMejor;
	}
	public double[] getListaMedias() {
		return listaMedias;
	}
}
