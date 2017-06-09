package yyz.njupt.server;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import yyz.njupt.db.DBManager;
import yyz.njupt.db.OnlineDB;
import yyz.njupt.db.OnlineUserInfo;

public class LoginAuthHandler extends ChannelInboundHandlerAdapter {

	// 业务逻辑线程池(业务逻辑最好跟netty io线程分开处理，线程切换虽会带来一定的性能损耗，但可以防止业务逻辑阻塞io线程)
	private final static ExecutorService workerThreadService = newBlockingExecutorsUseCallerRun(
			Runtime.getRuntime().availableProcessors() * 2);

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		LoginReqProto.LoginReq req = (LoginReqProto.LoginReq) msg;
		
		// 登录请求，因为需要连接数据库，可能造成阻塞，所以在自己的业务线程池中处理
		if (req.getRecCode() == 0) {

			workerThreadService.execute(new Runnable() {
				
				@Override
				public void run() {
					
					//verify passwd
					Connection conn = null;
					LoginRespProto.LoginResp resp = null;
					try {
						conn = DBManager.getConnection();
						String username = req.getUserName();
						String passwd = req.getPasswd();

						PreparedStatement pst = conn.prepareStatement("SELECT uid, passwd, state FROM userData WHERE username = ?");
						pst.setString(1, username);
						ResultSet rs = pst.executeQuery();
						
						if(rs.next()){// 找到账户
							if(rs.getString("passwd").equals(passwd)){// 密码正确
								if(rs.getInt("state") == 0){//登录成功
									int uid = rs.getInt("uid");
									if(OnlineDB.getOnlineDB().isOnline(uid)){// 在线， T下去先
										Channel channel = OnlineDB.getOnlineDB().getInfo(uid).getChannel();
										OnlineDB.getOnlineDB().logout(uid);
										channel.close();
									}
	                                OnlineDB.getOnlineDB().registOnline(uid, new OnlineUserInfo(uid, ctx.channel()));
									resp = createResp(Common.LOGIN_AUTH_RESP, uid);
									ctx.writeAndFlush(resp);
									ReferenceCountUtil.release(msg);
								}
								else{//账户锁定
									resp = createResp(Common.LOGIN_USER_LOCKED, -1);
									ctx.writeAndFlush(resp);
									ctx.close();
								}
							}
							else{// 密码错误
								resp = createResp(Common.LOGIN_PASSWD_ERROR, -1);
								ctx.writeAndFlush(resp);
								ctx.close();
							}
						}
						else{ //没有找到账户
							resp = createResp(Common.LOGIN_USERNAME_NOT_FOUND, -1);
							ctx.writeAndFlush(resp);
							ctx.close();
						}
					} catch (SQLException e) { //未知错误
						resp = createResp(Common.LOGIN_UNKONW_EXCEPTION, -1);
						ctx.writeAndFlush(resp);
						ctx.close();
						e.printStackTrace();
					}
					finally{
						try {
							conn.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
			});
		}
		//不是登录请求，转到下一个handler去处理
		else{
			ctx.fireChannelRead(msg);
		}
	}
	
	private LoginRespProto.LoginResp createResp(String contend, int uid) {
		LoginRespProto.LoginResp.Builder builder = LoginRespProto.LoginResp.newBuilder();
		builder.setRespCode(0);
		builder.setUID(uid);	
		builder.setRespContend(contend);
		return builder.build();
	}
	

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}

	/**
	 * 阻塞的ExecutorService
	 * 
	 * @param size
	 * @return
	 */
	public static ExecutorService newBlockingExecutorsUseCallerRun(int size) {
		return new ThreadPoolExecutor(size, size, 0L, TimeUnit.MILLISECONDS, new SynchronousQueue<Runnable>(),
				new RejectedExecutionHandler() {
					@Override
					public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
						try {
							executor.getQueue().put(r);
						} catch (InterruptedException e) {
							throw new RuntimeException(e);
						}
					}
				});
	}
}
