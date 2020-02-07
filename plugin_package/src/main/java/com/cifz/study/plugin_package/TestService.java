package com.cifz.study.plugin_package;

import android.content.Intent;
import android.util.Log;

/**
 * <pre>
 *     @author : wangzhen
 *     time   : 2020/02/07
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class TestService extends BaseService {

    private static final String TAG= TestService.class.getSimpleName();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 开启子线程，执行耗时任务
        new Thread(new Runnable(){

            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        Log.d(TAG, "插件里面的服务 正在执行中....");
                    }
                }
            }

        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
