package start;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Main extends JFrame {
    public static final int WIDTH=750+900;
    public static final int HEIGHT=900;
    public static final int PWIDTH=750;
    public static final int PHEIGHT=900;
    public static final int HWIDTH=900;
    public static final int HHEIGHT=700;
    public static final int MAX=100;
    public static final String TITLE="绘制直方图";
    private String imgSrc;
    Image img;
    BufferedImage bimg;
    JSlider jsH,jsV;
    JPanel up,picp,histp;
    JButton openFile,showHist,showTH;
    MyCanvas canvas;
    HistCanvas histCanvas;
    int imgw=PWIDTH,imgh=PHEIGHT;
    int xcenter=PWIDTH/2,ycenter=PHEIGHT/2;
    private int dx1=xcenter-imgw/2,dy1=ycenter-imgh/2,dx2=xcenter+imgw/2,dy2=ycenter+imgh/2;
    private int sx1=0,sy1=0,sx2,sy2;
    Font font=new Font("Microsoft Yahei",Font.BOLD,24);
    public Main(){
        this.setTitle(TITLE);
        init();
    }
    public void init(){
        imgSrc="D:\\my_learn\\java\\opencv\\images\\2.png";

        setImgSize();
        canvas=new MyCanvas();
        jsH=new JSlider();jsV=new JSlider();
        jsH.setMaximum(MAX);jsV.setMaximum(MAX);
        jsH.setValue(MAX/2);jsV.setValue(MAX/2);
        jsH.setOrientation(JSlider.HORIZONTAL);jsV.setOrientation(JSlider.VERTICAL);

         picp=new JPanel();
         picp.setPreferredSize(new Dimension(PWIDTH,PHEIGHT));
         up=new JPanel();
         openFile =new JButton("打开图片");openFile.setFont(font);
         showHist=new JButton("显示直方120图");showHist.setFont(font);
         showTH=new JButton("显示二值图片");showTH.setFont(font);
         Box control=Box.createHorizontalBox();
         control.add(openFile);control.add(showHist);control.add(showTH);
         picp.setLayout(new BorderLayout());picp.add("Center",canvas);
        up.setLayout(new BorderLayout());

        up.add("Center",picp);up.add("East",jsV);up.add("South",jsH);
         histp=new JPanel();
         histp.setPreferredSize(new Dimension(HWIDTH,HHEIGHT));
         histCanvas=new HistCanvas();
         histp.setLayout(new BorderLayout());histp.add("Center",histCanvas);

        Container c=this.getContentPane();
        Box hbox=Box.createHorizontalBox();
        Box vhox=Box.createVerticalBox();
        c.add(vhox);
        hbox.add(up);hbox.add(histp);
        vhox.add(hbox);vhox.add(control);
        this.setSize(WIDTH,HEIGHT);
//        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        openFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFile();
            }
        });
        jsV.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                resize(e);
            }
        });
        jsH.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                resize(e);
            }
        });
        showHist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawHistogram(imgSrc);
            }
        });
    }

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
            histp.add(histCanvas);

        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void resize(ChangeEvent e){
        if (e.getSource()==jsV){
            float valueH=jsV.getValue();
            int nowh=(int)(2*imgh*valueH/MAX);
            dy1=ycenter-nowh/2;dy2=ycenter+nowh/2;
            canvas.repaint();
        }else if(e.getSource()==jsH){
            float valueW=jsH.getValue();
            System.out.println(valueW);
            int nowW=(int)(2*imgw*valueW/MAX);
            dx1=xcenter-nowW/2;dx2=xcenter+nowW/2;
            canvas.repaint();

        }
    }
    //打开文件
    public void openFile(){
        FileDialog opendialog=new FileDialog(this,"打开图片");
        opendialog.setMode(FileDialog.LOAD);
        opendialog.setFile("*.jpg;*.png;*jpeg;*.bmp;*.gif");
        opendialog.setVisible(true);
        String fileName=opendialog.getFile();
        String dir=opendialog.getDirectory();
        if (fileName!=null){
            imgSrc=dir+fileName;
            setImgSize();
            canvas.repaint();
        }else{
            JOptionPane.showMessageDialog(this,"您已经取消了的选择");
        }
    }

        public void setImgSize(){
            img=Toolkit.getDefaultToolkit().getImage(imgSrc);
            try {
                bimg= ImageIO.read(new FileInputStream(imgSrc));
            } catch (IOException e) {
                e.printStackTrace();
            }
            int h=bimg.getHeight();
            int w=bimg.getWidth();
//            int w=img.getWidth(this);
//            int h=img.getWidth(this);
            if (w>PWIDTH){
                imgw=PWIDTH;
                imgh=h*imgw/w;
            }else {
                imgw=PWIDTH;
                imgh=w*imgh/h;
            }
            dx1=xcenter-imgw/2;dy1=ycenter-imgh/2;dx2=xcenter+imgw/2;dy2=ycenter+imgh/2;
            sx2=w;sy2=h;
        }
    class MyCanvas extends  Canvas{
        public void paint(Graphics g){

            g.drawImage(img,dx1,dy1,dx2,dy2,sx1,sy1,sx2,sy2,this);
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}

