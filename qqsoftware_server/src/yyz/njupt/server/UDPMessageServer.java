package yyz.njupt.server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import net.sf.json.JSONObject;
import yyz.njupt.db.OnlineDB;
import yyz.njupt.db.OnlineUserInfo;

/**
 * UDP信息中转服务器
 * 
 * @author yyz
 *
 */
public class UDPMessageServer implements Runnable {

	private DatagramPacket packet = null;
	private static Logger logger = Logger.getLogger(UDPMessageServer.class);

	public UDPMessageServer(DatagramPacket packet) {
		this.packet = packet;
	}

	/// 业务处理
	public void run() {
		try {

			String str = new String(packet.getData(), 0, packet.getLength());
			JSONObject json = JSONObject.fromObject(str);
			logger.info("Server received: " + str);
			
			// 处理心跳包
			if (json.getInt("recCode") == 0) {
				int fromUID = json.getInt("fromUID");
				// 更新最新的IP和端口号
				OnlineDB.getOnlineDB().updateUDP(fromUID, packet.getAddress().getHostAddress(),
						packet.getPort());
				
				// 处理信息转发 // 处理消息确认
			} else if (json.getInt("recCode") == 1 || json.getInt("recCode") == 2) {
				int fromUID = json.getInt("fromUID");
				int toUID = json.getInt("toUID");
				// 更新最新的IP和端口号
				OnlineDB.getOnlineDB().updateUDP(fromUID, packet.getAddress().getHostAddress(),
						packet.getPort());
				// 获得要接收你信息的人
				OnlineUserInfo toUserinfo = OnlineDB.getOnlineDB().getInfo(toUID);
				// 准备转发到客户端的数据包
				DatagramPacket datagramPacket = new DatagramPacket(packet.getData(), packet.getLength(),
						InetAddress.getByName(toUserinfo.getUDPHost()), toUserinfo.getUDPPort());
				// 发出数据包
				datagramSocket.send(datagramPacket);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static DatagramSocket datagramSocket = null;

	// 启动服务器
	public static void openServer() throws Exception {
		datagramSocket = new DatagramSocket(8065);
		// 制作线程池
		ExecutorService execute = Executors.newFixedThreadPool(1000);
		while (true) {
			try {
				// 等待客户端的数据
				byte[] b = new byte[1024 * 32];
				DatagramPacket datagramPacket = new DatagramPacket(b, b.length);
				datagramSocket.receive(datagramPacket);
				//////////////////////////////////////////

				// 数据一旦到手后 立马抓出一个线程处理
				execute.execute(new UDPMessageServer(datagramPacket));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}