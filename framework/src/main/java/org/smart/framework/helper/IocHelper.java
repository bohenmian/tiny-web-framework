package org.smart.framework.helper;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.smart.framework.annotation.Autowired;
import org.smart.framework.util.ReflectionUtil;

import java.util.Arrays;
import java.util.Map;

public final class IocHelper {

    static {
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        if (MapUtils.isNotEmpty(beanMap)) {
            beanMap.forEach((key, value) -> {
                if (ArrayUtils.isNotEmpty(key.getDeclaredFields())) {
                    Arrays.stream(key.getDeclaredFields())
                            .filter(field -> field.isAnnotationPresent(Autowired.class))
                            .filter(field -> beanMap.get(field.getType()) != null)
                            .forEach(field -> ReflectionUtil.setField(value, field, beanMap.get(field.getType())));
                }
            });
        }
    }
}
