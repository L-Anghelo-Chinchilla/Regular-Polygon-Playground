package src;

import java.util.*;

public class Bresenham{
    ArrayList<Punto> arr ;
    Bresenham(){
    }

 public ArrayList<Punto> calcular( int X1, int Y1, int X2, int Y2){
        arr = new ArrayList<Punto>();
    int dY,dX, IncYi,IncXi, IncYr, IncXr , avR ,  av, avI, k; 
     
       dY = (Y2 - Y1);
      dX = (X2 - X1);
    
     
      if (dY >= 0) 
          IncYi = 1;
      else{
          dY = -dY;
          IncYi = -1;
      }
    
      if (dX >= 0)
          IncXi = 1;
      else{
          dX = -dX;
          IncXi = -1;
      }
    
     
      if (dX >= dY) 
      {
          IncYr = 0;
          IncXr = IncXi;
      }else{
          IncXr = 0;
          IncYr = IncYi;
    
        
          k = dX; dX = dY; dY = k;
      }
    
    
     int X = X1; 
     int Y = Y1;
      avR = (2 * dY);
      av = (avR - dX);
      avI = (av - dX);
    
      
      do{
          arr.add(new Punto(X, Y)); 
           
          if (av >= 0) {
              X = (X + IncXi);     
              Y = (Y + IncYi);     
              av = (av + avI);
          }else{
              X = (X + IncXr);          
              Y = (Y + IncYr);          
              av = (av + avR);   
          }
      }while (X != X2); 
    return arr;
 }
}

    