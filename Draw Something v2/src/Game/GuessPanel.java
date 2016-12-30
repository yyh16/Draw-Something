package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class GuessPanel extends JPanel{
	
	private JPanel guess,name;
	private String[] names=new String[50];
	private int i=0;
	private JTextArea text;
	private DataOutputStream toServer;
	private DataInputStream fromServer;
	
	public GuessPanel() throws ClassNotFoundException, SQLException{
		
		
//		setBackground(Color.black);
		setBounds(190, 440, 620, 230);
		setLayout(null);

		guess=new JPanel();
		guess.setBounds(0, 0,620,30);
		guess.setBackground(Color.pink);
		JLabel jl=new JLabel("±¾ÂÖÄãÑ¡ÔñÒª²ÂµÄ´Ê»ãÓÐ£º");
		jl.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		JPanel jp=new JPanel();
		text=new JTextArea();
		text.setBounds(0, 0, 620, 200);
		text.setBackground(Color.white);
		guess.add(jl);
		guess.add(text);
		guess.setLayout(new GridLayout(1,2));
	//	guess.add(jp);
		
		name=new JPanel();
		name.setBounds(0,20,620,200);
		name.setLayout(new GridLayout(10,5));
		name.setBorder(BorderFactory.createEtchedBorder());
		DbConnect dbcon=new DbConnect();
		names=dbcon.getString();
		
		while(i<=49){
		JButton a=new JButton(names[i]);
		a.setBorder(BorderFactory.createEtchedBorder());
		a.addActionListener(new ButtonListener());
		name.add(a);
		i++;	
		System.out.println(i);
		}
		add(guess);
		add(name);
		this.setVisible(true);
		
		try {
			Socket socket = new Socket("127.0.0.1", 8000);
			System.out.println("local port" + socket.getLocalPort());

			toServer = new DataOutputStream(socket.getOutputStream());

			new Thread(new Runnable() {

				char kind;
				boolean isright;
				@Override
				public void run() {
					
					while (true) {
						try {
							fromServer = new DataInputStream(socket.getInputStream());
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						try {
//							for(int i=0;i<9999999;i++)
//							{
//								kind = fromServer.readChar();
//								if(kind=='z')
//									break;
//							}
//							System.out.println("¶Áµ½booleanÁËÂð"+"shi  ");
//							System.out.println(kind);
								switch (kind) {
								case 'z':
									isright=fromServer.readBoolean();
									if(isright==true){
										text.setText("");
										}
									break;
									}
										}
						catch (IOException e ) {
							e.printStackTrace();
						}
					}
				}
			}).start();

		} catch (IOException ex) {
			System.out.println(ex.toString() + '\n');
		}	
	}

	private class ButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JButton temp=(JButton)e.getSource();
			text.setText(null);
			text.setText(text.getText()+temp.getText());
			try {
				toServer.writeChar('a');
				toServer.writeUTF(text.getText());
				toServer.flush();
			} catch (IOException ex) {
				System.err.println(ex);
			}
		}
		
	}
//	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//		new GuessPanel();
//	}

}
