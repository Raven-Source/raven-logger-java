package org.raven.logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;
import org.raven.serializer.withJackson.ObjectMapperConfig;


public final class JsonUtil {
    private JsonUtil() {
    }

    static ObjectMapper mapper;

    static {
        mapper = ObjectMapperConfig.getObjectMapper();
    }

    public static void setMapper(ObjectMapper mapper) {
        JsonUtil.mapper = mapper;
    }

    public static ObjectMapper getMapper() {
        return mapper;
    }

    public static String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            return StringUtils.EMPTY;
        }
    }

}