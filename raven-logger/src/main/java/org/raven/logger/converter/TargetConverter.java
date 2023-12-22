package org.raven.logger.converter;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.ThrowableProxy;
import org.raven.commons.util.StringUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


/**
 * @author yi.liang
 * date 2020.1.6 00:00
 */
@SuppressWarnings("unchecked")
public abstract class TargetConverter<T> extends ClassicConverter {
    protected final Class<T> target;

    public TargetConverter() {

        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if (params[0] instanceof ParameterizedType) {
            target = (Class<T>) ((ParameterizedType) params[0]).getRawType();
        } else {
            target = (Class<T>) params[0];
        }
    }

    @Override
    public String convert(ILoggingEvent event) {

        IThrowableProxy tp = event.getThrowableProxy();
        if (tp != null && tp instanceof ThrowableProxy) {

            Throwable throwable = ((ThrowableProxy) tp).getThrowable();
            if (target.isAssignableFrom(throwable.getClass())) {
                return targetConvert((T) throwable);
            }

        }

        if (event.getArgumentArray() != null) {

            for (Object arg : event.getArgumentArray()) {
                if (arg != null && target.isAssignableFrom(arg.getClass())) {
                    return targetConvert((T) arg);
                }
            }

        }

        return StringUtils.EMPTY;

    }

    protected abstract String targetConvert(T tag);

}
