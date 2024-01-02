package org.raven.logger.delivery;

import lombok.Getter;
import lombok.Setter;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.errors.TimeoutException;

import java.io.IOException;
import java.util.concurrent.*;

public class AsyncDeliveryStrategy implements DeliveryStrategy {

    @Getter
    private volatile boolean started = false;

    @Getter
    @Setter
    private int worker = 4;

    @Getter
    @Setter
    private int queueMaxSize = 100000;

    private ExecutorService executor;

    @Override
    public <K, V, E> boolean send(Producer<K, V> producer, ProducerRecord<K, V> record, E event, FailedDeliveryCallback<E> failedDeliveryCallback) {

        if (started) {
            executor.submit(() -> {
                try {
                    producer.send(record, (metadata, exception) -> {
                        if (exception != null) {
                            failedDeliveryCallback.onFailedDelivery(event, exception);
                        }
                    });

                } catch (TimeoutException e) {
                    failedDeliveryCallback.onFailedDelivery(event, e);
                }
            });
            return true;
        } else return false;

    }

    @Override
    public void start() {
        started = true;
        executor = new ThreadPoolExecutor(worker, worker,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(queueMaxSize),
                new ThreadPoolExecutor.DiscardOldestPolicy());
    }

    @Override
    public void stop() {
        started = false;
        executor.shutdown();
    }

}
