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

        System.out.println("ola");

        BufferedImage [] storedImagens = new BufferedImage[9];

        for(int I=0;I<processors.length;I++){
            this.processors[I].receiveImage(criarImagem());
            this.processors[I].start();
        }

        try{ 
            
			while(this.count< this.processors.length){ 
                // System.out.println("processors length: "+processors.length+" aux length: "+count);
                Thread.sleep(500);
            }
		}catch(InterruptedException e){ 
			notifyAll(); 
		}

    
        for(int I=0;I<processors.length;I++){
            storedImagens[I] = processors[I].imagem;
        }

        // Troca verde
        // for(int I=0;I<storedImagens.length;I++){
        //     if(I+2 < 9){
        //         if(I != 0 && I != 3 && I != 6){
        //             // newImages[I].createGraphics().drawImage(imgOverlap(images[I+2],images[I]), 0, 0, null);
        //             processors[I].receiveImage(processors[I+2].sendImage());
        //             // int A= I+2;
        //             // System.out.println(I+" -recebe- "+A);
        //         }
        //     }
        // }

        showImages(storedImagens);

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

}