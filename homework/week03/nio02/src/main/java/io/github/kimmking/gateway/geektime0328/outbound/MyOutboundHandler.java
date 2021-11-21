package io.github.kimmking.gateway.geektime0328.outbound;

import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import io.github.kimmking.gateway.geektime0328.filter.MyHeaderHttpResponseFilter;
import io.github.kimmking.gateway.geektime0328.filter.MyHttpRequestFilter;
import io.github.kimmking.gateway.geektime0328.filter.MyHttpResponseFilter;
import io.github.kimmking.gateway.outbound.httpclient4.NamedThreadFactory;
import io.github.kimmking.gateway.router.HttpEndpointRouter;
import io.github.kimmking.gateway.router.RandomHttpEndpointRouter;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;

public class MyOutboundHandler {

	private CloseableHttpAsyncClient httpclient;
    private ExecutorService proxyService;
    private List<String> backendUrls;
    private String url;

    MyHttpResponseFilter filter = new MyHeaderHttpResponseFilter();
//    HttpEndpointRouter router = new RandomHttpEndpointRouter();
    
    public MyOutboundHandler(String url) {
    	this.url = formatUrl(url);
    	configHttpClient();
    }
    

    public MyOutboundHandler(List<String> backends) {
        this.backendUrls = backends.stream().map(this::formatUrl).collect(Collectors.toList());
        configHttpClient();
    }
    
    private void configHttpClient() {
    	System.out.println("configHttpClient");
    	 int cores = Runtime.getRuntime().availableProcessors();
         long keepAliveTime = 1000;
         int queueSize = 2048;
         RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();//.DiscardPolicy();
         proxyService = new ThreadPoolExecutor(cores, cores,
                 keepAliveTime, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(queueSize),
                 new NamedThreadFactory("proxyService"), handler);
         
         IOReactorConfig ioConfig = IOReactorConfig.custom()
                 .setConnectTimeout(1000)
                 .setSoTimeout(1000)
                 .setIoThreadCount(cores)
                 .setRcvBufSize(32 * 1024)
                 .build();
         
         httpclient = HttpAsyncClients.custom().setMaxConnTotal(40)
                 .setMaxConnPerRoute(8)
                 .setDefaultIOReactorConfig(ioConfig)
                 .setKeepAliveStrategy((response,context) -> 60)
                 .build();
         httpclient.start();
	}
    
    

    private String formatUrl(String backend) {
        return backend.endsWith("/")?backend.substring(0,backend.length()-1):backend;
    }
    
    public void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, MyHttpRequestFilter filter) {
//        String backendUrl = router.route(this.backendUrls);
//        final String url = backendUrl + fullRequest.uri();
    	try {
    		 filter.filter(fullRequest, ctx);
		} catch (RuntimeException e) {
			proxyService.submit(()->fetchGet(fullRequest, ctx, this.url, false));
			exceptionCaught(ctx, e);
		} catch (Exception e) {
			exceptionCaught(ctx, e);
		} 
       
        for (Entry<String, String> header : fullRequest.headers()) {
            System.out.println(header.toString());
        } 
        
        proxyService.submit(()->fetchGet(fullRequest, ctx, this.url, true));
    }
    
    private void fetchGet(final FullHttpRequest inbound, final ChannelHandlerContext ctx, final String url, Boolean filterBoolean) {
        final HttpGet httpGet = new HttpGet(url);
        //httpGet.setHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE);
        httpGet.setHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_KEEP_ALIVE);
        httpGet.setHeader("geektime", inbound.headers().get("geektime"));

        httpclient.execute(httpGet, new FutureCallback<HttpResponse>() {
            @Override
            public void completed(final HttpResponse endpointResponse) {
                try {
                    handleResponse(inbound, ctx, endpointResponse, filterBoolean);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    
                }
            }
            
            @Override
            public void failed(final Exception ex) {
            	System.out.println("failed");
                httpGet.abort();
                ex.printStackTrace();
            }
            
            @Override
            public void cancelled() {
            	System.out.println("cancelled");
                httpGet.abort();
            }
        });
    }
    
    private void handleResponse(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, final HttpResponse endpointResponse, Boolean filterbBoolean) throws Exception {
    	System.out.println("handleResponse");
    	FullHttpResponse response = null;
        byte[] body = null;
        try {
        	if (filterbBoolean) {
        		body = EntityUtils.toByteArray(endpointResponse.getEntity());
			} else {
				body = "不支持的uri".getBytes();
			}
                
            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(body));
            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", Integer.parseInt(endpointResponse.getFirstHeader("Content-Length").getValue()));

            filter.filter(response);

            for (Header header : endpointResponse.getAllHeaders()) {
                response.headers().set(header.getName(),header.getValue());
                System.out.println(header.getName() + " => " + header.getValue());
            } 
        
        } catch (Exception e) {
            e.printStackTrace();
            response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
            exceptionCaught(ctx, e);
        } finally {
            if (fullRequest != null) {
                if (!HttpUtil.isKeepAlive(fullRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    //response.headers().set(CONNECTION, KEEP_ALIVE);
                    ctx.write(response);
                }
            }
            ctx.flush();
        }
        
    }
    
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
    
    
	
	
}
