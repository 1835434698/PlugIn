package jianqiang.com.plugin2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jianqiang.mypluginlibrary.AppConstants;
import com.example.jianqiang.mypluginlibrary.activity.BasePluginActivity;
import com.example.jianqiang.mypluginlibrary.MyPlugins;

public class MainActivity extends BasePluginActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //startActivity，插件外跳转
        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String plugin1DexPath = MyPlugins.plugins.get("plugin1.apk");

                Intent intent = new Intent(AppConstants.PROXY_VIEW_ACTION);
                intent.putExtra(AppConstants.EXTRA_DEX_PATH, plugin1DexPath);
                intent.putExtra(AppConstants.EXTRA_CLASS, "jianqiang.com.plugin1.SecondActivity");
                startActivity(intent);
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AppConstants.PROXY_VIEW_ACTION);
                intent.putExtra(AppConstants.EXTRA_DEX_PATH, dexPath);
                intent.putExtra(AppConstants.EXTRA_CLASS, "jianqiang.com.plugin2.ActivityA");
                intent.putExtra("username", "baobao");
                startActivity(intent);
            }
        });
    }
}