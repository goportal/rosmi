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

        // basta comentar esse showImages e descomentar o abaixo
        showImages(imagens);
        try { Thread.sleep (1000); } catch (InterruptedException ex) {}
        showImages(reDraw(imagens));
        

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

        // Troca verde
        for(int I=0;I<newImages.length;I++){
            if(I+2 < 9){
                if(I != 0 && I != 3 && I != 6){
                    newImages[I].createGraphics().drawImage(imgOverlap(images[I+2],images[I]), 0, 0, null);
                    // int A= I+2;
                    // System.out.println(I+" -recebe- "+A);
                }
            }
        }

        BufferedImage [] newImages2 = cpBufferedArray(newImages);

        // showImages(newImages2);
        // try { Thread.sleep (1000); } catch (InterruptedException ex) {}

        // Troca azul
        for(int I=0;I<newImages.length;I++){
            if(I+3 < 9){
                newImages2[I+3].createGraphics().drawImage(imgOverlap(newImages[I],newImages[I+3]), 0, 0, null);
                // int A=I+3;
                // System.out.println(A+" -recebe- "+I);
            }

        }

        BufferedImage [] newImages3 = cpBufferedArray(newImages2);

        // showImages(newImages3);
        // try { Thread.sleep (1000); } catch (InterruptedException ex) {}

        // Troca vermelha
        for(int I=0;I<newImages.length;I++){
            if(I+2 < 9){
                if(I == 0 || I == 3 || I == 6){
                    newImages3[I].createGraphics().drawImage(imgOverlap(newImages2[I+2],newImages2[I]), 0, 0, null);
                    // int A= I+2;
                    // System.out.println(I+" -recebe- "+A);
                }
            }
        }

        return newImages3;

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

    //Utils

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

    static BufferedImage imgOverlap(BufferedImage img1, BufferedImage img2){

        BufferedImage imgAux = deepCopy(img2);

        for(int I=0;I<img1.getHeight();I++){
            for(int I2=0;I2<img1.getWidth();I2++){
                if(img1.getRGB(I, I2)!= -1){
                    imgAux.setRGB(I, I2, -16777216);
                    // System.out.println("o rgb que veio: "+img1.getRGB(I, I2));
                }
            }
        }

        return imgAux;

    }

}

