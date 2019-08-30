package org.smart.framework.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart.framework.annotation.Aspect;
import org.smart.framework.proxy.AspectProxy;
import org.smart.framework.proxy.Proxy;
import org.smart.framework.util.ClassUtil;

import java.util.*;

public final class AopHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(AopHelper.class);

    static {
        try {
            Map<Class<?>, Set<Class<?>>> aspectMap = createAspectMap();
            Map<Class<?>, List<Proxy>> targetMap = createTargetMap(aspectMap);
        } catch (Exception e) {
            LOGGER.error("aop failure", e);
        }
    }

    private static Map<Class<?>, List<Proxy>> createTargetMap(Map<Class<?>, Set<Class<?>>> aspectMap) throws Exception {
        Map<Class<?>, List<Proxy>> targetMap = new HashMap<>();
        for (Map.Entry<Class<?>, Set<Class<?>>> entry : aspectMap.entrySet()) {
            Class<?> aspectClass = entry.getKey();
            Set<Class<?>> targetClassSet = entry.getValue();
            for (Class<?> targetClass : targetClassSet) {
                Proxy aspect = (Proxy) aspectClass.newInstance();
                if (targetMap.containsKey(targetClass)) {
                    targetMap.get(targetClass).add(aspect);
                } else {
                    List<Proxy> aspectList = new ArrayList<>();
                    aspectList.add(aspect);
                    targetMap.put(targetClass, aspectList);
                }
            }
        }
        return targetMap;
    }

    private static Map<Class<?>, Set<Class<?>>> createAspectMap() {
        Map<Class<?>, Set<Class<?>>> aspectMap = new HashMap<>();
        addAspectProxy(aspectMap);
        return aspectMap;
    }

    private static void addAspectProxy(Map<Class<?>, Set<Class<?>>> aspectMap) {
        Set<Class<?>> aspectClassSet = ClassHelper.getClassSetBySuper(AspectProxy.class);
        aspectClassSet.stream()
                .filter(aspectClass -> aspectClass.isAnnotationPresent(Aspect.class))
                .forEach(aspectClass -> aspectMap.put(aspectClass, createTargetClassSet(aspectClass.getAnnotation(Aspect.class))));
    }

    private static Set<Class<?>> createTargetClassSet(Aspect aspect) {
        Set<Class<?>> targetClassSet = new HashSet<>();
        String pkg = aspect.pkg();
        String cls = aspect.cls();
        if (!pkg.equals("") && !cls.equals("")) {
            try {
                targetClassSet.add(Class.forName(pkg + "." + cls));
            } catch (ClassNotFoundException e) {
                LOGGER.error("add class fail", e);
                throw new RuntimeException();
            }
        } else {
            targetClassSet.addAll(ClassUtil.getClassSet(pkg));
        }
        return targetClassSet;
    }

}
