package com.monoshine.baseandroid.demo

import android.os.Bundle
import com.monoshine.baseandroid.R
import com.monoshine.baseandroid.base.BaseMVPFragment
import com.monoshine.baseandroid.model.BaseModel
import com.monoshine.baseandroid.model.BaseResponse
import com.monoshine.baseandroid.mvp.CommonPresenter
import com.monoshine.baseandroid.mvp.CommonView
import com.monoshine.baseandroid.mvp.HttpDto
import com.monoshine.baseandroid.util.Constant

class DemoFragment: BaseMVPFragment<CommonView.Presenter>(),CommonView.View {

    override fun initArgs(bundle: Bundle?) {
    }

    override fun initView(bundle: Bundle?) {

        setContentView(R.layout.demo_frag_layout)
        mPresenter = CommonPresenter(this)



    }

    override fun initData() {

        val body = BaseModel()
        body.name = "15088701234"
        body.password = "123456"

        val httpDto = HttpDto(Constant.LOGIN,body)
        mPresenter.getData(httpDto.setTryAgain(true))
    }

    override fun bindData(response: BaseResponse?) {
    }
}