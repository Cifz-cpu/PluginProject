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
 *     time   : 2020/02/07
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class PluginManager2 {
    private static PluginManager2 pluginManager2;

    private Context context;

    public static PluginManager2 getInstance(Context context){
        if (pluginManager2 == null){
            synchronized (PluginManager2.class){
                if(pluginManager2 == null){
                    pluginManager2 = new PluginManager2(context);
                }
            }
        }
        return pluginManager2;
    }

    private PluginManager2(Context context) {
        this.context = context;
    }

    private DexClassLoader dexClassLoader;
    private Resources resources;

    public void loadPlugin(){
        try {
            File file = new File(Environment.getExternalStorageState() + File.separator + "p.apk");
            if(!file.exists()){
                Toast.makeText(context,"插件包 不存在...",Toast.LENGTH_SHORT).show();
                return;
            }

            String pluginPath = file.getAbsolutePath();
            File fileDir = context.getDir("pluginDir",Context.MODE_PRIVATE);
            dexClassLoader = new DexClassLoader(pluginPath,fileDir.getAbsolutePath(),null,context.getClassLoader());

            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPathMethod = assetManager.getClass().getMethod("addAssetPath",String.class);
            addAssetPathMethod.invoke(assetManager,pluginPath);

            Resources r = context.getResources();
            resources = new Resources(assetManager,r.getDisplayMetrics(),r.getConfiguration());


        }catch (Exception e){
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
