package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class db {
    private final String db = "NOMBRE_DB";
    private final String user = "USUARIO_DB";
    private final String password = "CONTRASEÑA_DB";
    private final String url = "jdbc:mysql://localhost:3306/" + db;
    private Connection conn = null;

    public Connection getdb() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(this.url, this.user, this.password);
        } catch(SQLException e) {
            System.err.println(e);
        } catch(ClassNotFoundException ex) {
            Logger.getLogger(db.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
}
