package com.example.ap.util;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;

public class AsyncLoadImage {

	private Context mContext;
	private Map<String, SoftReference<Bitmap>> mHashMap;
	public AsyncLoadImage(Context mCOntext) {
		super();
		this.mContext = mContext;
		mHashMap = new HashMap<String, SoftReference<Bitmap>>();
	}
	public Bitmap iamgeLoad(final String strurl, final ImageCallBack mImageCallBack){
		Bitmap bitmap = null;
		if(mHashMap.containsKey(strurl)){
			bitmap = mHashMap.get(strurl).get();
			if(bitmap != null){
				return bitmap;
			}
		}
		final Handler handler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				switch (msg.what) {
				case 1:
					mImageCallBack.onImageLoad(strurl, (Bitmap) msg.obj);
					break;
				}
			}
		};
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Bitmap bitmap = NetUtil.getBitmapFromUrlByHttpClient(strurl);
				if(bitmap != null){
					mHashMap.put(strurl, new SoftReference<Bitmap>(bitmap));
					Message msg = handler.obtainMessage(1,bitmap);
					handler.sendMessage(msg);
				}
			}
		}).start();
			
		return bitmap;
	}
	public interface ImageCallBack{
		void onImageLoad(String str, Bitmap bitmap);
	}

}
