package Seleccion;

import java.util.Random;

import Base.Cromosoma;

public class Ruleta extends Seleccion {	

	private double[] puntuacion;
//	private double[] fitnessDesplazado;
	private double fitnessTotalPoblacion;
	Cromosoma[] poblacion;
	
	@Override
	public Cromosoma[] ejecutar(Cromosoma[] poblacion, int numGeneraciones) {
		// TODO Auto-generated method stub
		this.poblacion = new Cromosoma[poblacion.length];
		for (int i = 0; i < poblacion.length; i++) {
			this.poblacion[i] = poblacion[i].copy();
		}
		this.puntuacion = new double[this.poblacion.length];
//		this.fitnessDesplazado = new double[this.poblacion.length];
		this.fitnessTotalPoblacion = 0;
		
		Cromosoma[] pobSeleccionada = new Cromosoma[this.poblacion.length];
		
		//desplazamiento(poblacion);
		
		for (int i = 0; i < this.poblacion.length; i++) {
			this.fitnessTotalPoblacion += this.poblacion[i].getFitness();
		}
		
		for (int i = 0; i < this.poblacion.length; i++) {
			this.puntuacion[i] = this.poblacion[i].getFitness() / this.fitnessTotalPoblacion;
		}
		
		Random r = new Random();
		double probabilidad;
		double probAcumulada;
		for (int i = 0; i < this.poblacion.length; i++) {
			probAcumulada = this.puntuacion[0];
			probabilidad = r.nextDouble() % 1;
			int j = 1;
			while (j < this.poblacion.length && probabilidad > probAcumulada) {
				probAcumulada += this.puntuacion[j];
				j++;
			}
			pobSeleccionada[i] = (this.poblacion[j - 1].copy());
		}
	
		return pobSeleccionada;
	}
//	public void desplazamiento(Cromosoma[] poblacion) {
//		double fitnessMejor = 0;
//		for (int i = 0; i < poblacion.length; i++) {
//			if(fitnessMejor < poblacion[i].getFitness())
//				fitnessMejor = poblacion[i].getFitness();
//		}
//		fitnessMejor = fitnessMejor * 1.05;
//		for (int i = 0; i < poblacion.length; i++) {
//			this.fitnessDesplazado[i] = fitnessMejor - poblacion[i].getFitness();
//		}
//	}
}