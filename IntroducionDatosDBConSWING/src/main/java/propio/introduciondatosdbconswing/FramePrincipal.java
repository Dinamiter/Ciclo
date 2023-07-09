
package propio.introduciondatosdbconswing;


import Metodos.*;

/**
 *
 * @author ortbra
 */
public class FramePrincipal extends javax.swing.JFrame {

    String info;
    Metodos metodo=new Metodos(this);
    JDialogTablaAlumnos tabla;
    JDialogModificar dialogModificar;
    
    
    public FramePrincipal() {
        this.dispose();
        initComponents();
        this.setSize(400, 310);
        this.getDefaultCloseOperation();
        this.setVisible(true);
        this.setLocationRelativeTo(null);    
    }

    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Contenedor = new javax.swing.JPanel();
        labelNombre = new javax.swing.JLabel();
        campoNombre = new javax.swing.JTextField();
        labelApellido = new javax.swing.JLabel();
        campoApellido = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        campoCurso = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        campoClase = new javax.swing.JTextField();
        botonNuevo = new javax.swing.JButton();
        botonModificar = new javax.swing.JButton();
        botonTodos = new javax.swing.JButton();
        etiquetaInfo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setMaximumSize(new java.awt.Dimension(400, 300));
        setResizable(false);
        setSize(new java.awt.Dimension(400, 300));
        getContentPane().setLayout(null);

        Contenedor.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Contenedor.setLayout(null);

        labelNombre.setText("Nombre");
        Contenedor.add(labelNombre);
        labelNombre.setBounds(20, 10, 130, 16);
        Contenedor.add(campoNombre);
        campoNombre.setBounds(20, 30, 160, 22);

        labelApellido.setText("Apellido");
        Contenedor.add(labelApellido);
        labelApellido.setBounds(200, 10, 140, 16);
        Contenedor.add(campoApellido);
        campoApellido.setBounds(200, 30, 160, 22);

        jLabel2.setText("Curso");
        Contenedor.add(jLabel2);
        jLabel2.setBounds(20, 70, 110, 16);
        Contenedor.add(campoCurso);
        campoCurso.setBounds(20, 90, 90, 22);

        jLabel3.setText("Clase");
        Contenedor.add(jLabel3);
        jLabel3.setBounds(140, 70, 80, 16);
        Contenedor.add(campoClase);
        campoClase.setBounds(140, 90, 90, 22);

        botonNuevo.setText("Nuevo Alumno");
        botonNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNuevoActionPerformed(evt);
            }
        });
        Contenedor.add(botonNuevo);
        botonNuevo.setBounds(20, 150, 120, 25);

        botonModificar.setText("Modificar");
        botonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonModificarActionPerformed(evt);
            }
        });
        Contenedor.add(botonModificar);
        botonModificar.setBounds(20, 180, 120, 25);

        botonTodos.setText("Ver todos");
        botonTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonTodosActionPerformed(evt);
            }
        });
        Contenedor.add(botonTodos);
        botonTodos.setBounds(20, 210, 120, 25);

        etiquetaInfo.setBackground(new java.awt.Color(204, 204, 255));
        etiquetaInfo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Contenedor.add(etiquetaInfo);
        etiquetaInfo.setBounds(190, 210, 180, 16);

        getContentPane().add(Contenedor);
        Contenedor.setBounds(2, 26, 380, 240);

        jLabel1.setFont(new java.awt.Font("Snap ITC", 0, 14)); // NOI18N
        jLabel1.setText("PRACTICA CON BASES DE DATOS");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(40, 0, 310, 19);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNuevoActionPerformed

        metodo.nuevoAlumno();   
    }//GEN-LAST:event_botonNuevoActionPerformed

    private void botonTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonTodosActionPerformed
        
        tabla=new JDialogTablaAlumnos(this,true);
        tabla.setAlwaysOnTop(rootPaneCheckingEnabled);
     
    }//GEN-LAST:event_botonTodosActionPerformed

    private void botonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonModificarActionPerformed
        
        dialogModificar=new JDialogModificar(this, true);
        
        
    }//GEN-LAST:event_botonModificarActionPerformed

    
    public void getInfo(String informacion){  
        info=informacion;
        etiquetaInfo.setText(info);
    }
    
    public String getNombre(){  
        return campoNombre.getText();
    }
    
    public String getApellido(){  
        return campoApellido.getText();
    }
    
    public  Integer getCurso(){  
        Integer numClase= Integer.parseInt(campoCurso.getText());
        
        return numClase;
        
    }
    
    public String getClase(){ 
       return campoClase.getText();
    }
    
    
    
    
    
    
    
    
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FramePrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Contenedor;
    private javax.swing.JButton botonModificar;
    private javax.swing.JButton botonNuevo;
    private javax.swing.JButton botonTodos;
    private javax.swing.JTextField campoApellido;
    private javax.swing.JTextField campoClase;
    private javax.swing.JTextField campoCurso;
    private javax.swing.JTextField campoNombre;
    private javax.swing.JLabel etiquetaInfo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel labelApellido;
    private javax.swing.JLabel labelNombre;
    // End of variables declaration//GEN-END:variables
}
