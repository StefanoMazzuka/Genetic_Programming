package Base;

import GUI.Menu;
import Mutacion.Mutacion;
import Mutacion.MutacionTerminalSimple;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Arbol arbol= Arbol.inicializarArbol("COMPLETO", false, 1);
		Mutacion mutar = new MutacionTerminalSimple();
		arbol = mutar.mutar(arbol);
		
		
		Menu menu = new Menu();
		menu.setVisible(true);

	}
}