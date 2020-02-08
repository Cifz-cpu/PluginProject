package com.cifz.study.standard;

import android.content.Context;
import android.content.Intent;

/**
 * <pre>
 *     @author : wangzhen
 *     time   : 2020/02/08
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public interface BroadcastLifeInterface {

    void onReceive(Context context, Intent intent);

}
