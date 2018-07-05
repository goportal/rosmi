
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
import util.DiscreteEvent;

public class Processor extends Thread{

    CentralProcessor cProcessor;
    int id;
    BufferedImage receivedImage;
    BufferedImage imagem;
    boolean executar = false;
    boolean ligado = true;

    Processor(int sId,CentralProcessor cp){
        this.cProcessor = cp;
        this.id = sId;

        this.imagem = new BufferedImage( 1024, 256, BufferedImage.TYPE_INT_RGB );
        Graphics g = imagem.createGraphics();
        g.setColor( Color.WHITE );
        g.fillRect( 0, 0, 1024, 256);
    }



    public void run(){
        System.out.println("Start PE" + id);
        while(ligado){
            
            while(!executar){
                try{
                    Thread.sleep(500);
                }catch(Exception X){
                    System.out.println("ERROR: "+X.getMessage());
                }
            }

            mergeImages(receivedImage, imagem);
            returnImage();

            executar = false;
        }
        System.out.println("Ending PE" + id);
    }


    public void sendTo(BufferedImage aimagem, Processor processador){
        DiscreteEvent.add(getNome(),processador.getNome(),calculaSize(aimagem));
        processador.receiveImage(aimagem);  
    }

    public void returnImage(){
        //DiscreteEvent.add(getNome(),cProcessor.nome,calculaSize(imagem));
        cProcessor.receiveImg(imagem);
    }
    
    public synchronized void receiveImage(BufferedImage newImg){
        this.receivedImage = deepCopy(newImg);
        this.executar = true;
    }

    public BufferedImage getImage(){
        return this.imagem;
    }

    public void mergeImages(BufferedImage img1, BufferedImage img2){

        for(int I=0;I<img1.getWidth();I++){
            for(int I2=0;I2<img1.getHeight();I2++){
                if(img1.getRGB(I, I2)!= -1){
                    img2.setRGB(I, I2, img1.getRGB(I, I2));
                }
            }
        }
    }

    /*
    -14671840
    -8421505
    -2105377
    */

    //UTILS

    public int calculaSize(BufferedImage img){
        int tam = 1;
        for(int x=0;x<img.getWidth();x++){
            for(int y=0;y<img.getHeight();y++){
                if(img.getRGB(x, y) != -1){
                    tam++;
                }
            }
        } 
        
        return tam; 
    }

    static BufferedImage deepCopy(BufferedImage bi) {
        BufferedImage img = new BufferedImage(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_INT_RGB);

        for(int x=0;x<bi.getWidth();x++){
            for(int y=0;y<bi.getHeight();y++){
                img.setRGB(x, y, bi.getRGB(x, y));
            }
        } 

        return img;

        // ColorModel cm = bi.getColorModel();
        // boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        // WritableRaster raster = bi.copyData(null);
        // return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    public String getNome(){
        return "PE_"+this.id;
    }

    // public int recognize(){

    //     int quantia = 0;
  
    //     // load and convert the image into a usable format
    //     // BufferedImage image = UtilImageIO.loadImage(UtilIO.pathExample("particles01.jpg"));
    //     GrayF32 input = ConvertBufferedImage.convertFromSingle(imagem, null, GrayF32.class);

    //     GrayU8 binary = new GrayU8(input.width,input.height);

    //     // the mean pixel value is often a reasonable threshold when creating a binary image
    //     double mean = ImageStatistics.mean(input);

    //     // create a binary image by thresholding
    //     ThresholdImageOps.threshold(input, binary, (float) mean, true);

    //     // reduce noise with some filtering
    //     GrayU8 filtered = BinaryImageOps.erode8(binary, 1, null);
    //     filtered = BinaryImageOps.dilate8(filtered, 1, null);

    //     // Find the contour around the shapes
    //     List<Contour> contours = BinaryImageOps.contour(filtered, ConnectRule.EIGHT,null);

    //     // Fit an ellipse to each external contour and draw the results
    //     Graphics2D g2 = image.createGraphics();
    //     g2.setStroke(new BasicStroke(3));
    //     g2.setColor(Color.RED);

    //     for( Contour c : contours ) {
    //         FitData<EllipseRotated_F64> ellipse = ShapeFittingOps.fitEllipse_I32(c.external,0,false,null);
    //         VisualizeShapes.drawEllipse(ellipse.shape, g2);
    //         quantia++;
    //     }

    //     return quantia;

    // }

}