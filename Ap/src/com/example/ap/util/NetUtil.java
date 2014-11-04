package com.example.ap.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.HttpConnection;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class NetUtil {
	private static final String TAG = NetUtil.class.getSimpleName();
	public static boolean isNetOk(Context context){
		ConnectivityManager conn = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo NetInfo = conn.getActiveNetworkInfo();
		if(NetInfo == null && !NetInfo.isAvailable()){
			return false;
		}
		return true;
	}
	
	public static Bitmap getBitmapFromUrlByHttpConnection(String url){
		URL ul = null;
		HttpsURLConnection conn = null;
		try {
			ul = new URL(url);
			conn = (HttpsURLConnection) ul.openConnection();
			conn.setConnectTimeout(5 * 1000);
			conn.setReadTimeout(5 * 1000);
			if(conn.getResponseCode() == 200){
				return BitmapFactory.decodeStream(conn.getInputStream());
			}
		} catch (MalformedURLException e) {
			Log.e(TAG, "" + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e(TAG, "" + e.getMessage());
			e.printStackTrace();
		} finally{
			if(conn != null){
				conn.disconnect();
				conn = null;
			}
		}
		return null;
	}
	public static String getStringFromUrlByHttpConnection(String url){
		URL ul = null;
		HttpsURLConnection conn = null;
		BufferedReader br = null;
		try {
			ul = new URL(url);
			conn = (HttpsURLConnection) ul.openConnection();
			conn.setConnectTimeout(5 * 1000);
			conn.setReadTimeout(5 * 1000);
			if(conn.getResponseCode() == 200){
				br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String line = null;
				StringBuilder sb = new StringBuilder();
				while((line = br.readLine()) != null){
					sb.append(line).append("\n");
				}
				return sb.toString();
			}
		} catch (MalformedURLException e) {
			Log.e(TAG, "" + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e(TAG, "" + e.getMessage());
			e.printStackTrace();
		} finally{
			try {
				if(br != null){
					br.close();
					br = null;
				}
				if(conn != null){
					conn.disconnect();
					conn = null;
				}
			} catch (IOException e) {
				Log.e(TAG, "" + e.getMessage());
				e.printStackTrace();
			}
		}
		return null;
	}
	public static String getStringFromUrlByHttpClient(String url){
		HttpGet req = new HttpGet(url);
		HttpParams params = new BasicHttpParams();
		HttpConnectionParams.setSoTimeout(params, 5 * 1000);
		HttpConnectionParams.setConnectionTimeout(params, 5 * 1000);
		HttpClient client = new DefaultHttpClient(params);
		HttpResponse resp;
		try {
			resp = client.execute(req);
			if(resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				return EntityUtils.toString(resp.getEntity(), "utf-8");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e(TAG, e.getMessage()+"");
			e.printStackTrace();
		}
		return null;
	}
	public static Bitmap getBitmapFromUrlByHttpClient(String url){
		HttpGet req = new HttpGet(url);
		HttpParams params = new BasicHttpParams();
		HttpConnectionParams.setSoTimeout(params, 5 * 1000);
		HttpConnectionParams.setConnectionTimeout(params, 5 * 1000);
		HttpClient client = new DefaultHttpClient(params);
		HttpResponse resp;
		try {
			resp = client.execute(req);
			if(resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				return BitmapFactory.decodeStream(resp.getEntity().getContent());
			}else{
				Log.e(TAG, "服务器响应异常！");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e(TAG, e.getMessage()+"");
			e.printStackTrace();
		}
		return null;
	}
	
}
