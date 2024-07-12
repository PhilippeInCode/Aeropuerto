package accesoDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Pasajeros {

    private Connection conexion() throws SQLException {
        return DriverManager.getConnection(AccesoBBDD.URL, AccesoBBDD.USER, AccesoBBDD.PASSWORD);
    }

    private String generarCodigoAleatorio() {
        Random rand = new Random();
        return "PAS" + rand.nextInt(10000);
    }

    // Método para crear un pasajero
    public void crearPasajero(String nombre, String apellido, String edad, String dni, String sexo, String peso, String altura) {
        String codigo = generarCodigoAleatorio();
        String sql = "INSERT INTO pasajeros (codigo, nombre, apellidos, edad, dni, sexo, peso, altura) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = conexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, codigo);
            pstmt.setString(2, nombre);
            pstmt.setString(3, apellido);
            pstmt.setInt(4, Integer.parseInt(edad));
            pstmt.setString(5, dni);
            pstmt.setString(6, sexo);
            pstmt.setString(7, peso);
            pstmt.setString(8, altura);
            pstmt.executeUpdate();
            System.out.println("Pasajero creado: " + codigo);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Método para leer todos los pasajeros
    public List<String> leerPasajeros() {
        String sql = "SELECT * FROM pasajeros";
        List<String> pasajeros = new ArrayList<>();

        try (Connection conn = conexion();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                pasajeros.add("ID: " + rs.getString("id") + 
                			  ", Código: " + rs.getString("codigo") +
                              ", Nombre: " + rs.getString("nombre") +
                              ", Apellido: " + rs.getString("apellidos") +
                              ", Edad: " + rs.getString("edad") +
                              ", DNI: " + rs.getString("dni") +
                              ", Sexo: " + rs.getString("sexo") +
                              ", Peso: " + rs.getString("peso") +
                              ", Altura: " + rs.getString("altura"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return pasajeros;
    }

    // Método para leer pasajeros según criterios
    public List<String> leerPasajeros(String nombre, String apellidos, String dni) {
        String sql = "SELECT * FROM pasajeros WHERE nombre LIKE ? OR apellidos LIKE ? OR dni LIKE ?";
        List<String> pasajeros = new ArrayList<>();

        try (Connection conn = conexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + nombre + "%");
            pstmt.setString(2, "%" + apellidos + "%");
            pstmt.setString(3, "%" + dni + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                pasajeros.add("ID: " + rs.getString("id") +
                		      ", Código: " + rs.getString("codigo") +
                              ", Nombre: " + rs.getString("nombre") +
                              ", Apellido: " + rs.getString("apellidos") +
                              ", Edad: " + rs.getString("edad") +
                              ", DNI: " + rs.getString("dni") +
                              ", Sexo: " + rs.getString("sexo") +
                              ", Peso: " + rs.getString("peso") +
                              ", Altura: " + rs.getString("altura"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return pasajeros;
    }

    // Método para leer pasajeros de un vuelo específico
    public List<String> leerPasajerosDeVuelo(String codigoVuelo) {
        String sql = "SELECT p.* FROM pasajeros p " +
                     "JOIN vuelos_pasajeros vp ON p.id = vp.id_persona " +
                     "WHERE vp.codigo_vuelo = ?";
        List<String> pasajeros = new ArrayList<>();

        try (Connection conn = conexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, codigoVuelo);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                pasajeros.add("ID: " + rs.getString("id") +
                			  ", Código: " + rs.getString("codigo") +
                              ", Nombre: " + rs.getString("nombre") +
                              ", Apellido: " + rs.getString("apellidos") +
                              ", Edad: " + rs.getString("edad") +
                              ", DNI: " + rs.getString("dni") +
                              ", Sexo: " + rs.getString("sexo") +
                              ", Peso: " + rs.getString("peso") +
                              ", Altura: " + rs.getString("altura"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return pasajeros;
    }

    // Método para modificar un pasajero
    public boolean modificarPasajero(String codigo, String nombre, String apellido, String edad, String dni, String sexo, String peso, String altura) {
        String sql = "UPDATE pasajeros SET nombre = ?, apellidos = ?, edad = ?, dni = ?, sexo = ?, peso = ?, altura = ? WHERE codigo = ?";

        try (Connection conn = conexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, apellido);
            pstmt.setString(3, edad);
            pstmt.setString(4, dni);
            pstmt.setString(5, sexo);
            pstmt.setString(6, peso);
            pstmt.setString(7, altura);
            pstmt.setString(8, codigo);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    // Método para borrar un pasajero
    public boolean borrarPasajero(int id) {
        String sql = "DELETE FROM pasajeros WHERE id = ?";

        try (Connection conn = conexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    // Método para incluir un pasajero en un vuelo
    public boolean incluirPasajeroEnVuelo(int codigoPasajero, String codigoVuelo) {
        String sql = "INSERT INTO vuelos_pasajeros (codigo_vuelo, id_persona) VALUES (?, ?)";

        try (Connection conn = conexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, codigoVuelo);
            pstmt.setInt(2, codigoPasajero);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    
    
    // Método para eliminar un pasajero de un vuelo
    public boolean eliminarPasajeroDeVuelo(String codigoPasajero, String codigoVuelo) {
        String sql = "DELETE FROM vuelos_pasajeros WHERE codigo_vuelo = ? AND codigo_pasajero = ?";

        try (Connection conn = conexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, codigoVuelo);
            pstmt.setString(2, codigoPasajero);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
