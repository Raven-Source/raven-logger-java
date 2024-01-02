package org.raven.logger.converter;

import org.raven.commons.util.StringUtils;

public class UpperCaseConverter<E> extends SubPatternConverter<E> {

    @Override
    protected String transform(E event, String in) {

        if (in == null || in.isEmpty()) {
            return StringUtils.EMPTY;
        }

        return in.toUpperCase();
    }

}
