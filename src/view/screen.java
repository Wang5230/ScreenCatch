package view;
/**
 * the boradcast have not done,cilent and host should have different option and feedback
 */
import client.drawImage;
import client.receieve_Client;
import server.software;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.LinkedList;

public class screen extends JFrame{
    software sw = new software();
    static LinkedList<Image> bf = new LinkedList<>();
    static  int num=0,x,y;
    static  boolean flag =false,draw;
    static Point point,tem,now;
    static MouseListener ml;
    static MouseMotionAdapter mma;
    static JMenuBar jmenuBar;
    public void launch(){
        JFrame jf = new JFrame();
        jf.setTitle("watcher");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(800,600);
        jf.setLayout(null);
        JPanel jp = new JPanel(){
            @Override
            public void paint(Graphics g) {  //刷新函数
                super.paint(g);
                if(num>=40){
                    num=0;
                }
                g.drawImage(bf.removeFirst(), 0, 0, null);
                repaint();
                try{
                    Thread.sleep(100);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        jf.addWindowStateListener(new WindowAdapter() {  //监听窗口操作，最大化将图片放大显示
            @Override                                    //正常下提供图片拖动操作
            public void windowStateChanged(WindowEvent e){
                    if(e.getNewState()==Frame.MAXIMIZED_BOTH){ //进入全屏后取消鼠标监听，并且固定图片位置
                        jp.setLocation(0,30);
                        jp.removeMouseListener(ml);
                        jp.removeMouseMotionListener(mma);
                    }
                    else if(e.getNewState()==Frame.NORMAL||e.getOldState()==Frame.NORMAL){
                        System.out.println("nor");
                                ml =new MouseAdapter() {
                                @Override
                                public void mousePressed(MouseEvent e) {
                                    System.out.println("pre");
                                    tem = e.getPoint();
                                    point = jp.getLocation();
                                }

                            };
                                jp.addMouseMotionListener(mma =    new MouseMotionAdapter() {
                                    @Override
                                    public void mouseDragged(MouseEvent e) {
                                        super.mouseDragged(e);
                                        now = e.getPoint();
                                        x = point.x+now.x-tem.x;
                                        y = point.y+now.y-tem.y;
                                        if(x>0){                        //检测是否在正常区域范围内
                                            x = 0;                      //闪烁问题未解决
                                        }
                                        if(x<-575){
                                            x=-575;
                                        }
                                        if(y<-205){
                                            y=-205;
                                        }
                                        if(y>30){
                                            y=30;
                                        }
                                        jp.setLocation(x,y);
                                    }
                                });
                        //暂时禁用这一功能，图片repaint之后可能会在刷新中重写，随着坐标改变而改变了
                        jp.addMouseListener(ml);
                    }
                }

        });
        jp.setLayout(null);
        jp.setBounds(0,30,1360,768); //图片流以及组件的初始化
        jmenuBar = new JMenuBar();
        JMenu option = new JMenu("option");
        JMenu start = new JMenu("start");
        JMenu Reserved1 = new JMenu("stop");
        JMenu Reserved2 = new JMenu("Reserved2");
        jmenuBar.setLayout(null);
        jmenuBar.setLocation(0,0);
        jmenuBar.setBounds(0,0,100,30);
        JMenuItem b = new JMenuItem("start");
        JMenuItem host = new JMenuItem("host");
        JMenuItem user = new JMenuItem("user");
        user.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String address = JOptionPane.showInputDialog(null,"plz input"," ",JOptionPane.PLAIN_MESSAGE);
                System.out.println("try to conncet: "+address); //通过弹窗来接收用户要连接的host地址
                receieve_Client rc = new receieve_Client(address); //默认路径
                rc.start();
                draw = true;
            }
        });
        option.add(host);
        option.add(user);
        host.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("take ready for stream");
                sw.host_pushStream();
                System.out.println("ready!");
                user.setEnabled(false);
            }
        });
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("plz wait");
                flag=!flag;
                jp.setVisible(flag);
            }
        });
        Reserved1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    sw.closeStream();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        });
        jmenuBar.add(b);
        jmenuBar.add(option);
        jmenuBar.add(Reserved1);
        jmenuBar.add(Reserved2);
        jmenuBar.setVisible(true);
        jf.setJMenuBar(jmenuBar);
        if(draw){
            jf.add(jp);
        }
        jf.setVisible(true);
    }

    public static void main(String[] args) {
        Thread t2 = new Thread(){
            @Override
            public void run() {
                super.run();
                drawImage.getImg(bf,"E:\\Javaproject\\catchScrenn\\src\\showSrceen\\IMG");
                System.out.println(bf.size());
            }
        };

        Thread t1 = new Thread(){
            @Override
            public void run() {
                super.run();
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                new screen().launch();
            }
        };

            t2.start();
            t1.start();

    }
}
