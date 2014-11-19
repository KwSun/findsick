package com.kw.frame;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

import javax.swing.*;

public class sources {
	//private static final long serialVersionUID = 1L;
	private JFrame frame = new JFrame("疾病速查表");
	private JButton but = new JButton("返回");
	private JTextArea area = new JTextArea(8,10);
	private JPanel pan = new JPanel();
	
	public sources(){
		
		String filename =File.separator+"Users"+ File.separator+"apple"+File.separator+"Desktop"+File.separator+"illcheck"+File.separator+"src"+File.separator+"file"+File.separator+"content.txt";
		File file = new File(filename);
		if(file != null){
			try{
				Scanner scan = new Scanner(new FileInputStream(file));
				scan.useDelimiter("\n");
				while(scan.hasNext()){
					this.area.append(scan.next());
					this.area.append("\n");
				}
				scan.close();
			}catch(Exception ex){
				this.area.setText("文件读取错误");
			}
		}
		
		
		this.pan.add(but);
		frame.setLayout(new BorderLayout(3,3));
		frame.add(this.pan,BorderLayout.SOUTH);
		frame.add(new JScrollPane(this.area),BorderLayout.CENTER);
		//this.frame.setSize(330,180);
		//this.frame.setVisible(true);
		
		
		but.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				if(arg0.getSource() == but){
					frame.setVisible(false);
				}
			}
		});
		
		frame.setSize(600,600);
		frame.setVisible(true);
	}
}
