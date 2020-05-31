package com.citi.jhunter.util;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class JHunterReflectUtil {
    /**
     * 获取一个类下的指定成员
     *
     * @param clazz 目标类
     * @param name  属性名
     * @return 属性
     */
    public static Field getField(Class<?> clazz, String name) {
        for (Field field : getFields(clazz)) {
            if (JHunterCheckUtils.isEquals(field.getName(), name)) {
                return field;
            }
        }//for
        return null;
    }

    public static Set<Field> getFields(Class<?> clazz) {
        final Set<Field> fields = new LinkedHashSet<Field>();
        final Class<?> parentClazz = clazz.getSuperclass();
        Collections.addAll(fields, clazz.getDeclaredFields());
        if (null != parentClazz) {
            fields.addAll(getFields(parentClazz));
        }
        return fields;
    }

    /**
     * 获取对象某个成员的值
     *
     * @param <T>
     * @param target 目标对象
     * @param field  目标属性
     * @return 目标属性值
     * @throws IllegalArgumentException 非法参数
     * @throws IllegalAccessException   非法进入
     */
    public static <T> T getFieldValueByField(Object target, Field field) throws IllegalArgumentException, IllegalAccessException {
        final boolean isAccessible = field.isAccessible();
        try {
            field.setAccessible(true);
            //noinspection unchecked
            return (T) field.get(target);
        } finally {
            field.setAccessible(isAccessible);
        }
    }
}
