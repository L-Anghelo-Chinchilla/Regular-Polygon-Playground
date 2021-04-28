import java.util.*;
import java.awt.Color;
public class Poligono {
    
    private ArrayList<Punto> vertices;
    private Punto centro, pivote; 
    private int lados;
    private double radio;
    private String nombre ; 
    private Color color; 
    private int grosor; 
    private boolean estilo; 
   public Poligono (Punto c, Punto p, int l,
                    Color co , int g ,boolean e  ){
        pivote = p ;
        centro   = c ; 
        lados = l ; 
        radio = centro.distanciaCon(pivote);
        color = co ; 
        grosor = g;
        estilo  = e ; 
        calcularVertices();

    }
    
    void calcularVertices(){
    
        vertices = new ArrayList<Punto>();
       
        for(int i = lados ; i >= 0 ; i--){

            vertices.add(
                centro.tercerPunto( pivote , 360 /lados* i ,   centro.distanciaCon(pivote)));

        }

    }

    public void rotar(int angulo){

        int xP = pivote.getX(); 
        int yP = pivote.getY();
        int xC = centro.getX();
        int yC = centro.getY();
        
        int xN = (int) Math.round(xC+((xP-xC)*Math.cos(angulo))-((yP-yC)*Math.sin(angulo)));
        int yN = (int) Math.round(yC+((yP-yC)*Math.cos(angulo))+((xP-xC)*Math.sin(angulo)));
        
        pivote = new Punto(xN,yN);
        calcularVertices();

    }

    public void trasladar(Punto a ){ 
     
        int difx = centro.getX() - pivote.getX();
        int dify =  centro.getY() - pivote.getY();
        int nX = a.getX() + difx;
        int nY = a.getY() + dify;
        pivote = new Punto(nX,nY);
        centro  = a; 
        calcularVertices();
        
   }

   public void escalar(int factor){
        if(factor< 0 ){
         factor *= -0.01 ;
        } 
       int difX = centro.getX() - pivote.getX(); 
        int difY = centro.getY() - pivote.getY(); 
        int nX = centro.getX()+ (difX * factor);
        int nY = centro.getY()+(difY *factor);
        System.out.println(centro);
        System.out.println(pivote);
        pivote = new Punto(nX, nY);
        System.out.println(pivote);
        calcularVertices();
   }

    Punto  getPivote(){return pivote;}
    Punto  getCentro(){return centro;}
    int    getLados(){return lados;}
    double getRadio(){return radio; }
    String getNombre(){return nombre; }
    ArrayList<Punto> getVertices(){return vertices;}
    Color getColor(){return color;}
    boolean getEstilo(){return estilo;}
    int getGrosor(){return grosor;}

    void setPivote(Punto valor ){ pivote = valor;}
    void setCentro(Punto valor ){ centro = valor;}
    void setLados(int valor){lados = valor;}
    void setRadio(int valor){radio = valor; }
    void setNombre(String valor){ nombre = valor ; }    
    void setVertices(ArrayList<Punto> valor ){ vertices = valor;}
    void setColor(Color valor ){color=valor;} 
    void setGrosor(int valor){grosor=valor;}
    void setEstilo(boolean  valor){estilo=valor;}
}


