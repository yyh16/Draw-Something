package Game;

import java.awt.Color;
import java.awt.Font;
import java.lang.Thread.State;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public 	class TimerLabel extends JLabel {

    private int maxTime;
    private int count;
    private static final int SECOND = 1000;
    private static final int FONT_SIZE = 36;
    private Thread thread;
    private boolean pause;
    private boolean start;
    private boolean acountinue;

    /**
     * �½�һ����ʱ��ǩ
     * @param maxTime ����ʱ��ʼʱ��
     */
    public TimerLabel(int maxTime) {
        this.setHorizontalAlignment(JLabel.CENTER);
    	setBounds(200, 0, 200, 80);
    	setBackground(Color.green);
        this.setFont(new Font("Times New Roman", Font.BOLD, FONT_SIZE));
        this.pause = false;
        setMaxTime(maxTime);
    }

    /**
     * �޸ĵ���ʱ��ʼʱ��
     * @param maxTime �µ���ʼʱ��
     */
    public void setMaxTime(int maxTime) {
        if (this.start) {
            return;
        }
        this.maxTime = maxTime;
        this.count = maxTime;
        initText();
        this.thread = new Thread(new Runnable() {

            @Override
            public void run() {
                while (true)
                {
                	if(count!=0)
                	{
                		try {
                			if (!start) {
                				count = 0;
                				initText();
                				break;
                			}
                			if (!pause) {
                				Thread.sleep(SECOND);
                				count--;
                				initText();
                			}
                		} catch (InterruptedException ex) {
                			pause = true;
                		}
                	}
                	else
                		count=20;
                }
                done();
                
            }
        });
        this.start = false;
    }

    /**
     * ����ʱ��ɺ���ô˷���
     */
    protected void done() {
        JOptionPane.showMessageDialog(labelFor, "Time up!");
        
        
    }

    /**
     * ��ǩ�ַ��ɴ˷�������
     */
    protected void initText() {
        String min = String.valueOf(count / 60);
        String sec = String.valueOf(count % 60);
        while (min.length() < 2) {
            min = "0" + min;
        }
        while (sec.length() < 2) {
            sec = "0" + sec;
        }
        this.setText(min + ":" + sec);
    }

    /**
     * ��ͣ
     */
    public void pause() {
        if (start) {
            thread.interrupt();
        }
    }
    
    /**
     * ����ǩ����ʱ�Ƿ�ʼ
     * @return �����ʼ����true
     */
    public boolean isStart() {
        return start;
    }
    
    /**
     * �õ�����ʱ��ʼʱ��
     * @return ����ʱ��ʼʱ��
     */
    public int getMaxTime() {
        return maxTime;
    }
    
    /**
     * ����ǩ����ʱ�Ƿ���ͣ
     * @return ����ʱ��ͣ����true
     */
    public boolean isPause() {
        return pause;
    }
    
    /**
     * ����ͣ�лָ���ʱ
     */
    public void continueDo() {
        if (this.pause) {
            this.pause = false;
        }
    }
    
    
    /**
     * ȡ����ʱ
     */
    public void stop() {
        if (start) {
            start = false;
        }
    }


    /**
     * ��ʼ��ʱ
     */
    public void start() {
        if (thread.getState().equals(State.NEW)) {
            start = true;
            thread.start();
        } else if (thread.getState().equals(State.TERMINATED)) {
            setMaxTime(maxTime);
            start = true;
            thread.start();
        }
    }
}
