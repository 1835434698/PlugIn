package jianqiang.com.plugin1;

import android.os.Bundle;

import com.example.jianqiang.mypluginlibrary.activity.BasePluginActivity;

public class SecondActivity extends BasePluginActivity {

    private static final String TAG = "Client-SecondActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.second);
    }
}