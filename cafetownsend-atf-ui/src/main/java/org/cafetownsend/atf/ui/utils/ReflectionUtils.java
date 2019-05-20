package org.cafetownsend.atf.ui.utils;

import java.lang.reflect.Field;
import java.util.Objects;

public class ReflectionUtils {

    public static <T> T getFieldInstanceByName(String name, Object context) {
        try {
            Field declaredField = getFieldInHierarchy(name, context.getClass());

            if (Objects.equals(declaredField, null)) return null;

            declaredField.setAccessible(true);
            return (T) declaredField.get(context);
        } catch (IllegalAccessException e) {
            return null;
        }
    }

    private static Field getFieldInHierarchy(String name, Class clazz) {
        try {
            if (Objects.equals(clazz, null)) return null;
            return clazz.getDeclaredField(name);
        } catch (NoSuchFieldException e) {
            return getFieldInHierarchy(name, clazz.getSuperclass());
        }
    }
}
