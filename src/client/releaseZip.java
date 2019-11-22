package client;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class releaseZip {
    private String path = "E:\\Javaproject\\catchScrenn\\src\\showSrceen";
    private String getZip = "E:\\Javaproject\\catchScrenn\\transport\\transport.zip";
    public void getImg(){
        try {
            FileInputStream fis = new FileInputStream(getZip);
            ZipInputStream zis = new ZipInputStream(fis);
            BufferedInputStream bis = new BufferedInputStream(zis);
            ZipEntry zipEntry ;
            File out = null;
            while((zipEntry=zis.getNextEntry())!=null&&!zipEntry.isDirectory()){
                    out = new File(path,zipEntry.getName());
                    if(!out.exists()){
                        new File(out.getParent()).mkdirs();
                    }
                FileOutputStream fos = new FileOutputStream(out);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                int tem;
                while((tem=bis.read())!=-1){
                    bos.write(tem);
                }
                bos.close();
                fos.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
