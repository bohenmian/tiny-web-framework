package org.smart.framework.helper;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.smart.framework.annotation.RequestMapping;
import org.smart.framework.bean.Handler;
import org.smart.framework.bean.Request;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ControllerHelper {

    private static final Map<Request, Handler> HANDLER_MAP = new HashMap<>();

    static {
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        if (CollectionUtils.isNotEmpty(controllerClassSet)) {
            controllerClassSet.forEach(controllerClass -> {
                if (ArrayUtils.isNotEmpty(controllerClass.getDeclaredMethods())) {
                    Arrays.stream(controllerClass.getDeclaredMethods())
                            .filter(method -> method.isAnnotationPresent(RequestMapping.class))
                            .forEach(method -> {
                                RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                                HANDLER_MAP.put(new Request(requestMapping.value(), requestMapping.method().name()),
                                        new Handler(controllerClass, method));
                            });
                }
            });
        }
    }

    public static Handler getHandler(String requestPath, String requestMethod) {
        Request request = new Request(requestPath, requestMethod);
        return HANDLER_MAP.get(request);
    }
}
