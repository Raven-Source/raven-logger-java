package org.raven.logger;

/**
 * @author yi.liang
 * date 2019.12.10 17:01
 */
public interface Coder {
    String getCode();

    static Coder of(String code) {
        return new DefaultCoder(code);
    }

    public static class DefaultCoder implements Coder {

        private String code;

        public DefaultCoder(String code) {
            this.code = code;
        }

        @Override
        public String getCode() {
            return code;
        }
    }
}
