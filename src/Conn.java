
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conn {
    public static Connection getConnection() throws SQLException {
        Connection conn;
        String host;
        String user;
        String password;
        String database;
        Integer port;
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(".env"));
            host = prop.getProperty("POSTGRES_SERVER");
            user = prop.getProperty("POSTGRES_USER");
            password = prop.getProperty("POSTGRES_PASSWORD");
            database = prop.getProperty("POSTGRES_DB");
            port = Integer.parseInt(prop.getProperty("POSTGRES_PORT"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("No se pudo encontrar el archivo config.properties", e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Ocurrió un error al leer el archivo config.properties", e);
        }

        String url = String.format("jdbc:postgresql://%s:%s/%s", host, port, database);

        // Conexión a la base de datos
        try {
            // Validacion del driver
            Class.forName("org.postgresql.Driver");

            System.out.println("------------------");
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión a la base de datos PostgreSQL establecida correctamente.");
            return conn;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Error al establecer la conexión a la base de datos PostgreSQL.");
            return null;
        }
    }
}