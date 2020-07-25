package com.monoshine.baseandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import butterknife.OnClick
import com.monoshine.baseandroid.base.BaseMVPActivity
import com.monoshine.baseandroid.demo.DemoActivity
import com.monoshine.baseandroid.model.BaseResponse
import com.monoshine.baseandroid.mvp.CommonPresenter
import com.monoshine.baseandroid.mvp.CommonView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseMVPActivity<CommonPresenter>(),CommonView.View {


    override fun initArgs(intent: Intent?) {
    }

    override fun initView() {
        setContentView(R.layout.activity_main)

    }

    override fun initData() {

    }

    @OnClick(R.id.test_tv)
    fun viewClick(view: View){
        when(view.id)
        {
            R.id.test_tv ->
            {
                start(DemoActivity::class.java)
            }
        }
    }

    override fun bindData(response: BaseResponse?) {

    }
}