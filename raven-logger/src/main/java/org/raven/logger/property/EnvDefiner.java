package org.raven.logger.property;

import ch.qos.logback.core.PropertyDefinerBase;
import org.raven.logger.FieldsConstant;

/**
 * @author yi.liang
 */
public class EnvDefiner extends PropertyDefinerBase {

    private static final String ENV = "env";

    @Override
    public String getPropertyValue() {
        try {
            return System.getProperty(ENV).toLowerCase();
        } catch (Exception ex) {
        }
        return FieldsConstant.NO_VALUE_PLACEHOLDER;
    }
}
