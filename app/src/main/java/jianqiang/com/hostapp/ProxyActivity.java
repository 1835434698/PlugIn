package jianqiang.com.hostapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.jianqiang.mypluginlibrary.AppConstants;
import com.example.jianqiang.mypluginlibrary.activity.CJBackStack;
import com.example.jianqiang.mypluginlibrary.activity.IRemoteActivity;

import java.lang.reflect.Constructor;

public class ProxyActivity extends BaseHostActivity {

    private static final String TAG = "ProxyActivity";

    private String mClass;

    private IRemoteActivity mRemoteActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDexPath = getIntent().getStringExtra(AppConstants.EXTRA_DEX_PATH);
        mClass = getIntent().getStringExtra(AppConstants.EXTRA_CLASS);

        loadClassLoader();
        loadResources();

        launchTargetActivity(mClass);
    }

    void launchTargetActivity(final String className) {
        try {
            //反射出插件的Activity对象
            Class<?> localClass = dexClassLoader.loadClass(className);
            Constructor<?> localConstructor = localClass.getConstructor(new Class[] {});
            Object instance = localConstructor.newInstance(new Object[] {});

            mRemoteActivity = (IRemoteActivity) instance;
            CJBackStack.getInstance().launch(mRemoteActivity);

            //执行插件Activity的setProxy方法，建立双向引用
            mRemoteActivity.setProxy(this, mDexPath);

            Bundle bundle = new Bundle();
            mRemoteActivity.onCreate(bundle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult resultCode=" + resultCode);
        mRemoteActivity.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mRemoteActivity.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mRemoteActivity.onRestart();
    }
    @Override
    protected void onResume() {
        super.onResume();
        mRemoteActivity.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mRemoteActivity.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mRemoteActivity.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRemoteActivity.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
