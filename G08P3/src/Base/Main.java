package Base;

import GUI.Menu;
import Mutacion.Mutacion;
import Mutacion.MutacionTerminalSimple;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Cromosoma c = new Cromosoma();
		Mutacion mutar = new MutacionTerminalSimple();
		mutar.mutar(c.getArbol(), c.getNodosFuncion(), c.getNodosTerminales());
		
		
		Menu menu = new Menu();
		menu.setVisible(true);

	}
}