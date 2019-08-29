package org.smart.framework.helper;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.smart.framework.annotation.RequestMapping;
import org.smart.framework.bean.Handler;
import org.smart.framework.bean.Request;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ControllerHelper {

    private static final Map<Request, Handler> ACTION_MAP = new HashMap<>();

    static {
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        if (CollectionUtils.isNotEmpty(controllerClassSet)) {
            for (Class<?> collectionClass : controllerClassSet) {
                Method[] methods = collectionClass.getDeclaredMethods();
                if (ArrayUtils.isNotEmpty(methods)) {
                    for (Method method : methods) {
                        if (method.isAnnotationPresent(RequestMapping.class)) {
                            RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                            String requestPath = requestMapping.value();
                            String requestMethod = requestMapping.method().name();
                            Request request = new Request(requestPath, requestMethod);
                            Handler handler = new Handler(collectionClass, method);
                            ACTION_MAP.put(request, handler);
                        }
                    }
                }
            }
        }
    }

}
