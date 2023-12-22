package org.raven.logger.converter;

public class LowerCaseConverter<E> extends SubPatternConverter<E> {

    @Override
    protected String transform(E event, String in) {
        return in.toLowerCase();
    }
}
