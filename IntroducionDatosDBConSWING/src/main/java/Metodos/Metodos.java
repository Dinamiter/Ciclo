package Metodos;

import DTO.Alumno;
import Interfaces.IMetodos;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

import propio.introduciondatosdbconswing.FramePrincipal;

/**
 *
 * @author anton
 */
public class Metodos implements IMetodos {

    FramePrincipal principal;

    public Metodos(JFrame parent) {
        principal = (FramePrincipal) parent;
    }

    public Metodos() {
    }

    @Override
    public Connection conexion() {

        String usuario = "root", pass = "noruega80", url = "jdbc:mysql://localhost:3306/DBalumnos01";
        Connection conexion = null;
        try {

            conexion = DriverManager.getConnection(url, usuario, pass);

            if (conexion != null) {
                System.out.println("Conectando de la conexion...........");

            }

        } catch (SQLException ex) {
            System.out.println("/n/n*********Error al establecer conexion a la DB**********************************");

        }

        return conexion;
    }

    @Override
    public void nuevoAlumno() {

        try {

            String sentenciaSql = "INSERT INTO alumnos(nombre, apellido, curso, clase) VALUES (?,?,?,?)";

            try ( PreparedStatement pst = conexion().prepareStatement(sentenciaSql)) {
                pst.setString(1, principal.getNombre());
                pst.setString(2, principal.getApellido());
                pst.setInt(3, principal.getCurso());
                pst.setString(4, principal.getClase());

                pst.executeUpdate();

                principal.getInfo("Conectando...");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
                }

                principal.getInfo("Grabado!!!");

                pst.close();
                conexion().close();
            }

        } catch (SQLException e) {
            System.out.println("Error de acceso" + e.getMessage());
        }

    }

    @Override
    public Alumno buscarAlumno(int i) {

        String sql = "SELECT * FROM DBalumnos01.alumnos WHERE id=?";
        Alumno alumno = new Alumno();
        ArrayList alumnos = new ArrayList<>();

        try ( PreparedStatement selectStatement = conexion().prepareStatement(sql);) {
            selectStatement.setInt(1, i);

            ResultSet resultSet = selectStatement.executeQuery();

            while (resultSet.next()) {
                alumno.setId(resultSet.getInt(1));
                alumno.setNombre(resultSet.getString(2));
                alumno.setApellido(resultSet.getString(3));
                alumno.setCurso(resultSet.getInt(4));
                alumno.setClase(resultSet.getString(5));

                System.out.println(alumno.toString());
            }

            resultSet.close();
            conexion().close();

        } catch (SQLException ex) {
            System.out.println("error sql");

        }
        return alumno;   
    }

    @Override
    public ArrayList getAlumnos() {

        ArrayList<Alumno> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM DBalumnos01.alumnos";

        try ( PreparedStatement selectStatement = conexion().prepareStatement(sql);) {

            ResultSet resultSet = selectStatement.executeQuery();

            System.out.println(resultSet.toString());

            while (resultSet.next()) {

                Alumno usuario = new Alumno();

                usuario.setId(resultSet.getInt(1));
                usuario.setNombre(resultSet.getString(2));
                usuario.setApellido(resultSet.getString(3));
                usuario.setCurso(resultSet.getInt(4));
                usuario.setClase(resultSet.getString(5));
                usuarios.add(usuario);

            }
        } catch (SQLException ex) {
            System.out.println("error del resultset en metodos");
        }
        return usuarios;
    }

    @Override
    public void modificarAlumno(int id, String nombre, String apellido, int curso, String clase) {

        
        System.out.println(id+" "+nombre+" "+apellido+" "+curso+" "+clase);
        
        
        String cadId=Integer.toString(id) ;
        String cadCurso=Integer.toString(curso) ;
   
        String sentenciaSql = "UPDATE alumnos SET nombre=?,apellido=?,curso=?,clase=? WHERE id=?";

        try ( PreparedStatement pst = conexion().prepareStatement(sentenciaSql)) {

            
            pst.setString(1,nombre);
            pst.setString(2,apellido);
            pst.setString(3,cadCurso);
            pst.setString(4,clase);
            pst.setString(5,cadId);
           
      
            int filas=pst.executeUpdate();
            System.out.println(filas+" cambiadas en el asiento ************************");

            if (conexion() != null) {
                conexion().close();
            }
        } catch (SQLException e) {
            System.out.println("Error de acceso" + e.getMessage());
        }
    }

}


