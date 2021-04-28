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
            Bresenham bresenham = new Bresenham();

            ArrayList<Punto> linea = bresenham.calcular (arr.get(i).getX() ,arr.get(i).getY() ,arr.get(i+1).getX() ,arr.get(i+1).getY()  );
            if(p.getEstilo())
             segmentar(linea ,  15 , 10  );
            graficar(linea , p.getGrosor() , p.getColor());
        }
        repaint();

    }


    void segmentar(ArrayList<Punto> arr , int segmento , int espacio){
        int inicio  = segmento, fin  = espacio; 
        int i  =0 ;
        boolean continua = true;
        while(continua){
            
            inicio = segmento * i;
            fin = inicio + espacio;
            i++;
            try{
                arr.subList(inicio, fin ).clear(); 
                
            }catch(Exception e ){
                if(inicio < arr.size())
                arr.subList(inicio, arr.size()).clear();
                continua = false ; 
            }
        }
        
    }


   public void setPunto(int x  , int y ,int grosor ,Color c  ){

      for(int i = x-(grosor/2) ; i <= x+(grosor/2); i++) 
        for(int j = y -(grosor/2) ; j <= y +(grosor/2); j++)
            if(i >= 0  && i < img.getWidth() && j > 0 && j < img.getHeight() )
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


    public void trasladarPoligono(String nombre , Punto nuevoCentro) {
        poligonos.get(nombre).trasladar(nuevoCentro);;

    }


    public void rotarPoligono(String nombre, int angulo ) {
        poligonos.get(nombre).rotar(angulo);;
    }


    public void escalarPoligono(String nombre , int factor ) {
    poligonos.get(nombre).escalar(factor);        
    }


    public Poligono getPoligono(String nombre){

        return poligonos.get(nombre);

    }


    public void eliminarPoligono(String nombre) {
        poligonos.remove(nombre);
        graficarPoligonos();
    }

    public BufferedImage getImage(){

        BufferedImage image = img; 
        int color = new Color(235, 235, 235).getRGB();
        for (int y = 0; y < image.getHeight(); ++y) {
            for (int x = 0; x < image.getWidth(); ++x) {
                 int argb = image.getRGB(x, y);
                 if ((argb  ==  color ))
                 {
                      image.setRGB(x, y, 0);
                 }
            }
        }
        return image; 
    }

}