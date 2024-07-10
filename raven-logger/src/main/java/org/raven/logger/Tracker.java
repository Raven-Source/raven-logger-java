package org.raven.logger;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.raven.commons.util.StringUtils;
import org.raven.logger.model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.Date;

@Slf4j
public class Tracker {

    private static final Logger eventLog = LoggerFactory.getLogger("EVENT-LOGGER");

    public static void event(@NonNull Event event) {

        try {

            if (event.getTime() == null) {
                event.setTime(new Date());
            }

            if (StringUtils.isBlank(event.getUid())) {
                event.setUid(MDC.get(FieldsConstant.USER_ID));
            }

            eventLog.info(event.getEventId(), event);

        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }

}
