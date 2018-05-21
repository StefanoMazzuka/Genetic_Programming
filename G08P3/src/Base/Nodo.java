package Base;

public abstract class Nodo {

	protected String valor;
	protected String tipo;
	protected final String[] tiposFunciones = {"AND", "NOT", "OR", "IF"};
	protected final String[] tiposTerminales = {"A1", "A0", "D3", "D2", "D1", "D0"};
	protected int numArgumentos;
		
	/*
	 * Functions
	 */
	public int getPosTerminal() {
		for (int i = 0; i < this.tiposTerminales.length; i++) {
			if(this.tiposTerminales[i].equals(this.valor))
				return i;
		}
		return -1;
	}
	public abstract Nodo copy();
	
	/*
	 * Getter and Setters
	 */
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public int getNumArgumentos() {
		return numArgumentos;
	}
	public void setNumArgumentos(int numArgumentos) {
		this.numArgumentos = numArgumentos;
	}
	public String getTipo() {
		return tipo;
	}
	public String[] getTiposFunciones() {
		return tiposFunciones;
	}
	public String[] getTiposTerminales() {
		return tiposTerminales;
	}
}
