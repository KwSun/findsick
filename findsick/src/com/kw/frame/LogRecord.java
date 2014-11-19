package com.kw.frame;
import javax.swing.JOptionPane;
import java.text.DateFormat;
import java.util.Date;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
public class LogRecord {
	FileOutputStream fos = null;
	FileInputStream fis = null;
	BufferedReader bufread =null;
	BufferedWriter bufwrite =null;

	/**
	 * 添加日志函数模块
	 */
	public void addLog(String s) {
		try {
			
			FileWriter wr = new FileWriter(new File("src/com/kw/log.dat"), true);//建立节点流FileWriter
			bufwrite = new BufferedWriter(wr);//套在对象流BufferedWriter上，使用其write方法
			
			Date date = new Date();
			DateFormat df = DateFormat.getDateTimeInstance();
			String format = df.format(date).toString();// 时间日期
			
			bufwrite.write(s);//写入实参
			bufwrite.write(format);//写入时间
			bufwrite.newLine();//换行
			bufwrite.flush();
			
			wr.close();
			bufwrite.close();
		} catch (Exception ee) {
			JOptionPane.showMessageDialog(null, "添加日志时出错！");
		}
	}
/**
 * 清空日志函数模块
 */
	public void deleteLog() {
		try{
		FileOutputStream fos=new FileOutputStream("src/com/kw/log.dat",false);//用新文件覆盖，就是清空了
		fos.close();
		}
		catch(Exception ex){}
	}
	/**
	 * 读取日志
	 */
	public String readLog(){
        String str = "以下是本次诊断系统所记录的操作日志:"+"\n";
        try {
        	FileReader fr = new FileReader(new File("src/com/kw/log.dat"));
            bufread = new BufferedReader(fr);//用bufferedreader包装FileReader流，从而提供readLine提供读取一行的能力
            String line = bufread.readLine();//读一行，然后返回String字符串，容易处理
                while ((line!= null)) {
                	str = str + line +"\n";//读完每一行，换行一下
                    line = bufread.readLine();
                }
                fr.close();
                bufread.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return str;
	}

}
