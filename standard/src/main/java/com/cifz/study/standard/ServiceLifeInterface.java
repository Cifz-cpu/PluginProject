package com.cifz.study.standard;

import android.app.Service;
import android.content.Intent;

/**
 * <pre>
 *     @author : wangzhen
 *     time   : 2020/02/07
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public interface ServiceLifeInterface {

    public void insertService(Service service);

    public int onStartCommand(Intent intent, int flags, int startId);

    public void onCreate();

    public void onDestroy();

}
