package yyz.njupt.server;

import java.util.List;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import yyz.njupt.db.OnlineDB;

public class OnlineHandler extends ChannelInboundHandlerAdapter{

	public OnlineHandler(){
		
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg){
		LoginReqProto.LoginReq req = (LoginReqProto.LoginReq) msg;
		if(req.getRecCode() == 3){
			
			LoginRespProto.LoginResp.Builder builder = LoginRespProto.LoginResp.newBuilder();
			builder.setRespCode(3);
			builder.setRespContend(Common.GET_ONLINE_UIDS_RESP);
			
			List<Integer> friendsUIDs = req.getFriendsUIDsList();
			for(int uid : friendsUIDs){
				if(OnlineDB.getOnlineDB().isOnline(uid)){
					builder.addOnlineUIDs(uid);
				}
			}
			
			LoginRespProto.LoginResp resp = builder.build();
			ctx.writeAndFlush(resp);
			ReferenceCountUtil.release(msg);
		}
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
		cause.printStackTrace();
		ctx.close();
	}
}
