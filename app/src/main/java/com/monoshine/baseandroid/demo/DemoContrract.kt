package com.monoshine.baseandroid.demo

import com.monoshine.baseandroid.mvp._Presenter
import com.monoshine.baseandroid.mvp._View

interface DemoContrract {

    interface View: _View{

        fun bindData()
    }

    interface Presenter: _Presenter{

    }

}