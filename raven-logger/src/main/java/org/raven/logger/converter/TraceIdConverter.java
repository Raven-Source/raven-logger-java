package org.raven.logger.converter;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;

/**
 * @author yi.liang
 * @since JDK1.8
 * date 2019.12.02 18:29
 */
public class TraceIdConverter extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent iLoggingEvent) {
        return TraceContext.traceId();
    }
}
