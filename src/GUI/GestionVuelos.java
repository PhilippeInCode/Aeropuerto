package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;


public class GestionVuelos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldDestino;
	private JTextField textFieldOrigen;
	private JTextField textFieldCodigo;
	private JTextField textFieldCompleto;
	private JTextField textFieldNumPasajeros;
	private JTextField textFieldNumPlazas;
	private JTextField textFieldDuracion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionVuelos frame = new GestionVuelos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GestionVuelos() {
	    setTitle("Gestor de Vuelos");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(100, 100, 1298, 1008);
	    contentPane = new JPanel() {
	        private static final long serialVersionUID = 1L;

	        
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            ImageIcon img = new ImageIcon("C:\\Users\\User\\eclipse-workspace\\Aeropuerto\\src-resources-images\\img4.jpg");
	            g.drawImage(img.getImage(), 0, 0, getWidth(), getHeight(), this);
	        }
	    };
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    setContentPane(contentPane);
	    contentPane.setLayout(null);

	    // Lista de vuelos
	    JList listVuelos = new JList();
	    listVuelos.setBounds(26, 34, 270, 523);
	    contentPane.add(listVuelos);
	    
	    // Botones
	    JButton btnCrearVuelos = new JButton("Crear");
	    btnCrearVuelos.setFont(new Font("Tahoma", Font.BOLD, 12));
	    btnCrearVuelos.setBounds(36, 579, 107, 56);
	    contentPane.add(btnCrearVuelos);
	    
	    JButton btnEliminarVuelos = new JButton("Eliminar");
	    btnEliminarVuelos.setFont(new Font("Tahoma", Font.BOLD, 12));
	    btnEliminarVuelos.setBounds(179, 579, 107, 56);
	    contentPane.add(btnEliminarVuelos);
	    
	    JButton btnCancelarOpcionVuelos = new JButton("Cancelar");
	    btnCancelarOpcionVuelos.setFont(new Font("Tahoma", Font.BOLD, 20));
	    btnCancelarOpcionVuelos.setBounds(26, 836, 212, 108);
	    contentPane.add(btnCancelarOpcionVuelos);
	    
	    JButton btnAnyadirVuelos = new JButton("Añadir");
	    btnAnyadirVuelos.setFont(new Font("Tahoma", Font.BOLD, 12));
	    btnAnyadirVuelos.setBounds(318, 455, 157, 56);
	    contentPane.add(btnAnyadirVuelos);
	    
	    JButton btnBuscarVuelos = new JButton("Buscar");
	    btnBuscarVuelos.setFont(new Font("Tahoma", Font.BOLD, 12));
	    btnBuscarVuelos.setBounds(511, 455, 157, 56);
	    contentPane.add(btnBuscarVuelos);
	    
	    JButton btnModificarVuelos = new JButton("Modificar");
	    btnModificarVuelos.setFont(new Font("Tahoma", Font.BOLD, 12));
	    btnModificarVuelos.setBounds(696, 455, 157, 56);
	    contentPane.add(btnModificarVuelos);
	    
	    // Campos de texto y etiquetas
	    textFieldDestino = new JTextField();
	    textFieldDestino.setColumns(10);
	    textFieldDestino.setBounds(478, 122, 141, 32);
	    contentPane.add(textFieldDestino);
	    
	    JLabel labelDestino = new JLabel("Destino");
	    labelDestino.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
	    labelDestino.setBounds(408, 126, 67, 21);
	    contentPane.add(labelDestino);
	    
	    JLabel labelOrigen = new JLabel("Origen");
	    labelOrigen.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
	    labelOrigen.setBounds(408, 404, 67, 22);
	    contentPane.add(labelOrigen);
	    
	    textFieldOrigen = new JTextField();
	    textFieldOrigen.setColumns(10);
	    textFieldOrigen.setBounds(478, 401, 141, 32);
	    contentPane.add(textFieldOrigen);
	    
	    textFieldCodigo = new JTextField();
	    textFieldCodigo.setColumns(10);
	    textFieldCodigo.setBounds(478, 34, 141, 32);
	    contentPane.add(textFieldCodigo);
	    
	    JLabel labelCodigo = new JLabel("Código");
	    labelCodigo.setHorizontalAlignment(SwingConstants.LEFT);
	    labelCodigo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
	    labelCodigo.setBounds(408, 38, 67, 21);
	    contentPane.add(labelCodigo);
	    
	    textFieldCompleto = new JTextField();
	    textFieldCompleto.setColumns(10);
	    textFieldCompleto.setBounds(478, 76, 141, 32);
	    contentPane.add(textFieldCompleto);
	    
	    JLabel labelCompleto = new JLabel("Completo");
	    labelCompleto.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
	    labelCompleto.setBounds(408, 80, 67, 21);
	    contentPane.add(labelCompleto);
	    
	    // Añadir JSpinner para la fecha de salida
	    SpinnerDateModel dateModelSalida = new SpinnerDateModel();
	    JSpinner spinnerFechaSalida = new JSpinner(dateModelSalida);
	    JSpinner.DateEditor dateEditorSalida = new JSpinner.DateEditor(spinnerFechaSalida, "dd/MM/yyyy HH:mm:ss");
	    spinnerFechaSalida.setEditor(dateEditorSalida);
	    spinnerFechaSalida.setBounds(478, 174, 200, 32);
	    contentPane.add(spinnerFechaSalida);
	    
	    JLabel labelFechaSalida = new JLabel("Fecha salida");
	    labelFechaSalida.setHorizontalAlignment(SwingConstants.LEFT);
	    labelFechaSalida.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
	    labelFechaSalida.setBounds(392, 178, 83, 21);
	    contentPane.add(labelFechaSalida);
	    
	    // Añadir JSpinner para la fecha de llegada
	    SpinnerDateModel dateModelLlegada = new SpinnerDateModel();
	    JSpinner spinnerFechaLlegada = new JSpinner(dateModelLlegada);
	    JSpinner.DateEditor dateEditorLlegada = new JSpinner.DateEditor(spinnerFechaLlegada, "dd/MM/yyyy HH:mm:ss");
	    spinnerFechaLlegada.setEditor(dateEditorLlegada);
	    spinnerFechaLlegada.setBounds(478, 214, 200, 32);
	    contentPane.add(spinnerFechaLlegada);
	    
	    JLabel labelFechaLlegada = new JLabel("Fecha llegada");
	    labelFechaLlegada.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
	    labelFechaLlegada.setBounds(386, 219, 89, 22);
	    contentPane.add(labelFechaLlegada);
	    
	    textFieldNumPasajeros = new JTextField();
	    textFieldNumPasajeros.setColumns(10);
	    textFieldNumPasajeros.setBounds(478, 346, 141, 32);
	    contentPane.add(textFieldNumPasajeros);
	    
	    JLabel labelNumPasajeros = new JLabel("Número de pasajeros");
	    labelNumPasajeros.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
	    labelNumPasajeros.setBounds(342, 350, 133, 21);
	    contentPane.add(labelNumPasajeros);
	    
	    JLabel labelNumPlazas = new JLabel("Número de plazas");
	    labelNumPlazas.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
	    labelNumPlazas.setBounds(356, 303, 119, 22);
	    contentPane.add(labelNumPlazas);
	    
	    textFieldNumPlazas = new JTextField();
	    textFieldNumPlazas.setColumns(10);
	    textFieldNumPlazas.setBounds(478, 300, 141, 32);
	    contentPane.add(textFieldNumPlazas);
	    
	    textFieldDuracion = new JTextField();
	    textFieldDuracion.setColumns(10);
	    textFieldDuracion.setBounds(478, 258, 141, 32);
	    contentPane.add(textFieldDuracion);
	    
	    JLabel labelDuracion = new JLabel("Duración");
	    labelDuracion.setHorizontalAlignment(SwingConstants.LEFT);
	    labelDuracion.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
	    labelDuracion.setBounds(408, 262, 67, 21);
	    contentPane.add(labelDuracion);
	    
	    JLabel labelListaVuelos = new JLabel("Lista de vuelos");
	    labelListaVuelos.setFont(new Font("Tahoma", Font.BOLD, 12));
	    labelListaVuelos.setHorizontalAlignment(SwingConstants.CENTER);
	    labelListaVuelos.setBounds(26, 10, 270, 13);
	    contentPane.add(labelListaVuelos);
	    
	    JButton btnGestionPasajeros = new JButton("Gestionar pasajeros");
	    btnGestionPasajeros.setFont(new Font("Tahoma", Font.BOLD, 30));
	    btnGestionPasajeros.setBounds(854, 806, 420, 138);
	    contentPane.add(btnGestionPasajeros);
	}
}