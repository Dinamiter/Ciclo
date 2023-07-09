
package propio.introduciondatosdbconswing;

import Metodos.Metodos;

/**
 *
 * @author anton
 */
public class JDialogModificar extends javax.swing.JDialog {

    Metodos metodo;
    int id, curso;
    String nombre, apellido, clase;
    
    
    public JDialogModificar(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        this.setSize(400, 500);
        this.getDefaultCloseOperation();
        this.setVisible(true);
        this.setLocationRelativeTo(null); 
        
        
        
        
        
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        EtiquetaInfo = new javax.swing.JLabel();
        campoRecogerID = new javax.swing.JTextField();
        etiquetaDatosAlumno = new javax.swing.JLabel();
        etiquetaDatosNuevoAlumno = new javax.swing.JLabel();
        etiquetaNombre = new javax.swing.JLabel();
        campoModNombre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        campoModApellido = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        campoModCurso = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        campoModClase = new javax.swing.JTextField();
        botonBuscarAlumno = new javax.swing.JButton();
        botonGrabarModificacion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        EtiquetaInfo.setText("Introduzca el id del alumno a modificar:");
        getContentPane().add(EtiquetaInfo);
        EtiquetaInfo.setBounds(30, 28, 299, 16);
        getContentPane().add(campoRecogerID);
        campoRecogerID.setBounds(30, 57, 73, 22);

        etiquetaDatosAlumno.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(etiquetaDatosAlumno);
        etiquetaDatosAlumno.setBounds(30, 97, 318, 34);

        etiquetaDatosNuevoAlumno.setText("Introduzca datos modificados del alumno:");
        getContentPane().add(etiquetaDatosNuevoAlumno);
        etiquetaDatosNuevoAlumno.setBounds(30, 150, 250, 16);

        etiquetaNombre.setText("Nombre:");
        getContentPane().add(etiquetaNombre);
        etiquetaNombre.setBounds(30, 180, 60, 20);
        getContentPane().add(campoModNombre);
        campoModNombre.setBounds(90, 180, 160, 22);

        jLabel1.setText("Apellido");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(30, 210, 50, 20);
        getContentPane().add(campoModApellido);
        campoModApellido.setBounds(90, 210, 160, 22);

        jLabel2.setText("Curso");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(30, 236, 50, 30);
        getContentPane().add(campoModCurso);
        campoModCurso.setBounds(90, 240, 60, 22);

        jLabel3.setText("Clase");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(30, 270, 28, 16);
        getContentPane().add(campoModClase);
        campoModClase.setBounds(90, 270, 60, 22);

        botonBuscarAlumno.setText("Buscar");
        botonBuscarAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBuscarAlumnoActionPerformed(evt);
            }
        });
        getContentPane().add(botonBuscarAlumno);
        botonBuscarAlumno.setBounds(130, 50, 67, 30);

        botonGrabarModificacion.setText("Grabar Mod");
        botonGrabarModificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGrabarModificacionActionPerformed(evt);
            }
        });
        getContentPane().add(botonGrabarModificacion);
        botonGrabarModificacion.setBounds(130, 310, 120, 25);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonBuscarAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBuscarAlumnoActionPerformed
        
        metodo=new Metodos();
        int ID=Integer.valueOf(campoRecogerID.getText());
              
               id=metodo.buscarAlumno(ID).getId();
               nombre=metodo.buscarAlumno(ID).getNombre();
               apellido=metodo.buscarAlumno(ID).getApellido();
               curso=metodo.buscarAlumno(ID).getCurso();
               clase=metodo.buscarAlumno(ID).getClase();
        
        String alumnoEncontrado="  ID:"+id+"   "+nombre+"  "+apellido+"   "+curso+" "+ clase;
        
        etiquetaDatosAlumno.setText(alumnoEncontrado);
        
        campoModClase.setText(clase);
        campoModApellido.setText(apellido);
        campoModNombre.setText(nombre);
        campoModCurso.setText(Integer.toString(curso));

        
    }//GEN-LAST:event_botonBuscarAlumnoActionPerformed

    private void botonGrabarModificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGrabarModificacionActionPerformed
        
        
        
        metodo.modificarAlumno(id, nombre, apellido, curso, clase);
        
        
        
    }//GEN-LAST:event_botonGrabarModificacionActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel EtiquetaInfo;
    private javax.swing.JButton botonBuscarAlumno;
    private javax.swing.JButton botonGrabarModificacion;
    private javax.swing.JTextField campoModApellido;
    private javax.swing.JTextField campoModClase;
    private javax.swing.JTextField campoModCurso;
    private javax.swing.JTextField campoModNombre;
    private javax.swing.JTextField campoRecogerID;
    private javax.swing.JLabel etiquetaDatosAlumno;
    private javax.swing.JLabel etiquetaDatosNuevoAlumno;
    private javax.swing.JLabel etiquetaNombre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
