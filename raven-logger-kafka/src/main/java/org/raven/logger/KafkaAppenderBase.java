package org.raven.logger;


import ch.qos.logback.core.Context;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import ch.qos.logback.core.encoder.Encoder;
import ch.qos.logback.core.spi.AppenderAttachable;
import lombok.Getter;
import lombok.Setter;
import org.raven.logger.delivery.AsyncDeliveryStrategy;
import org.raven.logger.delivery.DeliveryStrategy;
import org.raven.logger.keying.KeyingStrategy;
import org.raven.logger.keying.NoKeyKeyingStrategy;

import java.util.HashMap;
import java.util.Map;

import static org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG;

public abstract class KafkaAppenderBase<E> extends UnsynchronizedAppenderBase<E> implements AppenderAttachable<E> {

    private static final String KAFKA_PROPERTY_PREFIX = "logging.kafka.";

    @Setter
    protected String topic = null;

    @Setter
    protected Encoder<E> encoder = null;

    @Setter
    protected KeyingStrategy<? super E> keyingStrategy = null;

    @Setter
    protected DeliveryStrategy deliveryStrategy;

    @Setter
    protected Integer partition = null;

    @Setter
    @Getter
    protected boolean appendTimestamp = true;

    @Getter
    protected Map<String, Object> producerConfig = new HashMap<>();

    @Getter
    @Setter
    protected TopicProvider topicProvider;

    protected boolean checkPrerequisites() {
        boolean errorFree = true;

        if (producerConfig.get(BOOTSTRAP_SERVERS_CONFIG) == null) {
            addError("No \"" + BOOTSTRAP_SERVERS_CONFIG + "\" set for the appender named [\""
                    + name + "\"].");
            errorFree = false;
        }

        if (topicProvider == null && topic == null) {
            addError("No topic set for the appender named [\"" + name + "\"].");
            errorFree = false;
        }

        if (encoder == null) {
            addError("No encoder set for the appender named [\"" + name + "\"].");
            errorFree = false;
        }

        if (keyingStrategy == null) {
            addInfo("No explicit keyingStrategy set for the appender named [\"" + name + "\"]. Using default NoKeyKeyingStrategy.");
            keyingStrategy = new NoKeyKeyingStrategy();
        }

        if (deliveryStrategy == null) {
            addInfo("No explicit deliveryStrategy set for the appender named [\"" + name + "\"]. Using default asynchronous strategy.");
            deliveryStrategy = new AsyncDeliveryStrategy();
        }

        return errorFree;
    }

    @Override
    public void setContext(Context context) {
        super.setContext(context);

        for (Map.Entry<String, String> entry : context.getCopyOfPropertyMap().entrySet()) {
            if (entry.getKey().startsWith(KAFKA_PROPERTY_PREFIX)) {
                addProducerConfigValue(entry.getKey().replace(KAFKA_PROPERTY_PREFIX, ""), entry.getValue());
            }
        }

    }

    public void addProducerConfig(String keyValue) {
        String[] split = keyValue.split("=", 2);
        if (split.length == 2)
            addProducerConfigValue(split[0], split[1]);
    }

    public void addProducerConfigValue(String key, Object value) {
        this.producerConfig.put(key, value);
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void stop() {
        super.stop();
    }

    @Override
    protected void append(E eventObject) {

    }
}
