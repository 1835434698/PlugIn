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
public interface IRemoteReceiver {
    public void onReceive(Context context, Intent intent);

    public void setProxy(BroadcastReceiver proxyReceiver);
}