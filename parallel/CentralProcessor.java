import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.imageio.*;
import java.io.*;

import util.DiscreteEvent;

public class CentralProcessor extends Thread{

    int count = 0;
    int aux = 0;

    String nome = "CP";
    Processor [][] processors;
    BufferedImage imagem;

    public CentralProcessor(Processor[][] tProcessors){
        this.processors = tProcessors;
    }

    public void run(){


        try{
            imagem = (BufferedImage)ImageIO.read(new File("imagem.bmp"));
        }catch(Exception X){
            System.out.println("ERROR: "+X.getMessage());
        }


        BufferedImage imagens[][] = segmentaImagem(imagem);

        for(int x=0;x<imagens.length;x++){
            for(int y=0;y<imagens[x].length;y++){
                sendTo(imagens[x][y],processors[x][y]);
                processors[x][y].executar = true;
            }
        }

        // executar();

        try{
			while(this.count< this.processors.length * this.processors[0].length){
                Thread.sleep(500);
            }
		}catch(InterruptedException E){
			notifyAll();
        }
        count = 0;
    


        // Troca verde             <------------------------------------------------------------------------------------
        
        
        for(int x=0;x<imagens.length;x++){
            for(int y=0;y<imagens[x].length;y++){
                if(x+1<3 && y-3>=0 ){
                    processors[x][y].sendTo(processors[x][y].imagem, processors[x+1][y-3]);
                    processors[x][y].executar = true;
                }else{
                    count++;
                }
            }
        } 

        // executar();

        try{
			while(this.count< this.processors.length * this.processors[0].length){
                Thread.sleep(500);
            }
		}catch(InterruptedException E){
			notifyAll();
        }
        count = 0;
        



        // Troca azul              <------------------------------------------------------------------------------------

        for(int x=0;x<imagens.length;x++){
            for(int y=0;y<imagens[x].length;y++){
                if(y+3<9 ){
                    processors[x][y].sendTo(processors[x][y].imagem, processors[x][y+3]);
                    processors[x][y].executar = true;
                }else{
                    count++;
                }
            }
        } 

        // executar();

        try{
			while(this.count< this.processors.length * this.processors[0].length){
                Thread.sleep(500);
            }
		}catch(InterruptedException E){
			notifyAll();
        }
        count = 0;


 
        // Troca vermelha              <------------------------------------------------------------------------------------
 
        
        for(int x=0;x<imagens.length;x++){
            for(int y=0;y<imagens[x].length;y++){
                if( x==3 ){
                    processors[x][y].sendTo(processors[x][y].imagem, processors[x-2][y]);
                    processors[x][y].executar = true;
                }else{
                    count++;
                }
            }
        } 

        // executar();

        try{
			while(this.count< this.processors.length * this.processors[0].length){
                // System.out.println("processors length: "+processors.length+" aux length: "+count);
                Thread.sleep(500);
            }
		}catch(InterruptedException E){
			notifyAll();
        }
        count = 0;

        //     
        BufferedImage storedImagens[][] = segmentaImagem(imagem);


        // segmentos complementares.              <------------------------------------------------------------------------------------
        
        for(int x=0;x<imagens.length;x++){
            for(int y=0;y<imagens[x].length;y++){

                processors[x][y].sendTo(storedImagens[x][y], processors[complex(x)][compley(y)]);
                processors[x][y].executar = true;

            }
        }   

        // executar();

        try{
			while(this.count< this.processors.length * this.processors[0].length){
                // System.out.println("processors length: "+processors.length+" aux length: "+count);
                Thread.sleep(500);
            }
		}catch(InterruptedException E){
			notifyAll();
        }   
        count = 0;
        

        getImgProcessors();
        
        System.out.println(DiscreteEvent.showEventList());


    }

    // public void executar(){
    //     for(int I=0;I<3;I++){
    //         for(int I2=0;I2<9;I++){
    //             processors[I][I2].executar = true;
    //         }
    //     }
    // }

    public synchronized void receiveImg(BufferedImage img){
        count++;
    }

    public void sendTo(BufferedImage aimagem, Processor processador){
        DiscreteEvent.add(this.nome,processador.getNome(),calculaSize(aimagem));
        processador.receiveImage(aimagem);
    }


    public void getImgProcessors(){
        BufferedImage [][] imgs = new BufferedImage[3][9];
        for(int x=0;x<processors.length;x++){
            for(int y=0;y<processors[x].length;y++){
                imgs[x][y] = processors[x][y].imagem;
            }
        }
        showImagem(juntaSegmentos(imgs));
    }

    private static void showImagem(BufferedImage imagem){
        JFrame rosmiFrame = new JFrame("ROSMI");

        JPanel Panel = new JPanel();

        Panel.setBackground (Color.BLACK);

        // esta com resolução alta, so esta em escala

        rosmiFrame.setSize(1024,768);
        JLabel label = new JLabel();
        label.setIcon(new ImageIcon(imagem.getScaledInstance(rosmiFrame.getWidth(),rosmiFrame.getHeight(), Image.SCALE_DEFAULT)));

        Panel.add(label);

        rosmiFrame.setVisible(true);  

        rosmiFrame.add(Panel);

    }

    private static void showImagens(BufferedImage[][] imagens){
        JFrame rosmiFrame = new JFrame("ROSMI");

        JPanel Panel = new JPanel();

        Panel.setBackground (Color.BLACK);

        // esta com resolução alta, so esta em escala

        rosmiFrame.setSize(1000,1000);

        JLabel jlabels[][] = new JLabel[3][9];

        for(int x=0;x<imagens.length;x++){
            for(int y=0;y<imagens[x].length;y++){
                jlabels[x][y] = new JLabel();
                jlabels[x][y].setIcon(new ImageIcon(imagens[x][y].getScaledInstance(100,70, Image.SCALE_DEFAULT)));
                Panel.add(jlabels[x][y]);
            }
        }
        rosmiFrame.setVisible(true);

        rosmiFrame.add(Panel);

    }


    // Utils

    public BufferedImage[][] segmentaImagem(BufferedImage imagem){
        BufferedImage[][] imagens = new BufferedImage [3][9];

        int tamx = imagem.getWidth()/3;
        int tamy = imagem.getHeight()/9;

        for(int x=0;x<imagens.length;x++){
            for(int y=0;y<imagens[x].length;y++){
                // System.out.println("img: "+(x)*tamx+" calc2: "+(y)*tamy+"   tamx: "+ tamx+"   tamy: "+ tamy);
                imagens[x][y] = imagem.getSubimage((x)*tamx, (y)*tamy, tamx, tamy);
            }
        }

        return imagens;
    }

    public BufferedImage juntaSegmentos(BufferedImage[][] aimagens){

        BufferedImage imagem = new BufferedImage(3072,2304,BufferedImage.TYPE_INT_RGB);

        int tamx = imagem.getWidth()/3;
        int tamy = imagem.getHeight()/9;

        for(int x=0;x<aimagens.length;x++){
            for(int y=0;y<aimagens[x].length;y++){
                // System.out.println("img: "+(x)*tamx+" calc2: "+(y)*tamy+"   tamx: "+ tamx+"   tamy: "+ tamy);
                drawInImg(imagem, aimagens[x][y], (x)*tamx, (y)*tamy);
            }
        }

        return imagem;
    }

    public void drawInImg(BufferedImage img, BufferedImage dimg, int x, int y){
        for(int ix=0;ix<dimg.getWidth();ix++){
            for(int iy=0;iy<dimg.getHeight();iy++){
                // System.out.println("x+ix: "+a+"   y+iy: "+b);
                img.setRGB(x+ix, y+iy, dimg.getRGB(ix, iy));
            }
        }
    }

    public int calculaSize(BufferedImage img){
        int tam = 1;
        for(int x=0;x<img.getWidth();x++){
            for(int y=0;y<img.getHeight();y++){
                if(img.getRGB(x, y) != -1){
                    // System.out.println("here: "+img.getRGB(x, y));
                    tam++;
                }
            }
        } 
        
        return tam; 
    }

    public int complex(int n){
        if(n==0){
            return 2;
        }
        if(n==1){
            return 1;
        }
        if(n==2){
            return 0;
        }
        return -1;
    }

    public int compley(int n){
        if(n == 3 || n==4 || n==5){
            return n;
        }
        if(n == 0){
            return 6;
        }
        if(n == 1){
            return 7;
        }
        if(n == 2){
            return 8;
        }
        if(n == 6){
            return 0;
        }
        if(n == 7){
            return 1;
        }
        if(n == 8){
            return 2;
        }
        return -1;
    }

}