package org.raven.logger.tag;

/**
 * @author yi.liang
 * @since JDK1.8
 * date 2020.03.02 13:54
 */
public class StringValueTag extends AbstractValueTag<String> {

    public StringValueTag(String tagKey, String tagVal) {
        super(tagKey, tagVal);
    }

    public static StringValueTag of(String tagKey, String tagVal) {
        return new StringValueTag(tagKey, tagVal);
    }

}
