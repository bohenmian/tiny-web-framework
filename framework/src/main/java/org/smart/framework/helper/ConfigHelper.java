package org.smart.framework.helper;

import org.smart.framework.constant.ConfigConstant;
import org.smart.framework.util.PropsUtil;

import java.util.Properties;

public final class ConfigHelper {

    private static final Properties CONF_FILE = PropsUtil.loadProps(ConfigConstant.CONFIG_FILE);

    public static String getDriver() {
        return PropsUtil.getString(CONF_FILE, ConfigConstant.JDBC_DRIVER);
    }

    public static String getUrl() {
        return PropsUtil.getString(CONF_FILE, ConfigConstant.JDBC_URL);
    }

    public static String getUsername() {
        return PropsUtil.getString(CONF_FILE, ConfigConstant.JDBC_USERNAME);
    }

    public static String getPassword() {
        return PropsUtil.getString(CONF_FILE, ConfigConstant.JDBC_PASSWORD);
    }

}
