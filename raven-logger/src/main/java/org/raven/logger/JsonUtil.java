package org.raven.logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.raven.commons.util.StringUtils;
import org.raven.logger.spi.ObjectMapperSupplier;
import org.raven.serializer.withJackson.ObjectMapperConfig;

import java.util.ServiceLoader;

@Slf4j
public final class JsonUtil {

    static ObjectMapperSupplier objectMapperSupplier;

    private JsonUtil() {
    }

    static ObjectMapper mapper;

    static {
        mapper = ObjectMapperConfig.getObjectMapper();

        ServiceLoader.load(ObjectMapperSupplier.class).forEach(supplier -> {
            objectMapperSupplier = supplier;
        });
    }

    public static void setMapper(ObjectMapper mapper) {
        JsonUtil.mapper = mapper;
    }

    public static ObjectMapper getMapper() {
        if (objectMapperSupplier != null) {
            return objectMapperSupplier.get();
        } else {
            return mapper;
        }
    }

    public static String toJson(Object obj) {
        try {
            return getMapper().writeValueAsString(obj);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return StringUtils.EMPTY;
        }
    }

}