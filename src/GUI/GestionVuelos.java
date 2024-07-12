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
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import accesoDatos.Vuelos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.swing.JComboBox;
import accesoDatos.Aeropuertos;
import java.awt.Color;

public class GestionVuelos extends JFrame {

    private static final long serialVersionUID = 1L;
    private PanelImagen contentPane;
    private JTextField textFieldCodigo;
    private JTextField textFieldCompleto;
    private JTextField textFieldNumPlazas;
    private JTextField textFieldDuracion;
    private JSpinner spinnerFechaSalida;
    private JSpinner spinnerFechaLlegada;
    private JList<String> listVuelos;
    private DefaultListModel<String> vuelosModel;
    private Vuelos vuelos;
    private JButton btnEliminarVuelos;
    private JButton btnBuscarVuelos;
    private JButton btnCrearVuelos;
    private JButton btnVolverAtrasVuelos;
    private JButton btnAnyadirVuelos;
    private JButton btnModificarVuelos;
    private JButton btnGestionPasajeros;
    private JComboBox<String> comboBoxOrigen, comboBoxDestino;
    private List<Integer> idAeropuertos;
    private JButton btnLimpiarCampos;

    public GestionVuelos(Gestiones gestiones) {
    	idAeropuertos = new ArrayList<Integer>();
    	
        vuelos = new Vuelos();
        setTitle("Gestor de Vuelos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1287, 733);
        contentPane = new PanelImagen("images\\img4.jpg");
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        vuelosModel = new DefaultListModel<>();
        listVuelos = new JList<>(vuelosModel);
        listVuelos.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && listVuelos.getSelectedValue() != null) {
                    rellenarCamposConDatos(listVuelos.getSelectedValue());
                    btnGestionPasajeros.setEnabled(true); // Enable the manage passengers button only when a flight is selected
                } else {
                    btnGestionPasajeros.setEnabled(false); // Disable if no flight is selected
                }
            }
        });
        
                JLabel labelListaVuelos = new JLabel("Lista de vuelos");
                labelListaVuelos.setOpaque(true);
                labelListaVuelos.setForeground(new Color(0, 0, 0));
                labelListaVuelos.setFont(new Font("Tahoma", Font.BOLD, 20));
                labelListaVuelos.setHorizontalAlignment(SwingConstants.CENTER);
                labelListaVuelos.setBounds(10, 10, 1248, 21);
                contentPane.add(labelListaVuelos);
                
        listVuelos.setBounds(10, 40, 1248, 399);
        contentPane.add(listVuelos);

        btnCrearVuelos = new JButton("Crear");
        btnCrearVuelos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                habilitarCamposParaCrear();
            }
        });
        btnCrearVuelos.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnCrearVuelos.setBounds(601, 450, 157, 56);
        contentPane.add(btnCrearVuelos);

        btnEliminarVuelos = new JButton("Eliminar");
        btnEliminarVuelos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarVuelo();
            }
        });
        btnEliminarVuelos.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnEliminarVuelos.setBounds(601, 586, 354, 56);
        contentPane.add(btnEliminarVuelos);

        btnVolverAtrasVuelos = new JButton("Volver");
        btnVolverAtrasVuelos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GestionVuelos.this.dispose();
                gestiones.setVisible(true);
            }
        });
        btnVolverAtrasVuelos.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnVolverAtrasVuelos.setBounds(26, 633, 131, 56);
        contentPane.add(btnVolverAtrasVuelos);

        btnAnyadirVuelos = new JButton("Añadir");
        btnAnyadirVuelos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                crearVuelo();
            }
        });
        btnAnyadirVuelos.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnAnyadirVuelos.setBounds(798, 449, 157, 56);
        contentPane.add(btnAnyadirVuelos);

        btnBuscarVuelos = new JButton("Buscar");
        btnBuscarVuelos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarVuelos();
            }
        });
        btnBuscarVuelos.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnBuscarVuelos.setBounds(798, 516, 157, 56);
        contentPane.add(btnBuscarVuelos);

        btnModificarVuelos = new JButton("Modificar");
        btnModificarVuelos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modificarVuelo();
            }
        });
        btnModificarVuelos.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnModificarVuelos.setBounds(601, 516, 157, 56);
        contentPane.add(btnModificarVuelos);

        JLabel labelDestino = new JLabel("Destino");
        labelDestino.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
        labelDestino.setBounds(38, 585, 70, 21);
        contentPane.add(labelDestino);

        JLabel labelOrigen = new JLabel("Origen");
        labelOrigen.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
        labelOrigen.setBounds(50, 541, 58, 22);
        contentPane.add(labelOrigen);

        textFieldCodigo = new JTextField();
        textFieldCodigo.setColumns(10);
        textFieldCodigo.setBounds(118, 449, 141, 32);
        textFieldCodigo.setEnabled(false);
        contentPane.add(textFieldCodigo);

        JLabel labelCodigo = new JLabel("Código");
        labelCodigo.setHorizontalAlignment(SwingConstants.LEFT);
        labelCodigo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
        labelCodigo.setBounds(48, 453, 67, 21);
        contentPane.add(labelCodigo);

        textFieldCompleto = new JTextField();
        textFieldCompleto.setColumns(10);
        textFieldCompleto.setBounds(118, 491, 141, 32);
        textFieldCompleto.setEnabled(false); // Campo no modificable
        contentPane.add(textFieldCompleto);

        JLabel labelCompleto = new JLabel("Completo");
        labelCompleto.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
        labelCompleto.setBounds(38, 495, 77, 21);
        contentPane.add(labelCompleto);

        // Añadir JSpinner para la fecha de salida
        SpinnerDateModel dateModelSalida = new SpinnerDateModel();
        spinnerFechaSalida = new JSpinner(dateModelSalida);
        JSpinner.DateEditor dateEditorSalida = new JSpinner.DateEditor(spinnerFechaSalida, "dd/MM/yyyy HH:mm:ss");
        spinnerFechaSalida.setEditor(dateEditorSalida);
        spinnerFechaSalida.setBounds(434, 449, 141, 32);
        contentPane.add(spinnerFechaSalida);

        JLabel labelFechaSalida = new JLabel("Fecha salida");
        labelFechaSalida.setHorizontalAlignment(SwingConstants.LEFT);
        labelFechaSalida.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
        labelFechaSalida.setBounds(328, 453, 100, 21);
        contentPane.add(labelFechaSalida);

        // Añadir JSpinner para la fecha de llegada
        SpinnerDateModel dateModelLlegada = new SpinnerDateModel();
        spinnerFechaLlegada = new JSpinner(dateModelLlegada);
        JSpinner.DateEditor dateEditorLlegada = new JSpinner.DateEditor(spinnerFechaLlegada, "dd/MM/yyyy HH:mm:ss");
        spinnerFechaLlegada.setEditor(dateEditorLlegada);
        spinnerFechaLlegada.setBounds(434, 489, 141, 32);
        contentPane.add(spinnerFechaLlegada);

        JLabel labelFechaLlegada = new JLabel("Fecha llegada");
        labelFechaLlegada.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
        labelFechaLlegada.setBounds(312, 494, 116, 22);
        contentPane.add(labelFechaLlegada);

        JLabel labelNumPlazas = new JLabel("Número de plazas");
        labelNumPlazas.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
        labelNumPlazas.setBounds(290, 577, 141, 22);
        contentPane.add(labelNumPlazas);

        textFieldNumPlazas = new JTextField();
        textFieldNumPlazas.setColumns(10);
        textFieldNumPlazas.setBounds(434, 574, 141, 32);
        contentPane.add(textFieldNumPlazas);

        textFieldDuracion = new JTextField();
        textFieldDuracion.setColumns(10);
        textFieldDuracion.setBounds(434, 532, 141, 32);
        textFieldDuracion.setEnabled(false); // Deshabilitar el campo de duración
        contentPane.add(textFieldDuracion);

        JLabel labelDuracion = new JLabel("Duración");
        labelDuracion.setHorizontalAlignment(SwingConstants.LEFT);
        labelDuracion.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
        labelDuracion.setBounds(350, 536, 81, 21);
        contentPane.add(labelDuracion);

        btnGestionPasajeros = new JButton("Gestionar pasajeros");
        btnGestionPasajeros.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GestionPasajeros gestionPasajeros = new GestionPasajeros(GestionVuelos.this, listVuelos.getSelectedValue().split(", ")[0].split(": ")[1]);
                GestionVuelos.this.setVisible(false);
                gestionPasajeros.setVisible(true);
            }
        });
        btnGestionPasajeros.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnGestionPasajeros.setBounds(1015, 635, 243, 48);
        btnGestionPasajeros.setEnabled(false); // Initially disabled
        contentPane.add(btnGestionPasajeros);

        comboBoxOrigen = new JComboBox<>();
        comboBoxOrigen.setBounds(118, 541, 146, 39);
        contentPane.add(comboBoxOrigen);

        comboBoxDestino = new JComboBox<>();
        comboBoxDestino.setBounds(118, 583, 146, 40);
        contentPane.add(comboBoxDestino);
        
        btnLimpiarCampos = new JButton("Limpiar formulario");
        btnLimpiarCampos.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                cargarVuelos();
                limpiarCampos();
                restablecerBotones();        		
        	}
        });
        btnLimpiarCampos.setBounds(402, 617, 141, 23);
        contentPane.add(btnLimpiarCampos);

        cargarAeropuertosCombo();
        restablecerBotones();
        configurarEventos();
        cargarVuelos();
    }

    private void cargarAeropuertosCombo() {
        Aeropuertos aeropuertos = new Aeropuertos();
        List<String> nombreAeropuertos = aeropuertos.leerTodosLosAeropuertos();
        for (String aeropuerto : nombreAeropuertos) {
            String nombre = aeropuerto.split(", ")[2].substring(8);
            comboBoxOrigen.addItem(nombre);
            comboBoxDestino.addItem(nombre);
            int idAeropuerto = Integer.parseInt(aeropuerto.split(", ")[0].split(": ")[1].trim());
            idAeropuertos.add(idAeropuerto);
        }
        comboBoxOrigen.setSelectedIndex(-1);
        comboBoxDestino.setSelectedIndex(-1);
    }

    private void habilitarCamposParaCrear() {
        textFieldCodigo.setText("");
        textFieldDuracion.setText("");
        textFieldNumPlazas.setText("");
        textFieldCompleto.setText("");
        spinnerFechaSalida.setValue(new Date());
        spinnerFechaLlegada.setValue(new Date());

        comboBoxOrigen.setEnabled(true);
        comboBoxDestino.setEnabled(true);
        comboBoxOrigen.setSelectedIndex(-1);
        comboBoxDestino.setSelectedIndex(-1);
        
        textFieldNumPlazas.setEnabled(true);
        spinnerFechaSalida.setEnabled(true);
        spinnerFechaLlegada.setEnabled(true);

        btnModificarVuelos.setEnabled(false);
        btnEliminarVuelos.setEnabled(false);
        btnBuscarVuelos.setEnabled(false);
        btnAnyadirVuelos.setEnabled(true);
        btnCrearVuelos.setEnabled(false);
        btnVolverAtrasVuelos.setEnabled(true);
        listVuelos.setEnabled(false);
    }

    private void rellenarCamposConDatos(String vueloData) {
        String[] data = vueloData.split(", ");
        textFieldCodigo.setText(data[0].split(": ")[1]);

        for (int i = 0; i < comboBoxOrigen.getItemCount(); i++) {
            String item;
            item = (String) comboBoxOrigen.getItemAt(i);
            if (item.equalsIgnoreCase(data[1].split(": ")[1])) {
                comboBoxOrigen.setSelectedIndex(i);
                break;
            }
        }
        for (int i = 0; i < comboBoxDestino.getItemCount(); i++) {
            String item;
            item = (String) comboBoxDestino.getItemAt(i);
            if (item.equalsIgnoreCase(data[2].split(": ")[1])) {
                comboBoxDestino.setSelectedIndex(i);
                break;
            }
        }

        try {
            spinnerFechaSalida.setValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(data[3].split(": ")[1]));
            spinnerFechaLlegada.setValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(data[4].split(": ")[1]));
            // Calculate and set the duration
            long durationInMinutes = calculateDuration((Date) spinnerFechaSalida.getValue(), (Date) spinnerFechaLlegada.getValue());
            textFieldDuracion.setText(formatDuration(durationInMinutes));
        } catch (Exception e) {
            e.printStackTrace();
        }
        textFieldNumPlazas.setText(data[6].split(": ")[1]);
        textFieldCompleto.setText(data[8].split(": ")[1]);

        comboBoxOrigen.setEnabled(true);
        comboBoxDestino.setEnabled(true);
        textFieldNumPlazas.setEnabled(true);
        spinnerFechaSalida.setEnabled(true);
        spinnerFechaLlegada.setEnabled(true);

        btnModificarVuelos.setEnabled(true);
        btnEliminarVuelos.setEnabled(true);
        btnAnyadirVuelos.setEnabled(false);
        btnBuscarVuelos.setEnabled(false);
        btnCrearVuelos.setEnabled(false);
        btnVolverAtrasVuelos.setEnabled(true);
    }

    private void buscarVuelos() {
        vuelosModel.clear();
        int id_origen = idAeropuertos.get(comboBoxOrigen.getSelectedIndex());
        int id_destino = idAeropuertos.get(comboBoxDestino.getSelectedIndex());
        List<String> vuelosList = vuelos.leerVuelos(id_origen, id_destino);
        for (String vuelo : vuelosList) {
            vuelosModel.addElement(vuelo);
        }
        listVuelos.setEnabled(true);
    }

    private void cargarVuelos() {
        vuelosModel.clear();
        List<String> vuelosList = vuelos.leerTodosLosVuelos();
        for (String vuelo : vuelosList) {
            vuelosModel.addElement(vuelo);
        }
        listVuelos.setEnabled(true);
    }

    private void crearVuelo() {
        Date fechaSalida = (Date) spinnerFechaSalida.getValue();
        Date fechaLlegada = (Date) spinnerFechaLlegada.getValue();
        long duracionEnMinutos = calculateDuration(fechaSalida, fechaLlegada);
        String formattedDuration = formatDuration(duracionEnMinutos);

        vuelos.crearVuelo(
            idAeropuertos.get(comboBoxOrigen.getSelectedIndex()),
            idAeropuertos.get(comboBoxDestino.getSelectedIndex()),
            fechaSalida,
            fechaLlegada,
            formattedDuration,
            Integer.parseInt(textFieldNumPlazas.getText())
        );
        cargarVuelos();
        limpiarCampos();
        restablecerBotones();
    }

    private void modificarVuelo() {
        try {
            String codigo = textFieldCodigo.getText();
            int id_origen = idAeropuertos.get(comboBoxOrigen.getSelectedIndex());
            int id_destino = idAeropuertos.get(comboBoxDestino.getSelectedIndex());
            Date fechaSalida = (Date) spinnerFechaSalida.getValue();
            Date fechaLlegada = (Date) spinnerFechaLlegada.getValue();
            int numPlazas = Integer.parseInt(textFieldNumPlazas.getText());

            if (vuelos.modificarVuelo(codigo, id_origen, id_destino, fechaSalida, fechaLlegada, numPlazas)) {
                JOptionPane.showMessageDialog(this, "Vuelo modificado correctamente.", "Modificación exitosa", JOptionPane.INFORMATION_MESSAGE);
                cargarVuelos();  // Recargar la lista de vuelos
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo modificar el vuelo.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, verifica que todos los campos numéricos son válidos.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al modificar el vuelo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private int safeParseInt(String value) {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            return 0;  // Return a default value or handle the exception as appropriate
        }
    }

    private void eliminarVuelo() {
        vuelos.borrarVuelo(textFieldCodigo.getText());
        cargarVuelos();
        limpiarCampos();
        restablecerBotones();
    }

    private void limpiarCampos() {
        textFieldCodigo.setText("");
        textFieldDuracion.setText("");
        textFieldNumPlazas.setText("");
        textFieldCompleto.setText("");
        spinnerFechaSalida.setValue(new Date());
        spinnerFechaLlegada.setValue(new Date());
        comboBoxOrigen.setSelectedIndex(-1);
        comboBoxDestino.setSelectedIndex(-1);
    }

    private void restablecerBotones() {
        btnModificarVuelos.setEnabled(false);
        btnEliminarVuelos.setEnabled(false);
        btnBuscarVuelos.setEnabled(false);
        btnAnyadirVuelos.setEnabled(false);
        btnCrearVuelos.setEnabled(true);
        btnVolverAtrasVuelos.setEnabled(true);
        listVuelos.setEnabled(true);
    }

    private void configurarEventos() {
        // Disable buttons initially except Cancel and Create
        btnModificarVuelos.setEnabled(false);
        btnEliminarVuelos.setEnabled(false);
        btnBuscarVuelos.setEnabled(false);
        btnAnyadirVuelos.setEnabled(false);
        btnCrearVuelos.setEnabled(true);
        btnVolverAtrasVuelos.setEnabled(true);

        // Enable search button when an item is selected in Origin or Destination
        ActionListener enableSearchListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean originSelected = comboBoxOrigen.getSelectedIndex() != -1;
                boolean destinationSelected = comboBoxDestino.getSelectedIndex() != -1;
                btnBuscarVuelos.setEnabled(btnCrearVuelos.isEnabled() && originSelected && destinationSelected);
            }
        };

        comboBoxOrigen.addActionListener(enableSearchListener);
        comboBoxDestino.addActionListener(enableSearchListener);
    }

    private long calculateDuration(Date departureDate, Date arrivalDate) {
        long diffInMillies = Math.abs(arrivalDate.getTime() - departureDate.getTime());
        return TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    private String formatDuration(long durationInMinutes) {
        long hours = durationInMinutes / 60;
        long minutes = durationInMinutes % 60;
        return String.format("%02d:%02d", hours, minutes);
    }
    
    public void refrescarListaVuelos() {
        vuelosModel.clear();
        List<String> vuelosList = vuelos.leerTodosLosVuelos();  // Asegúrate de que este método devuelve los datos actualizados.
        for (String vuelo : vuelosList) {
            vuelosModel.addElement(vuelo);
        }
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GestionVuelos frame = new GestionVuelos(null);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
