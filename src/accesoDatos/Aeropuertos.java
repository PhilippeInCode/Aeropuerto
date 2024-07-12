package accesoDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Aeropuertos {

    public void crearAeropuerto(String nombre, String ciudad) {
        String codigo = generarCodigoUnico(); // Generar código único
        try (Connection connection = DriverManager.getConnection(AccesoBBDD.URL, AccesoBBDD.USER, AccesoBBDD.PASSWORD)) {
            String query = "INSERT INTO Aeropuertos (codigo, nombre, ciudad) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, codigo);
                statement.setString(2, nombre);
                statement.setString(3, ciudad);
                statement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> leerAeropuertos(String nombre, String ciudad) {
        List<String> aeropuertosList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(AccesoBBDD.URL, AccesoBBDD.USER, AccesoBBDD.PASSWORD)) {
        	String query; 
        	if (ciudad.length() == 0) {
        		query = "SELECT id, codigo, nombre, ciudad FROM Aeropuertos WHERE nombre LIKE ?";
        	} else {
        		if (nombre.length() == 0) {
        			query = "SELECT id, codigo, nombre, ciudad FROM Aeropuertos WHERE ciudad LIKE ?";
        		} else {
        			query = "SELECT id, codigo, nombre, ciudad FROM Aeropuertos WHERE nombre LIKE ? OR ciudad LIKE ?";
        		}
        	}
            try (PreparedStatement statement = connection.prepareStatement(query)) {
            	if (ciudad.length() == 0) {
            		statement.setString(1, "%" + nombre + "%");
            	} else {
            		if (nombre.length() == 0) {
            			statement.setString(1, "%" + ciudad + "%");
            		} else {
            			statement.setString(1, "%" + nombre + "%");
            			statement.setString(2, "%" + ciudad + "%");
            		}
            	}
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String codigo = resultSet.getString("codigo");
                        String aeropuertoNombre = resultSet.getString("nombre");
                        String aeropuertoCiudad = resultSet.getString("ciudad");
                        aeropuertosList.add("ID: " + id + ", Código: " + codigo + ", Nombre: " + aeropuertoNombre + ", Ciudad: " + aeropuertoCiudad);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aeropuertosList;
    }

    public List<String> leerTodosLosAeropuertos() {
        List<String> aeropuertosList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(AccesoBBDD.URL, AccesoBBDD.USER, AccesoBBDD.PASSWORD)) {
            String query = "SELECT id, codigo, nombre, ciudad FROM Aeropuertos";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String codigo = resultSet.getString("codigo");
                    String nombre = resultSet.getString("nombre");
                    String ciudad = resultSet.getString("ciudad");
                    aeropuertosList.add("ID: " + id + ", Código: " + codigo + ", Nombre: " + nombre + ", Ciudad: " + ciudad);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aeropuertosList;
    }

    public void modificarAeropuerto(int id, String nombre, String ciudad) {
        try (Connection connection = DriverManager.getConnection(AccesoBBDD.URL, AccesoBBDD.USER, AccesoBBDD.PASSWORD)) {
            String query = "UPDATE Aeropuertos SET nombre = ?, ciudad = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, nombre);
                statement.setString(2, ciudad);
                statement.setInt(3, id);
                statement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void borrarAeropuerto(int id) {
        try (Connection connection = DriverManager.getConnection(AccesoBBDD.URL, AccesoBBDD.USER, AccesoBBDD.PASSWORD)) {
            connection.setAutoCommit(false);
            
/*            // Eliminar vuelos asociados al aeropuerto
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM Vuelos WHERE origen = ? OR destino = ?")) {
                statement.setInt(1, id);
                statement.setInt(2, id);
                statement.executeUpdate();
            }
*/
            // Eliminar el aeropuerto
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM Aeropuertos WHERE id = ?")) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }

            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String generarCodigoUnico() {
        String prefix = "AER";
        Random random = new Random();
        int number = 1000 + random.nextInt(9000); // Generar un número entre 1000 y 9999
        return prefix + number;
    }
}
