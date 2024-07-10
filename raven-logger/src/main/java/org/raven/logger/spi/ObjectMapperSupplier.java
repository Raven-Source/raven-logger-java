package org.raven.logger.spi;

import com.fasterxml.jackson.databind.ObjectMapper;

public interface ObjectMapperSupplier {

    ObjectMapper get();

}
