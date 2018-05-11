package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.math.plot.*;

public class Menu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int tama�oPoblacion;
	private int numeroGeneraciones;
	private double porcentajeCruce;
	private double porcentajeMutacion;
	private int profundidadArbol;
	private double[] generacion;
	private double[] mejoresFitnessAbsolutos;
	private double[] mejoresFitness;
	private double[] listaMedias;
	private String[] selecciones = { "Torneos", "Ruleta", "Estocastico universal"};
	private String[] mutaciones = {"Funcional", "Terminal", "Arbol"};
	private String[] inicializaciones = {"Creciente", "Completa"};

	public Menu() {
		JComboBox<String> seleccion = new JComboBox<String>(selecciones);
		JComboBox<String> mutacion = new JComboBox<String>(mutaciones);
		JComboBox<String> inicializacion = new JComboBox<String>(inicializaciones);
		JTextField tamPob = new JTextField("200");
		JTextField numGen = new JTextField("300");
		JTextField porCruce = new JTextField("0.75");
		JTextField porMuta = new JTextField("0.1");
		JTextField profundidad = new JTextField("5");
		JCheckBox IF = new JCheckBox("", true);
		JCheckBox eli = new JCheckBox("", true);
		JLabel empty = new JLabel();
		JButton ok = new JButton("Ok");
		JLabel fitMejor = new JLabel("Fitness Mejor:");
		JLabel genMejor = new JLabel("Gen Mejor:");
		
		setSize(new Dimension(700, 500));
		setLocationRelativeTo(null); 
		setTitle("Genetic Programming"); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Terminar el programa al pulsar la X
		setResizable(false);

		// Panel programa
		// Menu Panel
		JPanel menuPanel = new JPanel(new GridLayout(11, 2));
		menuPanel.add(new JLabel("Tipo de seleccion:"));
		menuPanel.add(seleccion);
		menuPanel.add(new JLabel("Tipo de mutaci�n:"));
		menuPanel.add(mutacion);
		menuPanel.add(new JLabel("Tipo de inicializaci�n:"));
		menuPanel.add(inicializacion);
		menuPanel.add(new JLabel("Tama�o de la poblaci�n:"));
		menuPanel.add(tamPob);
		menuPanel.add(new JLabel("Numero de generaciones:"));
		menuPanel.add(numGen);
		menuPanel.add(new JLabel("Porcentaje de cruce:"));
		menuPanel.add(porCruce);
		menuPanel.add(new JLabel("Porcentaje de mutaciones:"));
		menuPanel.add(porMuta);
		menuPanel.add(new JLabel("Profundidad del arbol:"));
		menuPanel.add(profundidad);
		menuPanel.add(new JLabel("IF:"));
		menuPanel.add(IF);
		menuPanel.add(new JLabel("Elitismo:"));
		menuPanel.add(eli);
		menuPanel.add(empty);
		menuPanel.add(ok);

		// Grafica Panel
		Plot2DPanel grafica = new Plot2DPanel();

		grafica.addLegend("SOUTH");

		JPanel resultados = new JPanel(new GridLayout(2, 1));
		resultados.add(fitMejor);
		resultados.add(genMejor);
		
		JPanel graficaPanel = new JPanel();
		graficaPanel.setLayout(new BorderLayout());
		graficaPanel.add(grafica, BorderLayout.CENTER);
		graficaPanel.add(resultados, BorderLayout.SOUTH);
		
		JPanel programa = new JPanel();
		programa.setLayout(new BorderLayout());
		programa.add(menuPanel, BorderLayout.WEST);
		programa.add(graficaPanel, BorderLayout.CENTER);
		// Fin panel programa
		
		add(programa);
		
		ok.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				if (tamPob.getText().equals("") || numGen.getText().equals("") || porCruce.getText().equals("") ||
						porMuta.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Por favor introduzca todos los datos.");
				}

				else {

					tama�oPoblacion = Integer.parseInt(tamPob.getText());
					numeroGeneraciones = Integer.parseInt(numGen.getText());
					porcentajeCruce = Double.parseDouble(porCruce.getText());
					porcentajeMutacion = Double.parseDouble(porMuta.getText());
					profundidadArbol = Integer.parseInt(profundidad.getText());

					if (eli.isSelected() == true) {

					}

					else {

					}
				} 
			}
		});	
	}

	public void pintarGrafica(JPanel graficaPanel, Plot2DPanel grafica, double[] x, double[] y, String nombre) {
		// define the legend position
		grafica.setAxisLabel(0, "Generaci�n");
		grafica.setAxisLabel(1, "Evaluaci�n");

		// add a line plot to the PlotPanel
		grafica.addLinePlot(nombre, x, y);
		graficaPanel.add(grafica, BorderLayout.CENTER);
		grafica.setVisible(true);
	}
}