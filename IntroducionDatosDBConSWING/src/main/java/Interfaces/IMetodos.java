/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import DTO.Alumno;
import java.sql.Connection;
import java.util.ArrayList;


/**
 *
 * @author ortbra
 */
public interface IMetodos {
    
    public Connection conexion();
    public void nuevoAlumno();
    public Alumno buscarAlumno(int i);
    public ArrayList getAlumnos();
    public void modificarAlumno(int id, String nombre,String apellido, int curso, String clase);
   
}
