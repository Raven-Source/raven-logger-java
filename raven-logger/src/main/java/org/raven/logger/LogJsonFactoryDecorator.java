package org.raven.logger;

import com.fasterxml.jackson.core.JsonFactory;
import net.logstash.logback.decorate.JsonFactoryDecorator;

/**
 * @author yi.liang
 * date 2019.12.02 14:18
 */
public class LogJsonFactoryDecorator implements JsonFactoryDecorator {

    @Override
    public JsonFactory decorate(JsonFactory factory) {

        factory.setCodec(JsonUtil.getMapper());
        return factory;
    }

}