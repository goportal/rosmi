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
public class main{

    public static void main(String args[]){
        JFrame f = new JFrame("ROSMI");
        JLabel label1, label2, label3, label4,label6, label5;
        JPanel subPanel = new JPanel();
        // subPanel.setPreferredSize (new Dimension(400, 400));
        subPanel.setBackground (Color.BLACK);

        //ImageIcon imgicon = new ImageIcon(criarImagem()) ;

        ImageIcon imgicon = criarImagem();

        label1 = new JLabel (imgicon);
        label2 = new JLabel (imgicon);
        label3 = new JLabel (imgicon);
        label4 = new JLabel (imgicon);
        label5 = new JLabel (imgicon);
        label6 = new JLabel (imgicon);
        
        subPanel.add (label1);
        subPanel.add (label2);
        subPanel.add (label3);
        subPanel.add (label4);
        subPanel.add (label5);
        subPanel.add (label6);
        
        f.setSize(917,917);
        f.setVisible(true);  
        f.add(subPanel);
        System.out.println("...Runing...");
    }

    // public static void main(String[]args){
    //     JFrame frm = new JFrame("Rosmi");
    //     JPanel pan = new JPanel();
    //     JLabel lbl = new JLabel( criarImagem() );
    //     pan.add( lbl );
    //     pan.add( lbl );
    //     frm.getContentPane().add( pan );
    //     frm.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    //     frm.pack();
    //     frm.show();
    // }

    private static ImageIcon criarImagem() {
        // int width=1024, height=768;
        int width=300,height=300;
        BufferedImage buffer = new BufferedImage( width, height, BufferedImage.TYPE_INT_RGB );
        Graphics g = buffer.createGraphics();
        g.setColor( Color.WHITE );
        g.fillRect( 0, 0, width, height );
        g.setColor( Color.BLACK );
        // (posição x,posição y, tamanho x,tamanho y)
        g.drawOval(50, 120, 75, 100);
        g.drawOval(120, 50, 100, 75);
        g.drawRect(200, 200, 200, 200);
        g.create(250, 100, 300, 300);
        return new ImageIcon( buffer );
    }

    private static ImageIcon criarImagem2() {
        // int width=1024, height=768;
        int width=300,height=300;
        BufferedImage buffer = new BufferedImage( width, height, BufferedImage.TYPE_INT_RGB );
        Graphics g = buffer.createGraphics();
        g.setColor( Color.WHITE );
        g.fillRect( 0, 0, width, height );
        g.setColor( Color.BLACK );
        // (posição x,posição y, tamanho x,tamanho y)
        g.drawOval(50, 50, 50, 50);
        g.drawRect(100, 100, 100, 100);
        g.create(200, 100, 250, 300);
        return new ImageIcon( buffer );
    }

}