package com.monoshine.baseandroid.base;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import talex.zsw.basecore.util.Tool;

/**
 * 作用：基本的Application,项目的Application继承自该类,调用setImg(int res)方法来设置基本图片
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
@SuppressLint("Registered")
public class BaseApplication extends MultiDexApplication
{
	private static Context mApplicationContext;

	@Override
    public void onCreate()
	{
		mApplicationContext = this;
		EventBus.getDefault().register(mApplicationContext);
		super.onCreate();
	}

	public EventBus getBus()
	{
		return EventBus.getDefault();
	}

	@Override
    protected void attachBaseContext(Context base)
	{
		super.attachBaseContext(base);
		MultiDex.install(this);
	}

	public void exit()
	{
		Tool.uninstall();
		EventBus.getDefault().unregister(mApplicationContext);
		android.os.Process.killProcess(android.os.Process.myPid());
	}

	@Subscribe
	public void onEvent(String event){}

}
