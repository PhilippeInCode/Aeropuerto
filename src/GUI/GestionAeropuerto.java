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
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class GestionAeropuerto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldCodigo;
	private JTextField textFieldCiudad;
	private JTextField textFieldNombre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionAeropuerto frame = new GestionAeropuerto();
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
	  public GestionAeropuerto() {
	        setTitle("Gestor de Aeropuertos");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setBounds(100, 100, 836, 682);
	        contentPane = new JPanel() {
	            private static final long serialVersionUID = 1L;

	            protected void paintComponent(Graphics g) {
	                super.paintComponent(g);
	                ImageIcon icon = new ImageIcon("C:\\Users\\User\\eclipse-workspace\\Aeropuerto\\src-resources-images\\img3.jpg"); 
	                Image img = icon.getImage();
	                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	            }
	        };
	        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

	        setContentPane(contentPane);
	        contentPane.setLayout(null);
		
		JList listAeropuertos = new JList();
		listAeropuertos.setBounds(27, 33, 270, 524);
		contentPane.add(listAeropuertos);
		
		JButton btnCrearAeropuerto = new JButton("Crear");
		btnCrearAeropuerto.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCrearAeropuerto.setBounds(37, 579, 107, 56);
		contentPane.add(btnCrearAeropuerto);
		
		JButton btnEliminarAeropuertos = new JButton("Eliminar");
		btnEliminarAeropuertos.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnEliminarAeropuertos.setBounds(180, 579, 107, 56);
		contentPane.add(btnEliminarAeropuertos);
		
		textFieldCodigo = new JTextField();
		textFieldCodigo.setBounds(495, 79, 141, 32);
		contentPane.add(textFieldCodigo);
		textFieldCodigo.setColumns(10);
		
		JButton btnBuscarAeropuertos = new JButton("Buscar");
		btnBuscarAeropuertos.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnBuscarAeropuertos.setBounds(552, 299, 157, 56);
		contentPane.add(btnBuscarAeropuertos);
		
		JButton btnCancelarOpcionAeropuerto = new JButton("Cancelar");
		btnCancelarOpcionAeropuerto.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCancelarOpcionAeropuerto.setBounds(353, 391, 157, 56);
		contentPane.add(btnCancelarOpcionAeropuerto);
		
		JButton btnAnyadirAeropuerto = new JButton("Añadir");
		btnAnyadirAeropuerto.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAnyadirAeropuerto.setBounds(353, 299, 157, 56);
		contentPane.add(btnAnyadirAeropuerto);
		
		JButton btnModificarAeropuertos = new JButton("Modificar");
		btnModificarAeropuertos.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnModificarAeropuertos.setBounds(552, 391, 157, 56);
		contentPane.add(btnModificarAeropuertos);
		
		JLabel labelCodigo = new JLabel("Código");
		labelCodigo.setHorizontalAlignment(SwingConstants.LEFT);
		labelCodigo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		labelCodigo.setBounds(425, 83, 67, 21);
		contentPane.add(labelCodigo);
		
		JLabel labelCiudad = new JLabel("Ciudad");
		labelCiudad.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		labelCiudad.setBounds(425, 171, 67, 21);
		contentPane.add(labelCiudad);
		
		JLabel labelNombre = new JLabel("Nombre");
		labelNombre.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		labelNombre.setBounds(425, 124, 67, 22);
		contentPane.add(labelNombre);
		
		textFieldCiudad = new JTextField();
		textFieldCiudad.setColumns(10);
		textFieldCiudad.setBounds(495, 167, 141, 32);
		contentPane.add(textFieldCiudad);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setColumns(10);
		textFieldNombre.setBounds(495, 121, 141, 32);
		contentPane.add(textFieldNombre);
		
		JLabel labelListaAeropuertos = new JLabel("Lista de aeropuertos");
		labelListaAeropuertos.setHorizontalAlignment(SwingConstants.CENTER);
		labelListaAeropuertos.setFont(new Font("Tahoma", Font.BOLD, 12));
		labelListaAeropuertos.setBounds(27, 10, 270, 13);
		contentPane.add(labelListaAeropuertos);
	}
}
