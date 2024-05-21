package org.raven.logger.test;

import org.raven.logger.spi.TraceIdSupplier;

public class SWTraceId implements TraceIdSupplier {
    @Override
    public String traceId() {
        return "abc";
    }
}
