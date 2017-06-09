package yyz.njupt.view.netService.loginService;


import org.apache.log4j.Logger;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import yyz.njupt.view.netService.Config;
import yyz.njupt.view.netService.POJO.LoginReqProto;
import yyz.njupt.view.netService.POJO.LoginRespProto;

public class ClientHandler2 extends ChannelInboundHandlerAdapter {

	private static final Logger logger = Logger.getLogger(ClientHandler2.class);
	
	public ClientHandler2() {

	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		logger.info("Actived 2");
		LoginReqProto.LoginReq req = Config.personalDataReq;
		LoginReqProto.LoginReq req2 = Config.friendsInfoListReq;
		ctx.writeAndFlush(req);
		ctx.writeAndFlush(req2);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		
		LoginRespProto.LoginResp resp = (LoginRespProto.LoginResp) msg;
		logger.debug(resp);
		if (resp.getRespCode() == 1) {// 个人资料回执
			Config.personalDataResp = resp.getPersonalData();
			ReferenceCountUtil.release(msg);
		} else if (resp.getRespCode() == 2) {// 好友资料回执
			Config.friendsInfoListResp = resp.getFriendsInfoList();
			Config.updateOnlineFriendsReq();
			ReferenceCountUtil.release(msg);
			ctx.fireChannelActive(); // 激活下一个handler Active， 获取好友的在线列表
		}
		else{ //交给下一个handler处理
			ctx.fireChannelRead(msg);
		}
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
		cause.printStackTrace();
		ctx.close();
	}
}
