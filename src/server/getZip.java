package server;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class getZip {
    public getZip(){

    }
    public void zip() throws  Exception {
        File f = new File("E:\\Javaproject\\catchScrenn\\IMG");
        FileOutputStream fos = new FileOutputStream("E:\\Javaproject\\catchScrenn\\transport\\transport.zip");
        CheckedOutputStream cs = new CheckedOutputStream(fos, new Adler32());
        ZipOutputStream zos = new ZipOutputStream(cs);
        takeZip(zos, f, f.getName());
        zos.close();
        cs.close();
    }
    public void takeZip(ZipOutputStream zos,File f ,String base)throws Exception{
        if(f.isDirectory()){
            File [] list = f.listFiles();
            System.out.println(list);
            if(list.length==0) {
                zos.putNextEntry(new ZipEntry(base + "/"));
            }else{
                for(File tem :list){
                    takeZip(zos,tem,base+"/"+tem.getName());
                }
            }
        }else{
            if(!f.exists()){
                zos.putNextEntry(new ZipEntry("/"));
                zos.closeEntry();
            }
            else{
                zos.putNextEntry(new ZipEntry(base));
                FileInputStream fis = new FileInputStream(f);
                int tem;
                while((tem=fis.read())!=-1){
                    zos.write(tem);
                }
//                zos.close();
//                fis.close();
            }
        }

    }
    }

