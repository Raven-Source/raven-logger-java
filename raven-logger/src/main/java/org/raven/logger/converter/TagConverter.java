package org.raven.logger.converter;

import org.raven.logger.JsonUtil;
import org.raven.logger.Tagger;

/**
 * @author yi.liang
 * date 2019.12.10 17:05
 */
public class TagConverter extends AbstractConverter<Tagger> {

    @Override
    protected String targetConvert(Tagger tag) {
        return JsonUtil.toJson(tag.getTag());
    }
}
