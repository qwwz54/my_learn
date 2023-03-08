package start;

import java.io.File;
import java.io.FileInputStream;

public class ImageFileDemo2 {
    public static void main(String[] args) {

        new ImageFileDemo2().showImgDeta("./images/1.bmp");
        new ImageFileDemo2().showHeader("./images/1.bmp");
    }
    public int getPixCount(String filename){
        try {
            File f=new File(filename);
            if (!f.exists()) return 0;
            FileInputStream fin=new FileInputStream(filename);
            System.out.println("可访问字节数是："+fin.available());
            byte temp[]=new byte[54];
            int count=fin.read(temp);
            int sum=temp[18]*temp[22];
            fin.close();
            return sum;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public void showImgDeta(String filename){
        try {
            File f=new File(filename);
            if (!f.exists()) return;
            FileInputStream fin=new FileInputStream(filename);
            int pix=this.getPixCount(filename);
            int all=fin.available();
            byte temp[]=new byte[pix];
            fin.skip(all-pix-1);
            int count=fin.read(temp);
            for (int i=0;i<count;i++){
                int t=Byte.toUnsignedInt(temp[i]);
                System.out.print(Integer.toHexString(t)+" ");
                if (i%20==19) System.out.println();
            }
            fin.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void showHeader(String filename){
        try {
            File f=new File(filename);
            if(!f.exists()) return;
            FileInputStream fin=new FileInputStream(filename);
            byte temp[]=new byte[54];
            int count=fin.read(temp);
            for (int i=0;i<count;i++){
                System.out.print(temp[i]+" ");
//                int t=Byte.toUnsignedInt(temp[i]);
//                System.out.print(Integer.toHexString(t)+" ");
                if (i%20==19)System.out.println();
            }
            System.out.println("+++++++++从头文件中获取的信息++++++++");
            System.out.println("该图像的宽："+temp[18]);
            System.out.println("该图像的长："+temp[22]);
            System.out.println("该图像的位数："+temp[28]);
            fin.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
