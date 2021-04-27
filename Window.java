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
        setBounds(0,0,1900,1000);
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
    
    
    setBounds(0,0,1800,1080);
    img = new BufferedImage(700, 685, BufferedImage.TYPE_BYTE_GRAY );
    lb = new JLabel("Introduzca x0, y0, xn yn" );
    x0 = new JTextField();
    y0 = new JTextField();
    yn = new JTextField();
    xn = new JTextField();
    
    
    
    
    // btnResen.addActionListener(new java.awt.event.ActionListener()
    // {             public void actionPerformed(java.awt.event.ActionEvent evt) {
        //         btnResenActionPerformed();
        
        //     }
        // });
        
        int MIN = -31;
        int MAX = 31;
        int INIT = 2;    
        
        sliderX = new JSlider(JSlider.HORIZONTAL, MIN, MAX, INIT);
        sliderY = new JSlider(JSlider.HORIZONTAL, MIN, MAX, INIT);
        
        sliderX.setMajorTickSpacing(10);    
        sliderX.setMinorTickSpacing(1);
        sliderX.setPaintTicks(true);
        sliderX.setPaintLabels(true);
        sliderY.setMajorTickSpacing(10);    
        sliderY.setMinorTickSpacing(1);
        sliderY.setPaintTicks(true);
        sliderY.setPaintLabels(true);
        
        spinner = new JSpinner();
        spinner.setValue(10);
        
        spinner2 = new JSpinner();
        spinner2.setValue(20);
        
        spinner.setEnabled(false);
        spinner2.setEnabled(false);
        
        JPanel aux = new JPanel();
        JLabel asistente = new JLabel("Hola! Dibuja algo!");
        JButton botonGuardar = new JButton("Guardar");
        aux.add(botonBorrar , BorderLayout.EAST);
        aux.add(botonGuardar, BorderLayout.WEST);
        aux.add(asistente, BorderLayout.SOUTH);
        
        
        JPanel panelEstilo  = new JPanel(/*new GridLayout(2, 2, 15, 5*/);
        continuo = new JRadioButton("Continuo", true );
        continuo.addItemListener(new ItemListener(){ 
            
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) 
                segmentado.setSelected(false);    
            }
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

panelEstilo.add(continuo , BorderLayout.NORTH);
panelEstilo.add(segmentado, BorderLayout.NORTH);
panelEstilo.setBorder(
    BorderFactory.createTitledBorder("Estilo de la linea"));
    
    
    JPanel panelBotones = new JPanel(new GridLayout(2,1, 5, 4));
    panelBotones.setBorder(
        BorderFactory.createTitledBorder("Dibujar "));
        
        JPanel editar = new JPanel(new GridLayout(1, 2,4, 10));
        editar.setBorder(
            BorderFactory.createTitledBorder("Editar"));
            editar.add(sliderX);
            editar.add(sliderY);
            
            JPanel panelColores = new JPanel(new GridLayout(2, 6, 5, 5)); 
            panelColores.setBorder(
                BorderFactory.createTitledBorder("Ecoja un color"));
    JPanel panelDatos = new JPanel(new GridLayout(2, 1, 5, 5)); 
    panelDatos.setBorder(
                    
BorderFactory.createTitledBorder("Datos"));
JPanel pDatos = new JPanel(new GridLayout(1,4 , 5 ,1 ));
pDatos.add(new JLabel("X: "));
pDatos.add(tbxDimX);
pDatos.add(new JLabel("Y: "));
pDatos.add(tbxDimY);

JPanel ppDatos = new JPanel(new GridLayout(1,4,10,1));
nombre = new JTextField();
nombre.setBounds(0,0 , 50,10);
lados = new JTextField();
lados.setBounds(0,0 , 50,10);

ppDatos.add(new JLabel("Nombre:"));
ppDatos.add(nombre);
ppDatos.add(new JLabel("Lados:"));
ppDatos.add(lados);

panelDatos.add(ppDatos );
panelDatos.add( pDatos);

p = new JPanel(new GridLayout(1, 6, 10, 10));

p.add(aux);
p.add(panelEstilo);
p.add(panelBotones);
p.add(editar);
p.add(panelColores);
p.add(panelDatos);
p.setBorder(
    BorderFactory.createTitledBorder(" Dibuje una linea "));
    p.setOpaque(true);
    p.setBackground(Color.WHITE);
    JPanel panelPoligonos = new JPanel();
    //panelPoligonos.add(panelDatos);
    panelColores.setBorder(
        BorderFactory.createTitledBorder("Ecoja un color"));
        Color [] colores = 
        {Color.BLACK,Color.RED,Color.YELLOW,Color.BLUE,Color.WHITE,Color.ORANGE,Color.GREEN,Color.PINK};    
        for(Color c : colores){
            JButton  negro = new JButton();
            negro.setBackground(c);
            negro.setForeground(c);
            negro.setOpaque(true);
            panelColores.add(negro);
        }
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.PAGE_AXIS));
        panelBotones.add(getJButton(panelBotones));
        JScrollPane scroll = new JScrollPane(panelBotones);
        add(p, BorderLayout.NORTH); 
        add(scroll, BorderLayout.EAST); 
        pan = new Panel( 1300, 985 , img);
        add(pan ,BorderLayout.CENTER );
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    
    private void botonBorrarActionPerformed(java.awt.event.ActionEvent evt) {
        
        pan.limpiar();
        pan.repaint();
        Poligono  p  = new Poligono(
            new Punto (200,200), 
            new Punto (300,300),
            sliderX.getValue()
            );
            pan.graficarPoligono(p );
            pan.repaint();
        }
        
        public void mouseClicked(MouseEvent e) {  
            pan.setPunto(e.getX(),e.getY() -130, sliderX.getValue());
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
        
        
        static public JPanel  getJButton(JPanel p){
            JPanel jp  = new JPanel();
            JPanel pan = new JPanel(new GridLayout(1,5 , 5,5));
            JLabel label = new JLabel("Poligono i");
            JButton tra = new JButton("Tra");
            JButton esc = new JButton("Esc");
            JButton rot = new JButton("Rot");
            JButton eli = new JButton("Eli");
            pan.add(label );
            jp.add(pan);
  
            pan.add(tra);
            pan.add(esc);
            pan.add(rot);
            pan.add(eli);
            pan.setBounds(0,0,250,400);
            eli.addActionListener(evt->{
                p.add(getJButton(p));
                p.revalidate();
                p.repaint();
            });
            //pan.pack();
            return jp;
        }
        
        private javax.swing.JFrame f;
        private javax.swing.JPanel p;
        private javax.swing.JButton botonBorrar;
        private javax.swing.JTextField tbxDimX,tbxDimY,x0 , y0 , xn,yn, nombre , lados;
        private javax.swing.JLabel lbl, lb;
        private BufferedImage img;
        private Panel pan;
        private JSlider sliderX , sliderY;
        private JSpinner spinner, spinner2;
        private JRadioButton continuo, segmentado;
        
}    
    
    // private void btnDDAActionPerformed(){
    //     int px0 , py0 , pxn , pyn ; 
    //     px0 = Integer.parseInt(x0.getText()); 
    //     py0 = Integer.parseInt(y0.getText())-130;
    //     pxn = Integer.parseInt(xn.getText());
    //     pyn = Integer.parseInt(yn.getText())-130;
    //     DDA b = new DDA();
    //     ArrayList<Punto> arr = b.DDA(px0,py0,pxn,pyn);
    //     int g  = sliderX.getValue();
    //     if(segmentado.isSelected()){
    //         int segmento  = (int)spinner.getValue();
    //         int espacio  = (int)spinner2.getValue();
    //         segmentar(arr,segmento , espacio);
    //     }
    //     pan.graficar(arr, g ); 
    // }
    
    // private void btnResenActionPerformed(){
        
        
    //     int px0 , py0 , pxn , pyn; 
    //     px0 = Integer.parseInt(x0.getText()); 
    //     py0 = Integer.parseInt(y0.getText())-130 ;
    //     pxn = Integer.parseInt(xn.getText());
    //     pyn = Integer.parseInt(yn.getText())-130 ;
    //     Bresenham b = new Bresenham();
    //     ArrayList<Punto> arr = b.calcular(px0,py0,pxn,pyn);
    //     int g  = sliderX.getValue();
    //     if(segmentado.isSelected()){
    //         int segmento  = (int)spinner.getValue();
    //         int espacio  = (int)spinner2.getValue();
    //         segmentar(arr,segmento , espacio);
    //     }
    //     pan.graficar(arr, g ); 
        
    // }
    