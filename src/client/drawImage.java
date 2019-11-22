package client;

import view.screen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;

public class drawImage  {
    private drawImage(){
    }
    public static void getImg(LinkedList<Image> linkedList, String path){
        File te =  new File(path){
            @Override
            public int compareTo(File pathname) {
                String name =pathname.getName();
                int suma=0,sumb=0;
                char a ,b;
                int posa=1,posb=1;
                for(int i=0;i<name.length();i++){
                    a=name.charAt(i);
                    if(a>='0'&&a<='9'){
                        suma+=a-48*posa;
                        posa*=10;
                    }
                }
                String nam = this.getName();
                for(int i=0;i<nam.length();i++){
                    b=nam.charAt(i);
                    if(b>='0'&&b<='9'){
                        sumb+=b-48*posb;
                        posb*=10;
                    }
                }
                if(suma<sumb){
                    return -1;
                }
               return 1;
            }
        };
        File [] file = te.listFiles();
        URL u;
        for(File tem :file ){
            if(tem.isDirectory()){
                getImg(linkedList,path+"/"+tem.getName());
                tem.delete();
            }
            else {
                u= screen.class.getClassLoader().getResource("showSrceen\\IMG\\"+tem.getName());
                System.out.println(u);
                try {
                    linkedList.add(ImageIO.read(u));
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
