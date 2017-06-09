package yyz.njupt.view.netService.loginService;



import org.apache.log4j.Logger;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import yyz.njupt.view.netService.Common;
import yyz.njupt.view.netService.Config;
import yyz.njupt.view.netService.POJO.LoginReqProto;
import yyz.njupt.view.netService.POJO.LoginRespProto;

public class ClientHandler1 extends ChannelInboundHandlerAdapter{
	
	private static final Logger logger = Logger.getLogger(ClientHandler1.class);
	
	public ClientHandler1(){
		
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx){
		logger.info("Actived 1");
		LoginReqProto.LoginReq req = Config.loginReq;
		//logger.info(req);
		ctx.writeAndFlush(req);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg){
		LoginRespProto.LoginResp resp = (LoginRespProto.LoginResp) msg;
		Config.LoginResp = resp;
		logger.debug(resp);
		//Login Request
		if(resp.getRespCode() == 0){
			// login success
			if(resp.getRespContend().equals(Common.LOGIN_AUTH_RESP)){
				logger.info("login success...");
				Config.UID = resp.getUID();
				Config.updatePersonalDataReq();
				Config.updateFriendsInfoListReq();
				ReferenceCountUtil.release(msg); 
				ctx.fireChannelActive();    //默认不会激活下一个handler
			}
			// login failed, close connection
			else{
				Config.LoginResp = null;
				logger.info("Login failed, close connection");
				ctx.close();
			}
		}
		// Calls ctx.fireChannelRead to forward to next handler in pipeline
		else { // 交给下一个Handler来处理
			ctx.fireChannelRead(msg);
		}
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
		cause.printStackTrace();
		ctx.close();
	}
}
