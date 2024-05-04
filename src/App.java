import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class App {
    private static Connection conn;
    private static CallableStatement cstm;
    private static ResultSet rs;

    public static void main(String[] args) throws Exception {

        System.out.println("Hello, World!");
        conn = Conn.getConnection();

        try {
            getListUsers();
        } finally {
            closeResources();
        }
    }

    public static void getListUsers() throws Exception {
        cstm = conn.prepareCall("SELECT * FROM tbl_usuarios");
        rs = cstm.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("pk_id_usuario");
            String name = rs.getString("nombre_usuario");
            System.out.println("User ID: " + id + ", Name: " + name);
        }
    }

    private static void closeResources() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (cstm != null) {
            cstm.close();
        }
        if (conn != null) {
            conn.close();
        }
    }
}
