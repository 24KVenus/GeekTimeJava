package io.github.kimmking.gateway.geektime0328.okhttp;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MyOkHttp {

	private static OkHttpClient okhc = new OkHttpClient();
	private static final MediaType jsonType = MediaType.get("application/json; charset=utf-8");
	
	/**
	 * 
	 * @Title: get
	 * @Description: TODO
	 * @param url
	 * @return
	 * @throws IOException 
	 * @throws
	 */
	public static String get(String url) throws IOException {
		
		if (url == null) {
			return null;
		}
		
		Request request = new Request.Builder()
				.url(url)
				.build();
		try (Response response = okhc.newCall(request).execute()) {
			return response.body().string();
		} 
	}
	
	/**
	 * 
	 * @Title: post
	 * @Description: TODO
	 * @param url
	 * @param json
	 * @return
	 * @throws IOException 
	 * @throws
	 */
	public static String post(String url, String json) throws IOException {
		
		if (url == null || json == null) {
			return null;
		}
		
		RequestBody body = RequestBody.create(jsonType, json);
		
		Request request = new Request.Builder()
				.url(url)
				.post(body)
				.build();
		
		try (Response response = okhc.newCall(request).execute()) {
			return response.body().string();
		}
	}
	
}

