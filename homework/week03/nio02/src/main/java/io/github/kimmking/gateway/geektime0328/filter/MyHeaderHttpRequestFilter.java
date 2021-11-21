package io.github.kimmking.gateway.geektime0328.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;

public class MyHeaderHttpRequestFilter implements MyHttpRequestFilter {

	@Override
	public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
		
		if(!fullRequest.uri().startsWith("/geektime")) {
			throw new RuntimeException("不支持的uri : " + fullRequest.uri());
		}
		HttpHeaders headers = fullRequest.headers();
		if(null == headers) {
			headers = new DefaultHttpHeaders();
		}
		headers.add("geektime", "gt0328");	
	}

	
}
