package Modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Curso {
    
    private long id;
    private String nombre;
    private int capacidad;
    private int profesor;

    public Curso(long id, String nombre, int capacidad, int profesor) {
        this.id = id;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.profesor = profesor;
    }

    public Curso() {
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

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getProfesor() {
        return profesor;
    }

    public void setProfesor(int profesor) {
        this.profesor = profesor;
    }


    public void salvar() throws ClassNotFoundException, SQLException{
        Connection con=Conexion.conectar();
        String sql="INSERT INTO cursos VALUES (null, ?,?,?)";
        PreparedStatement sp = con.prepareStatement(sql);
        sp.setString(1, nombre);
        sp.setInt(2, capacidad);
        sp.setInt(3, profesor);
        sp.executeUpdate();
        con.close(); 
    }
    
    public void actualizar() throws ClassNotFoundException, SQLException {
        Connection con = Conexion.conectar();
        String sql = "UPDATE cursos SET nombre=?, capacidad=?, profesor=? WHERE id=?";
        PreparedStatement sp = con.prepareStatement(sql);
        sp.setString(1, nombre);
        sp.setInt(2, capacidad);
        sp.setInt(3, profesor);
        sp.setLong(4, id);
        sp.executeUpdate();
    }
    
    public void eliminar() throws ClassNotFoundException, SQLException {
        Connection con = Conexion.conectar();
        String sql = "DELETE FROM cursos WHERE id=?";
        PreparedStatement sp = con.prepareStatement(sql);
        sp.setLong(1, id);
        sp.executeUpdate();
    }
    
    public void asignarProfesor(int idProfesor,int idCurso) throws ClassNotFoundException, SQLException{
        Connection con=Conexion.conectar();
        String sql="UPDATE cursos SET profesor=? WHERE id=?";
        PreparedStatement sp = con.prepareStatement(sql);
        sp.setInt(1, idProfesor);
        sp.setInt(2, idCurso);
        sp.executeUpdate();
        con.close(); 
    }
    
    
    public ResultSet findCursobyProfesor(int idProfesor) throws ClassNotFoundException, SQLException{
        ResultSet rs;
        Connection con=Conexion.conectar();
        String sql="SELECT * FROM cursos WHERE profesor=?";
        PreparedStatement sp = con.prepareStatement(sql);
        sp.setInt(1, idProfesor);
        rs = sp.executeQuery();
        return rs;
    }
    
    
     public ResultSet findAll() throws ClassNotFoundException, SQLException {
        ResultSet rs;
        Connection con = Conexion.conectar();
        String sql = "SELECT * FROM cursos";
        PreparedStatement sp = con.prepareStatement(sql);
        rs = sp.executeQuery();
        return rs;
    }
   
   
   public ResultSet findAllbyId(int id) throws ClassNotFoundException, SQLException {
        ResultSet rs;
        Connection con = Conexion.conectar();
        String sql = "SELECT cur.id as id, cur.nombre as nombre, cur.capacidad as capacidad, cur.profesor as profesor, pro.nombre as nombreprofesor FROM cursos cur INNER JOIN profesores pro ON cur.profesor=pro.id WHERE cur.id=?";
        PreparedStatement sp = con.prepareStatement(sql);
        sp.setInt(1,id);
        rs = sp.executeQuery();
        return rs;
    }
    
}
