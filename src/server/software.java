package server;

import client.receieve_Client;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class software {
    File f = new File("E:\\Javaproject\\catchScrenn\\IMG");
    List a = new LinkedList();
    ScreenCapture2 sc = new ScreenCapture2();
    sendZip_Server server = new sendZip_Server("E:\\Javaproject\\catchScrenn\\transport\\transport.zip");
    static boolean trans_ready=true,flag=false;
    public void closeStream() throws IOException {
        server.closeStream();
    }
    public  void host_pushStream() {
        server.getZip gz = new server.getZip();
        Thread t1 = new Thread() {
            @Override
            public void run() {
                super.run();
                while (flag) {
                    flag=!flag;
                    long st = System.currentTimeMillis();
                    sc.getScreen();
                    long et = System.currentTimeMillis();
                    System.out.println(et - st);
                }
            }
        };
        Thread t2 = new Thread(){
            @Override
            public void run() {
                while (true) {
                    if(trans_ready) {
                        try {

                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        trans_ready = false;
                    }
                    super.run();
                    try {
//                        long st = System.currentTimeMillis();
                        gz.zip();
//                        long et = System.currentTimeMillis();
//                        System.out.println(et - st);
                        server.start();
                        if(server.isFlag(server)) {
                            flag = true;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t1.start();
        t2.start();
    }
}



