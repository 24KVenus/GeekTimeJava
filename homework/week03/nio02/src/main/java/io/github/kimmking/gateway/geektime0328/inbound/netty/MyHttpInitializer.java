package io.github.kimmking.gateway.geektime0328.inbound.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

public class MyHttpInitializer extends ChannelInitializer<SocketChannel> {
	
	private String url;
	
	public MyHttpInitializer(String url) {
		this.url = url;
	}
	

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		System.out.println("initChannel");
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast(new HttpServerCodec());
		//p.addLast(new HttpServerExpectContinueHandler());
		pipeline.addLast(new HttpObjectAggregator(1024 * 1024));
		pipeline.addLast(new MyHttpHandler(this.url));
			
	}

	
}
