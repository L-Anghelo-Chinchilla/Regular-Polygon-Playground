package src;

import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;      
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;


public class Gui extends JFrame implements MouseListener{

    
    public Gui(){
        
        addMouseListener(this);
        actual = "Polygon ";
        estado= iesimo = 0 ; 
        color = Color.BLACK;
        setBounds(0,0,1900,1000);
        lbl = new JLabel("Defina ancho y alto");
        tbxDimX = new JTextField();
        tbxDimY = new JTextField();
        botonBorrar =new JButton();
        botonBorrar.setText("Clear");
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
 
        
        int MIN = -180;
        int MAX = 180;
        int INIT = 0;    
        
        sliderX = new JSlider(JSlider.HORIZONTAL, MIN, MAX, INIT);
        sliderY = new JSlider(JSlider.HORIZONTAL, 1, 10, 1);
        
        sliderX.setMajorTickSpacing(90);    
        sliderX.setMinorTickSpacing(45);
        sliderX.setPaintTicks(true);
        sliderX.setPaintLabels(true);
        sliderY.setMajorTickSpacing(2);    
        sliderY.setMinorTickSpacing(2);
        sliderY.setPaintTicks(true);
        sliderY.setPaintLabels(true);
        
        spinner = new JSpinner();
        spinner.setValue(4);

        JPanel aux = new JPanel();
        JLabel asistente = new JLabel("Hiya! Draw something!");
        JButton botonGuardar = new JButton("Save");
        botonGuardar.addActionListener(new java.awt.event.ActionListener()
        {             public void actionPerformed(java.awt.event.ActionEvent evt) {
            botonGuardaraAction();
        }

        
    });
    aux.add(botonGuardar, BorderLayout.WEST);
    aux.add(botonBorrar , BorderLayout.EAST);
        aux.add(asistente, BorderLayout.SOUTH);
        aux.setBackground(Color.white);
        
        
        JPanel panelEstilo  = new JPanel(new GridLayout(2, 2, 15, 5));
        panelEstilo.setBackground(Color.white);
        continuo = new JRadioButton("Continuous", true );
        continuo.addItemListener(new ItemListener(){ 
            
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) 
                segmentado.setSelected(false);    
            }
        }
        );
            
    segmentado = new JRadioButton("Segmented", false );
   segmentado.addItemListener(new ItemListener(){ 

    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
          //  spinner.setEnabled(true);
          //  spinner2.setEnabled(true);
            continuo.setSelected(false);                
        }
        else if (e.getStateChange() == ItemEvent.DESELECTED) {
           // spinner.setEnabled(false);
           // spinner2.setEnabled(false);
        }
    }
}
);

panelEstilo.add(continuo , BorderLayout.NORTH);
panelEstilo.add(segmentado, BorderLayout.NORTH);
panelEstilo.add(new JLabel("Line thickness"));
panelEstilo.add(spinner);

panelEstilo.setBorder(
    BorderFactory.createTitledBorder("Line style"));
    
    
     panelBotones = new JPanel(new GridLayout(2,1, 5, 4));
     panelBotones.setBackground(Color.white);

    panelBotones.setBorder(
        BorderFactory.createTitledBorder("Polygons"));
        
        JPanel editar = new JPanel(new GridLayout(1, 2,4, 10));
        editar.setBackground(Color.white);

        editar.setBorder(
            BorderFactory.createTitledBorder("Edit"));
            editar.add(sliderX);
            editar.add(sliderY);
            
            JPanel panelColores = new JPanel(new GridLayout(2, 6, 5, 5)); 
            panelColores.setBorder(
                BorderFactory.createTitledBorder("Choose a color"));
    JPanel panelDatos = new JPanel(new GridLayout(2, 1, 5, 5)); 
        panelDatos.setBackground(Color.white);
        
    panelDatos.setBorder(
                    
BorderFactory.createTitledBorder("Values"));
JPanel pDatos = new JPanel(new GridLayout(1,4 , 5 ,1 ));
pDatos.add(new JLabel("X: "));
pDatos.add(tbxDimX);
pDatos.add(new JLabel("Y: "));
pDatos.add(tbxDimY);

JPanel ppDatos = new JPanel(new GridLayout(1,4,10,1));
nombre = new JTextField();
nombre.setBounds(0,0 , 50,10);
lados = new JTextField("2");
lados.setBounds(0,0 , 50,10);

ppDatos.add(new JLabel("Name:"));
ppDatos.add(nombre);
ppDatos.add(new JLabel("Sides:"));
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
p.setBackground(Color.white);

p.setBorder(
    BorderFactory.createTitledBorder(" Draw a line "));
    p.setOpaque(true);
    p.setBackground(Color.WHITE);
    JPanel panelPoligonos = new JPanel();
    panelColores.setBackground(Color.white);
    
    panelColores.setBorder(
        BorderFactory.createTitledBorder("Choose a color "));
        Color [] colores = 
        {Color.BLACK,Color.RED,Color.YELLOW,Color.BLUE,Color.WHITE,Color.ORANGE,Color.GREEN,Color.PINK};    
        for(Color c : colores){
            JButton  negro = new JButton();
            negro.setBackground(c);
            negro.setForeground(c);
            negro.setOpaque(true);
            negro.addActionListener(evt->{
                color = negro.getBackground();
                p.repaint();
            });
            panelColores.add(negro);
        }
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.PAGE_AXIS));
        panelBotones.add(getJPanel(panelBotones, actual+iesimo++));
        JScrollPane scroll = new JScrollPane(panelBotones);
        scroll.setBackground(Color.white);
        add(p, BorderLayout.NORTH); 
        add(scroll, BorderLayout.EAST); 
        panel = new Panel( 1300, 985 , img);
        add(panel ,BorderLayout.CENTER );
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    
    private void botonGuardaraAction() {
        f = new JFrame();
        JPanel pan = new JPanel(new FlowLayout(FlowLayout.CENTER,1 , 50  )); 
        JPanel pane = new JPanel(new GridLayout(1,2,5,5));
        JTextField archivo = new JTextField();
        archivo.setSize( 500 ,50 );
        JButton boton = new JButton("Save");
        boton.addActionListener(new java.awt.event.ActionListener()
        {             public void actionPerformed(java.awt.event.ActionEvent evt) {
            guardarArchivo(archivo.getText());
        }

    });
        pane.add(archivo);        
        pane.add(boton);
        pan.add(pane);
        f.add(pan);         
        f.setBounds(50, 50 , 300, 200);
        f.setResizable(false);
        
        f.setVisible(true);
    }


    
    private void guardarArchivo(String texto) {

        File outputfile = new File("saves/" + texto + ".png");
        try{
            ImageIO.write(panel.getImage(), "png", outputfile);
        }catch(Exception e ){}
            f.dispose();

    }

    private void botonBorrarActionPerformed(java.awt.event.ActionEvent evt) {
        
        panel.eliminar();
        panel.repaint();
        iesimo = estado = 0 ;
        panelBotones.removeAll(); 
        panelBotones.repaint(); 
        
        }
    
        
    public void mouseClicked(MouseEvent e) {  
    }
    
    
    public void mouseEntered(MouseEvent e) {
        
      }
  

    public void mouseExited(MouseEvent e) {  } 
  
      
    public void mouseReleased(MouseEvent e) {
        punto2 = new Punto(e.getX(), e.getY()-130);

        if(punto1.distanciaCon(punto2) >= 2 ){
        
        int lad = 2 ; 
        try{
            lad = Integer.parseInt(lados.getText());
        }
        catch (NumberFormatException ex){
            lados.setText("Insert a number");
        }
        if( lad  <= 1)
        {   
            lados.setText("2");
        }
        Poligono  p  = new Poligono(
            punto1.puntoMedio(punto2),
            punto1, 
           lad,
            color , 
            (int)spinner.getValue(),
            segmentado.isSelected()
            );
        panel.addPoligono(actual + iesimo, p  );  
        panelBotones.add(getJPanel(panelBotones, actual+iesimo));
        panelBotones.revalidate();
        panelBotones.repaint();
          iesimo++;
        panel.graficarPoligonos();
    }
    }  
 

    public void mousePressed(MouseEvent e) {
        punto1 = new Punto(e.getX(), e.getY()-130);
        punto3 = punto1;
    }
      
    
    public JPanel  getJPanel(JPanel p, String nombre){
        JPanel jp  = new JPanel();
        JPanel panel = new JPanel(new GridLayout(1,5 , 5,5));
        JLabel label = new JLabel(nombre);
        JButton tra = new JButton("Tra");
        tra.addActionListener(evt->{
            this.nombre.setText(nombre);
            trasladar(nombre , punto3 );
            lados.setText("" +this.panel.getPoligono(nombre).getLados());

            tbxDimX.setText("" +this.panel.getPoligono(nombre).getCentro().getX());
            tbxDimY.setText("" +this.panel.getPoligono(nombre).getCentro().getY());
            
        });
        JButton esc = new JButton("Esc");
        esc.addActionListener(evt->{
            this.nombre.setText(nombre);
            lados.setText("" +this.panel.getPoligono(nombre).getLados());

            escalar(nombre , sliderY.getValue());
            tbxDimX.setText("" +this.panel.getPoligono(nombre).getCentro().getX());
            tbxDimY.setText("" +this.panel.getPoligono(nombre).getCentro().getY());
            
        });
        JButton rot = new JButton("Rot");
        rot.addActionListener(evt->{
            this.nombre.setText(nombre);
            rotar(nombre , sliderX.getValue());
            
            lados.setText("" +this.panel.getPoligono(nombre).getLados());
            tbxDimX.setText("" +this.panel.getPoligono(nombre).getCentro().getX());
            tbxDimY.setText("" +this.panel.getPoligono(nombre).getCentro().getY());
            
        });
        JButton eli = new JButton("Eli");
        eli.addActionListener(evt->{
            this.nombre.setText(nombre);
            eliminar(nombre);
        });
        panel.add(label );
        jp.add(panel);
        panel.add(tra);
        panel.add(esc);
        panel.add(rot);
        panel.setBounds(0,0,250,400);

        return jp;
    }

    
    public void trasladar (String nombre , Punto nuevoCentro ){     
        panel.trasladarPoligono(nombre, nuevoCentro);
        panel.graficarPoligonos();
    }
 

    public void rotar(String nombre , int angulo ){  
        panel.rotarPoligono(nombre, angulo );
        panel.graficarPoligonos();
    }
 

    public void escalar(String nombre , int factor ){    
        panel.escalarPoligono(nombre, factor);
        panel.graficarPoligonos();
    }
 
 
    public void eliminar(String nombre ){
        panel.eliminarPoligono(nombre);
        panelBotones.remove(Integer.parseInt(nombre.split(" ")[1]));
        panelBotones.repaint();
        panel.graficarPoligonos();
    }


private javax.swing.JFrame f;
private javax.swing.JPanel p, panelBotones;
private javax.swing.JButton botonBorrar;
        javax.swing.JTextField tbxDimX,tbxDimY,x0 , y0 , xn,yn, nombre , lados;
private javax.swing.JLabel lbl, lb;
private BufferedImage img;
private Panel panel;
private JSlider sliderX , sliderY;
private JSpinner spinner, spinner2;
private JRadioButton continuo, segmentado;
private int estado , iesimo; 
private Color color;  
private String actual; 
private Punto punto1 , punto2 , punto3 ; 

}    