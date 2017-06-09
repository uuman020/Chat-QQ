package yyz.njupt.view.netService;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import net.sf.json.JSONObject;
import yyz.njupt.view.netService.UDPService.Msg;


public class UpdateUDP extends Thread {

	// 每10秒钟 向服务器注册心跳一下
	public void run() {
		int uid = Config.UID;
		Msg msg = new Msg();
		msg.setRecCode(0);
		msg.setRecContend(Common.UDP_HEART_BEAT);
		msg.setFromUID(uid);
		msg.setToUID(-1); //无效
		msg.setCode(System.currentTimeMillis() + "");

		JSONObject json = JSONObject.fromObject(msg);
		byte[] bytes = json.toString().getBytes();
        
		while (true) {
			try {
				DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length,
						InetAddress.getByName(Config.IP), Config.UDP_PORT);
				// 将更新消息发送给服务器
				client.send(datagramPacket);
				Thread.sleep(9999);
			} catch (Exception e) {
			}
		}
	}

	private DatagramSocket client = null;

	public UpdateUDP(DatagramSocket client) {
		this.client = client;
		this.start();
	}
}
