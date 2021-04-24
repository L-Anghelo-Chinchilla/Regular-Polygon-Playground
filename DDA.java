import java.util.ArrayList;
public class DDA {
        ArrayList<Punto> arr;

    ArrayList<Punto> DDA (int x1,int y1,int x2,int y2)
{
    arr = new ArrayList();
   float ax,ay,x,y,res;
   int i;
   if((int)(x2-x1)>=(int)(y2-y1))
    
    res=(int)(x2-x1);
        else

    res=(int)(y2-y1);

   ax=(x2-x1)/res;
   ay=(y2-y1)/res;


   x=(float)x1;
   y=(float)y1;

   i=0;
   while(i<=res)
   {
        arr.add(new Punto((int)(x),(int)(y)));
        x=x+ax;
        y=y+ay;
        i++;
   }
   return arr;
}


}