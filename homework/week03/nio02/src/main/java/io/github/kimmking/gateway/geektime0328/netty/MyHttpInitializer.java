package io.github.kimmking.gateway.geektime0328.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

public class MyHttpInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast(new HttpServerCodec());
		//p.addLast(new HttpServerExpectContinueHandler());
		pipeline.addLast(new HttpObjectAggregator(1024 * 1024));
		pipeline.addLast(new MyHttpHandler());
			
	}

	
}
