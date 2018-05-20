package GestionArchivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Leer {
	private String rutaUsuario = System.getProperty("user.dir");
	private String separador = File.separator;
	private File archivo;
	private FileReader fr;
	private BufferedReader br;
	private String texto;

	public void leerCasos() {
		String userPath = System.getProperty("user.dir");
		String separator = File.separator;
		String folderPath = userPath + separator + "Files";
		File folder = new File(folderPath);
		String[] files = folder.list();
		
		
	}
}
