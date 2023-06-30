
package propio.dinosaurio;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

/**
 *
 * @author ortbra
 */
public class PanelJuego extends JPanel implements KeyListener
{
    Personaje p;
    Graphics g;
    
    public PanelJuego(){
        
    p=new Personaje();   
    p.pintar(g);
    
    }
    
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
      
    }

    @Override
    public void keyReleased(KeyEvent e) {
      
    }
    
     
    
    
    
}
