package start;

import java.awt.*;

//定义绘制直方图和二值图像的画布
public class HistCanvas extends Canvas {
    int hist[][]=null;
    //int pix[];
    //boolean flag=false;
    Color c[]= {Color.red,Color.blue,Color.green};
    int H_HEIGHT=700;
    int H_WIDTH=900;
    public void setHistPix(int[][] hist) {
        this.hist = hist;
    }
    Font font1=new Font("Microsoft Yahei",Font.ITALIC,18);
    Font font2=new Font("Microsoft Yahei",Font.BOLD,24);
    public void paint(Graphics g) {
        g.setFont(font2);
        System.out.println(H_HEIGHT-10);
        if(this.hist==null) {super.paint(g);return;}
        g.setColor(Color.black);
        g.drawLine(10, H_HEIGHT-50, H_WIDTH-30, H_HEIGHT-50);//横坐标的线
        g.drawLine(H_WIDTH-35, H_HEIGHT-55, H_WIDTH-30, H_HEIGHT-50);//横坐标的上箭头
        g.drawLine(H_WIDTH-35, H_HEIGHT-45, H_WIDTH-30, H_HEIGHT-50);//横坐标的下箭头
        g.drawString("灰度级", H_WIDTH-110, H_HEIGHT-60);//画横坐标的标注内容
        g.drawLine(10,  H_HEIGHT-50, 10, 10);//画纵坐标的线
        g.drawLine(5, 15, 10, 10);//画箭头
        g.drawLine(15, 15, 10, 10);//画箭头
        g.drawString("像素个数", 25, 40);//画纵坐标的标注

        for(int road=0;road<hist.length;road++) {
            g.setColor(c[road]);
            g.setFont(font1);
            for(int i=0; i<hist[road].length; i++) {
                g.drawLine(10+i*3, H_HEIGHT-50, 10+i*3, (int)(H_HEIGHT-50-hist[road][i]*1.2));//画出每个像素点的频率值
                System.out.println(H_HEIGHT-10-hist[road][i]);
                if(road==0 && i*2%30 == 0) {
                    g.setColor(Color.black);
                    g.drawString(i+"", 10+i*3, H_HEIGHT-20); //画出横坐标刻度
                    g.setColor(c[road]);
                }
            }

        }
        g.setColor(Color.black);
    }
}