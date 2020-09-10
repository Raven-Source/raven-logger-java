package org.raven.logger.converter;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;
import org.raven.logger.FieldsConstant;
import org.slf4j.MDC;

/**
 * @author yi.liang
 * @since JDK1.8
 * date 2020.07.30 18:29
 */
public class UserIdConverter extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent iLoggingEvent) {
        String uid = MDC.get(FieldsConstant.USER_ID);
        if (StringUtils.isNotBlank(uid)) {
            return uid;
        } else {
            return StringUtils.EMPTY;
        }
    }
}
