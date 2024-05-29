package GUI;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;

public class GestionPasajeros extends JFrame {

    private static final long serialVersionUID = 1L;
    private BackgroundPanel contentPane;
    private JTextField textFieldId;
    private JTextField textFieldNombre;
    private JTextField textFieldApellidos;
    private JTextField textFieldEdad;
    private JTextField textFieldDNI;
    private JTextField textFieldSexo;
    private JTextField textFieldPeso;
    private JTextField textFieldAltura;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GestionPasajeros frame = new GestionPasajeros();
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
    public GestionPasajeros() {
        setTitle("Gestor de Pasajeros");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 2225, 1304);
        
        // Reemplaza JPanel con BackgroundPanel
        contentPane = new BackgroundPanel("C:\\Users\\User\\eclipse-workspace\\Aeropuerto\\src-resources-images\\img5.jpg");
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JList listPasajerosExistentes = new JList();
        listPasajerosExistentes.setBounds(34, 52, 478, 1204);
        contentPane.add(listPasajerosExistentes);

        JLabel labelPasajerosExistentes = new JLabel("Pasajeros existentes");
        labelPasajerosExistentes.setFont(new Font("Tahoma", Font.BOLD, 16));
        labelPasajerosExistentes.setHorizontalAlignment(SwingConstants.CENTER);
        labelPasajerosExistentes.setBounds(34, 11, 478, 36);
        contentPane.add(labelPasajerosExistentes);

        JButton btnIncluirEnVuelo = new JButton("Incluir en vuelo");
        btnIncluirEnVuelo.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnIncluirEnVuelo.setBounds(541, 453, 144, 92);
        contentPane.add(btnIncluirEnVuelo);

        JButton btnCrearPasajero = new JButton("Crear");
        btnCrearPasajero.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnCrearPasajero.setBounds(709, 1137, 242, 108);
        contentPane.add(btnCrearPasajero);

        JButton btnEliminarPasajero = new JButton("Eliminar");
        btnEliminarPasajero.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnEliminarPasajero.setBounds(994, 1137, 231, 108);
        contentPane.add(btnEliminarPasajero);

        JList listPasajerosParaElVuelo = new JList();
        listPasajerosParaElVuelo.setBounds(709, 52, 516, 1064);
        contentPane.add(listPasajerosParaElVuelo);

        JLabel labelPasajerosParaElVuelo = new JLabel("Pasajeros para el vuelo");
        labelPasajerosParaElVuelo.setHorizontalAlignment(SwingConstants.CENTER);
        labelPasajerosParaElVuelo.setFont(new Font("Tahoma", Font.BOLD, 16));
        labelPasajerosParaElVuelo.setBounds(709, 11, 516, 36);
        contentPane.add(labelPasajerosParaElVuelo);

        textFieldId = new JTextField();
        textFieldId.setColumns(10);
        textFieldId.setBounds(1512, 56, 462, 56);
        contentPane.add(textFieldId);

        JLabel labelId = new JLabel("Id");
        labelId.setHorizontalAlignment(SwingConstants.LEFT);
        labelId.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        labelId.setBounds(1412, 52, 52, 56);
        contentPane.add(labelId);

        JButton btnAnyadirPasajero = new JButton("Añadir");
        btnAnyadirPasajero.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnAnyadirPasajero.setBounds(1369, 911, 278, 122);
        contentPane.add(btnAnyadirPasajero);

        JButton btnBuscarPasajero = new JButton("Buscar");
        btnBuscarPasajero.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnBuscarPasajero.setBounds(1726, 911, 278, 122);
        contentPane.add(btnBuscarPasajero);

        JLabel labelNombre = new JLabel("Nombre");
        labelNombre.setHorizontalAlignment(SwingConstants.LEFT);
        labelNombre.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        labelNombre.setBounds(1366, 150, 98, 56);
        contentPane.add(labelNombre);

        textFieldNombre = new JTextField();
        textFieldNombre.setColumns(10);
        textFieldNombre.setBounds(1512, 154, 462, 56);
        contentPane.add(textFieldNombre);

        JLabel labelApellidos = new JLabel("Apellidos");
        labelApellidos.setHorizontalAlignment(SwingConstants.LEFT);
        labelApellidos.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        labelApellidos.setBounds(1366, 252, 98, 56);
        contentPane.add(labelApellidos);

        textFieldApellidos = new JTextField();
        textFieldApellidos.setColumns(10);
        textFieldApellidos.setBounds(1512, 256, 462, 56);
        contentPane.add(textFieldApellidos);

        JLabel labelEdad = new JLabel("Edad");
        labelEdad.setHorizontalAlignment(SwingConstants.LEFT);
        labelEdad.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        labelEdad.setBounds(1402, 352, 65, 56);
        contentPane.add(labelEdad);

        textFieldEdad = new JTextField();
        textFieldEdad.setColumns(10);
        textFieldEdad.setBounds(1512, 356, 462, 56);
        contentPane.add(textFieldEdad);

        JLabel labelDNI = new JLabel("DNI");
        labelDNI.setHorizontalAlignment(SwingConstants.LEFT);
        labelDNI.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        labelDNI.setBounds(1402, 458, 65, 56);
        contentPane.add(labelDNI);

        textFieldDNI = new JTextField();
        textFieldDNI.setColumns(10);
        textFieldDNI.setBounds(1512, 462, 462, 56);
        contentPane.add(textFieldDNI);

        textFieldSexo = new JTextField();
        textFieldSexo.setColumns(10);
        textFieldSexo.setBounds(1512, 557, 462, 56);
        contentPane.add(textFieldSexo);

        JLabel labelSexo = new JLabel("Sexo");
        labelSexo.setHorizontalAlignment(SwingConstants.LEFT);
        labelSexo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        labelSexo.setBounds(1402, 553, 65, 56);
        contentPane.add(labelSexo);

        textFieldPeso = new JTextField();
        textFieldPeso.setColumns(10);
        textFieldPeso.setBounds(1512, 659, 462, 56);
        contentPane.add(textFieldPeso);

        JLabel labelPeso = new JLabel("Peso");
        labelPeso.setHorizontalAlignment(SwingConstants.LEFT);
        labelPeso.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        labelPeso.setBounds(1402, 655, 65, 56);
        contentPane.add(labelPeso);

        textFieldAltura = new JTextField();
        textFieldAltura.setColumns(10);
        textFieldAltura.setBounds(1512, 763, 462, 56);
        contentPane.add(textFieldAltura);

        JLabel labelAltura = new JLabel("Altura");
        labelAltura.setHorizontalAlignment(SwingConstants.LEFT);
        labelAltura.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        labelAltura.setBounds(1402, 759, 65, 56);
        contentPane.add(labelAltura);

        JButton btnCancelarPasajeros = new JButton("Cancelar");
        btnCancelarPasajeros.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnCancelarPasajeros.setBounds(1369, 1095, 278, 122);
        contentPane.add(btnCancelarPasajeros);

        JButton btnModificar = new JButton("Modificar");
        btnModificar.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnModificar.setBounds(1726, 1095, 278, 122);
        contentPane.add(btnModificar);
    }

    // Clase interna para el panel de fondo con imagen
    class BackgroundPanel extends JPanel {
        private static final long serialVersionUID = 1L;
        private Image backgroundImage;

        public BackgroundPanel(String fileName) {
            try {
                backgroundImage = new ImageIcon(fileName).getImage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}
