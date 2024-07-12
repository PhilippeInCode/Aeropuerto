package GUI;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import accesoDatos.InicializarBBDD;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Acceso extends JFrame {

    private static final long serialVersionUID = 1L;
    private PanelImagen contentPane;

    /**
     * Create the frame.
     */
    public Acceso() {
        setTitle("Aplicación de gestión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 892, 555);
        
        // Ruta de la imagen de fondo
        String imagePath = "images\\img1.jpg";
        contentPane = new PanelImagen(imagePath);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel labelTitulo = new JLabel("¡Bienvenido a la aplicación de gestión!");
        labelTitulo.setFont(new Font("Tahoma", Font.BOLD, 40));
        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        labelTitulo.setBounds(10, 34, 858, 67);
        contentPane.add(labelTitulo);
        
        JLabel labelCursiva = new JLabel("Pulse el botón de Entrar para comenzar");
        labelCursiva.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        labelCursiva.setHorizontalAlignment(SwingConstants.CENTER);
        labelCursiva.setBounds(117, 184, 615, 25);
        contentPane.add(labelCursiva);
        
        JLabel labelCrearBaseDatos = new JLabel("Comprobando si existe la base de datos");
        labelCrearBaseDatos.setBounds(56, 472, 421, 14);
        contentPane.add(labelCrearBaseDatos);

        JButton btnEntrar = new JButton("Entrar");
        Acceso acceso = this;
        btnEntrar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                // Inicializa la base de datos y las tablas
        		System.out.println("Pulsado Acceso");
        		Gestiones gestiones = new Gestiones();
        		acceso.setVisible(false);
        		gestiones.setVisible(true);
        	}
        });
        btnEntrar.setFont(new Font("Tahoma", Font.PLAIN, 60));
        btnEntrar.setToolTipText("");
        btnEntrar.setBounds(108, 234, 643, 206);
        contentPane.add(btnEntrar);

        boolean existeBD = new InicializarBBDD().crearBaseDeDatosYTablas();
        if (existeBD) 
        	labelCrearBaseDatos.setText("Encontrada la base de datos de aeropuertos");
        else 
        	labelCrearBaseDatos.setText("Creada la base de datos de aeropuertos");
    }
    
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Acceso frame = new Acceso();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
