package org.raven.logger.converter;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.raven.commons.util.StringUtils;
import org.raven.logger.spi.TraceIdSupplier;

import java.util.ServiceLoader;

/**
 * @author yi.liang
 * @since JDK1.8
 * date 2019.12.02 18:29
 */
public class TraceIdConverter extends ClassicConverter {

    private TraceIdSupplier traceIdSupplier;

    public TraceIdConverter() {
        ServiceLoader.load(TraceIdSupplier.class).forEach(supplier -> {
            traceIdSupplier = supplier;
        });
    }

    @Override
    public String convert(ILoggingEvent iLoggingEvent) {
        if (traceIdSupplier == null) {
            return StringUtils.EMPTY;
        } else {
            return traceIdSupplier.traceId();
        }
    }
}
