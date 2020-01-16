package com.aop.aspect;

import android.content.Context;

public class LoginSDK {
    private static LoginSDK instance;
    private Context mContext;

    private LoginSDK() {
    }

    public static LoginSDK getInstance() {
        if (instance == null) {
            synchronized (LoginSDK.class) {
                if (instance == null) {
                    instance = new LoginSDK();
                }
            }
        }
        return instance;
    }

    public void init(Context context, ILogin iLogin) {
        mContext = context.getApplicationContext();
        LoginUtil.getInstance().setApplicationContext(context);
        LoginUtil.getInstance().setLogin(iLogin);
    }
}
