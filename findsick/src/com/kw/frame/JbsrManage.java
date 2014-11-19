package com.kw.frame;

import java.awt.Container;
//import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

//import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
//import javax.swing.JFrame;
import javax.swing.JLabel;
//import javax.swing.JMenu;
//import javax.swing.JMenuBar;
//import javax.swing.JMenuItem;
import javax.swing.JPanel;
//import javax.swing.JRadioButton;
//import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

class link{
	public static final String DBDRIVER ="org.sqlite.JDBC";		//mysql的驱动语句
	public static final String DBURL = "jdbc:mysql://localhost:3306/illcheck";	//数据库连接地址
	//public static final String DBUSER = "root";								//数据库用户名
	//public static final String DBPASS = "930820";							//数据库密码
	//private int id;
	//private String name;
	//private String syptom;
	
	
	/*public link(){
		this.id = id;
		this.name = name;
		this.syptom = syptom;
	}*/
	
	
	
public Vector<String> seekAllSyptom()throws Exception{
		String a = null;
		Vector <String> as = new Vector <String>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT *"+"FROM demo";
		Class.forName(DBDRIVER);
		conn = DriverManager.getConnection("jdbc:sqlite:db/illcheck.db");
		stmt = conn.prepareStatement(sql);
		rs = stmt.executeQuery();		//实例化对象
		while(rs.next()){
			a = rs.getString(3);
			as.add(a);
		}
		return as;
	}
}
class JbsrManage extends JDialog{
	private static final long serialVersionUID = 1L;
	private JPanel pan2=null;
	private JButton but2=null;
	private JButton but3=null;
	private JButton but4=null;
	private JLabel input=null;
	private JTextArea xianshi=null;
	private JComboBox inputText=null;
	private ButtonGroup group1=null ;
	private Vector<String> details;
	private String oneSyptom = "症状如下：——————";
	
	public JbsrManage() throws Exception {
		super();
		 pan2= new JPanel();
		 but2= new JButton("添加病症");
		 but3= new JButton("开始诊断");
		 but4= new JButton("清空症状");
		input = new JLabel("逐条输入病症：");
		 xianshi = new JTextArea(); //显示的文本框
		 inputText=null;//下拉列表框
		 //group1 = new ButtonGroup();  //按钮组
		 oneSyptom = "症状如下：——————";
		 but4.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0){
					if(arg0.getSource() == but4){
						oneSyptom = "症状如下：——————";
						xianshi.setText(oneSyptom);
					}
				}
			});
		 but3.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0){
					if(arg0.getSource() == but3){
						try {
							new zhenduan(oneSyptom);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			});
			
		 but2.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0){
					if(arg0.getSource() == but2){
						oneSyptom = oneSyptom+'\n'+(String)inputText.getSelectedItem();
						xianshi.setText(oneSyptom);
					}
				}
			});
		
			link content = new link();
			details = content.seekAllSyptom();
			inputText = new JComboBox(details);
			input.setBounds(10,10,100,20); //设置大小
			inputText.setBounds(110, 10, 300, 20);
			but2.setBounds(500, 10, 100,20);
			xianshi.setBounds(10, 40, 800, 300);
			but4.setBounds(650,360,115,20);
			but3.setBounds(800, 360, 100, 20);
			pan2.setLayout(null);
			pan2.add(input);
			pan2.add(inputText);
			pan2.add(but2);
			pan2.add(xianshi);
			pan2.add(but4);
			pan2.add(but3);
			Container container=getContentPane();
			container.add(pan2);
			initialize();
	}

private void initialize(){
	this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	this.setSize(958, 468);
	this.setTitle("病症输入");
	this.setLocationRelativeTo(null);
	
}
}

		


		
		
		
		
		
		
		
		
		
		
		
	
	     
	


