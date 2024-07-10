package org.raven.logger;

import ch.qos.logback.classic.pattern.*;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.pattern.PatternLayoutBase;
import ch.qos.logback.core.pattern.parser.Parser;

import java.util.HashMap;
import java.util.Map;

public class TopicProvider extends PatternLayoutBase<ILoggingEvent> {


    public static final Map<String, String> DEFAULT_CONVERTER_MAP = new HashMap<String, String>();
    public static final Map<String, String> CONVERTER_CLASS_TO_KEY_MAP = new HashMap<String, String>();

    /**
     * @deprecated replaced by DEFAULT_CONVERTER_MAP
     */
    public static final Map<String, String> defaultConverterMap = DEFAULT_CONVERTER_MAP;

    public static final String HEADER_PREFIX = "#logback.classic pattern: ";

    static {
        DEFAULT_CONVERTER_MAP.putAll(Parser.DEFAULT_COMPOSITE_CONVERTER_MAP);

        DEFAULT_CONVERTER_MAP.put("d", DateConverter.class.getName());
        DEFAULT_CONVERTER_MAP.put("date", DateConverter.class.getName());
        CONVERTER_CLASS_TO_KEY_MAP.put(DateConverter.class.getName(), "date");

        DEFAULT_CONVERTER_MAP.put("level", LevelConverter.class.getName());
        DEFAULT_CONVERTER_MAP.put("le", LevelConverter.class.getName());
        DEFAULT_CONVERTER_MAP.put("p", LevelConverter.class.getName());
        CONVERTER_CLASS_TO_KEY_MAP.put(LevelConverter.class.getName(), "level");

        DEFAULT_CONVERTER_MAP.put("C", ClassOfCallerConverter.class.getName());
        DEFAULT_CONVERTER_MAP.put("class", ClassOfCallerConverter.class.getName());
        CONVERTER_CLASS_TO_KEY_MAP.put(ClassOfCallerConverter.class.getName(), "class");

        DEFAULT_CONVERTER_MAP.put("M", MethodOfCallerConverter.class.getName());
        DEFAULT_CONVERTER_MAP.put("method", MethodOfCallerConverter.class.getName());
        CONVERTER_CLASS_TO_KEY_MAP.put(MethodOfCallerConverter.class.getName(), "method");

        DEFAULT_CONVERTER_MAP.put("L", LineOfCallerConverter.class.getName());
        DEFAULT_CONVERTER_MAP.put("line", LineOfCallerConverter.class.getName());
        CONVERTER_CLASS_TO_KEY_MAP.put(LineOfCallerConverter.class.getName(), "line");

        DEFAULT_CONVERTER_MAP.put("F", FileOfCallerConverter.class.getName());
        DEFAULT_CONVERTER_MAP.put("file", FileOfCallerConverter.class.getName());
        CONVERTER_CLASS_TO_KEY_MAP.put(FileOfCallerConverter.class.getName(), "file");

        DEFAULT_CONVERTER_MAP.put("X", MDCConverter.class.getName());
        DEFAULT_CONVERTER_MAP.put("mdc", MDCConverter.class.getName());

        DEFAULT_CONVERTER_MAP.put("prefix", PrefixCompositeConverter.class.getName());

    }

    @Override
    public void start() {
        super.start();
    }

    public TopicProvider() {
        this.postCompileProcessor = null;
    }

    public Map<String, String> getDefaultConverterMap() {
        return DEFAULT_CONVERTER_MAP;
    }

    public String doLayout(ILoggingEvent event) {
        if (!isStarted()) {
            return CoreConstants.EMPTY_STRING;
        }
        return writeLoopOnConverters(event);
    }

    @Override
    protected String getPresentationHeaderPrefix() {
        return HEADER_PREFIX;
    }


}
