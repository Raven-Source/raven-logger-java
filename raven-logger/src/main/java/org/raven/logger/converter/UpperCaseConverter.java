package org.raven.logger.converter;

public class UpperCaseConverter<E> extends SubPatternConverter<E> {

    @Override
    protected String transform(E event, String in) {
        return in.toUpperCase();
    }

}
