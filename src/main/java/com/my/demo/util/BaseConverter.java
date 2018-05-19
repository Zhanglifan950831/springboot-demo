package com.my.demo.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * BaseConverter :
 *
 * @author zhanglifan
 * @version 1.0.0
 * @since 2018/04/28 17:30
 */
public class BaseConverter {

    /**
     * 拷贝属性
     *
     * @param source 源对象
     * @param clazz  目标对象的class
     * @param <T>    目标对象
     * @return
     */
    public static <T> T copyProperties(Object source, Class clazz) {
        /** 源对象的class */
        Class sourceClass = source.getClass();
        /** 源对象class的属性列表 */
        Field[] sourceFields = sourceClass.getDeclaredFields();
        /** 目标对象class的属性列表 */
        Field[] targetFields = clazz.getDeclaredFields();

        /** 实例化目标对象 */
        T target = null;
        try {
            target = (T) clazz.newInstance();
            for (Field targetField : targetFields) {
                /** 属性名称 */
                String name = targetField.getName();
                /** 源对象是否具有该属性名 */
                boolean isExist = false;
                for (Field sourceField : sourceFields) {
                    if (sourceField.getName().equals(name)) {
                        isExist = true;
                    }
                }
                if (isExist) {
                    String propertyName = name.substring(0, 1).toUpperCase().concat(name.substring(1));
                    Method getMethod = sourceClass.getMethod("get" + propertyName);
                    Method setMethod = clazz.getMethod("set" + propertyName, targetField.getType());
                    setMethod.invoke(target, getMethod.invoke(source));
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return target;
    }
}
