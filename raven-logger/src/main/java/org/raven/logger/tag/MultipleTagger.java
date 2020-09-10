package org.raven.logger.tag;


import org.raven.logger.Tagger;

/**
 * @author yi.liang
 * date 2020.1.6 00:00
 */
public class MultipleTagger implements Tagger {

    private String[] tag;

    public MultipleTagger(String[] tag) {
        this.tag = tag;
    }

    @Override
    public String[] getTag() {
        return tag;
    }
}
