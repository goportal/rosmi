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

public class CentralProcessor extends Thread{

    int count = 0;
    int aux = 0;
    Processor [] processors;

    public CentralProcessor(Processor[] tProcessors){
        this.processors = tProcessors;
    }

    public void run(){

        System.out.println("Start CP");

        for(int I=0;I<processors.length;I++){
            this.processors[I].receiveImage(criarImagem());
        }

        // for(int I=0;I<processors.length;I++){
        //     this.processors[I].start();
        // }

        BufferedImage [] storedImagens = new BufferedImage[9];
        BufferedImage [] theImagens = new BufferedImage[9];

        for(int I=0;I<processors.length;I++){
            theImagens[I] = processors[I].imagem;
            storedImagens[I] = criarImagem();
        }

        try{
			while(this.count< this.processors.length){
                // System.out.println("processors length: "+processors.length+" aux length: "+count);
                Thread.sleep(500);
            }
		}catch(InterruptedException E){
			notifyAll();
        }
        count = 0;
        // showImages(theImagens);
        
        
        // Troca verde             <------------------------------------------------------------------------------------
        for(int I=0;I<storedImagens.length;I++){
            if(I+2 < 9){
                if(I != 0 && I != 3 && I != 6){
                    // newImages[I].createGraphics().drawImage(imgOverlap(images[I+2],images[I]), 0, 0, null);
                    processors[I].receiveImage(processors[I+2].getImage());
                    // int A= I+2;
                    // System.out.println(I+" -recebe- "+A);
                }
            }
        }

        executarTarefas();
        count = 0;
        // showImages(theImagens);

        // Troca azul              <------------------------------------------------------------------------------------
        for(int I=0;I<storedImagens.length;I++){
            if(I+3 < 9){
                processors[I+3].receiveImage(processors[I].getImage());
                // newImages2[I+3].createGraphics().drawImage(imgOverlap(newImages[I],newImages[I+3]), 0, 0, null);
                // int A=I+3;
                // System.out.println(A+" -recebe- "+I);
                // if(I == 0 || I==1 || I==2){
                //     processors[I].receiveImage(NEWIMG());
                // }
            }
        }
        executarTarefas();
        count = 0;
        // showImages(theImagens);


        // Troca vermelha              <------------------------------------------------------------------------------------
        for(int I=0;I<storedImagens.length;I++){
            if(I+2 < 9){
                if(I == 0 || I == 3 || I == 6){
                    processors[I].receiveImage(processors[I+2].getImage());
                    // newImages3[I].createGraphics().drawImage(imgOverlap(newImages2[I+2],newImages2[I]), 0, 0, null);
                    // int A= I+2;
                    // System.out.println(I+" -recebe- "+A);
                }
            }
        }

        executarTarefas();
        count = 0;
        // showImages(theImagens);

        // segmentos complementares.              <------------------------------------------------------------------------------------
        for(int I=0;I<storedImagens.length;I++){
            processors[I].receiveImage(storedImagens[8-I]);
            // newImages4[I].createGraphics().drawImage(imgOverlap(storedImages[8-I],newImages4[I]), 0, 0, null);
            // int A= 8-I;
            // System.out.println(I+" -recebe- "+A);
        }

        executarTarefas();
        count = 0;
        // showImages(storedImagens);
        showImages(theImagens);

    }

    public synchronized void receiveImg(BufferedImage img){
        count++;
    }

    private BufferedImage criarImagem() {
        
        Draw draw = new Draw();

        // int width=1024, height=768;
        int width=300,height=300;
        BufferedImage buffer = new BufferedImage( width, height, BufferedImage.TYPE_INT_RGB );
        Graphics g = buffer.createGraphics();
        g.setColor( Color.WHITE );
        g.fillRect( 0, 0, width, height );
        g.setColor( Color.BLACK );
        // (posição x,posição y, tamanho x,tamanho y)

        int [] draws = draw.get(aux++);
        g.drawOval(draws[0], draws[1], draws[2], draws[3]);

        draws = draw.get(aux++);
        g.drawOval(draws[0], draws[1], draws[2], draws[3]);

        draws = draw.get(aux++);
        g.drawRect(draws[0], draws[1], draws[2], draws[3]);

        if(aux>= 27){
            aux=0;
        }

        return buffer;
    }

    private static void showImages(BufferedImage[] imagens){
        JFrame rosmiFrame = new JFrame("ROSMI");

        JPanel subPanel = new JPanel();

        subPanel.setBackground (Color.BLACK);

        JLabel [] labels = new JLabel[9];

        for(int I=0;I<9;I++){
            labels[I] = new JLabel (new ImageIcon(imagens[I]));
            subPanel.add (labels[I]);
        }

        rosmiFrame.setSize(917,917);

        rosmiFrame.setVisible(true);  

        rosmiFrame.add(subPanel);

    }

    public void executarTarefas(){
        for(int I=0;I<this.processors.length;I++){
            this.processors[I].executar = true;
        }

        try{ 
			while(this.count< this.processors.length){
                // System.out.println("processors length: "+processors.length+" aux length: "+count);
                Thread.sleep(100);
            }
		}catch(InterruptedException E){
			notifyAll();
		}

    }

    // Utils

    // public static BufferedImage[] cpBufferedArray(BufferedImage[] imagens){

    //     BufferedImage []newArray = new BufferedImage[9];

    //     for(int I=0;I<9;I++){
    //         newArray[I] = deepCopy(imagens[I]);
    //     }

    //     return newArray;

    // }

    // static BufferedImage deepCopy(BufferedImage bi) {
    //     ColorModel cm = bi.getColorModel();
    //     boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
    //     WritableRaster raster = bi.copyData(null);
    //     return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    // }


}