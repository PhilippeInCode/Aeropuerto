package accesoDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Date;

public class Vuelos {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(AccesoBBDD.URL, AccesoBBDD.USER, AccesoBBDD.PASSWORD);
    }

    private String generarCodigoAleatorio() {
        Random rand = new Random();
        return "VUE" + rand.nextInt(10000);
    }

    // Método para crear un vuelo
    public void crearVuelo(int origen, int destino, Date fechaSalida, Date fechaLlegada, String duracion, int numPlazas) {
        String codigo = generarCodigoAleatorio();
        String sql = "INSERT INTO Vuelos (codigo, id_origen, id_destino, fecha_salida, fecha_llegada, duracion, num_plazas, num_pasajeros, completo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, codigo);
            pstmt.setInt(2, origen);
            pstmt.setInt(3, destino);
            pstmt.setString(4, DATE_FORMAT.format(fechaSalida));
            pstmt.setString(5, DATE_FORMAT.format(fechaLlegada));
            pstmt.setString(6, duracion);
            pstmt.setInt(7, numPlazas);
            pstmt.setInt(8, 0); // Número de pasajeros inicial
            pstmt.setBoolean(9, false); // Completo inicialmente es falso
            pstmt.executeUpdate();
            System.out.println("Vuelo creado: " + codigo);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Método para leer vuelos
    public List<String> leerVuelos(int id_origen, int id_destino) {
        String sql = "SELECT * FROM Vuelos WHERE id_origen = ? OR id_destino = ?";       
        List<String> vuelos = new ArrayList<>();

        try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id_origen);
            pstmt.setInt(2, id_destino);
            ResultSet rs = pstmt.executeQuery();
            
            

            while (rs.next()) {
                String consulta = "SELECT * FROM Aeropuertos WHERE id = " + rs.getInt("id_origen");
                Statement statement = conn.createStatement();
                ResultSet rs_consulta = statement.executeQuery(consulta); 
            	rs_consulta.next();
            	String origen = rs_consulta.getString("nombre");

                consulta = "SELECT * FROM Aeropuertos WHERE id = " + rs.getInt("id_destino");
                statement = conn.createStatement();
                rs_consulta = statement.executeQuery(consulta); 
            	rs_consulta.next();
            	String destino = rs_consulta.getString("nombre");
            	
            	vuelos.add("Código: " + rs.getString("codigo") +
                           ", Origen: " + origen +
                           ", Destino: " + destino +
                           ", Fecha Salida: " + rs.getString("fecha_salida") +
                           ", Fecha Llegada: " + rs.getString("fecha_llegada") +
                           ", Duración: " + rs.getString("duracion") +
                           ", Plazas: " + rs.getInt("num_plazas") +
                           ", Pasajeros: " + rs.getInt("num_pasajeros") +
                           ", Completo: " + (rs.getBoolean("completo") ? "S" : "N"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return vuelos;
    }

    // Método para leer todos los vuelos
    public List<String> leerTodosLosVuelos() {
        String sql = "SELECT * FROM Vuelos";
        List<String> vuelos = new ArrayList<>();

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String consulta = "SELECT * FROM Aeropuertos WHERE id = " + rs.getInt("id_origen");
                Statement statement = conn.createStatement();
                ResultSet rs_consulta = statement.executeQuery(consulta); 
            	rs_consulta.next();
            	String origen = rs_consulta.getString("nombre");

                consulta = "SELECT * FROM Aeropuertos WHERE id = " + rs.getInt("id_destino");
                statement = conn.createStatement();
                rs_consulta = statement.executeQuery(consulta); 
            	rs_consulta.next();
            	String destino = rs_consulta.getString("nombre");
            	
                vuelos.add("Código: " + rs.getString("codigo") +
                           ", Origen: " + origen +
                           ", Destino: " + destino +
                           ", Fecha Salida: " + rs.getString("fecha_salida") +
                           ", Fecha Llegada: " + rs.getString("fecha_llegada") +
                           ", Duración: " + rs.getString("duracion") +
                           ", Plazas: " + rs.getInt("num_plazas") +
                           ", Pasajeros: " + rs.getInt("num_pasajeros") +
                           ", Completo: " + (rs.getBoolean("completo") ? "S" : "N"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return vuelos;
    }

    // Método para modificar un vuelo
    public boolean modificarVuelo(String codigo, int id_origen, int id_destino, Date fechaSalida, Date fechaLlegada, int numPlazas) {
        String sql = "UPDATE Vuelos SET id_origen = ?, id_destino = ?, fecha_salida = ?, fecha_llegada = ?, num_plazas = ? WHERE codigo = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id_origen);
            pstmt.setInt(2, id_destino);
            pstmt.setString(3, DATE_FORMAT.format(fechaSalida));
            pstmt.setString(4, DATE_FORMAT.format(fechaLlegada));
            pstmt.setInt(5, numPlazas);
            pstmt.setString(6, codigo);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("Error al modificar el vuelo: " + e.getMessage());
            return false;
        }
    }
    

    // Método para borrar un vuelo
    public boolean borrarVuelo(String codigo) {
        String sql = "DELETE FROM Vuelos WHERE codigo = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, codigo);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
