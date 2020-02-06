package com.cifz.study.standard;

import android.app.Activity;
import android.os.Bundle;

/**
 * <pre>
 *     @author : wangzhen
 *     time   : 2020/02/06
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public interface ActivityLifeInterface {

    void insertAppContext(Activity appActivity);

    // 生命周期方法
    void onCreate(Bundle savedInstanceState);

    void onStart();

    void onResume();

    void onDestroy();
}
