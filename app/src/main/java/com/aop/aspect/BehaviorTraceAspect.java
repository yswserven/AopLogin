package com.aop.aspect;

import com.aop.annotation.BehaviorTrace;
import com.aop.util.MyLog;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Created by: Ysw on 2020/1/10.
 * 这个是一个切面
 */
/* 在类的开始处需要添加Aspect注解 @author Ysw created 2020/1/10 */
@Aspect
public class BehaviorTraceAspect {
    private final String TAG = this.getClass().getSimpleName();
    private Boolean isLogin = true;
    //定义规则
    //原代码中哪些注解放到当前切面进行处理
    /* 注解名  使用的类  使用的方法  方法的参数 @author Ysw created 2020/1/10 */
    @Pointcut("execution(@com.aop.annotation.BehaviorTrace * *(..))")
    public void methodWithBeahviorTrace() {
    }
    //对进入切面的内容进行处理
    @Around("methodWithBeahviorTrace()")
    public void weaveJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        /* 拿到方法的签名 @author Ysw created 2020/1/10 */
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        /* 方法所在的类的名字 @author Ysw created 2020/1/10 */
        String className = methodSignature.getDeclaringType().getSimpleName();
        MyLog.d(TAG, "BehaviorTraceAspect.weaveJoinPoint：className = " + className);
        /* 方法名 @author Ysw created 2020/1/10 */
        String methodName = methodSignature.getName();
        MyLog.d(TAG, "BehaviorTraceAspect.weaveJoinPoint：methodName = " + methodName);
        /* 方法名注解上的值 @author Ysw created 2020/1/10 */
        String value = methodSignature.getMethod().getAnnotation(BehaviorTrace.class).value();
        MyLog.d(TAG, "BehaviorTraceAspect.weaveJoinPoint：value = " + value);
        ILogin iLogin = LoginUtil.getInstance().getLogin();
        if (iLogin == null) {
            throw new RuntimeException("LoginSDK 没有初始化！");
        }
        switch (methodName) {
            case "jumpMineActivity":
                if (iLogin.isLogin()) {
                    /* 此处表示继续执行被注解标识的方法 @author Ysw created 2020/1/10 */
                    joinPoint.proceed();
                } else {
                    iLogin.login();
                }
                break;
            case "loginOut":
                iLogin.loginOut();
                break;
        }
    }
}
