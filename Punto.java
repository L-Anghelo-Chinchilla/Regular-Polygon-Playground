import java.lang.*;

public  class Punto {

    private int x ;
    private int y ;

    Punto(int xx,int yy){
        x = xx ; 
        y = yy; 

    }


    public int getX(){
        return x; 
    }


    public int getY(){
        return y;
    }


    public double distanciaCon(Punto otro){
        int dx =  (int)Math.pow(x -otro.getX() ,2);
        int dy =  (int)Math.pow(y -otro.getY() ,2);
        double distancia = Math.sqrt(dx + dy ); 
        return  distancia     ;                   
    }


    public  Punto puntoMedio(Punto otro){
       int xx = Math.round((x + otro.getX())/2);
       int yy = Math.round((y + otro.getY())/2);
        return new Punto(xx, yy ); 
    }


    public   Punto tercerPunto(Punto pivote , int angulo , double distancia){
       
        angulo +=    Math.toDegrees(Math.atan2( x- pivote.getX()  ,  y - pivote.getY()  )) ;
     
        double xx = x  + Math.round(Math.cos (Math.toRadians(angulo))* distancia );
   
        
        double  yy =y  + Math.round(Math.sin(Math.toRadians(angulo)) * distancia);
      
        return new Punto((int)xx,(int)yy);
    }

    
    public String toString(){
        return "x:" + x +"  y: " + y ;

    }

}
