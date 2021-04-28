import java.util.*;
import javax.swing.*;
import java.awt.*;      
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class Panel extends JPanel {

    private int  x , y ;
    private BufferedImage img;
    private SortedMap<String,Poligono> poligonos; 

    Panel(int x , int y, BufferedImage img){
        
        this.x = x ; 
        this.y = y ; 
        this.img  = new BufferedImage(x,y,BufferedImage.TYPE_INT_ARGB  );
        poligonos = new TreeMap<String, Poligono>();
        limpiar();
        
    }



    public void graficar(ArrayList<Punto> puntos, int grosor , Color c  ){

        for(int i =0 ; i < puntos.size() ; i++){
        
            setPunto(puntos.get(i).getX(), puntos.get(i).getY(), grosor , c ); 
        
        }
       
    }


    void graficarPoligono(Poligono p ){

        ArrayList<Punto> arr = p.getVertices();
        for(int i = 0 ; i < arr.size()-1 ;  i++){
            Bresenham dda = new Bresenham();

            ArrayList<Punto> linea = dda.calcular (arr.get(i).getX() ,arr.get(i).getY() ,arr.get(i+1).getX() ,arr.get(i+1).getY()  );
            graficar(linea , 10 , p.getColor());
        }
        repaint();

    }


   public void setPunto(int x  , int y ,int grosor ,Color c  ){

      for(int i = x-(grosor/2) ; i <= x+(grosor/2); i++) 
        for(int j = y -(grosor/2) ; j <= y +(grosor/2); j++)
            if(i >= 0  && i < x && j > 0 && j < y )
            img.setRGB(i,j,c.getRGB());

    }


    void graficarPoligonos(){
        limpiar();
        
        for(String  key: poligonos.keySet())
            graficarPoligono(poligonos.get(key));
    
    }
    
    
    void addPoligono(String nombre , Poligono poligono ){
        
        poligonos.put(nombre, poligono);
        System.out.println(poligonos.size());
    }


    public void paint(Graphics g) {
        g.drawImage(this.img, 0,0,this); 

    }

    public void eliminar(){
        poligonos.clear();
        limpiar();
    }

    public void limpiar(){

        for (int i = 0 ; i <  x ; i++)
            for(int j = 0 ; j < y ; j++) 
                this.img.setRGB(i,j, new Color(235, 235, 235).getRGB() );
    }



    public void trasladarPoligono(String nombre) {
        poligonos.get(nombre);

    }



    public void rotarPoligono(String nombre) {
        poligonos.get(nombre);
    }



    public void escalarPoligono(String nombre) {
    poligonos.get(nombre);        
    }



    public void eliminarPoligono(String nombre) {
        poligonos.remove(nombre);
        graficarPoligonos();
    }
}