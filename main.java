import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
public class main{
    public static void main(String[]args){
        JFrame frm = new JFrame("Rosmi");
        JPanel pan = new JPanel();
        JLabel lbl = new JLabel( criarImagem() );
        pan.add( lbl );
        frm.getContentPane().add( pan );
        frm.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frm.pack();
        frm.show();
    }

    private static ImageIcon criarImagens(){
        int width=900,height=600;
        BufferedImage buffer = new BufferedImage( width, height, BufferedImage.TYPE_INT_RGB );
        Graphics g = buffer.createGraphics();
        g.setColor( Color.WHITE );
        g.fillRect( 0, 0, width, height );
        g.setColor(Color.BLACK);
        g.drawImage(criarImagem(), 100, 1000, ImageObserver observer);
    }

    private static ImageIcon criarImagem() {
        // int width=1024, height=768;
        int width=300,height=300;
        BufferedImage buffer = new BufferedImage( width, height, BufferedImage.TYPE_INT_RGB );
        Graphics g = buffer.createGraphics();
        g.setColor( Color.WHITE );
        g.fillRect( 0, 0, width, height );
        g.setColor( Color.BLACK );
        // g.drawLine( 0, 0, width, height );
        g.drawOval(50, 50, 50, 50);
        g.drawRect(100, 100, 100, 100);
        g.create(150, 100, 250, 300);
        // drawRect(int x, int y, int width, int height)
        return new ImageIcon( buffer );
    }

}