package Modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Principal {

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        Estudiante est = new Estudiante();
        Profesor pro = new Profesor();
        Curso cur = new Curso();
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String nombre, correo, subs;
        int edad, id, len, opc, idprof, idcur, idest;
        ResultSet rs1, rs2, rs3;

        for (int i = 0; i < 3; i++) {

            id = Integer.valueOf(input.readLine());

            nombre = input.readLine();
            pro.setNombre(nombre);

            correo = input.readLine();
            pro.setCorreo(correo);

            edad = Integer.valueOf(input.readLine());
            pro.setEdad(edad);
            pro.salvar();

        }

        for (int i = 0; i < 5; i++) {

            id = Integer.valueOf(input.readLine());

            nombre = input.readLine();
            est.setNombre(nombre);

            correo = input.readLine();
            est.setCorreo(correo);

            edad = Integer.valueOf(input.readLine());
            est.setEdad(edad);
            est.salvar();
        }

        for (int i = 0; i < 4; i++) {

            id = Integer.valueOf(input.readLine());

            nombre = input.readLine();
            cur.setNombre(nombre);

            len = Integer.valueOf(input.readLine());
            cur.setCapacidad(len);
            cur.salvar();
        }

        for (int i = 0; i < 16; i++) {
            opc = Integer.valueOf(input.readLine());
            if (opc == 1) {
                subs = input.readLine();
                idprof = Integer.valueOf(subs.substring(0, 1));
                idcur = Integer.valueOf(subs.substring(4));
                cur.asignarProfesor(idprof, idcur);
            } else {
                subs = input.readLine();
                idest = Integer.valueOf(subs.substring(0, 1));
                idcur = Integer.valueOf(subs.substring(4));
                est.asignarCurso(idest, idcur);
            }

        }

        idprof = Integer.valueOf(input.readLine());
        idest = Integer.valueOf(input.readLine());
        idcur = Integer.valueOf(input.readLine());
        rs1 = cur.findCursobyProfesor(idprof);
        rs2 = est.findEstudiantebyProfesor(idest);
        rs3 = est.findEstudiantebyCurso(idcur);

        while (rs1.next()) {
            System.out.println("Curso: " + rs1.getInt("id") + " - " + rs1.getString("nombre") + " - " + rs1.getInt("capacidad"));
        }

        while (rs2.next()) {
            System.out.println("Estudiante: " + rs2.getInt("id") + " - " + rs2.getString("nombre") + " - " + rs2.getInt("edad"));
        }

        while (rs3.next()) {
            System.out.println("Estudiante: " + rs3.getInt("id") + " - " + rs3.getString("nombre") + " - " + rs3.getInt("edad"));
        }

    }

}
