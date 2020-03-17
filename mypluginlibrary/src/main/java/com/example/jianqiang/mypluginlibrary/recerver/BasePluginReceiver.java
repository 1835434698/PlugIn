package com.example.jianqiang.mypluginlibrary.recerver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * ${CLASS} class
 *
 * @author Administrator
 * @date 2019/7/30
 */
public class BasePluginReceiver extends BroadcastReceiver implements IRemoteReceiver{

    public static final String TAG = "BasePluginReceiver";
    private BroadcastReceiver that;

    @Override
    public void setProxy(BroadcastReceiver proxyReceiver) {
        that = proxyReceiver;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

    }
}