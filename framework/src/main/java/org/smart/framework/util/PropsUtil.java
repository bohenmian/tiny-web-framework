package org.smart.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropsUtil {

    public static final Logger LOGGER = LoggerFactory.getLogger(PropsUtil.class);

    public static Properties loadProps(String fileName) {
        Properties properties = null;
        InputStream is = null;
        try {
            is = ClassUtil.getClassLoader().getResourceAsStream(fileName);
            if (is == null) {
                throw new FileNotFoundException(fileName + "is not exist");
            }
            properties = new Properties();
            properties.load(is);
        } catch (IOException e) {
            LOGGER.error("load properties file failure", e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    LOGGER.error("close input stream failure", e);
                }
            }
        }
        return properties;
    }

    public static String getString(Properties properties, String key) {
        return getString(properties, key, "");
    }

    public static String getString(Properties properties, String key, String defaultValue) {
        return isPropertiesContainsKey(properties, key) ? String.valueOf(getPropertiesValue(properties, key)) : defaultValue;
    }

    public static int getInt(Properties properties, String key) {
        return getInt(properties, key, 0);
    }

    private static int getInt(Properties properties, String key, int defaultValue) {
        return isPropertiesContainsKey(properties, key) ? Integer.parseInt(getPropertiesValue(properties, key)) : defaultValue;
    }

    public static boolean getBoolean(Properties properties, String key) {
        return getBoolean(properties, key, false);
    }

    private static boolean getBoolean(Properties properties, String key, boolean defaultValue) {
        return isPropertiesContainsKey(properties, key) ? Boolean.parseBoolean(getPropertiesValue(properties, key)) : defaultValue;
    }

    private static boolean isPropertiesContainsKey(Properties properties, String key) {
        return properties.containsKey(key);
    }

    private static String getPropertiesValue(Properties properties, String key) {
        return properties.getProperty(key);
    }
}
