package jianqiang.com.plugin2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.jianqiang.mypluginlibrary.AppConstants;
import com.example.jianqiang.mypluginlibrary.activity.BasePluginActivity;
import com.example.jianqiang.mypluginlibrary.activity.LaunchMode;

public class ActivityA extends BasePluginActivity {

    private static final String TAG = "Client-TestActivity";

    public ActivityA() {
        setLunchMode(LaunchMode.SINGLETOP);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_a);

        final TextView tv = (TextView)findViewById(R.id.tv1);

        String userName = getIntent().getStringExtra("username");
        if(userName != null)
            tv.setText(userName);

        Button btn = (Button)findViewById(R.id.button1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AppConstants.PROXY_VIEW_ACTION);
                intent.putExtra(AppConstants.EXTRA_DEX_PATH, dexPath);
                intent.putExtra(AppConstants.EXTRA_CLASS, "jianqiang.com.plugin2.ActivityA");
                intent.putExtra("username", tv.getText() + "jianqiang");
                startActivity(intent);
            }
        });
    }
}