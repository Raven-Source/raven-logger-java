package org.raven.logger.converter;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import org.raven.logger.FieldsConstant;

public class ExceptionNameConverter extends ClassicConverter {
    @Override
    public String convert(ILoggingEvent event) {

        IThrowableProxy tp = event.getThrowableProxy();
        if (tp == null) {
            return FieldsConstant.NO_VALUE_PLACEHOLDER;
        } else {
            return tp.getClassName();
        }

    }
}
