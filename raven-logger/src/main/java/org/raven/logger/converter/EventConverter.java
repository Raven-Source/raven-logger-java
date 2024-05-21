package org.raven.logger.converter;

import org.raven.commons.util.StringUtils;
import org.raven.logger.model.Event;

import java.util.List;
import java.util.stream.Collectors;

public class EventConverter extends TargetConverter<Event> {

    @Override
    protected String targetConvert(Event event) {

        List<String> optionList = getOptionList();
        if (optionList != null && !optionList.isEmpty()) {
            String property = optionList.get(0);

            List<String> options = optionList.stream().skip(1)
                    .collect(Collectors.toList());

            return event.getProperty(property, options);
        }

        return StringUtils.EMPTY;
    }
}
