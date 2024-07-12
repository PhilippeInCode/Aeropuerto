package GUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import accesoDatos.Aeropuertos;

public class GestionAeropuerto extends JFrame {

    private static final long serialVersionUID = 1L;
    private PanelImagen contentPane;
    private JTextField textFieldCodigo;
    private JTextField textFieldCiudad;
    private JTextField textFieldNombre;
    private JButton btnEliminarAeropuertos;
    private JButton btnBuscarAeropuertos;
    private JButton btnCrearAeropuerto;
    private JButton btnVolverAtrasAeropuertos;
    private JButton btnAnyadirAeropuerto;
    private JButton btnModificarAeropuertos;
    private JList<String> listAeropuertos;
    private DefaultListModel<String> aeropuertosModel;
    private Aeropuertos aeropuertos;
    private int idSeleccionado;

    /**
     * Create the frame.
     */
    public GestionAeropuerto(Gestiones gestiones) {
        aeropuertos = new Aeropuertos();
        setTitle("Gestor de Aeropuertos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 546, 684);
        contentPane = new PanelImagen("images\\img3.jpg");
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        aeropuertosModel = new DefaultListModel<>();
        listAeropuertos = new JList<>(aeropuertosModel);
        listAeropuertos.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && listAeropuertos.getSelectedValue() != null) {
                    rellenarCamposConDatos(listAeropuertos.getSelectedValue());
                }
            }
        });
        listAeropuertos.setBounds(27, 46, 480, 252);
        contentPane.add(listAeropuertos);

        btnCrearAeropuerto = new JButton("Crear");
        btnCrearAeropuerto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                habilitarCamposParaCrear();
            }
        });
        btnCrearAeropuerto.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnCrearAeropuerto.setBounds(346, 308, 161, 56);
        contentPane.add(btnCrearAeropuerto);

        btnEliminarAeropuertos = new JButton("Eliminar");
        btnEliminarAeropuertos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarAeropuerto();
            }
        });
        btnEliminarAeropuertos.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnEliminarAeropuertos.setBounds(346, 581, 161, 56);
        contentPane.add(btnEliminarAeropuertos);

        textFieldCodigo = new JTextField();
        textFieldCodigo.setEnabled(false);
        textFieldCodigo.setBounds(97, 351, 200, 32);
        contentPane.add(textFieldCodigo);
        textFieldCodigo.setColumns(10);

        btnBuscarAeropuertos = new JButton("Buscar");
        btnBuscarAeropuertos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarAeropuertos();
            }
        });
        btnBuscarAeropuertos.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnBuscarAeropuertos.setBounds(346, 440, 161, 56);
        contentPane.add(btnBuscarAeropuertos);

        btnVolverAtrasAeropuertos = new JButton("Volver");
        GestionAeropuerto gestionAeropuerto = this;
        btnVolverAtrasAeropuertos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gestionAeropuerto.dispose();
                gestiones.setVisible(true);
            }
        });
        btnVolverAtrasAeropuertos.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnVolverAtrasAeropuertos.setBounds(10, 581, 157, 56);
        contentPane.add(btnVolverAtrasAeropuertos);

        btnAnyadirAeropuerto = new JButton("Añadir");
        btnAnyadirAeropuerto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                crearAeropuerto();
            }
        });
        btnAnyadirAeropuerto.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnAnyadirAeropuerto.setBounds(346, 374, 161, 56);
        contentPane.add(btnAnyadirAeropuerto);

        btnModificarAeropuertos = new JButton("Modificar");
        btnModificarAeropuertos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modificarAeropuerto();
            }
        });
        btnModificarAeropuertos.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnModificarAeropuertos.setBounds(346, 512, 161, 56);
        contentPane.add(btnModificarAeropuertos);

        JLabel labelCodigo = new JLabel("Código");
        labelCodigo.setHorizontalAlignment(SwingConstants.LEFT);
        labelCodigo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
        labelCodigo.setBounds(34, 355, 60, 21);
        contentPane.add(labelCodigo);

        JLabel labelCiudad = new JLabel("Ciudad");
        labelCiudad.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
        labelCiudad.setBounds(27, 443, 67, 21);
        contentPane.add(labelCiudad);

        JLabel labelNombre = new JLabel("Nombre");
        labelNombre.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
        labelNombre.setBounds(27, 396, 67, 22);
        contentPane.add(labelNombre);

        textFieldCiudad = new JTextField();
        textFieldCiudad.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                habilitarBotonBusqueda();
            }
        });
        textFieldCiudad.setColumns(10);
        textFieldCiudad.setBounds(97, 439, 200, 32);
        contentPane.add(textFieldCiudad);

        textFieldNombre = new JTextField();
        textFieldNombre.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                habilitarBotonBusqueda();
            }
        });
        textFieldNombre.setColumns(10);
        textFieldNombre.setBounds(97, 393, 200, 32);
        contentPane.add(textFieldNombre);

        JLabel labelListaAeropuertos = new JLabel("Lista de aeropuertos");
        labelListaAeropuertos.setHorizontalAlignment(SwingConstants.CENTER);
        labelListaAeropuertos.setFont(new Font("Tahoma", Font.BOLD, 20));
        labelListaAeropuertos.setBounds(27, 10, 480, 22);
        contentPane.add(labelListaAeropuertos);
        
        JButton btnLimpiarCampos = new JButton("Limpiar formulario");
        btnLimpiarCampos.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                cargarAeropuertos(); // Recargar todos los aeropuertos después de crear uno nuevo
                limpiarCampos();
                restablecerBotones();
        	}
        });
        btnLimpiarCampos.setBounds(132, 492, 165, 23);
        contentPane.add(btnLimpiarCampos);

        restablecerBotones();
        cargarAeropuertos(); // Cargar aeropuertos al abrir la ventana
    }

    private void habilitarBotonBusqueda() {
        btnBuscarAeropuertos.setEnabled(textFieldNombre.getText().length() > 0 || textFieldCiudad.getText().length() > 0);
    }

    private void habilitarCamposParaCrear() {
        textFieldCodigo.setText("");
        textFieldNombre.setText("");
        textFieldCiudad.setText("");
        textFieldNombre.setEnabled(true);
        textFieldCiudad.setEnabled(true);
        
        btnModificarAeropuertos.setEnabled(false);
        btnEliminarAeropuertos.setEnabled(false);
        btnBuscarAeropuertos.setEnabled(false);
        btnAnyadirAeropuerto.setEnabled(true);
        btnCrearAeropuerto.setEnabled(false);
        btnVolverAtrasAeropuertos.setEnabled(true);
        listAeropuertos.setEnabled(false);
    }

    private void rellenarCamposConDatos(String aeropuertoData) {
        String[] data = aeropuertoData.split(", ");
        idSeleccionado = Integer.parseInt(data[0].split(": ")[1].trim());
        textFieldCodigo.setText(data[1].split(": ")[1]);
        textFieldNombre.setText(data[2].split(": ")[1]);
        textFieldCiudad.setText(data[3].split(": ")[1]);

        textFieldNombre.setEnabled(true);
        textFieldCiudad.setEnabled(true);
        
        btnModificarAeropuertos.setEnabled(true);
        btnEliminarAeropuertos.setEnabled(true);
        btnAnyadirAeropuerto.setEnabled(false);
        btnBuscarAeropuertos.setEnabled(false);
        btnCrearAeropuerto.setEnabled(false);
        btnVolverAtrasAeropuertos.setEnabled(true);
    }

    private void buscarAeropuertos() {
        aeropuertosModel.clear();
        List<String> aeropuertosList = aeropuertos.leerAeropuertos(textFieldNombre.getText(), textFieldCiudad.getText());
        for (String aeropuerto : aeropuertosList) {
            aeropuertosModel.addElement(aeropuerto);
        }
        listAeropuertos.setEnabled(true);
    }

    private void crearAeropuerto() {
        aeropuertos.crearAeropuerto(textFieldNombre.getText(), textFieldCiudad.getText());
        cargarAeropuertos(); // Recargar todos los aeropuertos después de crear uno nuevo
        limpiarCampos();
        restablecerBotones();
    }

    private void modificarAeropuerto() {
        try {
            aeropuertos.modificarAeropuerto(idSeleccionado, textFieldNombre.getText(), textFieldCiudad.getText());
            cargarAeropuertos(); // Recargar todos los aeropuertos después de modificar uno
            JOptionPane.showMessageDialog(this, "Aeropuerto modificado correctamente.", "Modificación exitosa", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
            restablecerBotones();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al modificar el aeropuerto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarAeropuerto() {
        try {
            aeropuertos.borrarAeropuerto(idSeleccionado);
            cargarAeropuertos(); // Recargar todos los aeropuertos después de eliminar uno
            JOptionPane.showMessageDialog(this, "Aeropuerto eliminado correctamente.", "Eliminación exitosa", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
            restablecerBotones();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar el aeropuerto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        textFieldCodigo.setText("");
        textFieldNombre.setText("");
        textFieldCiudad.setText("");
    }

    private void restablecerBotones() {
        btnModificarAeropuertos.setEnabled(false);
        btnEliminarAeropuertos.setEnabled(false);
        btnBuscarAeropuertos.setEnabled(false);
        btnAnyadirAeropuerto.setEnabled(false);        
        btnCrearAeropuerto.setEnabled(true);
        btnVolverAtrasAeropuertos.setEnabled(true);
        listAeropuertos.setEnabled(true);
    }

    private void cargarAeropuertos() {
        aeropuertosModel.clear();
        List<String> aeropuertosList = aeropuertos.leerTodosLosAeropuertos();
        for (String aeropuerto : aeropuertosList) {
            aeropuertosModel.addElement(aeropuerto);
        }
        listAeropuertos.setEnabled(true);
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GestionAeropuerto frame = new GestionAeropuerto(null);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
