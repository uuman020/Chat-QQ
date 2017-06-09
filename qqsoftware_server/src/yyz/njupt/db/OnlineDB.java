package yyz.njupt.db;

import java.net.InetSocketAddress;
import java.util.concurrent.ConcurrentHashMap;

import io.netty.channel.Channel;

public class OnlineDB {
	
	private static OnlineDB onlineDB = new OnlineDB();
	
	private OnlineDB(){
		
	}
	
	public static OnlineDB getOnlineDB(){
		return onlineDB;
	}
	
	// 在线用户登记在这里
	private static ConcurrentHashMap<Integer, OnlineUserInfo> onlineUsers = new ConcurrentHashMap<Integer, OnlineUserInfo>(); 
	
	public void registOnline(int uid, OnlineUserInfo info){
		onlineUsers.put(uid, info);
	}
	
	public OnlineUserInfo getInfo(int uid){
		return onlineUsers.get(uid);
	}
	
	public void logout(int uid){
		onlineUsers.remove(uid);
	}
	
	public boolean isOnline(int uid){
		return onlineUsers.containsKey(uid);
	}
	
	public void updateUDP(int uid, String host, int port){
		OnlineUserInfo info = onlineUsers.get(uid);
		info.setUDPHost(host);
		info.setUDPPort(port);
	}
	

}
