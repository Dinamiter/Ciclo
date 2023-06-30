
package propio.dinosaurio;

import javax.swing.JFrame;

/**
 *
 * @author OrtbrA
 */
public class Principal {
    
    public static void main(String[] args) {
        
        JFrame frame = new JFrame();
        PanelJuego panel=new PanelJuego();
        frame.add(panel);

        frame.setSize(500, 500);
        frame.getDefaultCloseOperation();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        
    }
    
}
