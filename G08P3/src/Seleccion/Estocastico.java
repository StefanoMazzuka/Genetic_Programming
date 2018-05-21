package Seleccion;

import java.util.Random;

import Base.Cromosoma;

public class Estocastico extends Seleccion {

	private double[] puntuacion;
	private double fitnessTotalPoblacion;
	Cromosoma[] poblacion;

	@Override
	public Cromosoma[] ejecutar(Cromosoma[] poblacion, int numGeneraciones) {
		// TODO Auto-generated method stub
		this.poblacion = poblacion;
		this.puntuacion = new double[this.poblacion.length];
		this.fitnessTotalPoblacion = 0;
		
		Cromosoma[] pobSeleccionada = new Cromosoma[this.poblacion.length];
		
		double N = this.poblacion.length;
		double distMarcas = 1 / N;
		double primeraMarca = 0.0;
		double[] arrayDeMarcas = new double[this.poblacion.length];
		double probAcumulada;
		
		Random r = new Random();
		
		/*
		 * Calculamos el fitnes total
		 */
		for (int i = 0; i < this.poblacion.length; i++) {
			this.fitnessTotalPoblacion += this.poblacion[i].getFitness();
		}
		
		/*
		 * Calculamos el fitness de cada idividuo entre el fitness total
		 */
		for (int i = 0; i < this.poblacion.length; i++) {
			this.puntuacion[i] = this.poblacion[i].getFitness() / this.fitnessTotalPoblacion;
		}
		
		/*
		 * Calculamos el empiece de la primera marca
		 */
		primeraMarca = r.nextDouble() % distMarcas;
		arrayDeMarcas[0] = primeraMarca;
		
		/*
		 * Calculamos el resto de marcas
		 */
		for (int i = 1; i < this.poblacion.length; i++) {
			arrayDeMarcas[i] = arrayDeMarcas[i-1] + distMarcas;
		}
		
		/*
		 * Comprobamos a que elemento pertenece la marca y lo añadimos a la poblacion
		 */
		probAcumulada = this.puntuacion[0];
		
		for (int j = 0; j < arrayDeMarcas.length; j++) {
			probAcumulada = this.puntuacion[0];
			int k = 1;
			while (k < this.poblacion.length && arrayDeMarcas[j] > probAcumulada) {
				probAcumulada += this.puntuacion[k];
				k++;
			}
			pobSeleccionada[j] = this.poblacion[k - 1].copy();
		}
		
		return pobSeleccionada;
	}
}