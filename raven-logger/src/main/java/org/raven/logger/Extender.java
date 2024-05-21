package org.raven.logger;

import java.util.HashMap;
import java.util.Map;

public interface Extender<T> {

    T getExt();

    static DefaultExtender of(Object ext) {
        return new DefaultExtender(ext);
    }

    static MapExtender map(){
        return new MapExtender();
    }

    public static class DefaultExtender implements Extender<Object> {

        private final Object ext;

        public DefaultExtender(Object ext) {
            this.ext = ext;
        }

        @Override
        public Object getExt() {
            return ext;
        }
    }

    public static class MapExtender implements Extender<Map<String, Object>> {

        private final Map<String, Object> ext;

        public MapExtender() {
            ext = new HashMap<>();
        }

        @Override
        public Map<String, Object> getExt() {
            return ext;
        }

        public MapExtender addExtValue(String key, Number value) {
            ext.put(key, value);
            return this;
        }

        public MapExtender addExtValue(String key, String value) {
            ext.put(key, value);
            return this;
        }
    }
}
