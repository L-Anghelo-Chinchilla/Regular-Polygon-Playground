import java.util.ArrayList;

public class Main {
   
      public static void main(String[] args) {
        Poligono  p  = new Poligono(
        new Punto (200,200), 
        new Punto (300,300),
        6
       );
        ArrayList<Punto>  arr  = p.getVertices();
        for(Punto d : arr)
        System.out.println(d);
        Window m = new Window();
    }

}