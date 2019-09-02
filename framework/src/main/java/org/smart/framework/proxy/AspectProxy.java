package org.smart.framework.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

public abstract class AspectProxy implements Proxy {

    private static final Logger LOGGER = LoggerFactory.getLogger(AspectProxy.class);

    @Override
    public Object doProxy(ProxyChain proxyChain) throws Throwable {
        Object result = null;

        Class<?> cls = proxyChain.getTargetClass();
        Method method = proxyChain.getTargetMethod();
        Object[] params = proxyChain.getMethodParams();
        begin();
        try {
            if (intercept(method, params)) {
                before(method, params);
                result = proxyChain.doProxyChain();
                after(method, params);
            } else {
                result = proxyChain.doProxyChain();
            }
        } catch (Exception e) {
            LOGGER.error("proxy error", e);
            error(method, params, e);
            throw e;
        } finally {
            end();
        }
        return result;
    }

    public void begin() {

    }

    public boolean intercept(Method method, Object[] param) throws Throwable {
        return true;
    }

    public void before(Method method, Object[] param) throws Throwable {

    }

    public void after(Method method, Object[] params) throws Throwable {
    }

    public void error(Method method, Object[] params, Throwable e) {

    }

    public void end() {
    }
}
