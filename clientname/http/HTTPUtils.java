package clientname.http;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.HttpClientBuilder;

public class HTTPUtils {

	public static HTTPReply sendGet(String endpoint) {
		return sendGet(endpoint, null);
	}
	
	public static HTTPReply sendGet(String endpoint, List<NameValuePair> params) {
		try {
			if(params != null) {
				endpoint += "?" + URLEncodedUtils.format(params, "UTF-8");
			}
			HttpGet httpGet = new HttpGet(endpoint);
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpResponse httpResponce = httpClient.execute(httpGet);
			return new HTTPReply(httpResponce);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new HTTPReply(null);
		}
	}
	
	public static HTTPReply sendPost(String endpoint) {
		return sendPost(endpoint, null);
	}
	
	public static HTTPReply sendPost(String endpoint, List<NameValuePair> params) {
		try {
			
			HttpPost httpPost = new HttpPost(endpoint);
			
			if(params != null) {
				httpPost.setEntity(new UrlEncodedFormEntity(params));
			}
			
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpResponse httpResponce = httpClient.execute(httpPost);
			return new HTTPReply(httpResponce);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new HTTPReply(null);
		}
	}
	
	public static void sendPostAsync(String endpoint, List<NameValuePair> params) {
		new Thread() {
			@Override
			public void run() {
				sendPost(endpoint, params);
			}
		}.start();
	}
	
	public static boolean downloadFile(String endpoint, File path) {
		try {
			InputStream inputStream = new URL(endpoint).openStream();
			FileOutputStream fileOutputStream = new FileOutputStream(path);
			IOUtils.copy(inputStream, fileOutputStream);
			IOUtils.closeQuietly(fileOutputStream);
			IOUtils.closeQuietly(inputStream);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
