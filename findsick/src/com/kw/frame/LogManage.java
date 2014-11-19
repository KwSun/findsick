package com.kw.frame;
import javax.swing.JFileChooser;
//import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JDialog;
import javax.swing.JScrollPane;

//import java.awt.FileDialog;
import java.awt.Rectangle;
//import java.io.BufferedWriter;
import java.io.FileOutputStream;
//import java.io.FileWriter;
import java.io.IOException;
//import java.io.PrintWriter;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.kw.frame.LogRecord;

public class LogManage extends JDialog{

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JScrollPane jScrollPane = null;
	private JTextArea jTextArea = null;
	private JButton jButton_del = null;
	private JButton jButton_save = null;
	
	public LogManage() {
		initialize();
	}
	private void initialize() {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(413, 268);
		this.setTitle("查看日志");
		this.setResizable(false);
		//this.setModal(true);
		this.setLocationRelativeTo(null);

		jTextArea = new JTextArea();
		jTextArea.setText(setText());//把从txt文本读出来的数据显示到jTextArea里边
		jTextArea.setLineWrap(true);

		jButton_save = new JButton();
		jButton_save.setBounds(new Rectangle(185, 210, 217, 22));
		jButton_save.setText("保存日志");
		jButton_save.addActionListener(new java.awt.event.ActionListener() {
			
			public void actionPerformed(java.awt.event.ActionEvent e) {		
				JFileChooser fc= new JFileChooser();
			    FileNameExtensionFilter ft= new FileNameExtensionFilter("*.txt", "txt");
			    fc.addChoosableFileFilter(ft);//文件过滤默认保存为TXT文件，选择所有文件时需加后缀   
			    fc.showSaveDialog(null);//显示保存文件对话框
			    String fileName=fc.getSelectedFile().getAbsolutePath().trim();//获取保存文件的路径及输入的文件名
			    if(fileName!=null)
			    try{
			    	String temp = jTextArea.getText();
			    	byte[] byteBuf = temp.getBytes();
				    FileOutputStream out=new FileOutputStream(fileName+ ".txt"); 
				    out.write(byteBuf); 
				    out.close(); 	    	
			    }
			    catch (IOException a)
			    {
			     System.out.println("保存文件出错!");
			     a.printStackTrace();
			    }
				
			    JOptionPane.showMessageDialog(null, "日志保存成功！");
			}
		});
		
		jButton_del = new JButton();
		jButton_del.setBounds(new Rectangle(5, 210, 170, 22));
		jButton_del.setText("清空日志");
		jButton_del.addActionListener(new java.awt.event.ActionListener() {
			
			public void actionPerformed(java.awt.event.ActionEvent e) {		
				LogRecord lg = new LogRecord();
				lg.deleteLog();
				JOptionPane.showMessageDialog(null, "日志已被清空！");
				jTextArea.setText(setText());
				jTextArea.repaint();
			}
		});
		
		jScrollPane = new JScrollPane();
		jScrollPane.setBounds(new Rectangle(4, 5, 400, 200));
		jScrollPane.setViewportView(jTextArea);
		jContentPane = new JPanel();
		jContentPane.setLayout(null);
		jContentPane.add(jScrollPane, null);
		jContentPane.add(jButton_del, null);

		jContentPane.add(jButton_save, null);
		this.setContentPane(jContentPane);
	}

	private String setText(){
		LogRecord lg = new LogRecord();
		return lg.readLog();
	}



}  
