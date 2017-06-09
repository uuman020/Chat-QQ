package yyz.njupt.view.netService;

import java.net.DatagramSocket;
import java.util.HashMap;
import java.util.List;

import yyz.njupt.view.ChatFrame;
import yyz.njupt.view.FaceJPanel;
import yyz.njupt.view.FriendsListDialog;
import yyz.njupt.view.FriendsListJPanel;
import yyz.njupt.view.LoginDialog;
import yyz.njupt.view.netService.POJO.LoginReqProto;
import yyz.njupt.view.netService.POJO.LoginRespProto;
import yyz.njupt.view.netService.POJO.RegReqProto;
import yyz.njupt.view.netService.UDPService.Msg;


/**
 * 
 * NetConfig Infomations
 * @School njupt
 * @author yyz
 *
 */
public class Config {
	
	public static final String IP = "127.0.0.1";
	public static final int LOGIN_PORT = 8063;
	public static final int REG_PORT = 8064;
	public static final int UDP_PORT = 8065;
	//Client UID
	public static int UID = -1;

	// 图形用户界面
	public static LoginDialog loginDialog = null;
	public static FriendsListDialog friendsListDialog = null;
	public static volatile FriendsListJPanel friendsListJPanel = new FriendsListJPanel();
	
	//LoginReq
	public static LoginReqProto.LoginReq loginReq = null;
	
	//Personal DataReq
	public static LoginReqProto.LoginReq personalDataReq = null; 
	
	
	//FriendsInfoListReq 
	public static LoginReqProto.LoginReq friendsInfoListReq = null;
	
	//OnlineFriendsReq
	public static LoginReqProto.LoginReq onlineFriendsReq = null; 
	
	/**
	 * resp 会被轮询
	 */
	// Login resp
    public static volatile LoginRespProto.LoginResp LoginResp = null; 
    
	//FriendsInfoListResp
	public static volatile List<LoginRespProto.FriendsInfo> friendsInfoListResp = null; 
	
	//PersonalDataResp
	public static volatile LoginRespProto.PersonalData personalDataResp = null; 
	
	//OnlineFriendsResp
	public static volatile List<Integer> onlineFriendsResp = null; 
	
	//LoginClient thread
	public static Thread thread = null;
	
	/**
	 * Build LoginReq
	 * @param userName
	 * @param passwd
	 */
	public static void setLoginReq(String userName, String passwd){
		LoginReqProto.LoginReq.Builder builder = LoginReqProto.LoginReq.newBuilder();
		
		builder.setRecCode(0);
		builder.setRecContend(Common.LOGIN_AUTH);
		builder.setUserName(userName);
		builder.setPasswd(passwd);
		
		Config.loginReq = builder.build();
	}
	
	/**
	 * Build PersonalDataReq
	 */
	public static void updatePersonalDataReq(){
		LoginReqProto.LoginReq.Builder builder = LoginReqProto.LoginReq.newBuilder();
		
		builder.setRecCode(1);
		builder.setRecContend(Common.GET_PERSONAL_DATA);
		builder.setUID(Config.UID);
		
		Config.personalDataReq = builder.build();
	}
	
	/**
	 * Build FriendsInfoListReq
	 */
	public static void updateFriendsInfoListReq(){
        LoginReqProto.LoginReq.Builder builder = LoginReqProto.LoginReq.newBuilder();
		
		builder.setRecCode(2);
		builder.setRecContend(Common.GET_FRIENDS_INFO);
		builder.setUID(Config.UID);
		
		Config.friendsInfoListReq = builder.build();
	}
	
	/**
	 * Build onlineFriendsReq
	 * Be called after update FriendsInfoListResp
	 */
	public static void updateOnlineFriendsReq(){
		LoginReqProto.LoginReq.Builder builder = LoginReqProto.LoginReq.newBuilder();
		builder.setRecCode(3);
		builder.setRecContend(Common.GET_ONLINE_UIDS);
	
		// Get friends uids from friendsInfoListResp
	    List<LoginRespProto.FriendsInfo> friendsUIDs = Config.friendsInfoListResp;
	    for(int i = 0; i < friendsUIDs.size(); i++){
	    	builder.addFriendsUIDs(friendsUIDs.get(i).getUID());
	    }
	    
	    Config.onlineFriendsReq = builder.build();
	}
	
	
	public static RegReqProto.ReqReq RegReq = null;
	
	//UDP
    public static DatagramSocket datagramSocket = null;
    
	// 聊天窗口登记
	public  static HashMap<Integer, ChatFrame> chatMap = new HashMap<>();

	// 显示聊天窗口
	public static void showChatFrame(int uid, String petname, String remark, int image, List<Msg> msgs) {

		if (!chatMap.containsKey(uid)) {
			ChatFrame chat = new ChatFrame(uid, petname, image, remark, msgs);
			chatMap.put(uid, chat);
		} else {
			chatMap.get(uid).setAlwaysOnTop(true);
			chatMap.get(uid).setVisible(true);
		}

	}

	public static void closeChatFrame(int uid) {
		chatMap.remove(uid);
	}
	
	public static HashMap<Integer, FaceJPanel> map = new HashMap<>();
}
