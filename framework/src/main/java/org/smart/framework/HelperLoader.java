package org.smart.framework;

import org.smart.framework.helper.BeanHelper;
import org.smart.framework.helper.ClassHelper;
import org.smart.framework.helper.ControllerHelper;
import org.smart.framework.helper.IocHelper;
import org.smart.framework.util.ClassUtil;

import java.util.Arrays;

public final class HelperLoader {

    public static void init() {
        Class<?>[] classList = {
                ClassHelper.class,
                BeanHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };
        Arrays.stream(classList).forEach(cls -> ClassUtil.loadClass(cls.getName()));
    }

}