package com.kw.frame;

import java.awt.BorderLayout;
//import java.awt.GridLayout;
//import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
//import java.util.Vector;

import javax.swing.*;
class search{
	public static final String DBDRIVER ="org.sqlite.JDBC";			//驱动语句
	//public static final String DBURL = "jdbc:mysql://localhost:3306/illcheck";	//数据库连接地址
	//public static final String DBUSER = "root";								//数据库用户名
	//public static final String DBPASS = "930820";	
	private String s;
	private boolean p=false;

	public search(String s){
		this.s = s;
	}
	public boolean find(String a)throws Exception{
		String b = s;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql ="SELECT *"+"FROM demo WHERE syptom LIKE ? ";
		Class.forName(DBDRIVER);
		conn = DriverManager.getConnection("jdbc:sqlite:db/illcheck.db");
		stmt = conn.prepareStatement(sql);
		stmt.setString(1,"%"+b+"%");
		rs = stmt.executeQuery();
		while(rs.next()){
			if(rs.getString(2).equals(a)){   //取疾病列
				p = true;break;
			}
		}
		return p;
	}
	
	
	public String find1()throws Exception{
		String a = s;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql ="SELECT *"+"FROM demo WHERE syptom LIKE ? ";
		Class.forName(DBDRIVER);
		//conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
		conn = DriverManager.getConnection("jdbc:sqlite:db/illcheck.db");
		stmt = conn.prepareStatement(sql);
		stmt.setString(1,"%"+a+"%");
		rs = stmt.executeQuery();		//执行SQL查询
		if(rs.next()){
			String name = rs.getString(2);//取疾病列
			rs.close();
			stmt.close();
			conn.close();
			return name;
		}
		rs.close();
		stmt.close();
		conn.close();
		return "查不出结果。。。";
	}
}


public class zhenduan {
	private JButton but = new JButton("返回");
	private JFrame frame = new JFrame("对应的疾病（可能）");//弹出的新窗体
	private JPanel pan = new JPanel();//创建面板
	private String[] array = null;
	private String b = ""; //定义b
	private JLabel lab = new JLabel();  //创建标签组件
	private int flag = 0;  //标志位
	private boolean p;
	
	public zhenduan(String a)throws Exception{
		array = a.split("\n");
		ArrayList<String> list = new ArrayList<String>();
		for(int i=1;i<array.length;i++){
			list.add(array[i]);
		}
		for(int j=1;j<array.length;j++){
			b = new search(array[j]).find1();
			
			
			if(b.equals("系统性红斑狼疮")||b.equals("类风湿性关节炎")||b.equals("多发性肌炎")){
				flag=0;
				for(int i=1;i<array.length;i++){
					if(new search(array[i]).find(b)){
						flag++;
					}
				}
				if(flag<4){
					b="查不出结果。。。";
				}
			}
		
		
			if(b.equals("纤维肌痛综合征")||b.equals("韦格纳肉芽肿")){
				flag=0;
				for(int i=1;i<array.length;i++){
					if(new search(array[i]).find(b)){
						flag++;
					}
				}
				if(flag<2){
					b="查不出结果。。。";
				}
			}
		
		
			if(b.equals("脂膜炎")){
				flag=0;
				for(int i=1;i<array.length;i++){
					if(new search(array[i]).find(b)){
						flag++;
					}
				}
				if(flag<1){
					b="查不出结果。。。";
				}
			}
		
		
			if(b.equals("大动脉炎")||b.equals("结节性多动脉炎")){
				flag=0;
				for(int i=1;i<array.length;i++){
					if(new search(array[i]).find(b)){
						flag++;
					}
				}
				if(flag<3){
					b="查不出结果。。。";
				}
			}
		
		
			if(b.equals("无肌病皮肌炎")){
				flag=0;
				p=false;
				for(int i=0;i<array.length;i++){
					if(array[i].equals("特征性的皮肤损害 眼睑的淡紫色斑（向阳疹）及眶周水肿，手背面的红色脱屑性皮疹尤其见于掌指关  高雪症")){
						for(int m=1;m<array.length;m++){
							if(new search(array[m]).find(b)){
								flag++;
							}
						}
					}
				}
				if(flag<1){
					b="查不出结果。。。";
				}else{
					p=true;
				}
				if(p!=true){
					b="查不出结果。。。";
				}
			}
				
		
			
			if(b.equals("抗磷脂综合征")){
				flag=0;
				p=false;
				for(int i=0;i<array.length;i++){
					if(array[i].equals("高滴度APL")){
						for(int m=1;m<array.length;m++){
							if(new search(array[m]).find(b)){
								flag++;
							}
						}
					}
				}
				if(flag<3){
					b="查不出结果。。。";
				}else{
					p=true;
				}
				if(p!=true){
					b="查不出结果。。。";
				}
			}
			
			
			if(b.equals("白塞氏")){
				flag=0;
				p=false;
				for(int i=0;i<array.length;i++){
					if(array[i].equals("反复口腔溃疡，由医师观察到或患者肯定诉说有阿弗他或疱疹性溃疡，在1年内反复3次。")){
						for(int m=1;m<array.length;m++){
							if(new search(array[m]).find(b)){
								flag++;
							}
						}
					}
				}
				if(flag<3){
					b="查不出结果。。。";
				}else{
					p=true;
				}
				if(p!=true){
					b="查不出结果。。。";
				}
			}
				
			
			if(b.equals("原发性干燥综合征")){
				flag=0;
				p=false;
				for(int i=1;i<array.length;i++){
					if(array[i].equals("眼干")){
						for(int n=1;n<array.length;n++){
							if(array[n].equals("口干")){
								for(int m=1;m<array.length;m++){
									if(new search(array[m]).find(b)){
										flag++;
									}
								}
							}
						}
					}
				}if(flag<3){
					b="查不出结果。。。";
				}else{
					p=true;
				}
				if(p!=true){
					b="查不出结果。。。";
				}
			}
			
			
			if(b.equals("系统性硬化")){
				flag=0;
				p=false;
				for(int i=1;i<array.length;i++){
					if(array[i].equals("手指及掌指关节(跖趾)关节近端皮肤增厚、紧绷、肿胀。可累及整个肢体、面部颈部和躯干")){
						p=true;
					}
				}
				for(int m=1;m<array.length;m++){
					if(new search(array[m]).find(b)){
						flag++;
					}
				}
				if(p!=true){
					if(flag>2){
						p=true;
					}else{
						b="查不出结果。。。";
					}
				}
			}	
		}
		
		
		but.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				frame.setVisible(false);
			}
		});
		
		
		lab.setText(b);
		frame.setLayout(new BorderLayout(1,2));
		frame.add(lab,BorderLayout.NORTH);
		frame.add(pan,BorderLayout.SOUTH);
		pan.add(but);
		frame.setSize(300,200);
		frame.setVisible(true);
	}
	
	
}
