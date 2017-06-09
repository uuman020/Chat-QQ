package yyz.njupt.view.netService.UDPService;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import net.sf.json.JSONObject;

/**
 * 接收服务器的中转消息
 * 
 * @author yyz
 *
 */
public class MessageService extends Thread {

	public void run() {
		while (true) {
			try {
				byte[] bytes = new byte[1024 * 32];
				DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
				client.receive(datagramPacket);		
				String str = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
				JSONObject json = JSONObject.fromObject(str);			
				MessagePool.getMessagePool().addMessage(json);
			} catch (Exception e) {
			}
		}
	}

	private DatagramSocket client = null;

	public MessageService(DatagramSocket client) {
		this.client = client;
		this.start();
	}
}
