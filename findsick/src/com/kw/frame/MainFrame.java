package com.kw.frame;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.Rectangle;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
//import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
//import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.kw.util.DBInit;


public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JMenu jMenu_Start = null;
	private JMenuBar jJMenuBar = null;
	private JMenu jMenu_Back = null;
	private JMenu jMenu_Help = null;
	private JMenuItem jMenuItem_ReLogin = null;
	public JMenuItem jMenuItem_Init = null;
	public JMenuItem jMenuItem_Change = null;
	public JMenuItem jMenuItem_User = null;
	private JMenuItem jMenuItem_setChar = null;
	private JMenuItem jMenuItem_Exit = null;
	public JMenuItem jMenuItem_Illchoose = null;
	public JMenuItem jMenuItem_SyptomInput = null;
	private JMenuItem jMenuItem_About = null;
	private JMenuItem jMenuItem_source = null;
	private JMenuItem jMenuItem_otherItem=null;
	public JMenuItem jMenuItem_CheckLog = null;
	private JMenuItem jMenuItem_Information = null;
	private JMenuItem jMenuItem_Add = null;
	private JLabel jLabel = null;
	private URL imgURL = null;
	private SystemTray sysTray = SystemTray.getSystemTray();
	
	Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/kw/images/icon.png"));
	private TrayIcon trayicon = new TrayIcon(image, "疾病诊断系统", createMenu());

	public MainFrame() {
		super();
		initialize();
	}

	private void initialize() {		
		this.setSize(640, 360);// 主界面大小
		this.setTitle("疾病诊断系统 V1.1");
		imgURL = this.getClass().getResource("/com/kw/images/icon.png");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(imgURL));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		
		
		this.addWindowListener(new WindowAdapter()// 系统关闭事件
		{
			public void windowClosing(WindowEvent e) {
				SystemTrayInitial();//初始化托盘图标
			}
		});

		jMenuItem_ReLogin = new JMenuItem();
		jMenuItem_ReLogin.setText("重新登录");
		jMenuItem_CheckLog = new JMenuItem();
		jMenuItem_CheckLog.setText("操作日志");
		jMenuItem_setChar = new JMenuItem();
		jMenuItem_setChar.setText("医疗笔记");
		jMenuItem_Change = new JMenuItem();
		jMenuItem_Change.setText("修改密码");
		jMenuItem_User = new JMenuItem();
		jMenuItem_User.setText("用户管理");
		jMenuItem_Init = new JMenuItem();
		jMenuItem_Init.setText("初始化数据库");
		jMenuItem_Exit = new JMenuItem();
		jMenuItem_Exit.setText("退出");
		jMenuItem_Illchoose = new JMenuItem();
		jMenuItem_Illchoose.setText("疾病选择");
		jMenuItem_SyptomInput = new JMenuItem();
		jMenuItem_SyptomInput.setText("病症输入");
		
		jMenuItem_Add = new JMenuItem();
		jMenuItem_Add.setText("疾病录入录入");
		jMenuItem_Information = new JMenuItem();
		jMenuItem_Information.setText("疾病信息总览");
		jMenuItem_About = new JMenuItem();
		jMenuItem_About.setText("关于");
		jMenuItem_source = new JMenuItem();
		jMenuItem_source.setText("所有症状源");
		jMenuItem_otherItem = new JMenuItem();
		jMenuItem_otherItem.setText("详细");
		jMenu_Start = new JMenu();
		jMenu_Start.setText("开始菜单");
		jMenu_Start.add(jMenuItem_ReLogin);
		jMenu_Start.add(jMenuItem_Change);
		jMenu_Start.add(jMenuItem_User);
		jMenu_Start.add(jMenuItem_CheckLog);
		jMenu_Start.add(jMenuItem_setChar);
		jMenu_Start.add(jMenuItem_Init);
		jMenu_Start.addSeparator();// 分割线
		jMenu_Start.add(jMenuItem_Exit);
		
		
		jMenu_Back = new JMenu();
		jMenu_Back.setText("疾病诊断");
		jMenu_Back.add(jMenuItem_Illchoose);
		jMenu_Back.add(jMenuItem_SyptomInput);
		jMenu_Help = new JMenu();
		jMenu_Help.setText("帮助");
		jMenu_Help.add(jMenuItem_source);
		jMenu_Help.add(jMenuItem_otherItem);
		jJMenuBar = new JMenuBar();
		jJMenuBar.setPreferredSize(new Dimension(10, 25));
		jJMenuBar.add(jMenu_Start);
		jJMenuBar.add(jMenu_Back);
		jJMenuBar.add(jMenu_Help);
		setJMenuBar(jJMenuBar);

		jLabel = new JLabel();
		jLabel.setText("JLabel");
		jLabel.setBounds(new Rectangle(1, -2, 640, 360));
		imgURL = this.getClass().getResource("/com/kw/images/main.png");
		jLabel.setIcon(new ImageIcon(imgURL));

		jContentPane = new JPanel();
		jContentPane.setLayout(null);
		jContentPane.add(jLabel, null);
		setContentPane(jContentPane);
		
		btnListener btn = new btnListener();
		jMenuItem_ReLogin.addActionListener(btn);
		jMenuItem_Change.addActionListener(btn);
		jMenuItem_User.addActionListener(btn);
		jMenuItem_Init.addActionListener(btn);
		jMenuItem_CheckLog.addActionListener(btn);
		jMenuItem_setChar.addActionListener(btn);
		jMenuItem_Exit.addActionListener(btn);
		jMenuItem_Illchoose.addActionListener(btn);
		jMenuItem_SyptomInput.addActionListener(btn);
		jMenuItem_Information.addActionListener(btn);
		jMenuItem_source.addActionListener(btn);
		jMenuItem_otherItem.addActionListener(btn);
	}
	
	
	
/**
 * 初始化托盘
 */
	private void SystemTrayInitial() {// 托盘
		if (!SystemTray.isSupported()) // 判断当前系统是否支持系统栏
			return;
				try {
					sysTray.add(trayicon);
				} catch (AWTException e1) {
					e1.printStackTrace();
				}
				setVisible(false);
				trayicon.displayMessage("疾病诊断系统", "", MessageType.INFO);// 窗体托盘时所显示的消息对话
			trayicon.addActionListener(new ActionListener()// 击图标时显示窗体
					{
						public void actionPerformed(ActionEvent e) {
							sysTray.remove(trayicon);
							setVisible(true);
						}
					});
	}
/**
 * 初始化托盘右键
 * @return
 */
	private PopupMenu createMenu() { // 创建系统栏菜单的方法
		PopupMenu menu = new PopupMenu();
		MenuItem exitItem = new MenuItem("退出本系统");
		exitItem.addActionListener(new ActionListener() { // 系统栏退出事件
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});
		MenuItem openItem = new MenuItem("打开主窗口");
		openItem.addActionListener(new ActionListener() {// 系统栏打开菜单项事件
					public void actionPerformed(ActionEvent e) {
						if (!isVisible()) {
							setVisible(true);
							sysTray.remove(trayicon);
						}
					}
				});
		
		/*MenuItem viewItem = new MenuItem("访问作者主页");
		viewItem.addActionListener(new ActionListener() {// 系统栏打开菜单项事件
					public void actionPerformed(ActionEvent e) {
						try {
							Runtime.getRuntime().exec("explorer http://hi.baidu.com/kw/blog");
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
				});*/
		menu.add(openItem);
		//menu.add(viewItem);
		menu.addSeparator();
		menu.add(exitItem);
		return menu;
	}

	/**
	 * 内部类监听器模块
	 */
	public class btnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			LogRecord lo = new LogRecord();
			if (e.getSource() == jMenuItem_ReLogin) {
				lo.addLog(Login.storeUserName + "   执行重新登录操作！");
				dispose();
				Login login = new Login();// 调用无参数构造方法
				login.setVisible(true);
			} else if (e.getSource() == jMenuItem_Change) {
				lo.addLog(Login.storeUserName + "   浏览修改密码模块！");
				ChangePassword cp = new ChangePassword();
				cp.setVisible(true);
			} else if (e.getSource() == jMenuItem_User) {
				lo.addLog(Login.storeUserName + "   浏览用户管理模块！");
				UserManage um = new UserManage();
				um.setVisible(true);
			} else if (e.getSource() == jMenuItem_CheckLog) {
				lo.addLog(Login.storeUserName + "   浏览日志查看模块！");
				LogManage sl = new LogManage();
				sl.setVisible(true);
			} else if (e.getSource() == jMenuItem_setChar) {
				lo.addLog(Login.storeUserName + "   浏览医疗笔记模块！");
				note ls = new note();
				//ls.setVisible(true);*/
			} else if (e.getSource() == jMenuItem_Init) {

				lo.addLog(Login.storeUserName + "   初始化数据库操作！！！");
				DBInit di = new DBInit();
				di.setVisible(true);
			} else if (e.getSource() == jMenuItem_Exit) {

				lo.addLog(Login.storeUserName + "   退出本系统！");
				System.exit(0);
			} else if (e.getSource() == jMenuItem_Illchoose) {
				JbxzManage jbxz;
				try {
					jbxz = new JbxzManage();
					jbxz.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (e.getSource() == jMenuItem_SyptomInput) {
				JbsrManage jbsr;
				try {
					jbsr = new JbsrManage();
					jbsr.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (e.getSource() == jMenuItem_source) {
				lo.addLog(Login.storeUserName + "   浏览所有症状源模块！");
				sources zz = new sources();
				//zz.setVisible(true);
			} else if (e.getSource() == jMenuItem_otherItem) {
				lo.addLog(Login.storeUserName + "   浏览详细模块！");
				other xx = new other();
				//xx.setVisible(true);*/
			
			}
		}

	}
}