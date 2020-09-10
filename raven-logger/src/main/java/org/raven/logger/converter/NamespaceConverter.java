package org.raven.logger.converter;

import ch.qos.logback.classic.spi.ILoggingEvent;
import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;
import org.raven.logger.FieldsConstant;
import org.raven.logger.Namespacer;
import org.raven.logger.PropertiesUtil;
import org.slf4j.MDC;

/**
 * @author yi.liang
 * @since JDK1.8
 * date 2020.03.03 14:54
 */
public class NamespaceConverter extends AbstractConverter<Namespacer> {

    private static final String PROPERTIES_FILE = "/META-INF/app.properties";
    private static String ns = StringUtils.EMPTY;

    public NamespaceConverter() {
        ns = PropertiesUtil.getString(PROPERTIES_FILE, "app.ns");
        if (StringUtils.isBlank(ns)) {
            ns = FieldsConstant.NO_VALUE_PLACEHOLDER;
        } else {
            ns = ns.toLowerCase();
        }
    }

    @Override
    public String convert(ILoggingEvent iLoggingEvent) {
        String curNs = super.convert(iLoggingEvent);

        if (StringUtils.isBlank(curNs)) {
            curNs = MDC.get(FieldsConstant.NAMESPACE);
        }

        if (StringUtils.isBlank(curNs)) {
            curNs = ns;
        }

        return curNs.toLowerCase();
    }

    @Override
    protected String targetConvert(Namespacer tag) {
        return tag.getNamespace();
    }
}
