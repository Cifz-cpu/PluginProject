package com.cifz.study.pluginproject;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.cifz.study.standard.ActivityLifeInterface;

import java.lang.reflect.Constructor;

/**
 * <pre>
 *     @author : wangzhen
 *     time   : 2020/02/06
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class ProxyActivity extends Activity {

    @Override
    public Resources getResources() {
        return PluginManager.getInstance(this).getResources();
    }

    @Override
    public ClassLoader getClassLoader() {
        return PluginManager.getInstance(this).getDexClassLoader();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String className = getIntent().getStringExtra("className");
        try {

            Class mPluginActivityClass = getClassLoader().loadClass(className);
            //实例化 插件里面的activity
            Constructor constructor = mPluginActivityClass.getConstructor(new Class[]{});
            Object mPluginActivity = constructor.newInstance(new Object[]{});

            ActivityLifeInterface activityLifeInterface = (ActivityLifeInterface) mPluginActivity;
            //注入宿主环境
            activityLifeInterface.insertAppContext(this);
            Bundle bundle = new Bundle();
            bundle.putString("appName", "我是宿主传递过来的信息");

            activityLifeInterface.onCreate(bundle);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void startActivity(Intent intent) {
        String className = intent.getStringExtra("className");
        Intent proxyIntent = new Intent(this,ProxyActivity.class);
        proxyIntent.putExtra("className",className);
        super.startActivity(proxyIntent);
    }

    @Override
    public ComponentName startService(Intent service) {
        String className = service.getStringExtra("className");
        Log.e("TestService","proxyact" + className);
        Intent proxyIntent = new Intent(this,ProxyService.class);
        proxyIntent.putExtra("className",className);
        return super.startService(proxyIntent);
    }

    @Override
    public Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter) {
        //全类名
        String pluginMyReceiverClassName = receiver.getClass().getName();
        return super.registerReceiver(new ProxyReceiver(pluginMyReceiverClassName), filter);
    }

    @Override
    public void sendBroadcast(Intent intent) {
        super.sendBroadcast(intent);
    }
}
