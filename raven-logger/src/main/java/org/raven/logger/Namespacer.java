package org.raven.logger;

/**
 * @author yi.liang
 * @since JDK1.8
 * date 2020.03.03 15:27
 */
public interface Namespacer {

    String getNamespace();

    static Namespacer of(String namespace) {
        return new DefaultNamespacer(namespace);
    }

    public static class DefaultNamespacer implements Namespacer {

        private String namespace;

        public DefaultNamespacer(String namespace) {
            this.namespace = namespace;
        }

        @Override
        public String getNamespace() {
            return namespace;
        }
    }

}
