package yyz.njupt.view.netService.loginService;

import org.apache.log4j.Logger;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import yyz.njupt.view.netService.Config;
import yyz.njupt.view.netService.POJO.LoginReqProto.LoginReq;
import yyz.njupt.view.netService.POJO.LoginRespProto.LoginResp;

public class ClientHandler3 extends ChannelInboundHandlerAdapter {

	private static final Logger logger = Logger.getLogger(ClientHandler3.class);
	private static volatile boolean run = false;
	private static Thread thread = null;

	public ClientHandler3() {

	}

	@SuppressWarnings("deprecation")
	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		if (ClientHandler3.thread != null) {
			if (ClientHandler3.thread.isAlive()) {
				ClientHandler3.run = false;
				ClientHandler3.thread.stop(); // 尝试杀掉线程
			}
		}
		thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (ClientHandler3.run) {
					LoginReq req = Config.onlineFriendsReq;
					ctx.writeAndFlush(req);
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		});
		ClientHandler3.run = true;
		ClientHandler3.thread.start();
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		yyz.njupt.view.netService.POJO.LoginRespProto.LoginResp resp = (LoginResp) msg;
		logger.debug(resp);
		if (resp.getRespCode() == 3) {// 更新好友列表回执
			Config.onlineFriendsResp = resp.getOnlineUIDsList();
			// 开始显示好友列表
			logger.info("Update OnlineFiends list: " + Config.onlineFriendsResp);
			Config.friendsListJPanel.updateJPanel();
			ReferenceCountUtil.release(msg);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}
}
