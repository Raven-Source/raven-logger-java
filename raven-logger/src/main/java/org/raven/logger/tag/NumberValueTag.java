package org.raven.logger.tag;

/**
 * @author yi.liang
 * @since JDK1.8
 * date 2020.03.02 13:54
 */
public class NumberValueTag extends AbstractValueTag<Number> {

    public NumberValueTag(String tagKey, Number tagVal) {
        super(tagKey, tagVal);
    }

    public static NumberValueTag of(String tagKey, Number tagVal) {
        return new NumberValueTag(tagKey, tagVal);
    }

}
