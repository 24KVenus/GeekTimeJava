package io.github.kimmking.gateway.geektime0328.inbound.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.kimmking.gateway.geektime0328.filter.MyHeaderHttpRequestFilter;
import io.github.kimmking.gateway.geektime0328.filter.MyHttpRequestFilter;
import io.github.kimmking.gateway.geektime0328.outbound.MyOutboundHandler;


public class MyHttpHandler extends ChannelInboundHandlerAdapter {
	private static Logger logger = LoggerFactory.getLogger(MyHttpHandler.class);
	private MyHttpRequestFilter filter = new MyHeaderHttpRequestFilter();
	private MyOutboundHandler handler;
	private String url;

	public MyHttpHandler(String url) {
		this.url = url;
		System.out.println("MyHttpHandler");
		handler = new MyOutboundHandler(url);
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
		System.out.println("channelActive " + ctx.name());	
	}
	
	@Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
		System.out.println("channelReadComplete " + ctx.name());
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
    	System.out.println("channelRead " + ctx.name());
        try {
//            logger.info("channelRead流量接口请求开始，时间为{}", startTime);
            System.out.println("channelRead");
            FullHttpRequest fullRequest = (FullHttpRequest) msg;
            String uri = fullRequest.uri();
            logger.info("接收到的请求url为{}", uri);
            handler.handle(fullRequest, ctx, filter);
    
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }
}
