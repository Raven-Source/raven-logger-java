package org.raven.logger;

import io.opentracing.tag.AbstractTag;
import org.raven.logger.tag.AbstractValueTag;
import org.raven.logger.tag.MultipleTagger;

import java.util.Arrays;

/**
 * @author yi.liang
 * date 2020.1.6 00:00
 */
public interface Tagger {

    String[] getTag();

    static Tagger of(String... tags) {
        return new MultipleTagger(tags);
    }

    static Tagger of(AbstractTag... tags) {
        return new MultipleTagger(Arrays.stream(tags)
                .map(x -> x.getKey())
                .toArray(String[]::new)
        );
    }


    static Tagger of(AbstractValueTag... tags) {
        return new MultipleTagger(Arrays.stream(tags)
                .map(x -> String.join("=",
                        x.getKey(),
                        x.getVal() != null ? x.getVal().toString() : ""))
                .toArray(String[]::new)
        );
    }

}
