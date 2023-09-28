package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Estudiante {

    private long id;
    private String nombre;
    private String celular;
    private String correo;
    private int edad;
    private ArrayList<Curso> Cursos;

    public Estudiante() {
    }

    public Estudiante(long id, String nombre, String celular, String correo, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.celular = celular;
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

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public ArrayList<Curso> getCursos() {
        return Cursos;
    }

    public void setCursos(ArrayList<Curso> Cursos) {
        this.Cursos = Cursos;
    }

    public void salvar() throws ClassNotFoundException, SQLException {
        Connection con = Conexion.conectar();
        String sql = "INSERT INTO estudiantes VALUES (null, ?,?,?,?)";
        PreparedStatement sp = con.prepareStatement(sql);
        sp.setString(1, nombre);
        sp.setString(2, celular);
        sp.setInt(3, edad);
        sp.setString(4, correo);
        sp.executeUpdate();
        con.close();
    }

    public void actualizar() throws ClassNotFoundException, SQLException {
        Connection con = Conexion.conectar();
        String sql = "UPDATE estudiantes SET nombre=?, celular=?, edad=?, correo=? WHERE id=?";
        PreparedStatement sp = con.prepareStatement(sql);
        sp.setString(1, nombre);
        sp.setString(2, celular);
        sp.setInt(3, edad);
        sp.setString(4, correo);
        sp.setLong(5, id);
        sp.executeUpdate();
    }
    
    public void eliminar() throws ClassNotFoundException, SQLException {
        Connection con = Conexion.conectar();
        String sql = "DELETE FROM estudiantes WHERE id=?";
        PreparedStatement sp = con.prepareStatement(sql);
        sp.setLong(1, id);
        sp.executeUpdate();
    }
    
    
    public void asignarCurso(int idestudiante, int idcurso) throws ClassNotFoundException, SQLException {
        Connection con = Conexion.conectar();
        String sql = "INSERT INTO cursoestudiante VALUES (null, ?,?)";
        PreparedStatement sp = con.prepareStatement(sql);
        sp.setInt(1, idestudiante);
        sp.setInt(2, idcurso);
        sp.executeUpdate();
    }

    public ResultSet findEstudiantebyProfesor(int idProfesor) throws ClassNotFoundException, SQLException {
        ResultSet rs;
        Connection con = Conexion.conectar();
        String sql = "SELECT DISTINCT est.id, est.nombre, est.edad FROM estudiantes est \n"
                + "INNER JOIN cursoestudiante cure ON est.id = cure.idestudiante\n"
                + "INNER JOIN cursos cur ON cure.idcurso = cur.id\n"
                + "INNER JOIN profesores prof ON cur.profesor = prof.id WHERE prof.id=?";
        PreparedStatement sp = con.prepareStatement(sql);
        sp.setInt(1, idProfesor);
        rs = sp.executeQuery();
        return rs;
    }

    public ResultSet findEstudiantebyCurso(int idCurso) throws ClassNotFoundException, SQLException {
        ResultSet rs;
        Connection con = Conexion.conectar();
        String sql = "SELECT DISTINCT est.id, est.nombre, est.edad FROM estudiantes est \n"
                + "INNER JOIN cursoestudiante cure ON est.id = cure.idestudiante\n"
                + "INNER JOIN cursos cur ON cure.idcurso = cur.id WHERE cur.id=?";
        PreparedStatement sp = con.prepareStatement(sql);
        sp.setInt(1, idCurso);
        rs = sp.executeQuery();
        return rs;
    }
    
   public ResultSet findAll() throws ClassNotFoundException, SQLException {
        
        Connection con = Conexion.conectar();
        String sql = "SELECT * FROM estudiantes";
        PreparedStatement sp = con.prepareStatement(sql);
        ResultSet rs = sp.executeQuery();
        return rs;
    }
   
   public ResultSet findAllbyId(int id) throws ClassNotFoundException, SQLException {
        ResultSet rs;
        Connection con = Conexion.conectar();
        String sql = "SELECT * FROM estudiantes WHERE id=?";
        PreparedStatement sp = con.prepareStatement(sql);
        sp.setInt(1,id);
        rs = sp.executeQuery();
        return rs;
    }

}
