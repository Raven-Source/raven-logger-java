package org.raven.logger.keying;

import lombok.Getter;
import lombok.Setter;

import java.nio.charset.StandardCharsets;

public class SpecifyKeyingStrategy implements KeyingStrategy<Object> {

    @Getter
    @Setter
    private String key;

    @Override
    public byte[] createKey(Object o) {
        return key.getBytes(StandardCharsets.UTF_8);
    }

}
