package Game;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
import java.awt.*;
import javax.swing.JOptionPane;
import javax.swing.*;
import com.sun.javafx.tk.Toolkit;

public class DbConnect {
	String name,password;
	Connection conn;
	Statement stmt;
	ResultSet rs;
	PreparedStatement ps;
	String word1,word2;
	private int bs[];
	public DbConnect(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		}
		try {			
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/myss","root", "");
		
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	public boolean logincheck(String accountStr,String passwordStr){
		
		boolean select = true; 
		String sql = "select * from login";
		
		try {
			
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();	
			int count = 0;
			while (rs.next())
				count++;
			rs = pstm.executeQuery();
			//���ڴ洢 �˺������count �� 2�е� object ���͵� ��λ����
			Object[][] Info = new Object[count][2];
			
			count = 0;
			while (rs.next()) {
				Info[count][0] = rs.getString("name");
				Info[count][1] = rs.getString("password");
				count++;
			}
			 	
			for (int i = 0; i < count; i++) {
				if (Info[i][0].equals(accountStr)
						&& Info[i][1].equals(passwordStr)) {
					select =false;
				//	JOptionPane.showMessageDialog(null, "��½�ɹ�");
					 new DrawSomething();
					break;
				}
			}
	
			if (select) {

				JOptionPane.showMessageDialog(null, "�˺Ż��������");
				return select;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		  
		return select;
	}
	public boolean register(String name,String password){
       String sql1="select name from login where name='"+name+"'";
		
		String sql = "insert into login(name,password)values('"+name+"','"+password+"')";
		boolean have = false;// ����У���˺��Ƿ����
		try {
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(sql1);
			if(result.next()){
				have=true;
				JOptionPane.showMessageDialog(null, "���˺��Ѿ�����");
				return false;
			}
			else{
			stmt.executeUpdate(sql);
			JOptionPane.showMessageDialog(null, "ע��ɹ�");
			return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close();
		}
		return false;
	}
	//���9����
	public  String[]  randof9(){
		
    	ArrayList<String> a = new ArrayList<String>();
    	int b[] = new int[9];
    	String answers[]=new String[9];
    	//ArrayList<String>  answers= new ArrayList<String>();
    	try{
    		stmt = conn.createStatement();
    		rs = stmt.executeQuery("select * from word");
    		while(rs.next()){
    			a.add(rs.getString("word"));
    		}
    		int i=0;
    		boolean judge;   		
            while (i<9){
            	judge=true;
    		    Random r = new Random();
    		    int x = r.nextInt(a.size());
    		    for (int j=0;j<i-1;j++){
    			   if (b[j]==x){
    				  judge=false;
    				  break;
    			    }   				
    		    }
    		    if (judge){
    			    b[i]=x;
        		    i++;
    		     }    		
            }
    		for (int z=0;z<9;z++){
    			answers[z]=a.get(b[z]);
    		}
    			
    		

    	}catch(SQLException e){
    		e.printStackTrace();
    	}
    	close();
    	return answers;
	}
	//��ȡ���е�50����
	public String[] getString(){
		String[] names=new String[50];
		try{
		stmt = conn.createStatement();
		rs = stmt.executeQuery("select * from word");
		System.out.println("success");
		int i=0;
		while (rs.next())
		{
			names[i]=rs.getString("word");
			i++;
			}
		}catch(SQLException e){
    		e.printStackTrace();
    	}
		
		close();
		return names;
	}
	public void updateWord (String phase1,String phase2){
		//String sql1="select * from word where word='"+phase1+"' ";
//		String sql2="UPDATE word SET word='����' WHERE word='����'";
		String sql2=" UPDATE word SET word ='"+phase2+"' WHERE word = '"+phase1+"' ";
		try{
			//conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/myss","root", "");
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql2);
			System.out.println(sql2);
			JOptionPane.showMessageDialog(null, "���³ɹ�,�´���Ϸ����");
		
		}catch (SQLException e1) {
				e1.printStackTrace();
		}finally{
				close();
		}
	}

	public void close(){
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
