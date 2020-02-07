package com.cifz.study.plugin_package;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

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
public class BaseService extends Service implements ServiceLifeInterface {

    public Service service;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void insertService(Service service) {
        this.service = service;
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onCreate() {

    }

    @SuppressLint("WrongConstant")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return 0;
    }
}

