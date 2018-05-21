package GestionArchivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JOptionPane;

public class Leer {

	boolean IO_MUX[][];
	
	public boolean[][] leerCasos() {

		FileReader fr;
		BufferedReader br;
		String userPath = System.getProperty("user.dir");
		String folderPath = userPath + File.separator + "src" + File.separator + "Resources" + File.separator + "IO_MUX.txt";
		File archivo = new File(folderPath);

		try {
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			int numFilas = Integer.parseInt(br.readLine());

			IO_MUX = new boolean[numFilas][7];
			String linea;
			String valores[];
			int fila = 0;
			while((linea = br.readLine()) != null) {
				valores = linea.split(",");
				for (int i = 0; i < 7; i++) {
					if (valores[i].equals("1"))
						IO_MUX[fila][i] = true;
					else 
						IO_MUX[fila][i] = false;
				}
				fila++;
			}

			br.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e);
		}

		return IO_MUX;
	}
}
