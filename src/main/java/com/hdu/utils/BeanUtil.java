package com.hdu.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

public class BeanUtil{
    private static ApplicationContext atx;
    private BeanUtil(ApplicationContext atx){
        this.atx=atx;
    }

    public static Environment getEnv(){
        return atx.getEnvironment();
    }

    public static String  getProperty(String key){
        return getEnv().getProperty(key);
    }

    public  static <T> T getProperty(String key,Class<T> t){
        return getEnv().getProperty(key,t);
    }

    public static <T> T getBean(String name,Class<T> clazz){
        if(canGet("getBean",clazz)){
            return atx.getBean(name,clazz);
        }else{
            return null;
        }

    }

    public static <T> T getBean(Class<T> clazz){
        if(canGet("getBean",clazz)){
            return atx.getBean(clazz);
        }else{
            return null;
        }
    }

    private static <T> boolean isService(Class<T> clazz){
        try {
            Service service=clazz.getDeclaredAnnotation(Service.class);
            Repository repository=clazz.getDeclaredAnnotation(Repository.class);
            if(null==service && null==repository){
                return false;
            }else{
                return true;
            }
        } catch (Exception e1) {
            return false;
        }
    }

    private static <T> boolean canGet(String method,Class<T> clazz){
//        if(!isService(clazz)){
//            return false;
//        }
        StackTraceElement[] elements=Thread.currentThread().getStackTrace();
        int i=0;
        StackTraceElement e=null;
        for(;i<elements.length;i++){
            e=elements[i];
            String className=e.getClassName();
            String methodName=e.getMethodName();
            if("context.support.utilities.BeanUtil".equals(className) && method.equals(methodName)){
                break;
            }
        }
        e=elements[i+1];
        try {
            return isService(Class.forName(e.getClassName()));
        } catch (Exception e1) {
            return false;
        }
    }
}
