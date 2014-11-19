package com.kw.frame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.*;

class DBdemo{
	public static final String DBDRIVER ="org.sqlite.JDBC";				//mysql的驱动语句
	//public static final String DBURL = "jdbc:mysql://localhost:3306/illcheck";	//数据库连接地址
	//public static final String DBUSER = "root";								//数据库用户名
	//public static final String DBPASS = "930820";							//数据库密码
	//private int id;
	//private String name;
	//private String syptom;
	
	public DBdemo(){
		/*this.id = id;
		this.name = name;
		this.syptom = syptom;*/
	}
	
	
	public Vector<String> seek1(String b)throws Exception{
		String a = null;
		Vector <String> as = new Vector <String>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT *"+"FROM demo WHERE name LIKE ? ";
		Class.forName(DBDRIVER);
		conn = DriverManager.getConnection("jdbc:sqlite:db/illcheck.db");
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%"+b+"%");
		rs = stmt.executeQuery();		//实例化对象
		while(rs.next()){
			a = rs.getString(3);
			as.add(a);
		}
		return as;
	}
}


class lookFor{
	private String content1 = "此疾病有如下症状：——————";
	private Vector<String> detail;
	
	public String details(String a)throws Exception{
		DBdemo content = new DBdemo();
		detail = content.seek1(a);
		for(int i = 0; i < detail.size(); i++){
			 content1=content1+'\n'+detail.get(i);
		}
		return content1;
	}
}


public class syptom {
	private JFrame frame = new JFrame("病症显示");
	private Container cont = frame.getContentPane();//创建容器
	//Container con=getContentPane();
	private JTextArea lab = new JTextArea();//创建文本区域
	private JPanel pan = new JPanel();//创建面板
	private JButton but = new JButton("返回");//创建按钮
	private String  b= null;
	
	public syptom(String a)throws Exception{
		
			
		but.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				frame.setVisible(false);
			}
		});
				

		b= new lookFor().details(a);
		lab.setText(b);
		lab.setBounds(10, 10, 500, 300);
		but.setBounds(520, 170,60, 20);
		pan.setLayout(null);//取消布局管理器设置
		pan.add(lab);
		pan.add(but);
		cont.add(pan);
		//setBounds(120,120,100,100);
		frame.setSize(600,350);
		frame.setFocusable(true);
		frame.setFocusableWindowState(true);
		frame.setVisible(true);
	}
}
