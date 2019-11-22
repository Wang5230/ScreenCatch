package client;

import java.io.*;
import java.net.Socket;

public class receieve_Client extends Thread{
    private String adress;
    private String path;
    public receieve_Client(String adress) {
        this.adress = adress;
        this.path = "E:\\Javaproject\\catchScrenn\\src\\showSrceen";
    }
    public receieve_Client(String adress, String path){
        this.adress =adress;
        this.path = path;
    }
    @Override
    public void run() {
        super.run();
        try{
            Socket sc = new Socket(adress,8808);
            File f  = new File(path);
            InputStream fis = sc.getInputStream();
            DataInputStream dis = new DataInputStream(fis);
            FileOutputStream fos = new FileOutputStream(path);
            DataOutputStream dos = new DataOutputStream(fos);
            byte [] tem = new byte[1024];
            int pos;
            while((pos=dis.read(tem))!=-1){
                dos.write(tem,0,pos);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        releaseZip rz = new releaseZip();
        rz.getImg();
    }
}
