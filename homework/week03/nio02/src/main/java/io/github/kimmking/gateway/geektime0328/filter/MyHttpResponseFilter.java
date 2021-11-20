package io.github.kimmking.gateway.geektime0328.filter;

import io.netty.handler.codec.http.FullHttpResponse;

public interface MyHttpResponseFilter {

	void filter(FullHttpResponse response);
	
}
