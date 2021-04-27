import java.util.*;
import javax.swing.*;
import java.awt.*;      
import java.awt.event.*;
import java.awt.image.BufferedImage;
public class Panel extends JPanel {
    private int  x , y ;
    private BufferedImage img;
    private ArrayList<Poligono> poligonos; 
    public void graficar(ArrayList<Punto> puntos, int grosor ){

        for(int i =0 ; i < puntos.size() ; i++){
        
            setPunto(puntos.get(i).getX(), puntos.get(i).getY(), grosor ); 
        
        }
       
    }

    void graficarPoligono(Poligono p ){

        ArrayList<Punto> arr = p.getVertices();
        for(int i = 0 ; i < arr.size()-1 ;  i++){
            Bresenham dda = new Bresenham();

            ArrayList<Punto> linea = dda.calcular (arr.get(i).getX() ,arr.get(i).getY() ,arr.get(i+1).getX() ,arr.get(i+1).getY()  );
            graficar(linea , 10 );
        }



    }

   public void setPunto(int x  , int y ,int grosor ){

      for(int i = x-(grosor/2) ; i <= x+(grosor/2); i++) 
        for(int j = y -(grosor/2) ; j <= y +(grosor/2); j++)
            if(i >= 0  && i < x && j > 0 && j < y )
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
       this.img  = new BufferedImage(x,y,BufferedImage.TYPE_INT_ARGB  );
       limpiar();

    }

    public void paint(Graphics g) {
      g.drawImage(this.img, 0,0,this); 
    }
}