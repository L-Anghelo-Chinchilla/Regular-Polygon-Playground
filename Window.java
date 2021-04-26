import java.util.*;
import javax.swing.*;
import java.awt.*;      
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.Timer;
public class Window extends JFrame implements MouseListener{

    private int resx, resy , px, py ;
    boolean point;
 
    Window(){
      
        addMouseListener(this);
        point = true;
        setBounds(100,50,400,350);
        lbl = new JLabel("Defina ancho y alto");
        tbxDimX = new JTextField();
        tbxDimY = new JTextField();
        botonBorrar =new JButton();
        botonBorrar.setText("Borrar");
        botonBorrar.addActionListener(new java.awt.event.ActionListener()
        {             public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBorrarActionPerformed(evt);
            }
        });

    
        setBounds(100,100,1000,1000);
        img = new BufferedImage(700, 685, BufferedImage.TYPE_BYTE_GRAY );

        lb = new JLabel("Introduzca x0, y0, xn yn" );
        x0 = new JTextField();
        y0 = new JTextField();
        yn = new JTextField();
        xn = new JTextField();
        btnDDA = new JButton("Dibujar con DDA");
        btnResen = new JButton("Dibujar con BRESENHAM ");
        
        btnDDA.addActionListener(new java.awt.event.ActionListener()
        {             public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDDAActionPerformed();
            }
        });
       
        btnResen.addActionListener(new java.awt.event.ActionListener()
        {             public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResenActionPerformed();

            }
        });

        x0.setPreferredSize(new java.awt.Dimension(50, 25));
        y0.setPreferredSize(new java.awt.Dimension(50, 25));
        xn.setPreferredSize(new java.awt.Dimension(50, 25));
        yn.setPreferredSize(new java.awt.Dimension(50, 25));

         int MIN = 1;
        int MAX = 31;
        int INIT = 2;    

        grosor = new JSlider(JSlider.HORIZONTAL, MIN, MAX, INIT);
        JPanel panelgrosor = new JPanel();
        panelgrosor.add(grosor);
        panelgrosor.setBorder(
            BorderFactory.createTitledBorder("Grosor de la linea"));


        
        grosor.setMajorTickSpacing(10);    
        grosor.setMinorTickSpacing(1);
        grosor.setPaintTicks(true);
        grosor.setPaintLabels(true);
        
        spinner = new JSpinner();
		spinner.setValue(10);

        spinner2 = new JSpinner();
		spinner2.setValue(20);

        spinner.setEnabled(false);
        spinner2.setEnabled(false);
        JPanel panelEstilo  = new JPanel(new GridLayout(2, 2, 15, 5));
        continuo = new JRadioButton("Continuo", true );

        continuo.addItemListener(new ItemListener(){ 
                
                public void itemStateChanged(ItemEvent e) {
                 if (e.getStateChange() == ItemEvent.SELECTED) {
                    segmentado.setSelected(false);
                }
                    else if (e.getStateChange() == ItemEvent.DESELECTED) {
            // Your deselected code here.
    }}
}
        );

        segmentado = new JRadioButton("Segmentado", false );
        segmentado.addItemListener(new ItemListener(){ 
                
                public void itemStateChanged(ItemEvent e) {
                 if (e.getStateChange() == ItemEvent.SELECTED) {
                     spinner.setEnabled(true);
                     spinner2.setEnabled(true);
                     continuo.setSelected(false);                
                }
                    else if (e.getStateChange() == ItemEvent.DESELECTED) {
                     spinner.setEnabled(false);
                     spinner2.setEnabled(false);

            
    }
    }
}
        );
        panelEstilo.add(continuo);
        panelEstilo.add(segmentado);
        
        panelEstilo.add(spinner);
        panelEstilo.add(spinner2);
        
        panelEstilo.setBorder(
            BorderFactory.createTitledBorder("Estilo de la linea"));


        JPanel panelBotones = new JPanel(new GridLayout(2,1, 5, 4));
        panelBotones.setBorder(
            BorderFactory.createTitledBorder("Dibujar "));

        panelBotones.add(btnDDA);
        panelBotones.add(btnResen);
        
        JPanel borrar = new JPanel(new GridLayout(2, 1,4, 10));
        borrar.setBorder(
            BorderFactory.createTitledBorder("Borrar "));
        borrar.add(botonBorrar);
        p = new JPanel(new GridLayout(1, 4, 10, 10));
        p.add(panelgrosor);
        p.add(panelEstilo);
        p.add(panelBotones);
        p.add(borrar);
       
        p.setBorder(
            BorderFactory.createTitledBorder(" Dibuje una linea "));
        p.setOpaque(true);
        p.setBackground(Color.WHITE);
        
        add(p, BorderLayout.NORTH);
        pan = new Panel( 1000, 985 , img);
        add(pan ,BorderLayout.CENTER );
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    
    private void botonBorrarActionPerformed(java.awt.event.ActionEvent evt) {
       
       pan.limpiar();
       pan.repaint();
       System.out.println("noooo ctm");
       Poligono  p  = new Poligono(
           new Punto (200,200), 
           new Punto (300,300),
           grosor.getValue()
           );
           pan.graficarPoligono(p );
           pan.repaint();
    }

  public void mouseClicked(MouseEvent e) {  
      pan.setPunto(e.getX(),e.getY() -130, grosor.getValue());
          if(point){
                x0.setText(""+e.getX());
                y0.setText(""+e.getY());
                point = !point; 
          }else{
                xn.setText(""+e.getX());
                point = !point; 
                yn.setText(""+e.getY());
          }

       
    }

    public void mouseEntered(MouseEvent e) {  }
    public void mouseExited(MouseEvent e) {  } 
    public void mouseReleased(MouseEvent e) {}  
    public void mousePressed(MouseEvent e) {}

    private void btnDDAActionPerformed(){
        int px0 , py0 , pxn , pyn ; 
        px0 = Integer.parseInt(x0.getText()); 
        py0 = Integer.parseInt(y0.getText())-130;
        pxn = Integer.parseInt(xn.getText());
        pyn = Integer.parseInt(yn.getText())-130;
        DDA b = new DDA();
        ArrayList<Punto> arr = b.DDA(px0,py0,pxn,pyn);
        int g  = grosor.getValue();
        if(segmentado.isSelected()){
        int segmento  = (int)spinner.getValue();
        int espacio  = (int)spinner2.getValue();
        segmentar(arr,segmento , espacio);
        }
       pan.graficar(arr, g ); 
    }
   
    private void btnResenActionPerformed(){
  
        
        int px0 , py0 , pxn , pyn; 
        px0 = Integer.parseInt(x0.getText()); 
        py0 = Integer.parseInt(y0.getText())-130 ;
        pxn = Integer.parseInt(xn.getText());
        pyn = Integer.parseInt(yn.getText())-130 ;
        Bresenham b = new Bresenham();
        ArrayList<Punto> arr = b.calcular(px0,py0,pxn,pyn);
        int g  = grosor.getValue();
        if(segmentado.isSelected()){
        int segmento  = (int)spinner.getValue();
        int espacio  = (int)spinner2.getValue();
        segmentar(arr,segmento , espacio);
        }
       pan.graficar(arr, g ); 
       
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
    
    private javax.swing.JFrame f;
    private javax.swing.JPanel p;
    private javax.swing.JButton botonBorrar, btnDDA, btnResen;
    private javax.swing.JTextField tbxDimX,tbxDimY,x0 , y0 , xn,yn;
    private javax.swing.JLabel lbl, lb;
    private BufferedImage img;
    private Panel pan;
    private JSlider grosor;
    private JSpinner spinner, spinner2;
    private JRadioButton continuo, segmentado;
}    