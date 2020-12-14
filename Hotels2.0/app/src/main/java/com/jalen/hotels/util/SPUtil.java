package com.jalen.hotels.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SPUtil {

    private final static String CONFIG = "config";
    private static SharedPreferences sp;

    public static void putBoolean(Context context, String key, boolean defaultvalue) {
        if (sp == null) {
            sp = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);//调用Context对象的getSharedPreferences()方法获得的SharedPreferences对象可以被同一应用程序下的其他组件共享
        }
        sp.edit().putBoolean(key, defaultvalue).commit();//提交
    }

    public static boolean getBoolean(Context context, String key, boolean defaultvalue) {
        if (sp == null) {
            sp = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        }
        return sp.getBoolean(key, defaultvalue);
    }

    public static void putString(Context context, String key, String defaultvalue) {
        if (sp == null) {
            sp = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        }
        sp.edit().putString(key, defaultvalue).commit();
    }

    public static String getString(Context context, String key, String defaultvalue) {
        if (sp == null) {
            sp = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        }
        return sp.getString(key, defaultvalue);
    }

    public static void putLong(Context context, String key, long defaultvalue) {
        if (sp == null) {
            sp = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        }
        sp.edit().putLong(key, defaultvalue).commit();
    }

    public static long getLong(Context context, String key, long defaultvalue) {
        if (sp == null) {
            sp = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        }
        return sp.getLong(key, defaultvalue);
    }

    public static void putInt(Context context, String key, int defaultvalue) {
        if (sp == null) {
            sp = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        }
        sp.edit().putInt(key, defaultvalue).commit();
    }

    public static int getInt(Context context, String key, int defaultvalue) {
        if (sp == null) {
            sp = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        }
        return sp.getInt(key, defaultvalue);
    }
}
