package org.raven.logger;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.LoggerContextVO;
import org.junit.Assert;
import org.junit.Test;
import org.raven.commons.util.StringUtils;
import org.raven.logger.converter.AbstractConverter;
import org.slf4j.Marker;

import java.util.Map;

public class AbstractConverterTest {

    public static class AbstractConverterImpl extends AbstractConverter<Long> {

        @Override
        protected String targetConvert(Long tag) {
            return tag.toString();
        }
    }

    @Test
    public void convertTest() {

        ILoggingEvent event = new ILoggingEvent() {
            @Override
            public String getThreadName() {
                return null;
            }

            @Override
            public Level getLevel() {
                return null;
            }

            @Override
            public String getMessage() {
                return null;
            }

            @Override
            public Object[] getArgumentArray() {
                return new Object[0];
            }

            @Override
            public String getFormattedMessage() {
                return null;
            }

            @Override
            public String getLoggerName() {
                return null;
            }

            @Override
            public LoggerContextVO getLoggerContextVO() {
                return null;
            }

            @Override
            public IThrowableProxy getThrowableProxy() {
                return null;
            }

            @Override
            public StackTraceElement[] getCallerData() {
                return new StackTraceElement[0];
            }

            @Override
            public boolean hasCallerData() {
                return false;
            }

            @Override
            public Marker getMarker() {
                return null;
            }

            @Override
            public Map<String, String> getMDCPropertyMap() {
                return null;
            }

            @Override
            public Map<String, String> getMdc() {
                return null;
            }

            @Override
            public long getTimeStamp() {
                return 0;
            }

            @Override
            public void prepareForDeferredProcessing() {

            }
        };

        AbstractConverterImpl abstractConverter = new AbstractConverterImpl();
        Assert.assertEquals(abstractConverter.convert(event), StringUtils.EMPTY);


    }

}
