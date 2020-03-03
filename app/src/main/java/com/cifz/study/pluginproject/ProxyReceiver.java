package com.cifz.study.pluginproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.cifz.study.standard.BroadcastLifeInterface;

/**
 * <pre>
 *     @author : wangzhen
 *     time   : 2020/02/08
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class ProxyReceiver extends BroadcastReceiver {
    /** 插件里面testreceiver的全类名 **/
    private String pluginMyReceiverClassName;

    public ProxyReceiver(String pluginMyReceiverClassName) {
        this.pluginMyReceiverClassName = pluginMyReceiverClassName;
    }    

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            Class pluginReceiver = PluginManager.getInstance(context).getDexClassLoader().loadClass(pluginMyReceiverClassName);
            Object o = pluginReceiver.newInstance();

            BroadcastLifeInterface broadcastLifeInterface = (BroadcastLifeInterface) o;

            broadcastLifeInterface.onReceive(context,intent);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
