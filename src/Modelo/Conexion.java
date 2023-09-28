package Modelo;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {

    public static String url = "colegio.db";
    public static Connection connect;

    public static Connection conectar() throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        String urls = "jdbc:sqlite:" + url;
        try {
            connect = DriverManager.getConnection(urls);
        } catch (SQLException ex) {
            System.err.println("No se ha podido conectar a la base de datos\n" + ex.getMessage());
        }
        return connect;
    }

    public static void close() throws SQLException {
        connect.close();
    }

}
