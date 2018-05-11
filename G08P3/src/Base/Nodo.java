package Base;

import java.util.Arrays;

public abstract class Nodo {

	protected String valor;
	protected String tipo;
	protected final String[] tiposFunciones = {"AND", "NOT", "OR", "IF"};
	protected final String[] tiposTerminales = {"A0", "A1", "D0", "D1", "D2", "D3"};
	protected int numArgumentos;
		
	/*
	 * Functions
	 */
	public abstract Nodo copy();
	
	/*
	 * Equals
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numArgumentos;
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		result = prime * result + Arrays.hashCode(tiposFunciones);
		result = prime * result + Arrays.hashCode(tiposTerminales);
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nodo other = (Nodo) obj;
		if (numArgumentos != other.numArgumentos)
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		if (!Arrays.equals(tiposFunciones, other.tiposFunciones))
			return false;
		if (!Arrays.equals(tiposTerminales, other.tiposTerminales))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}
	
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
