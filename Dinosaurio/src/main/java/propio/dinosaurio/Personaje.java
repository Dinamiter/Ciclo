
package propio.dinosaurio;

import java.awt.Graphics;

import java.awt.image.ImageObserver;

import java.awt.Image;

import javax.swing.ImageIcon;
/**
 *
 * @author ortbra
 */
public class Personaje {
    
    private Image imagen;
    private String personaje="/Recursos/bass.gif";
    
    int x,y,cx,cy;
    
    public Personaje(){
        x=40;
        y=60;
        ImageIcon  img=new ImageIcon(this.getClass().getResource(personaje));
        imagen=img.getImage();
        
    }
    
    public void pintar(Graphics g){
        ImageObserver observer = null;
        g.drawImage(imagen, x, y,observer );
    }
    
}
