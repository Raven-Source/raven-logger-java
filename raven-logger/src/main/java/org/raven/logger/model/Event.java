package org.raven.logger.model;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import org.raven.commons.constant.DateFormatString;
import org.raven.commons.util.DateTimeUtils;
import org.raven.commons.util.StringUtils;
import org.raven.logger.Extender;
import org.raven.logger.JsonUtil;
import org.raven.logger.Tagger;

import java.util.*;

@Getter
@EqualsAndHashCode
@ToString
@Accessors(chain = true)
@FieldNameConstants
public class Event {

    @Setter
    private String uid;

    @Setter
    private Date time;

    @Setter
    private String eventId;

    @Setter
    private Double value;

    @Setter
    private String level;

    private Map<String, Object> ext;

    private List<String> tag;

    public Event(@NonNull String eventId) {
        this.eventId = eventId;
        this.ext = new HashMap<>();
        this.tag = new ArrayList<>();
    }

    public Event addTag(@NonNull String value) {
        tag.add(value);
        return this;
    }

    public Event addTag(@NonNull Tagger tagger) {
        Collections.addAll(tag, tagger.getTag());
        return this;
    }

    public Event addExt(@NonNull Extender.MapExtender extender) {
        ext.putAll(extender.getExt());
        return this;
    }

    public Event addExtValue(@NonNull String key, Number value) {
        ext.put(key, value);
        return this;
    }

    public Event addExtValue(@NonNull String key, String value) {
        ext.put(key, value);
        return this;
    }

    public Event addExtValue(@NonNull String key, Number[] value) {
        ext.put(key, value);
        return this;
    }

    public Event addExtValue(@NonNull String key, String[] value) {
        ext.put(key, value);
        return this;
    }

    public String getProperty(@NonNull String property, List<String> options) {
        switch (property) {

            case Fields.uid:
                return uid;

            case Fields.eventId:
                return eventId;

            case Fields.level:
                return level;

            case Fields.value:
                return value == null ? StringUtils.EMPTY : value.toString();

            case Fields.time:

                String pattern = options == null || options.isEmpty()
                        ? DateFormatString.ISO_OFFSET_DATE_TIME
                        : options.get(0);

                return time == null ?
                        StringUtils.EMPTY :
                        DateTimeUtils.format(time, pattern);

            case Fields.tag:
                return JsonUtil.toJson(tag);

            case Fields.ext:
                return JsonUtil.toJson(ext);

            default:
                return StringUtils.EMPTY;
        }
    }

    public static Event of(@NonNull String eventId) {
        return new Event(eventId);
    }
}
