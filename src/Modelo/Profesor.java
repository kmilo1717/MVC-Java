package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class Profesor {
    private long id;
    private String nombre;
    private String correo;
    private long edad;

    public Profesor() {
    }

    public Profesor(long id, String nombre, String correo, long edad) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.edad = edad;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public long getEdad() {
        return edad;
    }

    public void setEdad(long edad) {
        this.edad = edad;
    }
    
    public void salvar() throws ClassNotFoundException, SQLException{
        Connection con=Conexion.conectar();
        String sql="INSERT INTO profesores VALUES (null, ?,?,?)";
        PreparedStatement sp = con.prepareStatement(sql);
        sp.setString(1, nombre);
        sp.setString(2, correo);
        sp.setLong(3, edad);
        sp.executeUpdate();
        con.close(); 
    }
    
    public void actualizar() throws ClassNotFoundException, SQLException {
        Connection con = Conexion.conectar();
        String sql = "UPDATE profesores SET nombre=?, correo=?, edad=? WHERE id=?";
        PreparedStatement sp = con.prepareStatement(sql);
        sp.setString(1, nombre);
        sp.setString(2, correo);
        sp.setLong(3, edad);
        sp.setLong(4, id);
        sp.executeUpdate();
    }
    
    public void eliminar() throws ClassNotFoundException, SQLException {
        Connection con = Conexion.conectar();
        String sql = "DELETE FROM profesores WHERE id=?";
        PreparedStatement sp = con.prepareStatement(sql);
        sp.setLong(1, id);
        sp.executeUpdate();
    }
    
     public ResultSet findAll() throws ClassNotFoundException, SQLException {
        ResultSet rs;
        Connection con = Conexion.conectar();
        String sql = "SELECT * FROM profesores";
        PreparedStatement sp = con.prepareStatement(sql);
        rs = sp.executeQuery();
        return rs;
    }
     public ResultSet findAllbyId(int id) throws ClassNotFoundException, SQLException {
        ResultSet rs;
        Connection con = Conexion.conectar();
        String sql = "SELECT * FROM profesores WHERE id=?";
        PreparedStatement sp = con.prepareStatement(sql);
        sp.setInt(1,id);
        rs = sp.executeQuery();
        return rs;
    }
            
    
}