package Base;

import java.util.Random;

/*
 * Falta:
 * toString()
 * getTerminalAleatorio()
 */

public class Terminal extends Nodo {
	
	public Terminal() {
		Random r = new Random();
		this.valor = this.tiposTerminales[r.nextInt(this.tiposTerminales.length)];
		this.tipo = "TERMINAL";
		this.numArgumentos = 0;
	}
	public Terminal(String valor) {
		this.valor = valor;
		this.tipo = "TERMINAL";
		this.numArgumentos = 0;
	}
	
	/*
	 * Functions
	 */
	@Override
	public Nodo copy() {
		Terminal t = new Terminal();
		t.valor = this.valor;
		t.tipo = this.tipo;
		t.numArgumentos = this.numArgumentos;
		return t;
	}
	
	public Nodo terminalRandom() {
		
		int i;
		Random r = new Random();
		
		i = r.nextInt(6);
		
		while(tiposTerminales[i] == valor) {
			i = r.nextInt(6);
		}
		
		return new Terminal(tiposTerminales[i]);
		
	}
}
