package yyz.njupt.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

import net.sf.json.JSONObject;
import yyz.njupt.view.netService.Config;
import yyz.njupt.view.netService.UDPService.Msg;

public class ChatFrame extends JFrame implements WindowListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea textArea_1;
	private final JLabel infoLabel = new JLabel();
	private final JLabel netnameLabel = new JLabel();
	private final JLabel img = new JLabel();
	private JTextArea textArea;

	private int  uid;
	private String petname;
	private int  image;
	private String remark;

	/**
	 * 添加消息
	 * 
	 * @param msg
	 */
	public void addMessage(yyz.njupt.view.netService.UDPService.Msg msg) {

		@SuppressWarnings("deprecation")
		String str = "\n" + petname + "  " + new Date().toLocaleString() + "\n" + msg.getRecContend() + "\n";

		textArea_1.setText(textArea_1.getText() + str);

		textArea_1.setSelectionStart(textArea_1.getText().toString().length());
		textArea_1.setSelectionEnd(textArea_1.getText().toString().length());
		textArea.requestFocus();
	}

	/**
	 * 添加自己的消息
	 * 
	 * @param msg
	 */
	public void addMyMessage(Msg msg) {

		@SuppressWarnings("deprecation")
		String str = "\n" + Config.personalDataResp.getPetname() + "  "
				+ new Date().toLocaleString() + "\n" + msg.getRecContend() + "\n";

		textArea_1.setText(textArea_1.getText() + str);
		textArea_1.setSelectionStart(textArea_1.getText().toString().length());
		textArea_1.setSelectionEnd(textArea_1.getText().toString().length());
		
		textArea.requestFocus();
	}

	/**
	 * Create the frame
	 */
	public ChatFrame(int touid, String petname, int image, String remark, List<Msg> msgs) {
		super();
		this.uid= touid;
		this.petname = petname;
		this.image= image;
		this.remark = remark;

		this.setTitle(this.petname);
		ImageIcon imageIcon = new ImageIcon("face0/" + this.image + ".png");

		this.setIconImage(imageIcon.getImage());

		infoLabel.setText(this.remark);
		netnameLabel.setText(this.petname);
		img.setIcon(imageIcon);

		setBounds(100, 100, 559, 655);

		final JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		getContentPane().add(panel, BorderLayout.NORTH);

		img.setPreferredSize(new Dimension(48, 48));
		panel.add(img, BorderLayout.WEST);

		final JPanel panel_1 = new JPanel();
		panel_1.setLayout(new BorderLayout());
		panel.add(panel_1, BorderLayout.CENTER);

		netnameLabel.setFont(new Font("微软雅黑", Font.BOLD, 14));
		panel_1.add(netnameLabel, BorderLayout.CENTER);

		panel_1.add(infoLabel, BorderLayout.SOUTH);

		final JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerLocation(400);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);

		final JPanel panel_2 = new JPanel();
		panel_2.setLayout(new BorderLayout());
		splitPane.setRightComponent(panel_2);

		final JPanel panel_3 = new JPanel();
		final FlowLayout flowLayout_1 = new FlowLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_3.setLayout(flowLayout_1);
		panel_2.add(panel_3, BorderLayout.NORTH);

		final JButton button_2 = new JButton();
		button_2.setText("字体");
		panel_3.add(button_2);

		final JButton button_3 = new JButton();
		button_3.setText("震屏");
		panel_3.add(button_3);

		final JPanel panel_4 = new JPanel();
		final FlowLayout flowLayout = new FlowLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_4.setLayout(flowLayout);
		panel_2.add(panel_4, BorderLayout.SOUTH);

		final JButton button = new JButton();
		button.setText("关闭");
		panel_4.add(button);

		final JButton button_1 = new JButton();
		button_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent e) {

				try {
					Msg msg = new Msg();
					msg.setRecCode(1);
					msg.setRecContend(textArea.getText());
					msg.setFromUID(Config.UID);
					msg.setToUID(uid);
					msg.setCode(System.currentTimeMillis() + "");

					JSONObject json = JSONObject.fromObject(msg);
					byte[] bytes = json.toString().getBytes();

					DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length,
							InetAddress.getByName(Config.IP), Config.UDP_PORT);
					Config.datagramSocket.send(datagramPacket);
					textArea.setText("");

					addMyMessage(msg);
				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}
		});
		button_1.setText("　发　送　");
		panel_4.add(button_1);

		final JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane, BorderLayout.CENTER);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		final JScrollPane scrollPane_1 = new JScrollPane();
		splitPane.setLeftComponent(scrollPane_1);

		textArea_1 = new JTextArea();
		scrollPane_1.setViewportView(textArea_1);

		this.addWindowListener(this);
		this.setVisible(true);
		//		
		for (Msg msg : msgs) {
			addMessage(msg);
		}
		msgs.clear();
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		Config.closeChatFrame(uid);
		this.dispose();
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}
