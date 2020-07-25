package com.monoshine.baseandroid.model

import com.monoshine.baseandroid.mvp.HttpDto
import java.io.Serializable

class BaseResponse: Serializable {

    var code: Int = 0
    var message: String? = null
    var data: Any? = null
    var httpDto: HttpDto? = null
}