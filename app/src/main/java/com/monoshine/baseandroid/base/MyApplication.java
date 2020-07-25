package com.monoshine.baseandroid.base;

import android.app.ActivityManager;

import android.content.Context;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.SPCookieStore;
import com.lzy.okgo.https.HttpsUtils;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.monoshine.baseandroid.BuildConfig;
import com.monoshine.baseandroid.MainActivity;
import com.squareup.leakcanary.LeakCanary;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;
import talex.zsw.basecore.util.Tool;

/**
 * 作用：使用的Application
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class MyApplication extends BaseApplication
{
	@Override
    public void onCreate()
	{
		super.onCreate();
		Tool.init(this, BuildConfig.DEBUG);
		Tool.initCaoc(0, MainActivity.class);
		initHttp();
		if(LeakCanary.isInAnalyzerProcess(this))
		{
			return;
		}
		LeakCanary.install(this);
	}

	@Override
    protected void attachBaseContext(Context base)
	{
		super.attachBaseContext(base);
		// 安装tinker
		//		Beta.installTinker();
		//		Beta.canNotifyUserRestart = false;
		//		Beta.betaPatchListener = new BetaPatchListener()
		//		{
		//			@Override public void onPatchReceived(String patchFile)
		//			{
		//				Log.d("Tinker", "补丁下载地址");
		//			}
		//
		//			@Override public void onDownloadReceived(long savedLength, long totalLength)
		//			{
		//				Log.d("Tinker", String.format(Locale.getDefault(), "%s %d%%", Beta.strNotificationDownloading, (int) (
		//					totalLength == 0 ? 0 : savedLength*100/totalLength)));
		//			}
		//
		//			@Override public void onDownloadSuccess(String msg)
		//			{
		//				Log.d("Tinker", "补丁下载成功");
		//			}
		//
		//			@Override public void onDownloadFailure(String msg)
		//			{
		//				Log.d("Tinker", "补丁下载失败");
		//			}
		//
		//			@Override public void onApplySuccess(String msg)
		//			{
		//				Log.d("Tinker", "补丁应用成功");
		//			}
		//
		//			@Override public void onApplyFailure(String msg)
		//			{
		//				Log.d("Tinker", "补丁应用失败");
		//			}
		//
		//			@Override public void onPatchRollback()
		//			{
		//				Log.d("Tinker", "补丁回滚");
		//			}
		//		};
	}

	@Override
    public void exit()
	{
		OkGo.getInstance().cancelAll();
		super.exit();
	}


	private boolean shouldInit()
	{
		ActivityManager am = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
		List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
		String mainProcessName = getPackageName();
		int myPid = android.os.Process.myPid();
		for(ActivityManager.RunningAppProcessInfo info : processInfos)
		{
			if(info.pid == myPid && mainProcessName.equals(info.processName))
			{
				return true;
			}
		}
		return false;
	}

	// --------------- 调试网络 ---------------

	private void initHttp()
	{
		HttpHeaders headers = new HttpHeaders();
		headers.put("Connection", "close");
		HttpParams params = new HttpParams();

		OkHttpClient.Builder builder = new OkHttpClient.Builder();

		//全局的读取超时时间
		builder.readTimeout(10*1000, TimeUnit.MILLISECONDS);
		//全局的写入超时时间
		builder.writeTimeout(10*1000, TimeUnit.MILLISECONDS);
		//全局的连接超时时间
		builder.connectTimeout(10*1000, TimeUnit.MILLISECONDS);

		//使用sp保持cookie，如果cookie不过期，则一直有效
		builder.cookieJar(new CookieJarImpl(new SPCookieStore(this)));
		builder.retryOnConnectionFailure(false);

		//信任所有证书,不安全有风险
		HttpsUtils.SSLParams sslParams1 = HttpsUtils.getSslSocketFactory();
		builder.sslSocketFactory(sslParams1.sSLSocketFactory, sslParams1.trustManager);
		builder.hostnameVerifier(new SafeHostnameVerifier());

		OkGo.getInstance().init(this)                       //必须调用初始化
		    .setOkHttpClient(builder.build())               //必须设置OkHttpClient
		    .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
		    .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
		    .setRetryCount(0)                               //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
		    .addCommonHeaders(headers)                      //全局公共头
		    .addCommonParams(params);                       //全局公共参数
		OkGo.getInstance().cancelAll();
	}

	private class SafeHostnameVerifier implements HostnameVerifier
	{
		@Override
        public boolean verify(String hostname, SSLSession session)
		{
			//			return hostname.equals("sendinfo.aibee.cn");
			return true;
		}
	}

}
