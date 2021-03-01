package com.dp.launcher.tv.utils;

import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射工具
 * @author GaoFei
 *
 */
public class ReflectUtils {
	private static final String TAG = "ReflectUtils";
    public static void setFieldValue(Object targetObject, String filedName, Object filedvalue){
        try{
            Field field = targetObject.getClass().getDeclaredField(filedName);
            field.setAccessible(true);
            field.set(targetObject, filedvalue);
        }catch (Exception e){
            Log.e(TAG, "setFieldValue->filedName:" + filedName);
            Log.e(TAG, "setFieldValue->value:" + filedvalue);
            Log.e(TAG, "setFieldValue->exception:" + e);
        }
        
    }

    /**
     * 获取当前Object类的属性，只能当前Object对象
     * @param targetObject
     * @param filedName
     * @return
     */
    public static Object getFieldValue(Object targetObject, String filedName){
        try{
            Field field = targetObject.getClass().getDeclaredField(filedName);
            field.setAccessible(true);
            return field.get(targetObject);
        }catch (Exception e){
            Log.e(TAG, "getFieldValue->filedName:" + filedName);
            Log.e(TAG, "getFieldValue->exception:" + e);
        }
        return null;
    }


    /**
     * 循环向上转型, 获取对象的 DeclaredField
     * @param object : 子类对象
     * @param fieldName : 父类中的属性名
     * @return 父类中的属性对象
     */

    public static Object getSuperDeclaredField(Object object, String fieldName){
        Field field = null ;

        Class<?> clazz = object.getClass() ;

        for(; clazz != Object.class ; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName) ;
                field.setAccessible(true);
                return field.get(object) ;
            } catch (Exception e) {
                //这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。
                //如果这里的异常打印或者往外抛，则就不会执行clazz = clazz.getSuperclass(),最后就不会进入到父类中了

            }
        }

        return null;
    }

    public static Object invokeMethod(Object object, String methodName, Class<?>[]paramTypes, Object[] values){
        try{
            Method method = object.getClass().getDeclaredMethod(methodName, paramTypes);
            method.setAccessible(true);
            return method.invoke(object, values);
            //return method.invoke(object, paramTypes);
        }catch (Exception e){
            Log.e(TAG, "invokeMethod->methodName:" + methodName);
            Log.e(TAG, "invokeMethod->exception:" + e);
            e.printStackTrace();
        }
        return null;
    }



    public static String getProperty(String key, String defaultValue) {
        String value = defaultValue;
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class, String.class);
            value = (String)(get.invoke(c, key, defaultValue));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return value;
        }
    }

    public static void setProperty(String key, String value) {
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method set = c.getMethod("set", String.class, String.class);
            set.invoke(c, key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
