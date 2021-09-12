package org.raven.logger.property;

import ch.qos.logback.core.PropertyDefinerBase;
import org.raven.commons.util.StringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author yi.liang
 */
public class HostNameDefiner extends PropertyDefinerBase {

    @Override
    public String getPropertyValue() {
        try {
            return InetAddress.getLocalHost().getHostName();

        } catch (UnknownHostException e) {
            return StringUtils.EMPTY;
        }
    }
}
