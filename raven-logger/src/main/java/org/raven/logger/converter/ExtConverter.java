package org.raven.logger.converter;

import org.raven.logger.Extender;
import org.raven.logger.JsonUtil;

/**
 * @author yi.liang
 * date 2019.12.10 17:05
 */
public class ExtConverter extends TargetConverter<Extender<?>> {

    @Override
    protected String targetConvert(Extender<?> tag) {
        return JsonUtil.toJson(tag.getExt());
    }
}
