package Game;
import javax.swing.*;
import java.awt.*;  
import java.net.*; 
public class StartGame extends JWindow implements Runnable {
	Thread splashThread; // 进度条更新线程  
    JProgressBar progress; // 进度条  
  
    public StartGame() {  
        Container container = getContentPane(); // 得到容器  
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); // 设置光标  
        
          container.add(new JLabel(new ImageIcon("picture/开始1.jpg")), BorderLayout.CENTER); // 增加图片  
        progress = new JProgressBar(1, 50); // 实例化进度条  
        progress.setStringPainted(true); // 描绘文字  
        progress.setString("加载程序中,请稍候......"); // 设置显示文字  
        progress.setBackground(Color.white); // 设置背景色  
        container.add(progress, BorderLayout.SOUTH); // 增加进度条到容器上  
  
        Dimension screen = getToolkit().getScreenSize(); // 得到屏幕尺寸  
        pack(); // 窗口适应组件尺寸  
        setLocation((screen.width - getSize().width) / 2,  
                (screen.height - getSize().height) / 2); // 设置窗口位置  
    }  
  
    public void start() {  
        this.toFront(); // 窗口前端显示  
        splashThread = new Thread(this); // 实例化线程  
        splashThread.start(); // 开始运行线程  
    }  
  
    public void run() {  
    	
        setVisible(true); // 显示窗口  
        try {  
            for (int i = 0; i < 50; i++) {  
                Thread.sleep(100); // 线程休眠  
                progress.setValue(progress.getValue() + 1); // 设置进度条值  
            }  
        } catch (Exception ex) {  
            ex.printStackTrace();  
        }  
        dispose(); // 释放窗口  
        new Login();
        new Server();
    }  

  
    public static void main(String[] args) { 
    	
    	StartGame splash = new StartGame();  
        splash.start(); // 运行启动界面  
    }  
}
