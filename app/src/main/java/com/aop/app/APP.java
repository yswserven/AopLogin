package com.aop.app;

import android.app.Application;
import android.content.Intent;

import com.aop.aspect.ILogin;
import com.aop.aspect.LoginSDK;
import com.aop.aspect.LoginUtil;
import com.aop.ui.LoginActivity;
import com.aop.util.MyLog;
import com.aop.util.SharePreferenceUtil;

/**
 * Created by: Ysw on 2020/1/10.
 */
public class APP extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        /* 日志打印初始化 @author Ysw created 2020/1/10 */
        MyLog.init();

        /* 登录判断SDK初始化 @author Ysw created 2020/1/10 */
        LoginSDK.getInstance().init(this, new ILogin() {
            @Override
            public void login() {
                Intent intent = new Intent(LoginUtil.getInstance().getApplicationContext(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }

            @Override
            public boolean isLogin() {
                return SharePreferenceUtil.getBooleanSp(SharePreferenceUtil.IS_LOGIN, APP.this);
            }

            @Override
            public void loginOut() {
                SharePreferenceUtil.setBooleanSp(SharePreferenceUtil.IS_LOGIN, false, APP.this);
            }
        });
    }
}
