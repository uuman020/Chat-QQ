package yyz.njupt.db;

import io.netty.channel.Channel;

public class OnlineUserInfo {

	private int uid;
	private Channel channel;
	private String UDPHost;
	private int UDPPort;
	
	public OnlineUserInfo(int uid, Channel channel){
		this.uid = uid;
		this.channel = channel;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public String getUDPHost() {
		return UDPHost;
	}

	public void setUDPHost(String uDPHost) {
		UDPHost = uDPHost;
	}

	public int getUDPPort() {
		return UDPPort;
	}

	public void setUDPPort(int uDPPort) {
		UDPPort = uDPPort;
	}
	
	public String toString(){
		return "UDPhost: " + UDPHost + "UDPport: " + UDPPort;
	}

}
