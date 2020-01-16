package com.aop.util;


import android.content.Context;
import android.widget.Toast;

import timber.log.Timber;

public class MyLog {
    private static final String TAG = "MyLog";
    public static final boolean DEBUG = true;

    public static void init() {
        if (DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public static void toast(Context context, String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }

    public static void v(String tag, String msg) {
        if (DEBUG) {
            Timber.tag(tag).v(msg);
        }
    }

    public static void d(String tag, String msg) {
        if (DEBUG) {
            Timber.tag(tag).d(msg);
        }
    }

    public static void d(String msg) {
        if (DEBUG) {
            Timber.tag(TAG).d(msg);
        }
    }

    public static void i(String tag, String msg) {
        if (DEBUG) {
            Timber.tag(tag).i(msg);
        }
    }

    public static void i(String msg) {
        if (DEBUG) {
            Timber.tag(TAG).i(msg);
        }
    }

    public static void w(String tag, String msg) {
        if (DEBUG) {
            Timber.tag(tag).w(msg);
        }
    }

    public static void e(String tag, String msg) {
        if (DEBUG) {
            Timber.tag(tag).e(msg);
        }
    }

    public static void e(String tag, Throwable e) {
        if (DEBUG) {
            Timber.tag(tag).e(e);
        }
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (DEBUG) {
            Timber.tag(tag).e(tr, msg);
        }
    }
}