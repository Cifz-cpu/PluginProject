package com.cifz.study.plugin_package;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.cifz.study.standard.ActivityLifeInterface;

/**
 * <pre>
 *     @author : wangzhen
 *     time   : 2020/02/06
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class BaseActivity extends Activity implements ActivityLifeInterface {

    public Activity appActivity;

    @Override
    public void insertAppContext(Activity appActivity) {
        this.appActivity = appActivity;
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onCreate(Bundle savedInstanceState) {

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onStart() {

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onResume() {

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onDestroy() {

    }

    public void setContentView(int resiD) {
        appActivity.setContentView(resiD);
    }

    public View findViewById(int resiD) {
        return appActivity.findViewById(resiD);
    }

    @Override
    public void startActivity(Intent intent) {
        Intent intentNew = new Intent();
        Log.e("plugin_project",intent.getComponent().getClassName());
        intentNew.putExtra("className",intent.getComponent().getClassName());
        appActivity.startActivity(intentNew);
    }
}
