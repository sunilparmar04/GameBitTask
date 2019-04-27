package com.test.gambit.utils;

import com.test.gambit.BuildConfig;


public final class AppLogger {

    private AppLogger() {
        // This utility class is not publicly instantiable
    }

    public static void d(String tag, String text) {
        if (BuildConfig.DEBUG)
            android.util.Log.d(tag, text);

    }

    public static void v(String tag, String text) {
        if (BuildConfig.DEBUG)
            android.util.Log.v(tag, text);
    }

    public static void i(String tag, String text) {
        if (BuildConfig.DEBUG)
            android.util.Log.i(tag, text);
    }

    public static void e(String tag, String text) {
        if (BuildConfig.DEBUG)
            android.util.Log.e(tag, text);
    }

    public static void logException(Throwable e) {
        if (BuildConfig.DEBUG)
            e.printStackTrace();
    }
}
