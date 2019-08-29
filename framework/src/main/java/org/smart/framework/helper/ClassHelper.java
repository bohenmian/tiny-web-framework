package org.smart.framework.helper;

import org.smart.framework.annotation.Controller;
import org.smart.framework.annotation.Service;
import org.smart.framework.util.ClassUtil;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ClassHelper {

    private static final Set<Class<?>> CLASS_SET;

    static {
        String basePackage = ConfigHelper.getBasePackage();
        CLASS_SET = ClassUtil.getClassSet(basePackage);
    }

    public static Set<Class<?>> getClassSet() {
        return CLASS_SET;
    }

    public static Set<Class<?>> getServiceClassSet() {
        return CLASS_SET.stream()
                .filter(cls -> cls.isAnnotationPresent(Service.class))
                .collect(Collectors.toSet());
    }

    public static Set<Class<?>> getControllerClassSet() {
        return CLASS_SET.stream()
                .filter(cls -> cls.isAnnotationPresent(Controller.class))
                .collect(Collectors.toSet());
    }

    public static Set<Class<?>> getBeanClassSet() {
        Set<Class<?>> classSet = new HashSet<>();
        classSet.addAll(getControllerClassSet());
        classSet.addAll(getServiceClassSet());
        return classSet;
    }
}
