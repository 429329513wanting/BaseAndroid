package com.monoshine.baseandroid.demo

import android.content.Intent
import com.monoshine.baseandroid.R
import com.monoshine.baseandroid.base.BaseMVPActivity
import com.monoshine.baseandroid.model.BaseModel
import com.monoshine.baseandroid.mvp.HttpDto
import com.monoshine.baseandroid.util.Constant


class DemoActivity : BaseMVPActivity<DemoContrract.Presenter>(),DemoContrract.View {

    override fun initArgs(intent: Intent?) {
    }

    override fun initView() {
        setContentView(R.layout.activity_demo)
        mPresenter = DemoPresenter(this)

        val body = BaseModel()
        body.name = "15088701234"
        body.password = "123456"

        val httpDto = HttpDto(Constant.LOGIN,body)
        mPresenter.getData(httpDto.setTryAgain(true))

    }

    override fun initData() {
    }

    override fun bindData() {
    }
}