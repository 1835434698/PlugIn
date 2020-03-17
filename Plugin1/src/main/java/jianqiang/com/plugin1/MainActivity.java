package jianqiang.com.plugin1;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.jianqiang.mypluginlibrary.AppConstants;
import com.example.jianqiang.mypluginlibrary.activity.BasePluginActivity;

public class MainActivity extends BasePluginActivity {

    private static final String TAG = "Client-MainActivity";

    Button button2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //startActivity，插件内跳转
        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AppConstants.PROXY_VIEW_ACTION);
                intent.putExtra(AppConstants.EXTRA_DEX_PATH, dexPath);
                intent.putExtra(AppConstants.EXTRA_CLASS, "jianqiang.com.plugin1.SecondActivity");
                startActivity(intent);
            }
        });

        //startActivityForResult
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AppConstants.PROXY_VIEW_ACTION);
                intent.putExtra(AppConstants.EXTRA_DEX_PATH, dexPath);
                intent.putExtra(AppConstants.EXTRA_CLASS, "jianqiang.com.plugin1.TestActivity");
                startActivityForResult(intent, 0);
            }
        });

        //跳转到宿主其它Activity
        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("userName", "baojianqiang");
                ComponentName componentName = new ComponentName("jianqiang.com.hostapp", "jianqiang.com.hostapp.MainActivity");
                intent.setComponent(componentName);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0 && resultCode == 2) {
            String userName = data.getStringExtra("username");
            button2.setText(button2.getText() + userName);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}