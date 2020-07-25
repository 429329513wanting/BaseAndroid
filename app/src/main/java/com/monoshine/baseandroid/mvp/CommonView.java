package com.monoshine.baseandroid.mvp;

import com.monoshine.baseandroid.model.BaseResponse;

/**
 * 作用: 通用View的实现
 * 作者: 赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class CommonView
{
	public interface View extends  _View
	{
		void bindData(BaseResponse response);
	}

	public interface Presenter extends  _Presenter
	{
	}
}
