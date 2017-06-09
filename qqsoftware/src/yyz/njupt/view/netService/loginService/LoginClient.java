package yyz.njupt.view.netService.loginService;

import org.apache.log4j.Logger;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import yyz.njupt.view.netService.POJO.LoginRespProto;

public class LoginClient {
	
	private static final Logger logger = Logger.getLogger(LoginClient.class);
	
	public void connect(String host, int port){
		EventLoopGroup group = new NioEventLoopGroup();
		
		try {
			Bootstrap boot = new Bootstrap();
			boot.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
					.handler(new ChannelInitializer<SocketChannel>() {
						public void initChannel(SocketChannel ch) {
							ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());
							ch.pipeline().addLast(new ProtobufDecoder(LoginRespProto.LoginResp.getDefaultInstance()));
							ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
							ch.pipeline().addLast(new ProtobufEncoder());
							ch.pipeline().addLast(new ClientHandler1());
							ch.pipeline().addLast(new ClientHandler2());
							ch.pipeline().addLast(new ClientHandler3());
						}
					});
			try {
				ChannelFuture f = boot.connect(host, port).sync();
				logger.info("Connect Successful!");
				f.channel().closeFuture().sync();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} finally {
			group.shutdownGracefully();
		}
	}
	
	public static void main(String[] args){
		int port = 8063;
		new LoginClient().connect("127.0.0.1", port);
	}

}
;