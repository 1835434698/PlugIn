package jianqiang.com.hostapp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.jianqiang.mypluginlibrary.AppConstants;
import com.example.jianqiang.mypluginlibrary.recerver.MyClassLoaders;

import java.io.File;

import dalvik.system.DexClassLoader;
import jianqiang.com.hostapp.DLUtils;
import jianqiang.com.hostapp.R;
import jianqiang.com.hostapp.Utils;
import jianqiang.com.hostapp.bean.PluginItem;


/**
 * ${CLASS} class
 *
 * @author Administrator * @date 2019/7/30
 */
public class ReceiverActivity  extends Activity {
    private static final String TAG = "ReceiverActivity";

    PluginItem pluginItem1;
    PluginItem pluginItem2;
    private static final String ACTION = "baobao2";

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        Utils.extractAssets(newBase, "plugin1.apk");
        Utils.extractAssets(newBase, "plugin2.apk");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reciver);

        pluginItem1 = generatePluginItem("plugin1.apk");
        pluginItem2 = generatePluginItem("plugin2.apk");
    }

    public void notifyReceiver1(View view) {
        Intent intent = new Intent(ACTION);
        intent.putExtra(AppConstants.EXTRA_PLUGIN_NAME, "plugin1.apk");
        intent.putExtra(AppConstants.EXTRA_CLASS, "jianqiang.com.plugin1.TestReceiver1");
        sendBroadcast(intent);
    }

    public void notifyReceiver2(View view) {
        Intent intent = new Intent(ACTION);
        intent.putExtra(AppConstants.EXTRA_PLUGIN_NAME, "plugin2.apk");
        intent.putExtra(AppConstants.EXTRA_CLASS, "jianqiang.com.plugin2.TestReceiver2");
        sendBroadcast(intent);
    }

    //    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    private PluginItem generatePluginItem(String apkName) {
        File file = getFileStreamPath(apkName);
        PluginItem item = new PluginItem();
        item.pluginPath = file.getAbsolutePath();
        item.packageInfo = DLUtils.getPackageInfo(this, item.pluginPath);

        String mDexPath = item.pluginPath;

        File dexOutputDir = this.getDir("dex", Context.MODE_PRIVATE);
        final String dexOutputPath = dexOutputDir.getAbsolutePath();
        DexClassLoader dexClassLoader = new DexClassLoader(mDexPath,
                dexOutputPath, null, getClassLoader());

        MyClassLoaders.classLoaders.put(apkName, dexClassLoader);

        return item;
    }

}
