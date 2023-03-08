package start.mytest;

import javax.swing.*;
import java.awt.*;

public class demo01 extends Canvas{
    Image img;

    private String imgSrc = "images/1.png";

    public demo01(){

        img=Toolkit.getDefaultToolkit().createImage(imgSrc);//取图像
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(img,0,0,0,0,0,0,0,0,null);
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setSize(800,500);
        f.getContentPane().add(new demo01());
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
