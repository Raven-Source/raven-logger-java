package org.raven.logger.property;

import ch.qos.logback.core.PropertyDefinerBase;
import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;
import org.raven.logger.PropertiesUtil;

/**
 * @author yi.liang
 * date 2019.12.02 13:42
 */
public class AppIdDefiner extends PropertyDefinerBase {

    private static final String PROPERTIES_FILE = "/META-INF/app.properties";
    private static String appId = StringUtils.EMPTY;

    public AppIdDefiner() {
        appId = PropertiesUtil.getString(PROPERTIES_FILE, "app.id");
        if (StringUtils.isBlank(appId)) {
            throw new RuntimeException("must Define app.id in " + PROPERTIES_FILE);
        } else {
            appId = appId.toLowerCase();
        }
    }

    @Override
    public String getPropertyValue() {
        return appId;
    }
}
