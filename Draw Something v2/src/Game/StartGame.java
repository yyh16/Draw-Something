package Game;
import javax.swing.*;
import java.awt.*;  
import java.net.*; 
public class StartGame extends JWindow implements Runnable {
	Thread splashThread; // �����������߳�  
    JProgressBar progress; // ������  
  
    public StartGame() {  
        Container container = getContentPane(); // �õ�����  
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); // ���ù��  
        
          container.add(new JLabel(new ImageIcon("picture/��ʼ1.jpg")), BorderLayout.CENTER); // ����ͼƬ  
        progress = new JProgressBar(1, 50); // ʵ����������  
        progress.setStringPainted(true); // �������  
        progress.setString("���س�����,���Ժ�......"); // ������ʾ����  
        progress.setBackground(Color.white); // ���ñ���ɫ  
        container.add(progress, BorderLayout.SOUTH); // ���ӽ�������������  
  
        Dimension screen = getToolkit().getScreenSize(); // �õ���Ļ�ߴ�  
        pack(); // ������Ӧ����ߴ�  
        setLocation((screen.width - getSize().width) / 2,  
                (screen.height - getSize().height) / 2); // ���ô���λ��  
    }  
  
    public void start() {  
        this.toFront(); // ����ǰ����ʾ  
        splashThread = new Thread(this); // ʵ�����߳�  
        splashThread.start(); // ��ʼ�����߳�  
    }  
  
    public void run() {  
    	
        setVisible(true); // ��ʾ����  
        try {  
            for (int i = 0; i < 50; i++) {  
                Thread.sleep(100); // �߳�����  
                progress.setValue(progress.getValue() + 1); // ���ý�����ֵ  
            }  
        } catch (Exception ex) {  
            ex.printStackTrace();  
        }  
        dispose(); // �ͷŴ���  
        new Login();
        new Server();
    }  

  
    public static void main(String[] args) { 
    	
    	StartGame splash = new StartGame();  
        splash.start(); // ������������  
    }  
}
