package server;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ScreenCapture2 {
        private static String format =".jpg";
        private static Dimension screen =Toolkit.getDefaultToolkit().getScreenSize();
        private static Rectangle rectangle = new Rectangle(screen);
    public ScreenCapture2(){

    }
    public void getScreen(){
        int num=0;
        try {
            Robot robot = new Robot();
            BufferedImage buffer = null;
            char a ='a';
            for(int i=0;i<10;i++) {
                buffer = robot.createScreenCapture(rectangle);
                File out = new File("E:\\Javaproject\\catchScrenn\\IMG\\" +a+++ ".jpg");
                ImageIO.write(buffer, "jpg", out);
                num++;
            }

        }catch(IOException e){
            e.printStackTrace();
        }
        catch (AWTException a){
            a.printStackTrace();
        }
    }
}
