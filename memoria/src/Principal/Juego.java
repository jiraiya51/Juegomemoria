package Principal;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author juan alberto
 */
public class Juego {
    
//atributo
JFrame ventana;
//presentacion
JPanel panelPresentacion;
JLabel fondoPresentacion;
JLabel BotonJugar;

//juego
JPanel panelJuego;
JLabel fondoJuego;
JLabel matriz[][];
int mat [][];
int matAux [][];
String Jugador;
Random aleatorio;
JLabel nombreJugador;
Timer tiempo;
JLabel cronometro;
int min;
int seg;
int contador;
Timer tiempoespera;
int contSeqEsp;
Timer tiempoespera1;
int ban;
int ban1;

int antnum;
int antx;
int anty;
int actualnum;
int actualx;
int actualy;
       




//constructor
public Juego(){

    //Ventana
    ventana = new JFrame("Juego de Memoria");
    ventana.setSize(600, 600);
    ventana.setLayout(null);
    ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ventana.setLocationRelativeTo(null);
    ventana.setResizable(false);
            
    
    //Jpanel presentacion 
    panelPresentacion = new JPanel();
    panelPresentacion.setSize(ventana.getWidth(), ventana.getHeight());//tamaño de la ventana
    panelPresentacion.setLocation(0, 0);
    panelPresentacion.setLayout(null);
    panelPresentacion.setVisible(true); 
    ventana.add(panelPresentacion, 0);
       //visibilidad de la ventana     
   
       
       
       
       //Fondo presentacion
       fondoPresentacion = new JLabel();
       fondoPresentacion.setSize(ventana.getWidth(), ventana.getHeight());
       fondoPresentacion.setLocation(0, 0); //Donde iniciara la imagen
       fondoPresentacion.setIcon(new ImageIcon("Imagenes/portada.jpg"));
       fondoPresentacion.setVisible(true);
       panelPresentacion.add(fondoPresentacion, 0);
      
       fondoPresentacion = new JLabel();
       fondoPresentacion.setSize(83,33);//tamaño de la imagen 
       fondoPresentacion.setLocation(250, 180); //Donde iniciara la imagen
       fondoPresentacion.setIcon(new ImageIcon("Imagenes/PLAY1.png"));
       fondoPresentacion.setVisible(true);
       panelPresentacion.add(fondoPresentacion, 0); 
      
       
      //---------------------
       //boton de jugar
       BotonJugar = new JLabel();
       BotonJugar.setSize(200, 200);
       BotonJugar.setLocation(223,180 ); //Donde iniciara la imagen
       BotonJugar.setIcon(new ImageIcon("Imagenes/hueso.jpg"));
       BotonJugar.setVisible(true);
       panelPresentacion.add(BotonJugar, 0);
      
      
      //JPanel jugar
    panelJuego = new JPanel();
    panelJuego.setSize(ventana.getWidth(), ventana.getHeight());//tamaño de la ventana
    panelJuego.setLocation(0, 0);
    panelJuego.setLayout(null);
    panelJuego.setVisible(true);
    
    //fondo de juego
     fondoJuego = new JLabel();
     fondoJuego.setSize(ventana.getWidth(), ventana.getHeight());
     fondoJuego.setLocation(0, 0); //Donde iniciara la imagen
     fondoJuego.setIcon(new ImageIcon("Imagenes/fondojuego.png"));
     fondoJuego.setVisible(true);
     panelJuego.add(fondoJuego, 0);
   
    //nombre de jugador
     nombreJugador= new JLabel();
     nombreJugador.setSize(150, 20);
     nombreJugador.setLocation(100, 10); //Donde iniciara la imagen
     nombreJugador.setForeground(Color.BLUE);
     nombreJugador.setVisible(true);
     panelJuego.add(nombreJugador, 0);
   
     //cronometro 
     cronometro= new JLabel();
     cronometro.setSize(150, 20);
     cronometro.setLocation(ventana.getWidth()-100, 10); //Donde iniciara la imagen
     cronometro.setForeground(Color.BLUE);
     cronometro.setVisible(true);
     panelJuego.add(cronometro, 0);
     
    //matriz logica
    mat = new int[4][4]; 
    matAux = new int[4][4];
    aleatorio = new Random();
    this.numerosAleatorios();
    
        //matriz de imagenes
     matriz = new JLabel[4][4];
     for (int i = 0; i < 4; i++) {
         for (int j = 0; j < 4; j++) {
             matriz[i][j] =  new JLabel();
                                              //asi arriba 175
             matriz[i][j].setBounds(183+(i*64), 175+(j*67), 64, 64);
             matriz[i][j].setIcon(new ImageIcon("Imagenes/"+matAux[i][j]+".png"));
             matriz[i][j].setVisible(true);
             panelJuego.add(matriz[i][j],0);
         
         }
    }
    //tiempo
     min = 0;
     seg = 0;
     
     tiempo = new Timer (1000, new ActionListener()
     {
         
         public void actionPerformed(ActionEvent e)
         {
             seg++;
             if(seg == 60){
                 min++;
                 seg = 0;
             }
             cronometro.setText("tiempo "+min+" : "+seg);
             
             
     }});
     //tiempo de espera
     contSeqEsp = 0;
     tiempoespera = new Timer (1000, new ActionListener()
     {
         
         public void actionPerformed(ActionEvent e)
         {
           contSeqEsp++;
     }});
     tiempoespera.start();
     tiempoespera.stop();
     contSeqEsp = 0;
     ban = 0;
     ban1 = 0;
     
     
  // evennto de clic de las cartas
  
    contador = 0;
       for (int i = 0; i < 4; i++) {
           for (int j = 0; j < 4; j++) {
                matriz[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e){
                        for (int k = 0; k < 4; k++) {
                            for (int l = 0; l < 4; l++) {
                                if(e.getSource() ==  matriz[k][l]){
                                    //pociones de figura 
                                    //System.out.println(k+" "+l);
                                    
                                   
                                    if(matAux[k][l] == 0 && contador !=2){
                                    matAux[k][l] = mat[k][l];
                                    matriz[k][l].setIcon(new ImageIcon("Imagenes/"+matAux[k][l]+".png"));
                                    contador++;
                                    actualnum = mat[k][l];
                                    actualx = k;
                                    actualy = l;
                                    if(contador == 1){
                                        antnum = mat[k][l];
                                        antx = k;
                                        anty = l;
                                    }
                                    
                                   
                                       
                                           tiempoespera1 = new Timer (500, new ActionListener()
                                             {
         
                                                public void actionPerformed(ActionEvent e)
                                            {
                                                 
                                            if(contador == 2 && ban1 == 0){
                                                tiempoespera.restart();
                                                ban1 = 1;
                                            }
                                            
                                            if(contador == 2 && contSeqEsp == 1){
                                                tiempoespera.stop();
                                                contSeqEsp = 0;
                                                if(matAux[actualx][actualy] == matAux[antx][anty]){
                                                    matAux[actualx][actualy] = -1;
                                                    matAux[antx][anty] = -1;
                                                    //voltiar la tarjetas
                                                      matriz[actualx][actualy].setIcon(new ImageIcon("Imagenes/"+matAux[actualx][actualy]+".png"));
                                                       matriz[antx][anty].setIcon(new ImageIcon("Imagenes/"+matAux[antx][anty]+".png"));
                                                       contador = 0;
                                                       
                                                    //ganador
                                                    int acum = 0;
                                                    for(int m = 0; m < 4; m++){
                                                     for(int n = 0; n < 4; n++){
                                                      if(matAux[m][n]== -1);
                                                          acum++;
                                                          System.out.println("cumulador es: "+acum);
                                                     }
                                                    }
                                                
                                                   
                                                    
                                                    if(acum == 20){
                                                         System.out.println("cumulador2 es: "+acum);
                                                        tiempo.stop();
                                                        JOptionPane.showMessageDialog(ventana,"FELICITACIONES GANO con un tiempo: "+min+":"+seg);
                                                        tiempo.restart();
                                                        for (int m = 0; m < 4; m++) {
                                                            for (int n = 0; n < 4; n++) {
                                                                mat[m][n] = 0;
                                                                matAux[m][n] = 0;
                                                               matriz[m][n].setIcon(new ImageIcon("Imagenes/"+matAux[m][n]+".png"));
                                                     
                                                              //volver a rellenar 
                                                               // for
                                                                
                                                            }
                                                            
                                                        }
                                                        numerosAleatorios();
                                                    }
                                                }
                                                
                                                
                                                
                                        for(int m = 0; m < 4; m++){
                                            for(int n = 0; n < 4; n++){
                                                System.out.print(matAux[m][n]+" ");
                                                if (matAux[m][n] != 0 && matAux[m][n] !=-1) {
                                                      matAux[m][n] = 0;
                                                      matriz[m][n].setIcon(new ImageIcon("Imagenes/"+matAux[m][n]+".png"));
                                                      System.out.println("hola");
                                                      contador = 0; 
                                                              
                                                    }
                                                
                                                }
                                            System.out.println("");
                                     
                                            } 
                                           tiempoespera1.stop();
                                           ban1=0;
                                          }
                                           ////-------
                                        }});
                                           if(ban == 0){
                                               tiempoespera1.start();
                                               ban = 1;
                                           }
                                        if(contador == 2)
                                          tiempoespera1.restart();
                                    
                                }
                                
                                }
                            }
                            
                        }
                    }
             });
               
               
           }
          
    }
 
     
     
     
    
    
    
      BotonJugar.addMouseListener(new MouseAdapter() {
       @Override
       public void mousePressed(MouseEvent e){
         System.out.println("presione el boton");
         //Nombre del jugador
          Jugador = JOptionPane.showInputDialog(ventana,"Nombre del jugador", "Escibe aqui");
           while (Jugador == null || Jugador.compareTo("Escribe aqui")==0 || Jugador.compareTo("")==0) {
               Jugador = JOptionPane.showInputDialog(ventana, "Debs ingresar usuario", "Escribe aqui");
               
           }
         nombreJugador.setText("Jugador:  " + Jugador);
         tiempo.start();
         panelPresentacion.setVisible(false);
         ventana.add(panelJuego);
         panelJuego.setVisible(true);
         
       }
      });
      
      
       ventana.add(panelPresentacion);  
       ventana.setVisible(true);
       
    
}
     public void numerosAleatorios (){
         int acumulador = 0;
         for (int i = 0; i < 4; i++)
             for (int j = 0; j < 4; j++){
                 mat[i][j] = 0;
                 matAux[i][j] = 0 ;
             }
         
         for (int i = 0; i < 4; i++){
             for (int j = 0; j < 4; j++){
                 mat[i][j] = aleatorio.nextInt(8)+1;
                 
                 do{
                     acumulador = 0;
                 
                    for (int k = 0; k < 4; k++){
                         for (int l = 0; l < 4; l++){
                             if (mat[i][j] == mat[k][l]){
                             acumulador +=1;
                            }
                         
                     
                        }
                 
                    }
                 if(acumulador == 3){
                      mat[i][j] = aleatorio.nextInt(8)+1;
                 }
         
             } while(acumulador ==3);
         }
        } 
         
         for (int i = 0; i < 4; i++){
             for (int j = 0; j < 4; j++){
                 System.out.print(mat[i][j]+"  ");
                 
             
             }
             System.out.println("");
         }

     
     
    }
     



}