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

    private static final Map<Request, Handler> HANDLER_MAP = new HashMap<>();

    static {
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        if (CollectionUtils.isNotEmpty(controllerClassSet)) {
            for (Class<?> controllerClass : controllerClassSet) {
                Method[] methods = controllerClass.getDeclaredMethods();
                if (ArrayUtils.isNotEmpty(methods)) {
                    for (Method method : methods) {
                        if (method.isAnnotationPresent(RequestMapping.class)) {
                            RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                            String requestPath = requestMapping.value();
                            String requestMethod = requestMapping.method().name();
                            Request request = new Request(requestPath, requestMethod);
                            Handler handler = new Handler(controllerClass, method);
                            HANDLER_MAP.put(request, handler);
                        }
                    }
                }
            }
        }
    }

    public static Handler getHandler(String requestPath, String requestMethod) {
        Request request = new Request(requestPath, requestMethod);
        return HANDLER_MAP.get(request);
    }
}
