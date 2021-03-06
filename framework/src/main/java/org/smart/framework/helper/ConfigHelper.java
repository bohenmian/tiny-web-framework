package org.smart.framework.helper;

import org.smart.framework.constant.ConfigConstant;
import org.smart.framework.util.PropsUtil;

import java.util.Properties;

public final class ConfigHelper {

    private static final Properties CONF_FILE = PropsUtil.loadProps(ConfigConstant.CONFIG_FILE);
    private static final String DEFAULT_JSP_PATH = "/WEB-INF/view";
    private static final String DEFAULT_ASSET_PATH = "/asset/";

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

    public static String getBasePackage() {
        return PropsUtil.getString(CONF_FILE, ConfigConstant.APP_BASE_PACKAGE);
    }

    public static String getJspPath() {
        return PropsUtil.getString(CONF_FILE, ConfigConstant.APP_JSP_PATH, DEFAULT_JSP_PATH);
    }

    public static String getAssetPath() {
        return PropsUtil.getString(CONF_FILE, ConfigConstant.APP_ASSET_PATH, DEFAULT_ASSET_PATH);
    }
}
