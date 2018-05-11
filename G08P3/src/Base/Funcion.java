package Base;

import java.util.Random;

/*
 * Falta:
 * toString()
 * getFuncionAleatoria()
 */

public class Funcion extends Nodo {

	public Funcion() {
		this.valor = "";
		this.tipo = "FUNCION";
		this.numArgumentos = 0;
	}
	
	public Funcion(boolean IFagregado) {
		Random r = new Random();
		if (IFagregado)
			this.valor = this.tiposFunciones[r.nextInt(this.tiposFunciones.length)];
		else 
			this.valor = this.tiposFunciones[r.nextInt(this.tiposFunciones.length) - 1];
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
}