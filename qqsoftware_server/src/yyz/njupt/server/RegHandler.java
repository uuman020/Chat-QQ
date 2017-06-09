package yyz.njupt.server;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;


import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import yyz.njupt.db.DBManager;

public class RegHandler extends ChannelInboundHandlerAdapter {

	private static final Logger logger = Logger.getLogger(RegHandler.class);
	private static ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

	public RegHandler() {

	}

	private RegRespProto.RegResp createResp(int respCode, String respContend) {
		RegRespProto.RegResp.Builder builder = RegRespProto.RegResp.newBuilder();
		builder.setRespCode(respCode);
		builder.setRespContend(respContend);
		return builder.build();
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {

		RegReqProto.ReqReq req = (RegReqProto.ReqReq) msg;

		logger.info(req.toString());

		if (req.getRecCode() == 0) {// 验证码请求
			String email = req.getEmail();

			// 随机产生6位验证码
			Random r = new Random();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < 6; i++) {
				sb.append(r.nextInt(10));
			}

			try {
				HtmlEmail email1 = new HtmlEmail();
				email1.setHostName("smtp.163.com");
				email1.setCharset("UTF-8");
				email1.addTo(email);// 收件地址
				email1.setFrom("aa172985207@163.com", "502-2Admin");
				email1.setAuthentication("aa172985207@163.com", "172985207");
				email1.setSubject("Admin");
				email1.setMsg("验证码是:" + "123456");
				email1.send();
				
				map.put(email, sb.toString());
				ctx.writeAndFlush(createResp(0, Common.REG_VERIFICATION_RESP_SUC));
				ReferenceCountUtil.release(msg);
				
			} catch (Exception e) {
				ctx.writeAndFlush(createResp(0, Common.REG_VERIFICATION_RESP_ERR));
				logger.error("Sent Email Error");
				e.printStackTrace();
			}
		}

		// 注册请求,验证request中的验证码是否正确
		else {
			
			String email = req.getEmail();
			String verification = req.getVerification();
			String passwd = req.getPasswd();
			
			RegRespProto.RegResp.Builder builder = RegRespProto.RegResp.newBuilder();
			builder.setRespCode(1);
			if(map.containsKey(email)){
				String ver = map.get(email);
				if(ver.equals(verification)){//验证码正确
					Connection conn = null;
					try {
						conn = DBManager.getConnection();
						PreparedStatement pst = conn.prepareStatement("INSERT INTO userData(username, passwd) "
								+ "VALUES(?, ?)");
						pst.setString(1, email);
						pst.setString(2, passwd);
						pst.executeQuery();
						
						builder.setRespContend(Common.REG_RESP_SUC);
						ctx.writeAndFlush(builder.build());
					} catch (SQLException e) {
						builder.setRespContend(Common.REG_RESP_ERR);
						ctx.writeAndFlush(builder.build());
						e.printStackTrace();
					}
				}
				else{ //验证码错误
					builder.setRespContend(Common.REG_RESP_ERR);
					ctx.writeAndFlush(builder.build());
				}
			}
			else{ //还没取得验证码
				builder.setRespContend(Common.REG_RESP_ERR);
				ctx.writeAndFlush(builder.build());
			}
    
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		logger.error("Error Caughted");
		cause.printStackTrace();
		ctx.close();
	}
}
