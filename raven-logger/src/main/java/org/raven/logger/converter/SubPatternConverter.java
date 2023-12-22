package org.raven.logger.converter;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.pattern.CompositeConverter;
import ch.qos.logback.core.pattern.Converter;
import ch.qos.logback.core.pattern.parser.Parser;

public abstract class SubPatternConverter<E> extends CompositeConverter<E> {

    @Override
    public void start() {
        try {
            String subPattern = getFirstOption();
            if (subPattern != null) {
                Context context = getContext();
                Parser<E> parser = new Parser<>(subPattern);
                parser.setContext(context);
                Converter<E> childConverter = parser.compile(parser.parse(), PatternLayout.DEFAULT_CONVERTER_MAP);
                setChildConverter(childConverter);
            }
        } catch (Exception e) {
            addError("Error in SubPatternConverter: " + e.getMessage(), e);
        }

        super.start();
    }
}
