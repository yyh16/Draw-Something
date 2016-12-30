package Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sun.javafx.sg.prism.web.NGWebView;
import Game.Login;
import Game.Login.picturePanel;


public class UpdWordPanel extends JPanel{
	private JLabel fontjl,phase1Jlable,phase2Jlable;
	private JTextField phrase1,phrase2;
	private JPanel phrase1Jpanel,phrase2Jpanel,jp1,jp2;
	private JButton confirm;
	private String phase1,phase2;
	//private picturePanel jp3;
	public UpdWordPanel(){
		setBackground(Color.white);
		setBounds(840, 35, 220, 180);
		fontjl=new JLabel("¸ü»»´ÊÓï");
		jp1=new JPanel();
		jp2=new JPanel();
	   
		jp1.add(fontjl);
		fontjl.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		phase1Jlable=new JLabel("´Ê   Óï£º");
		phase2Jlable=new JLabel("¸ü»»Îª£º");
		phrase1 =new JTextField(10);
		phrase2=new JTextField(10);
		phrase1Jpanel=new JPanel();
		phrase2Jpanel=new JPanel();
		phrase1Jpanel.add(phase1Jlable);
		phrase1Jpanel.add(phrase1);
		phrase2Jpanel.add(phase2Jlable);
		phrase2Jpanel.add(phrase2);
		confirm= new JButton("È·¶¨");
		confirm.setPreferredSize(new Dimension(80,30));
		jp2.add(confirm);
		confirm.addActionListener(new buttonListener());
		this.setLayout(new GridLayout(4,1));
		this.add(jp1);
		this.add(phrase1Jpanel);
		this.add(phrase2Jpanel);
		this.add(jp2);
		
	}
	private class buttonListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			if(e.getSource()==confirm)
			{ phase1 = phrase1.getText();
			  phase2 = phrase2.getText();  
			// ´ÊÓïÊÇ·ñÎª¿Õ
					if (phase1.isEmpty() || phase2.isEmpty()) {
						JOptionPane.showMessageDialog(null, "´ÊÓïÎª¿Õ");
						return ;
					}
			DbConnect dbcon=new DbConnect();
			dbcon.updateWord(phase1,phase2);
		    	phrase1.setText(null);
		    	phrase2.setText(null);
		    }
			
		}
		}


}
