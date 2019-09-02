package org.smart.framework.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart.framework.annotation.Transaction;
import org.smart.framework.helper.DatabaseHelper;

public class TransactionProxy implements Proxy {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionProxy.class);

    @Override
    public Object doProxy(ProxyChain proxyChain) throws Throwable {
        Object result;
        if (proxyChain.getTargetMethod().isAnnotationPresent(Transaction.class)) {
            try {
                DatabaseHelper.beginTransaction();
                LOGGER.debug("begin transaction");
                result = proxyChain.doProxyChain();
                DatabaseHelper.commitTransaction();
                LOGGER.debug("commit transaction");
            } catch (Exception e) {
                DatabaseHelper.rollbackTransaction();
                LOGGER.debug("rollback transaction");
                throw e;
            }
        } else {
            result = proxyChain.doProxyChain();
        }
        return result;
    }
}
