package io.github.kimmking.gateway.geektime0328.httpclient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


public class MyHttpClient {
	
	private static CloseableHttpClient httpclient = HttpClients.createDefault();
	
	/**
	 * 
	 * @Title: get
	 * @Description: TODO
	 * @param url
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException 
	 * @throws
	 */
	public static String get(String url) throws ClientProtocolException, IOException {
		
		if (null == url) {
			return null;
		}
		
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = null;
		
		try{
			response = httpclient.execute(httpGet);
			HttpEntity entity = (HttpEntity) response.getEntity();
			return EntityUtils.toString(entity, "UTF-8");
		} finally {
			if (null != response) {
				response.close();
			}
		}
	}
	
	/**
	 * 
	 * @Title: post
	 * @Description: TODO
	 * @param url
	 * @param map
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException 
	 * @throws
	 */
	public static String post(String url, Map<String, String> map) throws ClientProtocolException, IOException {
		
		if (null== url || null == map) {
			return null;
		}
		
		HttpPost httpPost = new HttpPost(url);
		
		List<NameValuePair> list = new ArrayList<>();
		for (String key : map.keySet()) {
			list.add(new BasicNameValuePair(key, map.get(key)));
		}
		httpPost.setEntity(new UrlEncodedFormEntity(list));
		CloseableHttpResponse response = null;
		try {
			response = httpclient.execute(httpPost);
			HttpEntity entity = (HttpEntity) response.getEntity();
			return EntityUtils.toString(entity, "UTF-8");
		} finally {
			if (null != response) {
				response.close();
			}
		}
	}

}
