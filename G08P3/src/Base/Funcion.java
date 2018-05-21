package Base;

import java.util.Random;

public class Funcion extends Nodo {

	public Funcion() {
		this.valor = "";
		this.tipo = "FUNCION";
		this.numArgumentos = 0;
	}
	
	public Funcion(boolean IFagregado) {
		Random r = new Random();
		int pos;
		if (IFagregado) {
			pos = r.nextInt(this.tiposFunciones.length);
			this.valor = this.tiposFunciones[pos];
		}
			
		else {
			pos = r.nextInt(this.tiposFunciones.length - 1);
			this.valor = this.tiposFunciones[pos];
		}
			
		this.tipo = "FUNCION";
		numArgumentosSegunValor(valor);
	}
	
	public Funcion(String valor) {
		this.valor = valor;
		this.tipo = "FUNCION";
		numArgumentosSegunValor(valor);
	}
	
	/*
	 * Functions
	 */
	private void numArgumentosSegunValor(String valor) {
		if (valor.equals("AND") || valor.equals("OR"))
			this.numArgumentos = 2;
		else {
			if (valor.equals("NOT"))
				this.numArgumentos = 1;
			else if (valor.equals("IF"))
				this.numArgumentos = 3;
		}
	}
	@Override
	public Nodo copy() {
		Funcion f = new Funcion();
		f.valor = this.valor;
		f.tipo = this.tipo;
		f.numArgumentos = this.numArgumentos;
		return f;
	}
	public Nodo funcionRandom() {
		
		if (this.valor == "AND")
			return new Funcion("OR");
		else if (this.valor == "OR")
			return new Funcion("AND");
		
		return this;
	}
}