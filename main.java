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

import java.util.Arrays;
import java.util.Scanner;

public class main{
    static int aux = 0;
    public static void main(String []args){

        // Scanner sysIn = new Scanner(System.in);

        BufferedImage [] imagens = new BufferedImage[9];

        for(int I=0;I<9;I++){
            imagens[I] = criarImagem();
        }

        showImages(reDraw(imagens));
        // showImages(imagens);
        System.out.println("...Runing...");

        // int A = sysIn.nextInt();
        // System.out.println("res: "+A);

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

    private static BufferedImage[] reDraw(BufferedImage[] images){
        
        BufferedImage [] newImages = cpBufferedArray(images);

        for(int I=0;I<newImages.length;I++){
            if(I+3 < 9){
                // images[I+3].createGraphics().drawImage(newImages[I], 0, 0, null);
                newImages[I].createGraphics().drawImage(images[I+3], 0, 0, null);
                // conteudo de newImagens[I] passa pra imagens[I+3]
            }
            if(I-2 > 0){
                newImages[I-2].createGraphics().drawImage(images[I], 0, 0, null);
                // images[I].createGraphics().drawImage(newImages[I-2], 0, 0, null);
                // conteudo de newImagens[i-2] recebe imagens[I]
            }
        }

        return newImages;

    }

    private static BufferedImage criarImagem() {
        
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

    //utils

    public static BufferedImage[] cpBufferedArray(BufferedImage[] imagens){

        BufferedImage []newArray = new BufferedImage[9];

        for(int I=0;I<9;I++){
            newArray[I] = deepCopy(imagens[I]);
        }

        return newArray;

    }

    static BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

}

