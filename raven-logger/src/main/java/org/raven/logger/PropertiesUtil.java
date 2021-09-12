package org.raven.logger;

import org.raven.commons.util.StringUtils;
import org.raven.logger.property.AppIdDefiner;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author yi.liang
 * @since JDK1.8
 * date 2020.03.02 16:04
 */
public class PropertiesUtil {


    private PropertiesUtil() {

    }

    private static final Map<String, Properties> propertiesMap;

    static {
        propertiesMap = new HashMap<>();
    }

    public static String getString(String propertiesPath, String key) {
        return getString(propertiesPath, key ,StringUtils.EMPTY);
    }

    public static String getString(String propertiesPath, String key, String defaultValue) {


        Properties properties = propertiesMap.get(propertiesPath);
        if (properties == null) {
            properties = get(propertiesPath);
        }

        if (properties != null) {
            return properties.getProperty(key, defaultValue);
        } else {
            return defaultValue;
        }

    }

    private static synchronized Properties get(String propertiesPath) {
        Properties properties = propertiesMap.get(propertiesPath);
        if (properties != null) {
            propertiesMap.put(propertiesPath, properties);
            return properties;
        }

        properties = load(propertiesPath);
        if (properties != null) {
            propertiesMap.put(propertiesPath, properties);
            return properties;
        }

        return null;
    }

    private static Properties load(String propertiesPath) {

        InputStream in = null;
        String value = null;
        try {
            in = Thread.currentThread().getContextClassLoader().getResourceAsStream(propertiesPath);

            if (in == null) {
                in = AppIdDefiner.class.getResourceAsStream(propertiesPath);
            }
            if (in != null) {
                Properties prop = new Properties();
                prop.load(in);
                return prop;
            }
            return null;
        } catch (Exception ignored) {
            return null;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception ignored) {
                }
            }
        }
    }

}
