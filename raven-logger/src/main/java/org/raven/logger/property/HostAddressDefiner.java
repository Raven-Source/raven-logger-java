package org.raven.logger.property;

import ch.qos.logback.core.PropertyDefinerBase;
import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author yi.liang
 * date 2019.12.02 14:17
 */
public class HostAddressDefiner extends PropertyDefinerBase {

    @Override
    public String getPropertyValue() {
        try {
            return InetAddress.getLocalHost().getHostAddress();

        } catch (UnknownHostException e) {
            return StringUtils.EMPTY;
        }
    }
}
