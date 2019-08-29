package org.smart.framework.helper;

import org.smart.framework.util.ReflectionUtil;

import java.util.HashMap;
import java.util.Map;

public class BeanHelper {

    private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<>();

    static {
        ClassHelper.getBeanClassSet().forEach(cls -> BEAN_MAP.put(cls, ReflectionUtil.newInstance(cls)));
    }

    public Map<Class<?>, Object> getBeanMap() {
        return BEAN_MAP;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<?> cls) {
        if (!BEAN_MAP.containsKey(cls)) {
            throw new RuntimeException("can not get bean by class " + cls);
        }
        return (T) BEAN_MAP.get(cls);
    }
}
