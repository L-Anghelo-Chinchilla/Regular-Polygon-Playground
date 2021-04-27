import java.util.*;

public class Poligono {
    
    private ArrayList<Punto> vertices;
    private Punto centro, pivote; 
    private int lados;
    private double radio;
    private String nombre ; 
   public Poligono (Punto c, Punto p, int l){
        
        pivote = p ;
        centro   = c ; 
        lados = l ; 
        radio = centro.distanciaCon(pivote);
        calcularVertices();

    }
    
    void calcularVertices(){
    
        vertices = new ArrayList<Punto>();
       
        for(int i = lados ; i >= 0 ; i--){

            vertices.add(
                centro.tercerPunto( pivote , 360 /lados* i ,   centro.distanciaCon(pivote)));

        }

    }

    Punto  getPivote(){return pivote;}
    Punto  getCentro(){return centro;}
    int    getLados(){return lados;}
    double getRadio(){return radio; }
    String getNombre(){return nombre; }
    ArrayList<Punto> getVertices(){return vertices;}
    
    void setPivote(Punto valor ){ pivote = valor;}
    void setCentro(Punto valor ){ centro = valor;}
    void setLados(int valor){lados = valor;}
    void setRadio(int valor){radio = valor; }
    void setNombre(String valor){ nombre = valor ; }    
    void setVertices(ArrayList<Punto> valor ){ vertices = valor;}
    
}