package GUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import accesoDatos.Pasajeros;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.Color;

public class GestionPasajeros extends JFrame {

    private static final long serialVersionUID = 1L;
    private PanelImagen contentPane;
    private JTextField textFieldCodigo;
    private JTextField textFieldNombre;
    private JTextField textFieldApellidos;
    private JTextField textFieldEdad;
    private JTextField textFieldDNI;
    private JTextField textFieldSexo;
    private JTextField textFieldPeso;
    private JTextField textFieldAltura;
    private JList<String> listPasajerosExistentes;
    private JList<String> listPasajerosParaElVuelo;
    private DefaultListModel<String> pasajerosExistentesModel;
    private DefaultListModel<String> pasajerosVueloModel;
    private Pasajeros pasajeros;
    private JButton btnModificar;
    private JButton btnEliminarPasajero;
    private JButton btnBuscarPasajero;
    private JButton btnAnyadirPasajero;
    private JButton btnCrearPasajero;
    private JButton btnVolverAtrasPasajeros;
    private JLabel labelCodigoVuelo;
    private JButton btnIncluirEnVuelo;   
    private JButton btnLimpiarCampos;
    private String codigo;

    /**
     * Create the frame.
     */
    public GestionPasajeros(GestionVuelos gestionVuelos, String codigo) {
    	this.codigo = codigo;
        pasajeros = new Pasajeros();
        setTitle("Gestor de Pasajeros");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1024, 768);

        // Reemplaza JPanel con BackgroundPanel
        contentPane = new PanelImagen("images\\img5.jpg");
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        pasajerosExistentesModel = new DefaultListModel<>();
        listPasajerosExistentes = new JList<>(pasajerosExistentesModel);
        listPasajerosExistentes.addListSelectionListener(new ListSelectionListener() {
        	public void valueChanged(ListSelectionEvent e) {
        		String pasajero = listPasajerosExistentes.getSelectedValue();
        		if (pasajero == null)
        			return;
        		String[] datos_pasajeros = pasajero.split(", ");
        		List<String> aux = new ArrayList<String>();
        		for (String datos_pasajero : datos_pasajeros) {
        			aux.add(datos_pasajero.split(": ")[1]);
        		}
        		
        		textFieldCodigo.setText(aux.get(1));
        		textFieldNombre.setText(aux.get(2));
        		textFieldApellidos.setText(aux.get(3));
        		textFieldEdad.setText(aux.get(4));
        		textFieldDNI.setText(aux.get(5));
        		textFieldSexo.setText(aux.get(6));
        		textFieldPeso.setText(aux.get(7));
        		textFieldAltura.setText(aux.get(8));
        		
        		// Revisar el estado que debe estar cada botón
        		btnIncluirEnVuelo.setEnabled(true);
        		btnEliminarPasajero.setEnabled(true);
        		btnModificar.setEnabled(true);
        		btnAnyadirPasajero.setEnabled(false);
        	}
        });

        listPasajerosExistentes.setBounds(20, 46, 970, 148);
        contentPane.add(listPasajerosExistentes);

        JLabel labelPasajerosExistentes = new JLabel("Pasajeros existentes");
        labelPasajerosExistentes.setForeground(new Color(0, 0, 0));
        labelPasajerosExistentes.setFont(new Font("Tahoma", Font.BOLD, 20));
        labelPasajerosExistentes.setHorizontalAlignment(SwingConstants.CENTER);
        labelPasajerosExistentes.setBounds(20, 10, 970, 32);
        contentPane.add(labelPasajerosExistentes);

        btnIncluirEnVuelo = new JButton("Incluir en vuelo");
        btnIncluirEnVuelo.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String pasajero = listPasajerosExistentes.getSelectedValue();
        		if (pasajerosVueloModel.contains(pasajero)) {
                    JOptionPane.showMessageDialog(null, "Pasajero ya estaba inscrito a este vuelo", "Error", JOptionPane.ERROR_MESSAGE);
        		} else {
        			pasajerosVueloModel.addElement(pasajero);
        			listPasajerosParaElVuelo.setEnabled(false);

        			int id_pasajero = Integer.parseInt(pasajero.split(": ")[1].split(", ")[0]);
            		pasajeros.incluirPasajeroEnVuelo(id_pasajero, codigo);
        		}        		
        	}
        });
        
        btnIncluirEnVuelo.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnIncluirEnVuelo.setBounds(408, 205, 181, 51);
        contentPane.add(btnIncluirEnVuelo);

        btnCrearPasajero = new JButton("Crear");
        btnCrearPasajero.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                habilitarCamposParaCrear();
            }
        });
        btnCrearPasajero.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnCrearPasajero.setBounds(666, 476, 121, 59);
        contentPane.add(btnCrearPasajero);

        btnEliminarPasajero = new JButton("Eliminar");
        btnEliminarPasajero.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarPasajero();
            }
        });
        btnEliminarPasajero.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnEliminarPasajero.setBounds(666, 627, 279, 59);
        contentPane.add(btnEliminarPasajero);

        pasajerosVueloModel = new DefaultListModel<>();        
        listPasajerosParaElVuelo = new JList<>(pasajerosVueloModel);
        listPasajerosParaElVuelo.setEnabled(false);
        listPasajerosParaElVuelo.setBounds(20, 303, 970, 148);
        contentPane.add(listPasajerosParaElVuelo);

        JLabel labelPasajerosParaElVuelo = new JLabel("Pasajeros para el vuelo: " + codigo);
        labelPasajerosParaElVuelo.setHorizontalAlignment(SwingConstants.CENTER);
        labelPasajerosParaElVuelo.setFont(new Font("Tahoma", Font.BOLD, 20));
        labelPasajerosParaElVuelo.setBounds(20, 265, 970, 28);
        contentPane.add(labelPasajerosParaElVuelo);

        textFieldCodigo = new JTextField();
        textFieldCodigo.setColumns(10);
        textFieldCodigo.setBounds(117, 476, 201, 32);
        textFieldCodigo.setEnabled(false);
        contentPane.add(textFieldCodigo);

        JLabel labelCodigo = new JLabel("Codigo");
        labelCodigo.setHorizontalAlignment(SwingConstants.LEFT);
        labelCodigo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
        labelCodigo.setBounds(42, 471, 65, 36);
        contentPane.add(labelCodigo);

        btnAnyadirPasajero = new JButton("Añadir");
        btnAnyadirPasajero.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                crearPasajero();
            }
        });
        btnAnyadirPasajero.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnAnyadirPasajero.setBounds(824, 476, 121, 59);
        contentPane.add(btnAnyadirPasajero);

        btnBuscarPasajero = new JButton("Buscar");
        btnBuscarPasajero.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarPasajero();
            }
        });
        btnBuscarPasajero.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnBuscarPasajero.setBounds(666, 550, 121, 59);
        contentPane.add(btnBuscarPasajero);

        JLabel labelNombre = new JLabel("Nombre");
        labelNombre.setHorizontalAlignment(SwingConstants.LEFT);
        labelNombre.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
        labelNombre.setBounds(42, 514, 65, 36);
        contentPane.add(labelNombre);

        textFieldNombre = new JTextField();
        textFieldNombre.setColumns(10);
        textFieldNombre.setBounds(115, 519, 203, 32);
        textFieldNombre.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                habilitarBotonBusqueda();
            }
        });
        contentPane.add(textFieldNombre);

        JLabel labelApellidos = new JLabel("Apellidos");
        labelApellidos.setHorizontalAlignment(SwingConstants.LEFT);
        labelApellidos.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
        labelApellidos.setBounds(32, 557, 75, 36);
        contentPane.add(labelApellidos);

        textFieldApellidos = new JTextField();
        textFieldApellidos.setColumns(10);
        textFieldApellidos.setBounds(117, 562, 201, 32);
        textFieldApellidos.addKeyListener(new KeyAdapter() {

        	public void keyReleased(KeyEvent e) {
                habilitarBotonBusqueda();
            }
        });
        contentPane.add(textFieldApellidos);

        JLabel labelEdad = new JLabel("Edad");
        labelEdad.setHorizontalAlignment(SwingConstants.LEFT);
        labelEdad.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
        labelEdad.setBounds(57, 603, 50, 36);
        contentPane.add(labelEdad);

        textFieldEdad = new JTextField();
        textFieldEdad.setColumns(10);
        textFieldEdad.setBounds(115, 608, 203, 32);
        contentPane.add(textFieldEdad);

        JLabel labelDNI = new JLabel("DNI");
        labelDNI.setHorizontalAlignment(SwingConstants.LEFT);
        labelDNI.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
        labelDNI.setBounds(356, 471, 42, 36);
        contentPane.add(labelDNI);

        textFieldDNI = new JTextField();
        textFieldDNI.setColumns(10);
        textFieldDNI.setBounds(408, 476, 201, 32);
        contentPane.add(textFieldDNI);

        textFieldSexo = new JTextField();
        textFieldSexo.setColumns(10);
        textFieldSexo.setBounds(408, 520, 201, 32);
        contentPane.add(textFieldSexo);

        JLabel labelSexo = new JLabel("Sexo");
        labelSexo.setHorizontalAlignment(SwingConstants.LEFT);
        labelSexo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
        labelSexo.setBounds(348, 515, 50, 36);
        contentPane.add(labelSexo);

        textFieldPeso = new JTextField();
        textFieldPeso.setColumns(10);
        textFieldPeso.setBounds(408, 563, 201, 32);
        contentPane.add(textFieldPeso);

        JLabel labelPeso = new JLabel("Peso");
        labelPeso.setHorizontalAlignment(SwingConstants.LEFT);
        labelPeso.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
        labelPeso.setBounds(358, 558, 40, 36);
        contentPane.add(labelPeso);

        textFieldAltura = new JTextField();
        textFieldAltura.setColumns(10);
        textFieldAltura.setBounds(408, 609, 201, 32);
        contentPane.add(textFieldAltura);

        JLabel labelAltura = new JLabel("Altura");
        labelAltura.setHorizontalAlignment(SwingConstants.LEFT);
        labelAltura.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
        labelAltura.setBounds(348, 604, 50, 36);
        contentPane.add(labelAltura);

        btnVolverAtrasPasajeros = new JButton("Volver");
        GestionPasajeros gestionPasajeros = this;
        btnVolverAtrasPasajeros.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gestionPasajeros.dispose();
                gestionVuelos.setVisible(true);
            }
        });
        btnVolverAtrasPasajeros.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnVolverAtrasPasajeros.setBounds(10, 661, 139, 59);
        contentPane.add(btnVolverAtrasPasajeros);

        btnModificar = new JButton("Modificar");
        btnModificar.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnModificar.setBounds(824, 545, 121, 64);
        btnModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modificarPasajero();
            }
        });
        contentPane.add(btnModificar);

        labelCodigoVuelo = new JLabel();
        labelCodigoVuelo.setBounds(417, 600, 244, 36);
        contentPane.add(labelCodigoVuelo);
        
        btnLimpiarCampos = new JButton("Limpiar formulario");
        btnLimpiarCampos.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                limpiarCampos();
                restablecerBotones();
                listPasajerosExistentes.setEnabled(true);
        	}
        });
        btnLimpiarCampos.setBounds(426, 663, 162, 23);
        contentPane.add(btnLimpiarCampos);

        restablecerBotones();
        cargarPasajeros();
    }

    private void habilitarBotonBusqueda() {
        btnBuscarPasajero.setEnabled(
            !textFieldNombre.getText().isEmpty() || 
            !textFieldApellidos.getText().isEmpty() || 
            !textFieldDNI.getText().isEmpty()
        );
    }

    private void restablecerBotones() {
        btnModificar.setEnabled(false);
        btnEliminarPasajero.setEnabled(false);
        btnBuscarPasajero.setEnabled(false);
        btnAnyadirPasajero.setEnabled(false);
        btnCrearPasajero.setEnabled(true);
        btnVolverAtrasPasajeros.setEnabled(true);
        btnIncluirEnVuelo.setEnabled(false);
    }

    private void habilitarCamposParaCrear() {
        textFieldCodigo.setText("");
        textFieldNombre.setText("");
        textFieldApellidos.setText("");
        textFieldEdad.setText("");
        textFieldDNI.setText("");
        textFieldSexo.setText("");
        textFieldPeso.setText("");
        textFieldAltura.setText("");

        textFieldNombre.setEnabled(true);
        textFieldApellidos.setEnabled(true);
        textFieldEdad.setEnabled(true);
        textFieldDNI.setEnabled(true);
        textFieldSexo.setEnabled(true);
        textFieldPeso.setEnabled(true);
        textFieldAltura.setEnabled(true);

        btnModificar.setEnabled(false);
        btnEliminarPasajero.setEnabled(false);
        btnBuscarPasajero.setEnabled(false);
        btnAnyadirPasajero.setEnabled(true);
        btnCrearPasajero.setEnabled(false);
        btnVolverAtrasPasajeros.setEnabled(true);
        btnIncluirEnVuelo.setEnabled(false);
        listPasajerosExistentes.setEnabled(false);
    }

    private void cargarPasajeros() {
        pasajerosExistentesModel.clear();
        List<String> pasajerosList = pasajeros.leerPasajeros();
        for (String pasajero : pasajerosList) {
            pasajerosExistentesModel.addElement(pasajero);
        }
        listPasajerosExistentes.setEnabled(true);
        
        pasajerosVueloModel.clear();
        pasajerosList = pasajeros.leerPasajerosDeVuelo(codigo);
        for (String pasajero : pasajerosList) {
            pasajerosVueloModel.addElement(pasajero);
        }
}

    private void buscarPasajero() {
        pasajerosExistentesModel.clear();
        List<String> pasajerosList = pasajeros.leerPasajeros(
            textFieldNombre.getText(), 
            textFieldApellidos.getText(), 
            textFieldDNI.getText()
        );
        for (String pasajero : pasajerosList) {
            pasajerosExistentesModel.addElement(pasajero);
        }
        listPasajerosExistentes.setEnabled(true);
    }

    private void crearPasajero() {
    	if (esDNIValido(textFieldDNI.getText())) {
	        pasajeros.crearPasajero(
	            textFieldNombre.getText(),
	            textFieldApellidos.getText(),
	            textFieldEdad.getText(),  
	            textFieldDNI.getText(),             
	            textFieldSexo.getText(),
	            textFieldPeso.getText(), 
	            textFieldAltura.getText() 
	        );
	        cargarPasajeros();
	        limpiarCampos();
	        restablecerBotones();
    	} else {
    		JOptionPane.showMessageDialog(null, "El DNI introducido no es válido", "Error", JOptionPane.ERROR_MESSAGE);    		
    	}
    }

    private void modificarPasajero() {
        pasajeros.modificarPasajero(
            textFieldCodigo.getText(),
            textFieldNombre.getText(),
            textFieldApellidos.getText(),
            textFieldEdad.getText(),  
            textFieldDNI.getText(),  
            textFieldSexo.getText(),
            textFieldPeso.getText(),  
            textFieldAltura.getText() 
        );
        cargarPasajeros();
        limpiarCampos();
        restablecerBotones();
    }

    private void eliminarPasajero() {
    	String pasajero = listPasajerosExistentes.getSelectedValue();
    	int id_pasajero = Integer.parseInt(pasajero.split(": ")[1].split(", ")[0]);
        pasajeros.borrarPasajero(id_pasajero);
        cargarPasajeros();
        limpiarCampos();
        restablecerBotones();
    }

    private void limpiarCampos() {
        textFieldCodigo.setText("");
        textFieldNombre.setText("");
        textFieldApellidos.setText("");
        textFieldEdad.setText("");
        textFieldDNI.setText("");
        textFieldSexo.setText("");
        textFieldPeso.setText("");
        textFieldAltura.setText("");
    }
    
    public boolean esDNIValido(String dni) {
        // Expresión regular para verificar el formato y que la letra esté en mayúscula
        String dniPattern = "^[0-9]{8}[A-Z]$";
        Pattern pattern = Pattern.compile(dniPattern);
        Matcher matcher = pattern.matcher(dni);

        if (!matcher.matches()) {
            return false; // El formato no es correcto
        }

        // Obtener los números del DNI
        int numero;
        try {
            String numeroStr = dni.substring(0, 8); // Tomamos los primeros 8 caracteres
            numero = Integer.parseInt(numeroStr); // Convertimos a entero
        } catch (NumberFormatException e) {
            return false; // Si no se puede convertir a número, el DNI no es válido
        }

        // Array de letras posibles en el DNI
        char[] letras = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};

        // Calcular la letra correspondiente
        int indiceLetra = numero % 23; // Calculamos el módulo para obtener el índice de la letra
        char letraEsperada = letras[indiceLetra];

        // Imprimir la letra esperada en la consola
        System.out.println("Letra esperada: " + letraEsperada);

        // Obtener la letra del DNI ingresado
        char letra = dni.charAt(8); // La letra ya se valida que sea mayúscula por la expresión regular

        // Verificar si la letra es correcta
        return letra == letraEsperada;
    }
   
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GestionPasajeros frame = new GestionPasajeros(null, null);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
