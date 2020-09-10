package org.raven.logger.tag;

/**
 * @author yi.liang
 * @since JDK1.8
 * date 2020.03.02 13:53
 */
public abstract class AbstractValueTag<T> {

    protected final T val;
    protected final String key;

    public AbstractValueTag(String tagKey, T tagVal) {
        this.key = tagKey;
        this.val = tagVal;
    }

    public String getKey() {
        return this.key;
    }

    public T getVal() {
        return this.val;
    }
}
