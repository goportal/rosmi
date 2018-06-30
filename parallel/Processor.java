import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.*;

public class Processor extends Thread{

    int id;
    BufferedImage receivedImage;
    BufferedImage imagem;
    boolean start = false;

    Processor(int sId){
        this.id = sId;
        this.imagem = new BufferedImage( 300, 300, BufferedImage.TYPE_INT_RGB );
        Graphics g = imagem.createGraphics();
        g.setColor( Color.WHITE );
        g.fillRect( 0, 0, 300, 300 );
    }

    public void run(){

        System.out.println("start pro");
        //Thread.yield();

        // while(!start){};
        System.out.println("received img");

        mergeImages(receivedImage, imagem);
        sendImage();

        System.out.println("ending pro");
        //Thread.yield();
        
    }

    public BufferedImage sendImage(){
        return this.imagem;
    }
    
    public void receiveImage(BufferedImage newImg){
        this.receivedImage = newImg;
    }

    public void mergeImages(BufferedImage img1, BufferedImage img2){

        for(int I=0;I<img1.getHeight();I++){
            for(int I2=0;I2<img1.getWidth();I2++){
                if(img1.getRGB(I, I2)!= -1){
                    img2.setRGB(I, I2, -16777216);
                    // System.out.println("o rgb que veio: "+img1.getRGB(I, I2));
                }
            }
        }

       // return img2;

    }

}