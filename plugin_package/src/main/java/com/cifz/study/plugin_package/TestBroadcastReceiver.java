package com.cifz.study.plugin_package;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.cifz.study.standard.BroadcastLifeInterface;

/**
 * <pre>
 *     @author : wangzhen
 *     time   : 2020/02/08
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class TestBroadcastReceiver extends BroadcastReceiver implements BroadcastLifeInterface {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"我是插件的广播",Toast.LENGTH_SHORT).show();
    }
}
