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
    private int jobs = 2;

    @Getter
    @Setter
    private int queueMaxSize = 5000;

    private ExecutorService executor;

    public AsyncDeliveryStrategy() {

//        executor = Executors.newFixedThreadPool(tasks);

    }

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
        executor = new ThreadPoolExecutor(jobs, jobs,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(queueMaxSize));
    }

    @Override
    public void stop() {
        started = false;
        executor.shutdown();
    }

}
