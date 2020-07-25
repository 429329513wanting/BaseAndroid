package com.monoshine.baseandroid.util;

import talex.zsw.basecore.util.SpTool;

public class Constant {

    public static final String DEFAULT_SERVER = "http://wisdomapp.ruguoyihou.com";// 正式

    public static String getServer()
    {
        return SpTool.getString(STR_SERVER, DEFAULT_SERVER);
    }

    public static final String STR_SERVER = "STR_SERVER";

    public static final String LOGIN = "/api/login";


}
