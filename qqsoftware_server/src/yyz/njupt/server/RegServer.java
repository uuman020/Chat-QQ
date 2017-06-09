package yyz.njupt.server;

import org.apache.log4j.Logger;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

public class RegServer {
	
	private static final Logger logger = Logger.getLogger(RegServer.class);
	//端口号
	int port = 0;
	public RegServer(int port){
		this.port = port;
	}
	
	public void startServer(){
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		try {
			ServerBootstrap boot = new ServerBootstrap();
			boot.group(bossGroup, workerGroup)
			    .channel(NioServerSocketChannel.class)
			    .option(ChannelOption.SO_BACKLOG, 1024)
			    .childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel ch) throws Exception {

						ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());
						ch.pipeline().addLast(new ProtobufDecoder(RegReqProto.ReqReq.getDefaultInstance()));
						ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
						ch.pipeline().addLast(new ProtobufEncoder());
						ch.pipeline().addLast(new RegHandler());
					}
				});
			
			try {
				ChannelFuture f = boot.bind(port).sync();
				logger.info("RegServer start at port: " + port + "...");
				f.channel().closeFuture().sync();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} finally{
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
	
	public static void main(String[] args){
		int port = 8064;
		new RegServer(port).startServer();
	}
	
}