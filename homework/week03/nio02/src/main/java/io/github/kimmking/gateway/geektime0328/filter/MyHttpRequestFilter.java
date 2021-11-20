package io.github.kimmking.gateway.geektime0328.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

public interface MyHttpRequestFilter {

	 void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx);
	
}
