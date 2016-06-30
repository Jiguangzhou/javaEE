package com.kaishengit.aop;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * AOP 通知类
 */

public class Notice {

    /**
     * 前置通知
     */
    public void beforeNotice(){
        System.out.println("前置通知");
    }

    /**
     * 后置通知
     */
    public void afterAdvice(Object result){
        System.out.println("后置通知:"+result);
    }

    /**
     * 异常通知
     */
    public void exceptionNotice(Exception ex){
        System.out.println("异常通知:"+ex.getMessage());
    }

    public void finallyNotice(){
        System.out.println("最终通知");
    }

    /**
     *环绕通知
     */
    public Object aroundNotice(ProceedingJoinPoint joinPoint){
        Object object = null;
        try {
            System.out.println("~~~~前置通知~~~~");
            object = joinPoint.proceed();
            System.out.println("~~~~后置通知~~~~"+object);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("~~~~异常通知~~~~");
        }finally {
            System.out.println("~~~~最终通知~~~~");
        }
        return object;

    }
}
