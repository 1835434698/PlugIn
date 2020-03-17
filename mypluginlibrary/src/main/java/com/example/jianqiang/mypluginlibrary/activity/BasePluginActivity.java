package com.example.jianqiang.mypluginlibrary.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;

public class BasePluginActivity extends Activity implements IRemoteActivity {

    private static final String TAG = "Client-BaseActivity";

    /**
     * 等同于mProxyActivity，可以当作Context来使用，会根据需要来决定是否指向this<br/>
     * 可以当作this来使用
     */
    protected Activity that;
    protected String dexPath;


    private int launchMode = LaunchMode.STANDARD;

    public void setLunchMode(int launchMode) {
        this.launchMode = launchMode;
    }

    @Override
    public int getLaunchMode() {
        return launchMode;
    }


    @Override
    public void setProxy(Activity proxyActivity, String dexPath) {
        that = proxyActivity;
        this.dexPath = dexPath;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onRestart() {
    }

    @Override
    public void onResume() {
    }

    @Override
    public void onPause() {
    }

    @Override
    public void onStop() {
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public void setContentView(int layoutResID) {
        that.setContentView(layoutResID);
    }

    @Override
    public View findViewById(int id) {
        return that.findViewById(id);
    }

    @Override
    public void startActivity(Intent intent) {
        that.startActivity(intent);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        that.startActivityForResult(intent, requestCode);
    }

    @Override
    public Resources getResources() {
        return that.getResources();
    }

    @Override
    public void finish() {
        that.finish();
    }

    @Override
    public Intent getIntent() {
        return that.getIntent();
    }
}
