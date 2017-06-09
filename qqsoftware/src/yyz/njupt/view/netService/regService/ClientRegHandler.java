package yyz.njupt.view.netService.regService;

import org.apache.log4j.Logger;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import yyz.njupt.view.netService.Config;
import yyz.njupt.view.netService.POJO.RegReqProto;
import yyz.njupt.view.netService.POJO.RegRespProto;

public class ClientRegHandler extends ChannelInboundHandlerAdapter{
	
	private static Logger logger = Logger.getLogger(ClientRegHandler.class);
	
	public ClientRegHandler(){
		
	}
	
	
	@Override
	public void channelActive(ChannelHandlerContext ctx){
		RegReqProto.ReqReq req = Config.RegReq;
		logger.info("Channel Actived: " + req.toString());
		ctx.writeAndFlush(req);
	}
	
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg){
		RegRespProto.RegResp resp = (RegRespProto.RegResp) msg;
		
		if(resp.getRespCode() == 0){ // 验证码请求回执
			ReferenceCountUtil.release(msg);
			ctx.close();
		}
		else{ // 注册请求回执
			//处理登录成功的逻辑
		}
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
		logger.error("Register Error");
		cause.printStackTrace();
		ctx.close();
	}

}
