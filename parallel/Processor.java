import java.awt.image.BufferedImage;
import java.util.concurrent.locks.ReentrantLock;
import java.awt.Color;
import java.awt.*;

public class Processor extends Thread{

    CentralProcessor cProcessor;
    int id;
    BufferedImage receivedImage;
    BufferedImage imagem;
    boolean executar = true;
    ReentrantLock lock = new ReentrantLock();

    Processor(int sId,CentralProcessor cp){
        this.cProcessor = cp;
        this.id = sId;

        this.imagem = new BufferedImage( 300, 300, BufferedImage.TYPE_INT_RGB );
        Graphics g = imagem.createGraphics();
        g.setColor( Color.WHITE );
        g.fillRect( 0, 0, 300, 300 );
    }

    public void run(){

        while(true){
            if(this.id == 1){
                // Thread.yield();
                try{ Thread.sleep(3000);} catch(Exception E){};
            }
            Thread.yield();
            while(!executar){}
            System.out.println("processor: "+this.id);
            // Thread.yield();

            

            // while(!start){};
            // System.out.println("received img");

            mergeImages(receivedImage, imagem);
            sendImage();

            // System.out.println("ending pro");
            //lock.unlock();
            executar = false;
        }
        
    }

    public void sendImage(){
        cProcessor.receiveImg(imagem);
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