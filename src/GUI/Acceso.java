package GUI;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Acceso extends JFrame {

    private static final long serialVersionUID = 1L;
    private CustomPanel contentPane;

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

    /**
     * Create the frame.
     */
    public Acceso() {
        setTitle("Aplicación de gestión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 892, 555);
        
        // Ruta de la imagen de fondo
        String imagePath = "C:\\Users\\User\\eclipse-workspace\\Aeropuerto\\src-resources-images\\img1.jpg";
        contentPane = new CustomPanel(imagePath);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setFont(new Font("Tahoma", Font.PLAIN, 60));
        btnEntrar.setToolTipText("");
        btnEntrar.setBounds(108, 234, 643, 206);
        contentPane.add(btnEntrar);
        
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
    }
}

class CustomPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private Image backgroundImage;

    public CustomPanel(String imagePath) {
        backgroundImage = new ImageIcon(imagePath).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
