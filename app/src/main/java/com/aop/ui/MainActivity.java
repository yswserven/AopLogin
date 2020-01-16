package com.aop.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.aop.R;
import com.aop.annotation.BehaviorTrace;
import com.aop.aspect.ProxyHandler;
import com.aop.aspect.ProxyLogin;

import java.lang.reflect.Proxy;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements ProxyLogin {
    private final String TAG = this.getClass().getSimpleName();
    private ProxyLogin proxyLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        proxyLogin = (ProxyLogin) Proxy.newProxyInstance(this.getClassLoader(), new Class[]{ProxyLogin.class}, new ProxyHandler(this));
    }


    /**
     * 这个是未登录的时候
     *
     * @author Ysw created at 2020/1/10 10:16
     */
    public void jump(View view) {
        jumpMineActivity();
    }


    @BehaviorTrace("这个是测试的值")
    private void jumpMineActivity() {
        Intent intent = new Intent(MainActivity.this, MineActivity.class);
        startActivity(intent);
    }


    @BehaviorTrace("这个是退出登录")
    public void loginOut(View view) {

    }

    public void proxyJump(View view) {
        proxyLogin.proxyLogin();
    }

    @Override
    public void proxyLogin() {
        Intent intent = new Intent(MainActivity.this, MineActivity.class);
        startActivity(intent);
    }
}
