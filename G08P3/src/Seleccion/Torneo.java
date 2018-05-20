package Seleccion;

import java.util.ArrayList;
import java.util.Random;

import Base.Cromosoma;

public class Torneo extends Seleccion {

	private double[] fitnessDesplazado;
	Cromosoma[] poblacion;
	Cromosoma[] poblacionTrio;
	Cromosoma[] poblacionSeleccionada;
	
	@Override
	public Cromosoma[] ejecutar(Cromosoma[] poblacion, int numGeneraciones) {
		// TODO Auto-generated method stub
		this.poblacion = poblacion;
		this.poblacionTrio = new Cromosoma[3];
		this.poblacionSeleccionada = new Cromosoma[this.poblacion.length];
		this.fitnessDesplazado = new double[this.poblacion.length];
		
		double mejor = 0.0;
		Cromosoma mejorCromosoma = null;
		Random r = new Random();
		int cualToca = 0;
		
		/*
		 * Recorremos el tama�o de la poblacion. A�adimos al pobTrio el trio de cromosomas y comprobamos
		 * cual es el mejor de esos tres para a�adirlo a la pobSeleccionada. 
		 */
		for (int j = 0; j < this.poblacion.length; j++) {	
			/*
			 * Elegimos el trio al azar
			 */
			mejor = Double.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                cualToca = r.nextInt(this.poblacion.length) ;
                this.poblacionTrio[i] = this.poblacion[cualToca].copy();

                /*
                 * Comprobamos cual es el mejor elemento del trio
                 */
                //desplazamiento(pobTrio);

                // .Fitness??
                if (mejor > this.poblacionTrio[i].getFitness()) {
                    mejor = this.poblacionTrio[i].getFitness();
                    mejorCromosoma = this.poblacionTrio[i].copy();
                }
            }
			
			/*
			 * A�adimos el cromosoma a la poblacion seleccionada
			 */
    		this.poblacionSeleccionada[j] = mejorCromosoma;
			
			/*
			 * Limpiamos el array de pobTrio
			 */
    		this.poblacionTrio = new Cromosoma[3];
//			for (int i = 0; i < 3; i++) {
//				this.poblacionTrio.remove(0);		
//			}
			
			/*
			 * Seteamos la poblacion orginal
			 */	
		}
		return this.poblacionSeleccionada;
	}
	public void desplazamiento(ArrayList<Cromosoma> pob) {
		double fitnessMejor = 0;
		for (int i = 0; i < pob.size(); i++) {
			if(fitnessMejor < pob.get(i).getFitness())
				fitnessMejor = pob.get(i).getFitness();
		}
		fitnessMejor = fitnessMejor * 1.05;
		for (int i = 0; i < pob.size(); i++) {
			this.fitnessDesplazado[i] = fitnessMejor - pob.get(i).getFitness();
		}
	}
}