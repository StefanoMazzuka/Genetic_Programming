package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.math.plot.*;

import Base.AlgoritmoGenetico;

public class Menu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private double[] generacion;
	private double[] mejoresFitnessAbsolutos;
	private double[] mejoresFitness;
	private double[] listaMedias;
	private String[] selecciones = {"Torneos", "Ruleta", "Estocastico universal"};
	private String[] mutaciones = {"Funcional", "Terminal"};
	private String[] inicializaciones = {"Creciente", "Completo"};

	public Menu() {
		JComboBox<String> seleccion = new JComboBox<String>(selecciones);
		JComboBox<String> mutacion = new JComboBox<String>(mutaciones);
		JComboBox<String> inicializacion = new JComboBox<String>(inicializaciones);
		JTextField tamPob = new JTextField("200");
		JTextField numGen = new JTextField("300");
		JTextField porCruce = new JTextField("0.75");
		JTextField porMuta = new JTextField("0.1");
		JTextField profundidad = new JTextField("6");
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
		menuPanel.add(new JLabel("Tipo de mutación:"));
		menuPanel.add(mutacion);
		menuPanel.add(new JLabel("Tipo de inicialización:"));
		menuPanel.add(inicializacion);
		menuPanel.add(new JLabel("Tamaño de la población:"));
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

					
					int tipoSeleccion = seleccion.getSelectedIndex();
					int tipoMutacion = mutacion.getSelectedIndex();
					String tipoInicializacion = (String) inicializacion.getSelectedItem();
					int lPoblacion = Integer.parseInt(tamPob.getText());
					int numGeneraciones = Integer.parseInt(numGen.getText());
					double porcentajeCruce = Double.parseDouble(porCruce.getText());
					double porcentajeMutacion = Double.parseDouble(porMuta.getText());
					int profundidadMaxima = Integer.parseInt(profundidad.getText());
					boolean elitista = eli.isSelected();
					boolean funcionIf = IF.isSelected();
					
					generacion = new double[numGeneraciones];
					for (int i = 0; i < numGeneraciones; i++)
						generacion[i] = i;
					mejoresFitnessAbsolutos = new double[numGeneraciones];
					mejoresFitness = new double[numGeneraciones];
					
					AlgoritmoGenetico ag;

					ag = new AlgoritmoGenetico(tipoSeleccion, tipoMutacion, tipoInicializacion, lPoblacion, numGeneraciones,
							porcentajeCruce, porcentajeMutacion, profundidadMaxima, elitista, funcionIf);
					ag.ejecutar();

					fitMejor.setText("Fitness Mejor: " + ag.getFitnessMejorAbsoluto());
					genMejor.setText("Gen Mejor: " + ag.getFenotipoMejorAbsoluto());

					mejoresFitnessAbsolutos = ag.getListaFitnessMejorAbsoluto();
					mejoresFitness = ag.getListaFitnessMejor();
					listaMedias = ag.getListaMedias();

					grafica.setVisible(false);
					grafica.removeAllPlots();
					pintarGrafica(graficaPanel, grafica, generacion, mejoresFitnessAbsolutos, "Mejor absoluto");
					pintarGrafica(graficaPanel, grafica, generacion, mejoresFitness, "Mejor de la generación");
					pintarGrafica(graficaPanel, grafica, generacion, listaMedias, "Media de la generación");
				} 
			}
		});	
	}

	public void pintarGrafica(JPanel graficaPanel, Plot2DPanel grafica, double[] x, double[] y, String nombre) {
		// define the legend position
		grafica.setAxisLabel(0, "Generación");
		grafica.setAxisLabel(1, "Evaluación");

		// add a line plot to the PlotPanel
		grafica.addLinePlot(nombre, x, y);
		graficaPanel.add(grafica, BorderLayout.CENTER);
		grafica.setVisible(true);
	}
}