package com.kw.frame;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

import javax.swing.*;

public class note implements ActionListener {
	//private static final long serialVersionUID = 1L;
	private JTextArea area = new JTextArea(8,10);
	private JFrame frame = new JFrame("诊断笔记");
	private JButton open = new JButton("打开笔记");
	private JButton save = new JButton("保存笔记");
	private JLabel label = new JLabel("笔记现在无内容");
	private JPanel butPan = new JPanel();
	
	public note (){
		this.butPan.add(open);
		this.butPan.add(save);
		frame.setLayout(new BorderLayout(3,3));
		frame.add(this.label,BorderLayout.NORTH);
		frame.add(this.butPan,BorderLayout.SOUTH);
		frame.add(new JScrollPane(this.area),BorderLayout.CENTER);
		this.frame.setSize(330,180);
		this.frame.setVisible(true);
		this.open.addActionListener(this);
		this.save.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e){
		File file = null;
		int result = 0 ;
		JFileChooser fileChooser = new JFileChooser();
		if(e.getSource()==this.open){
			this.area.setText("");
			fileChooser.setApproveButtonText("确定");
			fileChooser.setDialogTitle("打开笔记");
			result = fileChooser.showOpenDialog(this.frame);
			if(result==JFileChooser.APPROVE_OPTION){
				file = fileChooser.getSelectedFile();
				this.label.setText("打开的医疗笔记为："+file.getName());
			}else if(result==JFileChooser.CANCEL_OPTION){
				this.label.setText("未选择任何笔记");
			}else{
				this.label.setText("操作出现错误");
			}
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
					this.label.setText("文件读取错误");
				}
			}
		}
		if(e.getSource()==this.save){
			result = fileChooser.showSaveDialog(this.frame);
			if(result ==JFileChooser.APPROVE_OPTION){
				file = fileChooser.getSelectedFile();
				this.label.setText("选择的存储笔记名称为："+file.getName());
			}else if(result==JFileChooser.CANCEL_OPTION){
				this.label.setText("未选择任何笔记");
			}else{
				this.label.setText("操作出现错误");
			}
			if(file !=null){
				try{
					PrintStream out=new PrintStream(new FileOutputStream(file));
					out.print(this.area.getText());
					out.close();
				}catch(Exception ex){
				this.label.setText("笔记保存失败");
				}
			}
		}
	}
}
