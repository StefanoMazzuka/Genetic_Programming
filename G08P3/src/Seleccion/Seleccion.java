package Seleccion;

import Base.Cromosoma;

public abstract class Seleccion {
	public abstract Cromosoma[] ejecutar(Cromosoma[] poblacion, int numGeneraciones);
}