package yyz.njupt.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import yyz.njupt.view.netService.Config;
import yyz.njupt.view.netService.POJO.LoginRespProto;


/**
 * 
 * 
 * @author yyz
 *
 */
public class FriendsListDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final JLabel petname = new JLabel();
	final JLabel image = new JLabel(new ImageIcon("face0/0.png"));
	final JLabel remark = new JLabel();

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
					FriendsListDialog frame = new FriendsListDialog();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void setPersonalData() {	
		LoginRespProto.PersonalData resp = Config.personalDataResp;

		this.setTitle(resp.getPetname());		
		this.petname.setText(resp.getPetname());
		this.remark.setText(resp.getRemark());
		this.image.setIcon(new ImageIcon("face0/" + resp.getImage() + ".png"));
	}
	

	public FriendsListDialog() {
		super();
		setBounds(1050, 100, 246, 643);

		final JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(5, 5));
		getContentPane().add(panel, BorderLayout.NORTH);

		image.setPreferredSize(new Dimension(55, 55));

		panel.add(image, BorderLayout.WEST);

		final JPanel panel_1 = new JPanel();
		final BorderLayout borderLayout = new BorderLayout(5, 5);
		panel_1.setLayout(borderLayout);
		panel.add(panel_1, BorderLayout.CENTER);

		petname.setFont(new Font("微软雅黑", Font.BOLD, 16));
		petname.setText("");
		panel_1.add(petname, BorderLayout.CENTER);

		remark.setFont(new Font("宋体", Font.PLAIN, 12));
		remark.setText("");
		panel_1.add(remark, BorderLayout.SOUTH);

		final JPanel panel_2 = new JPanel();
		panel_2.setLayout(new BorderLayout());
		getContentPane().add(panel_2, BorderLayout.SOUTH);

		final JPanel panel_3 = new JPanel();
		final FlowLayout flowLayout_1 = new FlowLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_3.setLayout(flowLayout_1);
		panel_2.add(panel_3);

		final JButton button = new JButton();
		button.setText("设置");
		panel_3.add(button);

		final JButton button_2 = new JButton();
		button_2.setText("查找");
		panel_3.add(button_2);

		final JPanel panel_4 = new JPanel();
		final FlowLayout flowLayout = new FlowLayout();
		panel_4.setLayout(flowLayout);
		panel_2.add(panel_4, BorderLayout.EAST);

		final JButton button_1 = new JButton();
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				System.exit(0);
			}
		});
		button_1.setText("退出");
		panel_4.add(button_1);

		final JTabbedPane tabbedPane = new JTabbedPane();
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		final JPanel panel_5 = new JPanel();
		panel_5.setLayout(new BorderLayout());
		tabbedPane.addTab("我的好友", null, panel_5, null);

		final JScrollPane scrollPane = new JScrollPane();
		panel_5.add(scrollPane, BorderLayout.CENTER);
		scrollPane.getViewport().add(Config.friendsListJPanel);
		setPersonalData();
	}

}
