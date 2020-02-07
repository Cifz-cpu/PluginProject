package com.cifz.study.pluginproject;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * <pre>
 *     @author : wangzhen
 *     time   : 2020/02/06
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class PluginManager {
    private static PluginManager pluginManager;

    private Context context;

    public static PluginManager getInstance(Context context){
        if(pluginManager == null){
            synchronized (PluginManager.class){
                if(pluginManager == null){
                    pluginManager = new PluginManager(context);
                }
            }
        }
        return pluginManager;
    }

    private PluginManager(Context context) {
        this.context = context;
    }

    private DexClassLoader dexClassLoader;
    private Resources resources;

    public void loadPlugin(){
        try {

            File file = new File(Environment.getExternalStorageDirectory() + File.separator + "p.apk");
            if(!file.exists()){
                Toast.makeText(context,"插件包 不存在...",Toast.LENGTH_SHORT).show();
                return;
            }
            String pluginPath = file.getAbsolutePath();
            File fileDir = context.getDir("pDir",Context.MODE_PRIVATE);
            dexClassLoader = new DexClassLoader(pluginPath,fileDir.getAbsolutePath(),null,context.getClassLoader());

            //加载插件的layout

            AssetManager assetManager = AssetManager.class.newInstance();
            Method methodAddAssetPath = assetManager.getClass().getMethod("addAssetPath",String.class);
            methodAddAssetPath.invoke(assetManager,pluginPath);

            Resources r = context.getResources();

            resources = new Resources(assetManager,r.getDisplayMetrics(),r.getConfiguration());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public DexClassLoader getDexClassLoader() {
        return dexClassLoader;
    }

    public Resources getResources() {
        return resources;
    }
}
