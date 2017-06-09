package yyz.njupt.view;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import yyz.njupt.view.netService.Config;
import yyz.njupt.view.netService.UDPService.Msg;

/**
 * school: njupt
 * @author yyz
 *
 */
public class FaceJPanel extends JPanel implements Comparable<FaceJPanel>, Runnable, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int image;
	private String petname;
	private String remark;
	private int uid;
	private JLabel label_image;
	private JLabel label_petname;
	private JLabel label_remark;

	public FaceJPanel(int uid, String petname, String remark, int image) {
		this.image = image;
		this.petname = petname;
		this.remark = remark;
		this.uid = uid;

		this.setLayout(null);

		label_image = new JLabel();
		label_image.setBounds(4, 4, 48, 48);
		add(label_image);

		label_image.setIcon(new ImageIcon("face1/" + this.image + ".png"));

		label_petname = new JLabel();
		label_petname.setBounds(58, 10, 478, 18);
		add(label_petname);
		label_petname.setFont(new Font("微软雅黑", Font.BOLD, 14));
		label_petname.setText(this.petname);

		label_remark = new JLabel();
		label_remark.setBounds(58, 28, 478, 18);
		add(label_remark);
		label_remark.setText(this.remark);

		this.addMouseListener(this);
	}

	private boolean online = false;

	public void updateOnline(boolean online) {
		this.online = online;
		if (this.online) {
			label_image.setIcon(new ImageIcon("face0/" + this.image + ".png"));
		} else {
			label_image.setIcon(new ImageIcon("face1/" + this.image + ".png"));
		}
	}

	@Override
	public int compareTo(FaceJPanel other) {
		if (this.online && !other.online) {
			return -1;
		} else if (!this.online && other.online) {
			return 1;
		} else {
			return this.petname.compareTo(other.petname);
		}
	}

	// 所有的消息
	private List<Msg> msgs = new ArrayList<>();

	boolean run = true;

	public void run() {
		run = true;
		int x = this.getX();
		int y = this.getY();

		while (run) {

			this.setLocation(x - 1, y - 1);
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {

			}
			this.setLocation(x + 2, y + 2);
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {

			}
		}
		this.setLocation(x, y);
	}

	private Thread thread = null;

	// 寄存消息
	public void addMessage(Msg msg) {

		msgs.add(msg);// 添加消息进去

		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		} else if (thread.getState() == Thread.State.TERMINATED) {
			thread = new Thread(this);
			thread.start();
		} else if (run == false) {
			thread = new Thread(this);
			thread.start();
		}

	}

	int xx = 0;
	int yy = 0;

	public void mouseClicked(MouseEvent e) {

		if (e.getClickCount() == 2) {
			if (this.online) {
				run = false;// 终止线程
				Config.showChatFrame(uid, petname, remark, image, msgs);
			}
		}
	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}


	public void mouseEntered(MouseEvent e) {
		System.out.println("mouse entered");
		xx = this.getX();
		yy = this.getY();
		this.setLocation(xx - 3, yy - 3);
	}



	public void mouseExited(MouseEvent e) {
		this.setLocation(xx, yy);
	}

}
