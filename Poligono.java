import java.util.*;
//import java.util.math;
public class Poligono {

   public Poligono (Punto c, Punto p, int l){
        pivote = p ;
        centro   = c ; 
        lados = l ; 
        vertices = new ArrayList<Punto>();
       // vertices.add(pivote);
       
        calcularVertices();
            System.out.println("conio ");
        for(Punto pp : vertices ){

            System.out.println(pp.getX());
            System.out.println(pp.getY());
        }
            System.out.println("pipi");
    }

    void calcularVertices(){
            System.out.println("calcular vertices ");
       
        for(int i = lados ; i >= 0 ; i--){

            vertices.add(
                centro.tercerPunto( pivote , 360 /lados* i ,   centro.distanciaCon(pivote)));

        }

    }

    Punto getPivote(){return pivote;}
    Punto getCentro(){return centro;}
    int getLados(){return lados;}
    ArrayList<Punto> getVertices(){return vertices;}
    private ArrayList<Punto> vertices;
    private Punto centro, pivote; 
    private int lados;
}