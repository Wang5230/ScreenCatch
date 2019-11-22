package server;
/**
 * used for host ot sent the zip file to the cilent and giva the method to shut down the stream
 * and restart new stream
 */

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class sendZip_Server extends Thread {
    private String path;
    static ServerSocket ss = null;
    static boolean flag = false;

    public sendZip_Server(String path) {
        this.path = path;
    }

    public void closeStream() throws IOException {
        ss.close();
    }

    @Override
    public void run() {
        super.run();
        flag = false;
        try {
            ss = new ServerSocket(8808);
            Socket so = ss.accept();
            File f = new File(path);
            FileInputStream fis = new FileInputStream(f);
            OutputStream os = so.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            byte[] tem = new byte[1024];
            int pos;
            while ((pos = fis.read(tem)) != -1) {
                dos.write(tem, 0, pos);
            }

    }catch(IOException e) {
        e.printStackTrace();
    }
       flag = true;
}

    public boolean isFlag(sendZip_Server sendZip_server) {
        return sendZip_server.flag;
    }
}

