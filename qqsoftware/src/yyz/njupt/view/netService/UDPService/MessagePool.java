package yyz.njupt.view.netService.UDPService;


import net.sf.json.JSONObject;
import yyz.njupt.view.ChatFrame;
import yyz.njupt.view.FaceJPanel;
import yyz.njupt.view.netService.Config;

/**
 * 消息池 会把所有的消息接收到池里进行存储
 * 
 * @author 凯哥
 *
 */
public class MessagePool {

	private MessagePool() {
	}

	private static MessagePool messagePool = new MessagePool();

	public static MessagePool getMessagePool() {
		return messagePool;
	}

	// 不管是给谁消息 都应该在池里存储起来
	public void addMessage(JSONObject json) {
		
		JSONObject jsonObject = JSONObject.fromObject(json);
		int toUID = jsonObject.getInt("toUID");
		int fromUID = jsonObject.getInt("fromUID");
		String msg = jsonObject.getString("recContend");
        int recCode = jsonObject.getInt("recCode");
		String code = jsonObject.getString("code");

		// 把接收好的消息 包装在Msg对象内
		Msg msgObj = new Msg();
		msgObj.setCode(code);
		msgObj.setRecContend(msg);
		msgObj.setFromUID(fromUID);
		msgObj.setToUID(toUID);
		msgObj.setRecCode(recCode);

		try {
			ChatFrame chatFrame = Config.chatMap.get(fromUID);
			if (chatFrame.isVisible()) {
				chatFrame.addMessage(msgObj);
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			FaceJPanel faceJPanel = Config.map.get(fromUID);
			faceJPanel.addMessage(msgObj);
		}
	}

}
