
package propio.introduciondatosdbconswing;

import DTO.Alumno;
import Metodos.Metodos;
import Tabla.TablaModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author anton
 */
public class JDialogTablaAlumnos extends javax.swing.JDialog {

    //static FramePrincipal principal;
    Metodos metodo=new Metodos();
    
    public JDialogTablaAlumnos(java.awt.Frame parent, boolean modal) {
        //super(parent, modal);
        initComponents();
        
        this.setSize(500, 450);
        this.getDefaultCloseOperation();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        
        inicializa();
        cargaTablaAlumnos();
    }
    
    @SuppressWarnings("unchecked")
    public void inicializa(){
    
        String camposTabla[] = {"Id", "Nombre", "Apellido","Curso","Clase"};
        DefaultTableModel modelo = new DefaultTableModel(null, camposTabla);
        TablaAlumnos.setModel(modelo);
    
    
    }
    
    public void cargaTablaAlumnos(){
        
        String camposTabla[] = {"Id", "Nombre", "Apellido","Curso","Clase"};
        DefaultTableModel modelo = new DefaultTableModel(null, camposTabla);
       //modelo.setColumnIdentifiers(camposTabla);
        TablaAlumnos.setModel(modelo);
       
        
        try{
            
            
            System.out.println(metodo.getAlumnos().toString());
        ArrayList<Alumno> usuarios = metodo.getAlumnos();
        
           
        
        
        
        
        String filas[] = new String[5];
            for (Alumno a : usuarios) {
       
                int numId=a.getId();
                filas[0] = Integer.toString(numId);
                filas[1] = a.getNombre();
                filas[2] = a.getApellido();
                int numCurso=a.getCurso();
                filas[3] = Integer.toString(numCurso);
                filas[4] = a.getClase();

                modelo.addRow(filas);
            }

            TablaAlumnos.setModel(modelo);
        }catch(Exception e){System.out.print("Error carga de tabla");}
        
    }
    
   
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        TablaAlumnos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        TablaAlumnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(TablaAlumnos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaAlumnos;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
