package jianqiang.com.hostapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jianqiang.mypluginlibrary.AppConstants;
import com.example.jianqiang.mypluginlibrary.MyPlugins;

import java.io.File;
import java.util.ArrayList;

import jianqiang.com.hostapp.activity.ReceiverActivity;
import jianqiang.com.hostapp.activity.ServiceActivity;
import jianqiang.com.hostapp.bean.PluginItem;

public class MainActivity extends Activity implements OnItemClickListener {

    private ArrayList<PluginItem> mPluginItems = new ArrayList<PluginItem>();
    private PluginAdapter mPluginAdapter;

    private ListView mListView;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        Utils.extractAssets(newBase, "plugin1.apk");
        Utils.extractAssets(newBase, "plugin2.apk");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        mPluginAdapter = new PluginAdapter();
        mListView = findViewById(R.id.plugin_list);
        mListView.setAdapter(mPluginAdapter);
        mListView.setOnItemClickListener(this);
    }

    private void initData() {
        File file1 = getFileStreamPath("plugin1.apk");
        File file2 = getFileStreamPath("plugin2.apk");
        File[] plugins = {file1, file2};

        for (File plugin : plugins) {

            PluginItem item = new PluginItem();
            item.pluginPath = plugin.getAbsolutePath();
            item.packageInfo = DLUtils.getPackageInfo(this, item.pluginPath);
            mPluginItems.add(item);

            MyPlugins.plugins.put(plugin.getName(), item.pluginPath);
        }

        mPluginAdapter.notifyDataSetChanged();
    }

    public void receiver(View view) {
        startActivity(new Intent(this, ReceiverActivity.class));
    }

    public void service(View view) {
        startActivity(new Intent(this, ServiceActivity.class));
    }

    private class PluginAdapter extends BaseAdapter {

        private LayoutInflater mInflater;

        public PluginAdapter() {
            mInflater = MainActivity.this.getLayoutInflater();
        }

        @Override
        public int getCount() {
            return mPluginItems.size();
        }

        @Override
        public Object getItem(int position) {
            return mPluginItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.plugin_item, parent, false);
                holder = new ViewHolder();
                holder.appIcon = (ImageView) convertView.findViewById(R.id.app_icon);
                holder.appName = (TextView) convertView.findViewById(R.id.app_name);
                holder.apkName = (TextView) convertView.findViewById(R.id.apk_name);
                holder.packageName = (TextView) convertView.findViewById(R.id.package_name);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            PluginItem item = mPluginItems.get(position);
            PackageInfo packageInfo = item.packageInfo;
            holder.appIcon.setImageDrawable(DLUtils.getAppIcon(MainActivity.this, item.pluginPath));
            holder.appName.setText(DLUtils.getAppLabel(MainActivity.this, item.pluginPath));
            holder.apkName.setText(item.pluginPath.substring(item.pluginPath.lastIndexOf(File.separatorChar) + 1));
            holder.packageName.setText(packageInfo.applicationInfo.packageName);
            return convertView;
        }
    }

    private static class ViewHolder {
        public ImageView appIcon;
        public TextView appName;
        public TextView apkName;
        public TextView packageName;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(AppConstants.PROXY_VIEW_ACTION);
        intent.putExtra(AppConstants.EXTRA_DEX_PATH, mPluginItems.get(position).pluginPath);
        intent.putExtra(AppConstants.EXTRA_CLASS, mPluginItems.get(position).packageInfo.packageName + ".MainActivity");
        startActivity(intent);
    }
}
