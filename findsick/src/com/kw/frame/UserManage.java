package com.kw.frame;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Rectangle;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Label;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import javax.swing.ImageIcon;

import com.kw.util.DBOperation;

public class UserManage extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JButton jButton_Add = null;
	private Label label = null;
	private Label label1 = null;
	private Label label2 = null;
	private JTextField jTextField_Name = null;
	private JPasswordField jPasswordField_Password = null;
	private JComboBox jComboBox_Limit = null;

	private JButton jButton_Del = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private DefaultTableModel dtm;
	/**
	 * 构造函数模块
	
	 */
	public UserManage() {
		super();
		initialize();
	}

	private void initialize() {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("用户管理");
		this.setModal(true);
		this.setSize(new Dimension(495, 205));
		this.setLocation(new Point(0, 0));
		this.setLocationRelativeTo(null);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/com/kw/images/icon.jpg")));

		jButton_Add = new JButton();
		jButton_Add.setBounds(new Rectangle(16, 130, 78, 26));
		jButton_Add.setText("添加");
		jTextField_Name = new JTextField();
		jTextField_Name.setBounds(new Rectangle(97, 18, 94, 23));
		jPasswordField_Password = new JPasswordField();
		jPasswordField_Password.setBounds(new Rectangle(97, 56, 94, 22));
		jComboBox_Limit = new JComboBox();// 不要漏了这句话
		jComboBox_Limit.addItem("医疗专家");
		jComboBox_Limit.addItem("诊断医生");
		jComboBox_Limit.setSelectedIndex(0);
		jComboBox_Limit.setBounds(new Rectangle(98, 92, 93, 21));
		jButton_Del = new JButton();
		jButton_Del.setBounds(new Rectangle(112, 129, 78, 26));

		jButton_Del.setText("删除");
		label2 = new Label();
		label2.setBounds(new Rectangle(15, 89, 76, 22));
		label2.setText("权   限：");
		label1 = new Label();
		label1.setBounds(new Rectangle(15, 54, 76, 24));
		label1.setText("密   码：");
		label = new Label();
		label.setBounds(new Rectangle(15, 18, 76, 25));
		label.setText("用 户 名：");
		
		jContentPane = new JPanel();
		jContentPane.setLayout(null);
		jContentPane.add(jButton_Add, null);
		jContentPane.add(label, null);
		jContentPane.add(label1, null);
		jContentPane.add(label2, null);
		jContentPane.add(jTextField_Name, null);
		jContentPane.add(jPasswordField_Password, null);
		jContentPane.add(jComboBox_Limit, null);
		jContentPane.add(jButton_Del, null);
		jContentPane.setBorder(BorderFactory.createTitledBorder("管理一体化"));
		setContentPane(jContentPane);

		Search();// 显示用户这种查询方式和以后对话框胡查询方式不一样的！
		
		jButton_Add.addActionListener(new btnListener());
		jButton_Del.addActionListener(new btnListener());
	}

	public class btnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == jButton_Del) {
				int row = jTable.getSelectedRow();
				if (row != -1) {
					String name1 = (String) jTable.getValueAt(row, 0);
					if (Login.storeUserName == name1
							|| Login.storeUserName.equals(name1)) {
						JOptionPane.showMessageDialog(null, "我靠呀！自己不能删除自己！");
					} else {
						DBOperation dbo = new DBOperation();
						String sql = ("delete from user where Name = '"
								+ name1 + "'");
						dbo.TheAll(sql);
						dtm.removeRow(jTable.getSelectedRow());
						JOptionPane.showMessageDialog(null, "删除用户成功！");
						dbo.CloseAll(); 
					}
				} else {
					JOptionPane.showMessageDialog(null, "请选择需要删除的行！");
					return;
				}
			} else if (e.getSource() == jButton_Add) {
				String sql = "Insert Into user (Name,Password,Privilege) Values('"
						+ jTextField_Name.getText()
						+ "','"
						+ new String(jPasswordField_Password.getPassword())
								.trim() + "','"
						+ jComboBox_Limit.getSelectedItem().toString() + "')";
				String sql1 = "select Name from user where Name= '"
						+ jTextField_Name.getText().trim() + " ' ";// 查询是否有重名
				if (jTextField_Name.getText() == null
						|| jTextField_Name.getText().equals("")
						|| jTextField_Name.getText().trim() == "")// 用户名是否为空
				{
					JOptionPane.showMessageDialog(null, "用户名不能为空！");
				} else {
					DBOperation dbo = new DBOperation();
					ResultSet rs = dbo.Query(sql1);

					try {
						if (rs.next())// 对结果rs集进行遍历，如果有记录就提示系统重名
						{
							JOptionPane.showMessageDialog(null, "用户名重名！",
									"系统提示", JOptionPane.ERROR_MESSAGE);
						} else {
							Vector<Vector<String>> row = new Vector<Vector<String>>();
							Vector<String> col1 = new Vector<String>();
							Vector<String> col2 = new Vector<String>();
							col1.add(jTextField_Name.getText());
							row.add(col1);
							col1.add(jComboBox_Limit.getSelectedItem()
									.toString());
							row.add(col2);
							dtm.addRow(row.get(0));// 在dtm中也要添加相应的数据，jTable才会刷新！
							JOptionPane.showMessageDialog(null, "添加成功！");
							dbo.TheAll(sql);
						}
					} catch (Exception ee) {
						ee.printStackTrace();
					}
					dbo.CloseAll();
				}

			}
		}
	}

	/**
	 * 更新表单函数模块// 显示用户这种查询方式和以后对话框胡查询方式不一样的！
	 */
	private void Search() {
		DBOperation dbo = new DBOperation();
		Vector<Vector<String>> row = new Vector<Vector<String>>();
		ResultSet rs = dbo.Query("select Name,Privilege  from user");
		try {
			while (rs.next()) {
				Vector<String> col = new Vector<String>();
				col.add((rs.getString("Name")).trim());
				row.add(col);
			}
		} catch (SQLException b) {
			b.printStackTrace();
		}
		try {
			rs.close();
		} catch (SQLException b) {
			b.printStackTrace();
		}
		String[] tableTitles = { "用户名", "用户权限" };// 标题
		Vector<String> tableTitle = new Vector<String>();// 将列标题放到迭代器中，DefaultTableModel要用
		for (int i = 0; i < tableTitles.length; i++) {
			tableTitle.add(tableTitles[i]);
		}
		dtm = new DefaultTableModel();
		dtm.setDataVector(row, tableTitle);
		jTable = new JTable(dtm);// 新建表
		jTable.setBounds(new Rectangle(202, 15, 268, 136));
		jScrollPane = new JScrollPane();
		jScrollPane.setBounds(new Rectangle(202, 15, 268, 136));
		jScrollPane.setViewportView(jTable);
		jContentPane.add(jScrollPane, null);
	}

}
