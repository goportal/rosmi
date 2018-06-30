import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class CentralProcessor extends Thread{

    int aux = 0;
    Processor []processors;

    public CentralProcessor(Processor[] tProcessors){
        this.processors = tProcessors;
    }

    public void run(){

        System.out.println("ola");

        BufferedImage [] storedImagens = new BufferedImage[9];

        for(int I=0;I<processors.length;I++){
            this.processors[I].receiveImage(criarImagem());
        }

        for(int I=0;I<processors.length;I++){
            this.processors[I].run();
            this.processors[I].start = true;
        }

        for(int I=0;I<processors.length;I++){
            storedImagens[I] = processors[I].imagem;
        }

        try{ Thread.sleep(2000);} catch(Exception E){};

        showImages(storedImagens);

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

        // g.create(200, 100, 300, 300);
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