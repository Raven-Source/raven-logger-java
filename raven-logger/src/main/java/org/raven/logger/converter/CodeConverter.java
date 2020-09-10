package org.raven.logger.converter;


import org.raven.logger.Coder;

/**
 * @author yi.liang
 * date 2019.12.10 17:05
 */
public class CodeConverter extends AbstractConverter<Coder> {

    @Override
    protected String targetConvert(Coder tag) {
        return tag.getCode();
    }

}
