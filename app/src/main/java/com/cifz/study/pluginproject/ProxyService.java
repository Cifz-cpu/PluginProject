package com.cifz.study.pluginproject;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.cifz.study.standard.ServiceLifeInterface;

/**
 * <pre>
 *     @author : wangzhen
 *     time   : 2020/02/07
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class ProxyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String className = intent.getStringExtra("className");
        Log.e("TestService","ProxyService-----" + className);
        try {
            Class mTestServiceClass = PluginManager.getInstance(this).getDexClassLoader().loadClass(className);
            Object mTestService = mTestServiceClass.newInstance();

            ServiceLifeInterface serviceLifeInterface = (ServiceLifeInterface) mTestService;
            serviceLifeInterface.insertService(this);
            serviceLifeInterface.onStartCommand(intent,flags,startId);
        } catch (Exception e) {
            Log.e("TestService","ProxyService-----error" + e.getMessage());
            e.printStackTrace();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
