package com.monoshine.baseandroid.demo

import com.monoshine.baseandroid.model.BaseResponse
import com.monoshine.baseandroid.mvp.HttpDto
import com.monoshine.baseandroid.mvp._PresenterImpl
import com.monoshine.baseandroid.util.Constant

class DemoPresenter(view:DemoContrract.View):_PresenterImpl<DemoContrract.View,BaseResponse>(view)
,DemoContrract.Presenter{

    override fun requestSuccess(response: BaseResponse, httpDto: HttpDto) {

        when(httpDto.url)
        {
            Constant.LOGIN ->
            {

                mView.bindData()
            }

        }
    }
}