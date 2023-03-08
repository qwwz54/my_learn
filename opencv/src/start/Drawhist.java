package start;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.IOException;

public class Drawhist {
     static HistCanvas histCanvas = new HistCanvas();
    public void drawHistogram(String imgSrc){
        try{
            BufferedImage bfImg = ImageIO.read(new File(imgSrc));//图片缓冲区
            int w = bfImg.getWidth();//宽
            int h = bfImg.getHeight();//高
            int pix[] = new int[w*h];
            int hist[][] = new int[3][256];//rgb 二维

            int imgType = bfImg.getType();//图片类型
            int tempred,tempgreen,tempblue;
            bfImg.getRGB(0,0,w,h,pix,0,w);// --把bfImg中的每个像素变更成颜色模型的索引值存在pix中--

            ColorModel cm = ColorModel.getRGBdefault(); //创建一个可以获取颜色的模型

            //开始取rgb的灰度值并把他们存在hist当中
            for(int i=0;i<pix.length;i++){
                tempred = cm.getRed(pix[i]);
                tempblue = cm.getBlue(pix[i]);
                tempgreen = cm.getGreen(pix[i]);
                hist[0][tempred]++;
                hist[1][tempgreen]++;
                hist[2][tempblue]++;
            }
            for(int road=0;road<hist.length;road++){
                int max = 0;
                for(int i=0;i<hist[road].length;i++){//取出每一个通道的灰度值

                    if(hist[road][i]>max){
                        max = hist[road][i];//
                    }

                }
                for(int i=0;i<hist[road].length;i++) {
                    hist[road][i] = (int) (hist[road][i] / (float) max * 250) * 2;
                }
            }
            histCanvas.setHistPix(hist);
            histCanvas.repaint();

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
       new Drawhist().drawHistogram("D:\\my_learn\\java\\opencv\\images\\2.bmp");
        JFrame f = new JFrame();
        f.setSize(800,500);
        f.getContentPane().add(histCanvas);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
