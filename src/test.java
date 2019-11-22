import java.io.*;

public class test {
    public static void main(String [] args)throws IOException {
          String adress ="172.168";
          Runtime runtime = Runtime.getRuntime();
//          for(int i =0 ; i<=255 ; i++){
//              for(int j = 0 ;j<=255 ; j++){
//                  String command = adress+"."+i+"."+j;
//
//              }
//          }
        Process process = runtime.exec("C:\\Users\\WangHao\\Desktop\\Musicdownloader V3.0");
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(process.getInputStream(), "GBK"));
        String line = null;
        while((line=bufferedReader.readLine()) != null)
        {
            stringBuilder.append(line+"\n");
        }
        System.out.println(stringBuilder);
    }
}
