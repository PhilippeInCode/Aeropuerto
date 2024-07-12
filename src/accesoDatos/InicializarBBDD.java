package accesoDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class InicializarBBDD {
    public InicializarBBDD() {
    }	

    public boolean crearBaseDeDatosYTablas() {    	
        try (Connection connection = DriverManager.getConnection(AccesoBBDD.URL_BASE, AccesoBBDD.USER, AccesoBBDD.PASSWORD)) {
            Statement statement = connection.createStatement();
        	String consulta = "SELECT COUNT(*) AS total FROM information_schema.tables WHERE table_schema = 'aeropuerto' AND table_name = 'Aeropuertos';";
        	ResultSet rs = statement.executeQuery(consulta); 
        	rs.next();
        	if (rs.getInt("total") > 0) {
                statement.executeUpdate("USE aeropuerto");
        		return true;        		
        	}

            // Crear base de datos
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS aeropuerto");
            statement.executeUpdate("USE aeropuerto");

            // Crear tabla Aeropuertos
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Aeropuertos (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "codigo VARCHAR(255) NOT NULL," +
                    "nombre VARCHAR(255) NOT NULL," +
                    "ciudad VARCHAR(255) NOT NULL)");

            // Crear tabla Vuelos
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Vuelos (" +
                    "codigo VARCHAR(255) PRIMARY KEY," +
                    "id_origen INT NOT NULL," + // Cambiado a VARCHAR
                    "id_destino INT NOT NULL," + // Cambiado a VARCHAR
                    "fecha_salida DATETIME NOT NULL," +
                    "fecha_llegada DATETIME NOT NULL," +
                    "duracion TIME NOT NULL," +
                    "num_plazas INT NOT NULL," +
                    "num_pasajeros INT NOT NULL," +
                    "completo BOOLEAN NOT NULL," +
                    "FOREIGN KEY (id_origen) REFERENCES Aeropuertos(id) ON DELETE CASCADE," +
                    "FOREIGN KEY (id_destino) REFERENCES Aeropuertos(id) ON DELETE CASCADE)"
                    );

            // Crear tabla Pasajeros
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Pasajeros (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "codigo VARCHAR(10) NOT NULL," +
                    "nombre VARCHAR(255) NOT NULL," +
                    "apellidos VARCHAR(255) NOT NULL," +
                    "edad INT NOT NULL," +
                    "dni VARCHAR(255) NOT NULL UNIQUE," +
                    "sexo CHAR(1) NOT NULL," +
                    "peso DOUBLE," +
                    "altura DOUBLE)");

            // Crear tabla Vuelos_Pasajeros
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Vuelos_Pasajeros (" +
                    "codigo_vuelo VARCHAR(255) NOT NULL," +
                    "id_persona INT NOT NULL," +
                    "PRIMARY KEY (codigo_vuelo, id_persona)," +
                    "FOREIGN KEY (codigo_vuelo) REFERENCES Vuelos(codigo) ON DELETE CASCADE," +
                    "FOREIGN KEY (id_persona) REFERENCES Pasajeros(id) ON DELETE CASCADE)");

            // Crear tabla Aeropuertos_Vuelos
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Aeropuertos_Vuelos (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "id_aeropuerto INT NOT NULL," +
                    "codigo_vuelo VARCHAR(255) NOT NULL," +
                    "tipo CHAR(1) NOT NULL," + // 'O' para origen, 'D' para destino
                    "FOREIGN KEY (id_aeropuerto) REFERENCES Aeropuertos(id) ON DELETE CASCADE," +
                    "FOREIGN KEY (codigo_vuelo) REFERENCES Vuelos(codigo) ON DELETE CASCADE)");

            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
		return false; 
 }
}
