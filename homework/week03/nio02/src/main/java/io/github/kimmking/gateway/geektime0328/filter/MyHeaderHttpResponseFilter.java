package io.github.kimmking.gateway.geektime0328.filter;

import io.netty.handler.codec.http.FullHttpResponse;

public class MyHeaderHttpResponseFilter implements MyHttpResponseFilter {

	@Override
	public void filter(FullHttpResponse response) {
		response.headers().set("geektime", "gt0328");	
	}

}
