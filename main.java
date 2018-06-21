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
    static int aux = 0;
    public static void main(String []args){

        JFrame f = new JFrame("ROSMI");
        JLabel label1, label2, label3, label4, label5, label6, label7, label8, label9;
        JPanel subPanel = new JPanel();
        // subPanel.setPreferredSize (new Dimension(400, 400));
        subPanel.setBackground (Color.BLACK);

        //ImageIcon imgicon = new ImageIcon(criarImagem()) ;

        label1 = new JLabel (criarImagem());
        label2 = new JLabel (criarImagem());
        label3 = new JLabel (criarImagem());
        label4 = new JLabel (criarImagem());
        label5 = new JLabel (criarImagem());
        label6 = new JLabel (criarImagem());
        label7 = new JLabel (criarImagem());
        label8 = new JLabel (criarImagem());
        label9 = new JLabel (criarImagem());
        
        subPanel.add (label1);
        subPanel.add (label2);
        subPanel.add (label3);
        subPanel.add (label4);
        subPanel.add (label5);
        subPanel.add (label6);
        subPanel.add (label7);
        subPanel.add (label8);
        subPanel.add (label9);
        
        f.setSize(917,917);
        f.setVisible(true);  
        f.add(subPanel);

        System.out.println("...Runing...");

    }

    private static ImageIcon criarImagem() {
        
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
        return new ImageIcon( buffer );
    }

}

