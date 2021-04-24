import java.util.*;
import javax.swing.*;
import java.awt.*;      
import java.awt.event.*;
import java.awt.image.BufferedImage;
public class Panel extends JPanel {
    private int  x , y ;
    private BufferedImage img;

    public void graficar(ArrayList<Punto> puntos, int grosor ){

        for(int i =0 ; i < puntos.size() ; i++){
        
            setPunto(puntos.get(i).getX(), puntos.get(i).getY(), grosor ); 
        
        }
       
    }
   
   public void setPunto(int x  , int y ,int grosor ){

      for(int i = x-(grosor/2) ; i <= x+(grosor/2); i++) 
        for(int j = y -(grosor/2) ; j <= y +(grosor/2); j++)
            img.setRGB(i,j,new Color(0, 0, 0).getRGB());
        repaint();
    }

   public void limpiar(){
       for (int i = 0 ; i <  x ; i++)
           for(int j = 0 ; j < y ; j++) 
                this.img.setRGB(i,j, new Color(235, 235, 235).getRGB() );
   }

    Panel(int x , int y, BufferedImage img){
     
       this.x = x ; 
       this.y = y ; 
       this.img  = new BufferedImage(x,y,BufferedImage.TYPE_BYTE_GRAY );
       limpiar();

    }

    public void paint(Graphics g) {
      g.drawImage(this.img, 0,0,this); 
    }
}