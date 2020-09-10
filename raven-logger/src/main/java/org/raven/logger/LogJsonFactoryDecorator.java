package org.raven.logger;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import net.logstash.logback.decorate.JsonFactoryDecorator;

/**
 * @author yi.liang
 * date 2019.12.02 14:18
 */
public class LogJsonFactoryDecorator implements JsonFactoryDecorator {

    @Override
    public MappingJsonFactory decorate(MappingJsonFactory factory) {
        // 禁用对非ascii码进行escape编码的特性
        factory.setCodec(JsonUtil.getMapper());
        factory.disable(JsonGenerator.Feature.ESCAPE_NON_ASCII);
        return factory;
    }

}