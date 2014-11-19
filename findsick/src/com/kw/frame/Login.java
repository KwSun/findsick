
package com.kw.frame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
//import javax.swing.UIManager;
import java.awt.Rectangle;
import javax.swing.*;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.Reader;
//import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.ImageIcon;

import com.kw.util.DBOperation;
import com.kw.frame.LogRecord;


public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
 private JButton jButton21 = null;
 private JButton jButton22 = null;
	private JTextField jTextField = null;
	private  JPasswordField jPasswordField = null;
	private JLabel jLabel = null;
 static String storeUserName = null;// 登录用户名
 static String storeUserPassword = null;// 登录密码
	static boolean RELOAD = true;// 重新登陆标记
	private JLabel jLabel_User = null;
	private JLabel jLabel_userName = null;
	private JLabel jLabel_password = null;
	private URL imgURL = null;
	
	
	private BtnListener btl = null;
	
	private void initialize() {

		this.setResizable(false);
		this.setSize(287, 375);//287，375
		this.setTitle("欢迎登陆");
		imgURL = this.getClass().getResource("/com/kw/images/logo_0.png");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(imgURL));
		this.setLocationRelativeTo(null);
		// this.setUndecorated(true);//设置无边框
		/*try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");// 使用windows外观
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);*/
		jButton21 = new JButton();
		jButton21.setBounds(new Rectangle(21, 312, 78, 26));
		
/*		InputStream in=this.getClass().getResourceAsStream(""); 
		Reader data=new InputStreamReader(in);*/
	
		imgURL = this.getClass().getResource("/com/kw/images/icon.png");//解决打包找不到资源的问题
	
		jButton21.setText("登录");
		getRootPane().setDefaultButton(jButton21);// 回车登录

		jButton22 = new JButton();
		jButton22.setBounds(new Rectangle(186, 312, 78, 26));
		jButton22.setText("退出");

		jTextField = new JTextField(20);
		jTextField.setBounds(new Rectangle(122, 239, 124, 22));

		jPasswordField = new JPasswordField();
		jPasswordField.setBounds(new Rectangle(122, 276, 124, 22));

		jLabel = new JLabel();
		jLabel.setText("logo");
		jLabel.setBounds(new Rectangle(0, -1, 278, 198));//278,198
		imgURL = this.getClass().getResource("/com/kw/images/login.jpg");
		jLabel.setIcon(new ImageIcon(imgURL));
		jLabel_password = new JLabel();
		jLabel_password.setBounds(new Rectangle(29, 276, 71, 19));
		jLabel_password.setText("密 码：");
		jLabel_userName = new JLabel();
		jLabel_userName.setBounds(new Rectangle(29, 241, 71, 19));
		jLabel_userName.setText("用户名：");
		jLabel_User = new JLabel();
		jLabel_User.setBounds(new Rectangle(2, 200, 275, 111));
		imgURL = this.getClass().getResource("/com/kw/images/user.gif");
		jLabel_User.setIcon(new ImageIcon(imgURL));
		jLabel_User.setText("User");

		jContentPane = new JPanel();// 新建jPanel面板
		jContentPane.setLayout(null);
		jContentPane.setBackground(new Color(255, 255, 255));
		jContentPane.add(jLabel_userName, null);
		jContentPane.add(jLabel_password, null);
		jContentPane.add(jButton21, null);
		jContentPane.add(jButton22, null);
		jContentPane.add(jTextField, null);
		jContentPane.add(jPasswordField, null);
		jContentPane.add(jLabel, null);
		jContentPane.add(jLabel_User, null);

		setContentPane(jContentPane);
		
		btl = new BtnListener();
		jButton21.addActionListener(btl);
		jButton22.addActionListener(btl);

	}
	/*
	 * 监听类
	 */
	
	public class BtnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == jButton21) {
				String user = jTextField.getText().trim();
				String password =new String(jPasswordField.getPassword()).trim();//char to String
				storeUserName = user;
				storeUserPassword = password;
				String sql ="select * from user where Name='" + user+ "' and Password='" + password + "'";;
				DBOperation dbo = new DBOperation();
				ResultSet rs = dbo.Query(sql);
					try {
						if(rs.next()){
								dispose();
								MainFrame mf = new MainFrame();
								mf.setVisible(true);	
/*								if (rs.getString("Limit").trim().equals("0")) {
									JOptionPane.showMessageDialog(null, "欢迎 【"
											+ user + "】管理员登陆！", "关于疾病诊断系统",
											JOptionPane.INFORMATION_MESSAGE);
				                    LogRecord lo = new LogRecord();
				                    lo.addLog( user + "  管理员登陆系统！");
								} else {

									JOptionPane.showMessageDialog(null, "欢迎【"
											+ user + "】用户登陆！", "系统提示",
											JOptionPane.INFORMATION_MESSAGE);
				                    LogRecord lo = new LogRecord();
				                    lo.addLog( user + " 登陆系统！");
									mf.jMenuItem_CheckLog.setEnabled(false);
									mf.jMenuItem_Init.setEnabled(false);
									mf.jMenuItem_User.setEnabled(false);	
							}*/				
						}
						else{
							JOptionPane.showMessageDialog(null, "用户名或密码错误", "系统提示",JOptionPane.ERROR_MESSAGE);
		                    LogRecord lo = new LogRecord();
		                    lo.addLog( user + "   用户尝试登录本系统！但失败了~");
						}
					} catch (HeadlessException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					dbo.CloseAll();
			}
			else if (e.getSource() == jButton22) {
				System.exit(0);
			}
		}
	}

	/**
	 * 主函数模块
	 */
	public static void main(String[] args) {
		Login login = new Login();
		login.setVisible(true);
	}

	public Login() {
		super();
		initialize();
	}

	
}
