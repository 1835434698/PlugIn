package com.example.jianqiang.mypluginlibrary.activity;

import android.view.ContextThemeWrapper;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;

public class BasePluginActivity extends ContextThemeWrapper implements IRemoteActivity {

    private static final String TAG = "Client-BaseActivity";

    /**
     * 等同于mProxyActivity，可以当作Context来使用，会根据需要来决定是否指向this<br/>
     * 可以当作this来使用
     */
    protected Activity that;
    protected String dexPath;

    public String getPackageName(){
        Log.d(TAG, "111111 getPackageName  = "+that.getPackageName());
        return that.getPackageName();
    }

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

//    @Override
    public void startActivity(Intent data) {
        Log.d(TAG, "path startActivity ");
        Intent intent = replaceIntent(data);
        that.startActivity(intent);
    }

//    @Override
    public void startActivityForResult(Intent data, int requestCode) {
        Intent intent = replaceIntent(data);
        that.startActivityForResult(intent, requestCode);
    }

    private Intent replaceIntent(Intent data) {
        Log.d(TAG, "111");

        if (!TextUtils.isEmpty(data.getAction())){
            if (TextUtils.isEmpty(data.getStringExtra(AppConstants.EXTRA_DEX_PATH))) {
                data.putExtra(AppConstants.EXTRA_DEX_PATH, dexPath);
            }
            return data;
        }else {
            if (!TextUtils.isEmpty(data.getComponent().getClassName()) && data.getComponent().getClassName().contains(data.getComponent().getPackageName())){
                return data;
            }else {
                Intent intent = new Intent(AppConstants.PROXY_VIEW_ACTION);
                intent.putExtra(AppConstants.EXTRA_DEX_PATH, dexPath);
                Log.d(TAG, "path getClassName = " + data.getComponent().getClassName());
                intent.putExtras(data);
                intent.putExtra(AppConstants.EXTRA_CLASS, data.getComponent().getClassName());
                String userName = intent.getStringExtra("userName");
                Log.d(TAG, " userName = "+userName);
                return intent;
            }

        }
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
    
    public void onBackPressed() {
        that.onBackPressed();
    }

    public final void setResult(int resultCode, Intent data) {
        that.setResult(resultCode, data);
    }
}
