package org.raven.logger.delivery;

public interface FailedDeliveryCallback<E> {

    void onFailedDelivery(E evt, Throwable throwable);
}
