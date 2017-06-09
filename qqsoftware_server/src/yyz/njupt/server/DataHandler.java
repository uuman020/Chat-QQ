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

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import yyz.njupt.db.DBManager;

public class DataHandler extends ChannelInboundHandlerAdapter {

	// 业务逻辑线程池(业务逻辑最好跟netty io线程分开处理，线程切换虽会带来一定的性能损耗，但可以防止业务逻辑阻塞io线程)
	private final static ExecutorService workerThreadService = newBlockingExecutorsUseCallerRun(
			Runtime.getRuntime().availableProcessors() * 2);

	public DataHandler() {

	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		
		LoginReqProto.LoginReq req = (LoginReqProto.LoginReq) msg;

		// 个人资料请求
		if (req.getRecCode() == 1) {
			workerThreadService.execute(new Runnable() {

				@Override
				public void run() {
					int uid = req.getUID();
					Connection conn = null;
					try {
						conn = DBManager.getConnection();

						LoginRespProto.LoginResp.Builder builder = LoginRespProto.LoginResp.newBuilder();
						builder.setRespCode(1);
						builder.setRespContend(Common.GET_PERSONAL_DATA_RESP);

						// 获得个人资料
						PreparedStatement pst = conn.prepareStatement(
								"SELECT petname, remark, image, ege, sex FROM userData WHERE uid = ?");
						pst.setInt(1, uid);
						ResultSet rs = pst.executeQuery();
						if (rs.next()) {

							LoginRespProto.PersonalData.Builder builder2 = LoginRespProto.PersonalData.newBuilder();
							builder2.setPetname(rs.getString("petname"));
							builder2.setRemark(rs.getString("remark"));
							builder2.setImage(rs.getInt("image"));
							builder2.setEge(rs.getInt("ege"));
							builder2.setSex(rs.getString("sex"));

							builder.setPersonalData(builder2.build());
						}
						// 写出去
						ctx.writeAndFlush(builder.build());
						ReferenceCountUtil.release(msg);
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						try {
							conn.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
			});
		}
		// 获得所有好友的资料
		else if (req.getRecCode() == 2) {

			workerThreadService.execute(new Runnable() {

				@Override
				public void run() {
					// 获得所有好友的资料
					int uid = req.getUID();
					Connection conn = null;
					try {
						conn = DBManager.getConnection();
						PreparedStatement pst = conn.prepareStatement(
								"SELECT u.uid, u.petname, u.remark, u.image  FROM userData as u INNER JOIN friends as f ON u.uid =f.fid WHERE f.uid = ?");
						pst.setInt(1, uid);
						ResultSet rs = pst.executeQuery();

						LoginRespProto.LoginResp.Builder builder = LoginRespProto.LoginResp.newBuilder();
						builder.setRespCode(2);
						builder.setRespContend(Common.GET_FRIENDS_INFO_RESP);

						while (rs.next()) {
							LoginRespProto.FriendsInfo.Builder builder3 = LoginRespProto.FriendsInfo.newBuilder();
							builder3.setUID(rs.getInt("uid"));
							builder3.setPetname(rs.getString("petname"));
							builder3.setRemark(rs.getString("remark"));
							builder3.setImage(rs.getInt("image"));

							builder.addFriendsInfo(builder3.build());
						}

						ctx.writeAndFlush(builder.build());
						ReferenceCountUtil.release(msg);
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						try {
							conn.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
			});
		}
		// Calls ctx.fireChannelRead to forward to next handler in pipeline
		else {
			ctx.fireChannelRead(msg);
		}
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
