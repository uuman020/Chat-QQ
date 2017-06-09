package yyz.njupt.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.DatagramSocket;
import java.net.SocketException;

import javax.swing.JButton;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import yyz.njupt.view.netService.Common;
import yyz.njupt.view.netService.Config;
import yyz.njupt.view.netService.UpdateUDP;
import yyz.njupt.view.netService.POJO.LoginRespProto;
import yyz.njupt.view.netService.UDPService.MessageService;
import yyz.njupt.view.netService.loginService.LoginClient;

public class LoginDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPasswordField reg_password2;
	private JPasswordField reg_password1;
	private JTextField code;
	private JTextField reg_username;
	private JPasswordField password;
	private JTextField username;

	/**
	 * Launch the application
	 * 
	 * @param args
	 */
	public static void main(String args[]) {

		// test.Main.main(args);
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		// test.Main.main(args);

		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginDialog frame = new LoginDialog();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame
	 */
	public LoginDialog() {
		super();
		setTitle("Chat-QQ");
		setResizable(false);
		setAlwaysOnTop(true);//
		getContentPane().setLayout(null);
		setBounds(100, 100, 293, 290);// 646 314

		setLocation(WindowsXY.getXY(this.getSize()));

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		/*
		 * final JLabel label = new JLabel(); label.setText("账号:");
		 * label.setBounds(10, 102, 65, 24); getContentPane().add(label);
		 */

		final JLabel emailLabel = new JLabel();
		emailLabel.setText("账号:");
		emailLabel.setBounds(10, 109, 65, 24);
		getContentPane().add(emailLabel);

		username = new JTextField();
		username.setBounds(55, 99, 219, 48);
		getContentPane().add(username);

		final JLabel label_1 = new JLabel();
		label_1.setText("密码:");
		label_1.setBounds(10, 186, 65, 18);
		getContentPane().add(label_1);

		password = new JPasswordField();
		password.setBounds(55, 171, 219, 48);
		getContentPane().add(password);

		final JButton loginbutton = new JButton();
		loginbutton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(final ActionEvent e) {

				String userEmail = username.getText().trim();
				String passwd = password.getText().trim();
				if (userEmail.equals("")) {
					javax.swing.JOptionPane.showMessageDialog(LoginDialog.this, "用户名不能为空");
					return;
				}
				if (passwd.equals("")) {
					javax.swing.JOptionPane.showMessageDialog(LoginDialog.this, "密码不能为空");
					return;
				}
				// 验证email格式是否正确
				if (userEmail.indexOf('@') < 0) {
					javax.swing.JOptionPane.showMessageDialog(LoginDialog.this, "邮箱格式不正确");
					return;
				}

				Config.setLoginReq(userEmail, passwd);

				// 启动登录客户端
				Config.thread = new Thread(new Runnable() {

					@Override
					public void run() {
						new LoginClient().connect(Config.IP, Config.LOGIN_PORT);
					}
				});
				Config.thread.start();

				System.out.println("正在登录中..");
				while (Config.LoginResp == null) {
					// 等待登录状态回执
				}
				LoginRespProto.LoginResp resp = Config.LoginResp;
				if (resp.getRespContend().equals(Common.LOGIN_AUTH_RESP)) {// 登录成功

					while (Config.personalDataResp == null || Config.onlineFriendsResp == null
							|| Config.friendsInfoListResp == null) {
						// 等待个人资料和好友资料的到来
					}
					///////////////////////////////////////////// 启动UDP服务器
					try {
						Config.datagramSocket = new DatagramSocket();
					} catch (SocketException e1) {
						e1.printStackTrace();
					}
					
					// 启动心跳包
					new UpdateUDP(Config.datagramSocket);
					// 启动消息服务
					new MessageService(Config.datagramSocket);  

					LoginDialog.this.setVisible(false);
					Config.friendsListDialog = new FriendsListDialog();
					Config.friendsListDialog.setVisible(true);
					LoginDialog.this.dispose();
				} else if (resp.getRespContend().equals(Common.LOGIN_PASSWD_ERROR)) {// 密码错误
					javax.swing.JOptionPane.showMessageDialog(LoginDialog.this, "登录密码错误");
					return;
				} else if (resp.getRespContend().equals(Common.LOGIN_USERNAME_NOT_FOUND)) {// 找不到账户
					javax.swing.JOptionPane.showMessageDialog(LoginDialog.this, "找不到账户");
					return;
				} else if (resp.getRespContend().equals(Common.LOGIN_USER_LOCKED)) {// 账户锁定
					javax.swing.JOptionPane.showMessageDialog(LoginDialog.this, "账户锁定");
					return;
				} else if (resp.getRespContend().equals(Common.LOGIN_UNKONW_EXCEPTION)) {// 未知错误
					javax.swing.JOptionPane.showMessageDialog(LoginDialog.this, "未知错误");
					return;
				}
			}

		});

		loginbutton.setText("登录");
		loginbutton.setBounds(177, 225, 97, 51);
		getContentPane().add(loginbutton);

		final JButton button_1 = new JButton();
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {

				if (LoginDialog.this.getHeight() == 646) {// 646 314
					LoginDialog.this.setSize(293, 290);
				} else {
					LoginDialog.this.setSize(293, 646);
				}
				// setLocation(WindowsXY.getXY(LoginDialog.this.getSize()));
			}
		});
		button_1.setText("注册");
		button_1.setBounds(10, 225, 97, 51);
		getContentPane().add(button_1);

		final JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "注册用户", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, null, null));
		panel.setBounds(10, 306, 264, 271);
		getContentPane().add(panel);

		/*
		 * final JLabel label_2 = new JLabel(); label_2.setText("  Email:");
		 * label_2.setBounds(10, 33, 65, 18); panel.add(label_2);
		 */

		final JLabel emailLabel_1 = new JLabel();
		emailLabel_1.setText("  Email:");
		emailLabel_1.setBounds(10, 39, 65, 18);
		panel.add(emailLabel_1);

		reg_username = new JTextField();
		reg_username.setBounds(63, 27, 180, 43);
		panel.add(reg_username);

		final JButton button_2 = new JButton();
		button_2.setText("发送验证码");
		button_2.setBounds(146, 76, 97, 30);
		panel.add(button_2);

		code = new JTextField();
		code.setBounds(63, 113, 85, 43);
		panel.add(code);

		final JLabel label_3 = new JLabel();
		label_3.setText("验证码:");
		label_3.setBounds(10, 125, 65, 18);
		panel.add(label_3);

		reg_password1 = new JPasswordField();
		reg_password1.setBounds(63, 162, 180, 43);
		panel.add(reg_password1);

		reg_password2 = new JPasswordField();
		reg_password2.setBounds(63, 211, 180, 43);
		panel.add(reg_password2);

		final JLabel label_4 = new JLabel();
		label_4.setText("  密码");
		label_4.setBounds(10, 174, 65, 18);
		panel.add(label_4);

		final JLabel label_5 = new JLabel();
		label_5.setText("重复密码:");
		label_5.setBounds(10, 223, 65, 18);
		panel.add(label_5);

		final JButton button_3 = new JButton();
		button_3.setText("注册");
		button_3.setBounds(177, 583, 97, 30);
		getContentPane().add(button_3);

		final JButton button_4 = new JButton();
		button_4.setText("退出");
		button_4.setBounds(10, 583, 97, 30);
		getContentPane().add(button_4);

		// 在Netconfig里面就能一份这个对象，以做销毁
		Config.loginDialog = this;
	}

}
