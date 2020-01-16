package com.aop.aspect;

import android.content.Context;

public class LoginUtil {
    private LoginUtil() {
    }

    private static LoginUtil instance;

    public static LoginUtil getInstance() {
        if (instance == null) {
            synchronized (LoginUtil.class) {
                if (instance == null) {
                    instance = new LoginUtil();
                }
            }
        }
        return instance;
    }

    private ILogin iLogin;

    ILogin getLogin() {
        return iLogin;
    }

    void setLogin(ILogin iLogin) {
        this.iLogin = iLogin;
    }

    private Context applicationContext;

    public Context getApplicationContext() {
        return applicationContext;
    }

    void setApplicationContext(Context applicationContext) {
        this.applicationContext = applicationContext;
    }
}
