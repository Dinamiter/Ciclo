/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tabla;

import DTO.Alumno;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author anton
 */
public class TablaModel extends AbstractTableModel {

    List<Alumno> listaAlumnos;
    String[]columnas={"Id","Nombre","Apellido","Curso","Clase"};
    
  
    public TablaModel(List<Alumno> listaDeAlumnos){
        this.listaAlumnos=listaDeAlumnos;
    }

    
    
    
    @Override
    public int getRowCount() {
        return this.listaAlumnos.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       
        switch (columnIndex) {
            case 0 -> {
                return listaAlumnos.get(rowIndex).getId();
            }

            case 1 -> {
                return listaAlumnos.get(rowIndex).getNombre();
            }
            case 2 -> {
                return listaAlumnos.get(rowIndex).getApellido();
            }
            case 3 -> {
                return listaAlumnos.get(rowIndex).getCurso();
            }
            case 4->{
                return listaAlumnos.get(rowIndex).getClase();
            }
        }
        return null;

    }
    
    @Override
    public String getColumnName(int columna) {
        return columnas[columna];
    }
    
}
