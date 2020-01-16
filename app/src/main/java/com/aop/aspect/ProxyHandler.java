package com.aop.aspect;

import android.content.Context;
import android.content.Intent;

import com.aop.ui.LoginActivity;
import com.aop.util.SharePreferenceUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by: Ysw on 2020/1/10.
 */
public class ProxyHandler implements InvocationHandler {

    private Context mContext;
    private Object mTarget;

    public ProxyHandler(Context context) {
        this.mContext = context;
        this.mTarget = context;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        Object result = null;
        if (SharePreferenceUtil.getBooleanSp(SharePreferenceUtil.IS_LOGIN, mContext)) {
            result = method.invoke(mTarget, objects);
        } else {
            Intent intent = new Intent(mContext, LoginActivity.class);
            mContext.startActivity(intent);
        }
        return result;
    }
}
