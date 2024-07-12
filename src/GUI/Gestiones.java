package GUI;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Gestiones extends JFrame {

    private static final long serialVersionUID = 1L;
    private PanelImagen contentPane;

    /**
     * Create the frame.
     */
    public Gestiones() {
        setTitle("Gestiones");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1020, 586);
        
        // Ruta de la imagen de fondo
        String imagePath = "images\\img2.jpg";
        contentPane = new PanelImagen(imagePath);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JButton btnGesAeropuertos = new JButton("Gestión de aeropuertos");
        Gestiones gestiones = this;
        btnGesAeropuertos.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.out.println("Pulsado Gestión Aeropuertos");
        		GestionAeropuerto gestionAeropuerto = new GestionAeropuerto(gestiones);
        		gestiones.setVisible(false);
        		gestionAeropuerto.setVisible(true);
        	}
        });
        btnGesAeropuertos.setFont(new Font("Tahoma", Font.BOLD, 30));
        btnGesAeropuertos.setBounds(20, 257, 436, 190);
        contentPane.add(btnGesAeropuertos);
        
        JButton btnGesVuelos = new JButton("Gestión de vuelos");
        btnGesVuelos.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.out.println("Pulsado Gestión Vuelos");
        		GestionVuelos gestionVuelos = new GestionVuelos(gestiones);
        		gestiones.setVisible(false);
        		gestionVuelos.setVisible(true);
        	}
        });
        btnGesVuelos.setFont(new Font("Tahoma", Font.BOLD, 30));
        btnGesVuelos.setBounds(539, 257, 457, 190);
        contentPane.add(btnGesVuelos);
        
        JLabel labelTitulo = new JLabel("Seleccione la opción que desea gestionar");
        labelTitulo.setFont(new Font("Tahoma", Font.BOLD, 30));
        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        labelTitulo.setBounds(20, 96, 976, 49);
        contentPane.add(labelTitulo);
    }


    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Gestiones frame = new Gestiones();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}


