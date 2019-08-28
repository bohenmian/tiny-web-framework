package org.smart.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ClassUtil {

    public static final Logger LOGGER = LoggerFactory.getLogger(ClassUtil.class);

    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    private static Class<?> loadClass(String className, boolean isInitialized) {
        Class<?> cls;
        try {
            cls = Class.forName(className, isInitialized, getClassLoader());
        } catch (ClassNotFoundException e) {
            LOGGER.error("load class failure");
            throw new RuntimeException();
        }
        return cls;
    }

    public static Class<?> loadClass(String className) {
        return loadClass(className, true);
    }
}
