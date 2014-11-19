package com.kw.frame;

import java.awt.*;
import java.awt.event.*;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.Vector;
//import java.net.*;

import javax.swing.*;

class JbxzManage extends JDialog{
	private static final long serialVersionUID = 1L;
	private JPanel pan1 = new JPanel();
	private JButton but1 = new JButton("症状查询");
	private JLabel input = new JLabel("逐条输入病症：");
	private JLabel lab1 = new JLabel();
	private JLabel lab2 = new JLabel();
	private JTextArea xianshi = new JTextArea(); //显示的文本框
	private ButtonGroup group1 = new ButtonGroup();  //按钮组
	
	private JRadioButton exp1 = new JRadioButton("系统性红斑狼疮");//实例化JRadioButton对象
	private JRadioButton exp2 = new JRadioButton("类风湿性关节炎");
	private JRadioButton exp3 = new JRadioButton("原发性干燥综合征");
	private JRadioButton exp4 = new JRadioButton("系统性硬化");
	private JRadioButton exp5 = new JRadioButton("抗磷脂综合征");
	private JRadioButton exp6 = new JRadioButton("白塞氏");
	private JRadioButton exp7 = new JRadioButton("韦格纳肉芽肿");
	private JRadioButton exp8 = new JRadioButton("结节性多动脉炎");
	private JRadioButton exp9 = new JRadioButton("大动脉炎");
	private JRadioButton exp10 = new JRadioButton("脂膜炎");
	private JRadioButton exp11 = new JRadioButton("纤维肌痛综合征");
	private JRadioButton exp12 = new JRadioButton("多发性肌炎");
	//private JRadioButton exp13 = new JRadioButton("无肌病皮肌炎");
	
	public JbxzManage() throws Exception {		
		but1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				String a=null;
				if(arg0.getSource() == but1){
					if(exp1.isSelected()){
						a = exp1.getText();
						try {
							//(JbxzManage.this).setFocusable(false);
							//(JbxzManage.this).setFocusableWindowState(false);
							new syptom(a);
							
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(exp2.isSelected()){
						a = exp2.getText();
						try {
							new syptom(a);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(exp3.isSelected()){
						a = exp3.getText();
						try {
							new syptom(a);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(exp4.isSelected()){
						a = exp4.getText();
						try {
							new syptom(a);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(exp5.isSelected()){
						a = exp5.getText();
						try {
							new syptom(a);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(exp6.isSelected()){
						a = exp6.getText();
						try {
							new syptom(a);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(exp7.isSelected()){
						a = exp7.getText();
						try {
							new syptom(a);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(exp8.isSelected()){
						a = exp8.getText();
						try {
							new syptom(a);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(exp9.isSelected()){
						a = exp9.getText();
						try {
							new syptom(a);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(exp10.isSelected()){
						a = exp10.getText();
						try {
							new syptom(a);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(exp11.isSelected()){
						a = exp11.getText();
						try {
							new syptom(a);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(exp12.isSelected()){
						a = exp12.getText();
						try {
							new syptom(a);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					/*}else if(exp13.isSelected()){
						a = exp13.getText();
						try {
							new syptom(a);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}*/
					}
				}
			}
		});
		pan1.setBorder(BorderFactory.createTitledBorder("疾病名称是下列哪一项："));
		pan1.setLayout(new GridLayout(4,7)); //网格布局
		group1.add(exp1);
		group1.add(exp2);
		group1.add(exp3);
		group1.add(exp4);
		group1.add(exp5);
		group1.add(exp6);
		group1.add(exp7);
		group1.add(exp8);
		group1.add(exp9);
		group1.add(exp10);
		group1.add(exp11);
		group1.add(exp12);
		//group1.add(exp13);
		pan1.add(exp1);
		pan1.add(exp2);
		pan1.add(exp3);
		pan1.add(exp4);
		pan1.add(exp5);
		pan1.add(exp6);
		pan1.add(exp7);
		pan1.add(exp8);
		pan1.add(exp9);
		pan1.add(exp10);
		pan1.add(exp11);
		pan1.add(exp12);
		//pan1.add(exp13);
		pan1.add(lab1);
		pan1.add(lab2);
		pan1.add(but1);
		input.setBounds(10,10,100,20); //设置大小
		xianshi.setBounds(10, 40, 800, 300);
		Container container=getContentPane();
		container.add(pan1);
		initialize();
}

	private void initialize(){
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			this.setSize(958, 468);
			this.setTitle("疾病选择");
			this.setLocationRelativeTo(null);
			//this.setModal(true);
		}
		
}
	
		
		
			





